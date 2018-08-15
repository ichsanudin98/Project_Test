package id.co.ichsanudinstore.student.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import id.co.ichsanudinstore.student.R;
import id.co.ichsanudinstore.student.adapter.MainAdapter;
import id.co.ichsanudinstore.student.api.endpoint.course.cud.CourseResponse;
import id.co.ichsanudinstore.student.api.endpoint.course.cud.CourseService;
import id.co.ichsanudinstore.student.config.Constant;
import id.co.ichsanudinstore.student.entity.CourseEntity;
import id.co.ichsanudinstore.student.entity.StudentEntity;
import id.co.ichsanudinstore.student.interfaces.OnMainItemClick;
import id.co.ichsanudinstore.student.utils.SharedPrefs;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnMainItemClick {
    private static final transient String TAG = MainActivity.class.getSimpleName();

    private Context context;

    private Realm mRealm;

    private Toolbar toolbar;

    private FloatingActionButton mButtonAdd;

    private RecyclerView recyclerView;
    private MainAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private String optAddCourse, optAddStudent;
    private String errorAddCourse, errorMultipleRequest, errorConnection;

    private AlertDialog mOptDialog, mCourseAdd;
    private AlertDialog.Builder mCourseBuilder;

    private EditText mEdtCourse;
    private Button mButtonPositive;

    private Call<CourseResponse> crudCourse;

    private Boolean isRequest = false;

    public MainActivity() {

    }

    private void init() {
        context = this.getApplicationContext();

        mRealm = Realm.getDefaultInstance();

        this.messages();

        toolbar = this.findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mButtonAdd = this.findViewById(R.id.main_add);
        mButtonAdd.setOnClickListener(this);

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RealmResults<StudentEntity> mStudents = mRealm.where(StudentEntity.class).findAll();
        mAdapter = new MainAdapter(mStudents, this, this);
        recyclerView = this.findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        if (SharedPrefs.getmPreferences(context).getString(Constant.URL.BASE, "").equalsIgnoreCase("")) {
            SharedPrefs.saveBaseURL(Constant.URL.BASE, MainActivity.this);
        }
    }

    private void messages() {
        optAddCourse = getResources().getString(R.string.opt_add_course);
        optAddStudent = getResources().getString(R.string.opt_add_student);

        errorAddCourse = getResources().getString(R.string.error_course_available);
        errorMultipleRequest = this.getResources().getString(R.string.error_requesting_multiple);
        errorConnection = this.getResources().getString(R.string.error_connection);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mButtonAdd)) {
            final CharSequence[] options = {optAddCourse, optAddStudent};
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Pilihan");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    if (options[which].equals(optAddCourse)) {
                        mCourseBuilder = new AlertDialog.Builder(MainActivity.this);
                        mEdtCourse = new EditText(MainActivity.this);
                        mEdtCourse.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                        mEdtCourse.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (s.length() > 0)
                                    mEdtCourse.setError(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        mCourseBuilder.setTitle(optAddCourse);
                        mCourseBuilder.setView(mEdtCourse);
                        mCourseBuilder.setCancelable(false);
                        mCourseBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mOptDialog.cancel();
                            }
                        });
                        mCourseBuilder.setPositiveButton(R.string.save, null);
                        mCourseAdd = mCourseBuilder.create();
                        mCourseAdd.show();

                        mButtonPositive = mCourseAdd.getButton(AlertDialog.BUTTON_POSITIVE);
                        mButtonPositive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!TextUtils.isEmpty(mEdtCourse.getText().toString())) {
                                    CourseEntity mCourse = mRealm.where(CourseEntity.class)
                                            .equalTo("name", mEdtCourse.getText().toString(), Case.INSENSITIVE)
                                            .findFirst();

                                    if (mCourse == null) {
                                        /*if (!isRequest) {
                                            try {
                                                crudCourse = CourseService.sendRequest(MainActivity.this,
                                                        SharedPrefs.getmPreferences(context).getString(Constant.URL.BASE, "") +
                                                                Constant.URL.COURSE,
                                                        0,
                                                        null,
                                                        mEdtCourse.getText().toString());
                                                isRequest = true;
                                                crudCourse.enqueue(new Callback<CourseResponse>() {
                                                    @Override
                                                    public void onResponse(@NonNull Call<CourseResponse> call, @NonNull Response<CourseResponse> response) {
                                                        CourseResponse courseResponse = response.body();
                                                        isRequest = false;

                                                        if (courseResponse != null) {
                                                            if (courseResponse.getResponse_code() == 0) {
                                                                try {
                                                                    mRealm.beginTransaction();
                                                                    CourseEntity data = new CourseEntity();
                                                                    data.setId(System.currentTimeMillis());
                                                                    data.setName(mEdtCourse.getText().toString());
                                                                    mRealm.copyToRealmOrUpdate(data);
                                                                    mRealm.commitTransaction();

                                                                    mCourseAdd.dismiss();
                                                                } catch (Exception e) {
                                                                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(context, courseResponse.getResponse_message(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(context, errorConnection, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(@NonNull Call<CourseResponse> call, @NonNull Throwable t) {
                                                        isRequest = false;
                                                        Toast.makeText(context, errorConnection, Toast.LENGTH_SHORT).show();
                                                        t.printStackTrace();
                                                    }
                                                });
                                            } catch (Exception e) {
                                                isRequest = false;
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Toast.makeText(context, errorMultipleRequest, Toast.LENGTH_SHORT).show();
                                        }*/

                                        try {
                                            mRealm.beginTransaction();
                                            CourseEntity data = new CourseEntity();
                                            data.setId(System.currentTimeMillis());
                                            data.setName(mEdtCourse.getText().toString());
                                            mRealm.copyToRealmOrUpdate(data);
                                            mRealm.commitTransaction();

                                            mCourseAdd.dismiss();
                                        } catch (Exception e) {
                                            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, errorAddCourse, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    mEdtCourse.setError("Wajib diisi");
                                }
                            }
                        });
                    } else if (options[which].equals(optAddStudent)) {
                        Intent create = new Intent(MainActivity.this, CreateStudentActivity.class);
                        create.putExtra("type", 0);
                        startActivity(create);
                    }
                }
            });
            mOptDialog = builder.create();
            mOptDialog.show();
        }
    }

    @Override
    public void onClick(int position, String nik) {
        Intent create = new Intent(MainActivity.this, CreateStudentActivity.class);
        create.putExtra("type", 1);
        create.putExtra("nik", nik);
        startActivity(create);
    }
}
