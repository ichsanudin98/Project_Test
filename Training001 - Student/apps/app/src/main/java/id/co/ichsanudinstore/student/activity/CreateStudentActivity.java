package id.co.ichsanudinstore.student.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.ichsanudinstore.student.R;
import id.co.ichsanudinstore.student.adapter.create_student.CourseAdapter;
import id.co.ichsanudinstore.student.entity.CourseEntity;
import id.co.ichsanudinstore.student.entity.StudentCourseEntity;
import id.co.ichsanudinstore.student.entity.StudentEntity;
import id.co.ichsanudinstore.student.interfaces.OnStudentCourseItem;
import id.co.ichsanudinstore.student.pojo.StudentCoursePojo;
import io.realm.Realm;
import io.realm.RealmResults;

public class CreateStudentActivity extends AppCompatActivity
        implements View.OnClickListener, OnStudentCourseItem {
    private Context context;

    private Realm mRealm;

    private Toolbar mToolbar;

    private EditText mEdtName, mEdtNIK, mEdtMajor;

    private TextView mTxvCourseTitle, mButtonCourseAdd;

    private CourseAdapter mAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private AppCompatButton mButtonSave;

    private int type;

    private String hintNIK, hintName, hintMajor;
    private String titleList, titleListAdd, titleSave, titleDialog;

    private List<String> mCourses;
    private List<StudentCoursePojo> mStudentCourses;
    {
        mCourses = new ArrayList<>();
        mStudentCourses = new ArrayList<>();
    }

    private Boolean isCourseAvail = false;

    private Spinner mSpinner;
    private AlertDialog.Builder mCourseBuilder;
    private AlertDialog mCourseDialog;

    private void messages() {
        hintName = this.getResources().getString(R.string.hint_name);
        hintNIK = this.getResources().getString(R.string.hint_nik);
        hintMajor = this.getResources().getString(R.string.hint_majors);

        titleList = this.getResources().getString(R.string.title_course);
        titleListAdd = this.getResources().getString(R.string.title_course_add);
        titleSave = this.getResources().getString(R.string.save);

        titleDialog = this.getResources().getString(R.string.opt_add_student_course);
    }

    /*
     * Deklarasi seluruh variabel dan pemberian fungsi seperti saat edittext sedang dalam mode
     * pengetikan ataupun aksi pemberian aksi apabila terjadi suatu kontak seperti aksi button click
     * */
    private void init() {
        context = this.getApplicationContext();

        mRealm = Realm.getDefaultInstance();

        this.messages();

        mToolbar = this.findViewById(R.id.create_toolbar);
        setSupportActionBar(mToolbar);

        mEdtName = this.findViewById(R.id.create_name);
        mEdtName.setHint(hintName);
        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    mEdtName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtNIK = this.findViewById(R.id.create_nik);
        mEdtNIK.setHint(hintNIK);
        mEdtNIK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    mEdtNIK.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtMajor = this.findViewById(R.id.create_majors);
        mEdtMajor.setHint(hintMajor);
        mEdtMajor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    mEdtMajor.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTxvCourseTitle = this.findViewById(R.id.create_course_title);
        mTxvCourseTitle.setText(titleList);

        mButtonCourseAdd = this.findViewById(R.id.create_course_add_title);
        mButtonCourseAdd.setOnClickListener(this);
        mButtonCourseAdd.setText(titleListAdd);

        mButtonSave = this.findViewById(R.id.create_course_save);
        mButtonSave.setOnClickListener(this);
        mButtonSave.setText(titleSave);

        type = getIntent().getIntExtra("type", 0);

        if (type != 0) {
            mButtonCourseAdd.setVisibility(View.GONE);
            mButtonSave.setVisibility(View.GONE);
        } else {
            RealmResults<StudentCourseEntity> mStudentsCourse = mRealm.where(StudentCourseEntity.class)
                    .equalTo("student_nik", getIntent().getStringExtra("nik"))
                    .findAll();

            if (mStudentsCourse.size() != 0) {
                StudentCoursePojo mStudentCoursePojo;
                for (StudentCourseEntity mCourse : mStudentsCourse) {
                    mStudentCoursePojo = new StudentCoursePojo();
                    mStudentCoursePojo.setId(mCourse.getId());
                    mStudentCoursePojo.setCourse_id(mCourse.getCourse_id());
                    mStudentCoursePojo.setCourse_name(mRealm.where(CourseEntity.class)
                            .equalTo("id", mCourse.getCourse_id())
                            .findFirst()
                            .getName()
                    );
                    mStudentCoursePojo.setStudent_nik(mCourse.getStudent_nik());
                    mStudentCourses.add(mStudentCoursePojo);
                }
            }
        }

        mAdapter = new CourseAdapter(mStudentCourses, this);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView = this.findViewById(R.id.create_course_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private boolean isValid() {
        if (TextUtils.isEmpty(mEdtName.getText())) {
            mEdtName.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(mEdtNIK.getText())) {
            mEdtNIK.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(mEdtMajor.getText())) {
            mEdtMajor.setError("Wajib diisi");
            return false;
        }

        if (mRealm.where(StudentEntity.class)
                .equalTo("nik", mEdtNIK.getText().toString())
                .findFirst() != null) {
            Toast.makeText(context, "NIK sudah ada", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mCourses.size() == 0) {
            Toast.makeText(context, titleDialog, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (mCourses.size() == 1) {
                if (mCourses.get(0).equals(titleDialog)) {
                    Toast.makeText(context, titleDialog, Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        this.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onClick(View v) {
        if (mButtonSave.equals(v)) {
            if (isValid()) {
                mRealm.beginTransaction();
                StudentEntity mData = new StudentEntity();
                mData.setNik(mEdtNIK.getText().toString());
                mData.setName(mEdtName.getText().toString());
                mData.setMajors(mEdtMajor.getText().toString());
                StudentCourseEntity mStudentCourseEntity;
                for (StudentCoursePojo course : mStudentCourses) {
                    mStudentCourseEntity = new StudentCourseEntity();
                    mStudentCourseEntity.setId(System.currentTimeMillis());
                    mStudentCourseEntity.setCourse_id(course.getCourse_id());
                    mStudentCourseEntity.setStudent_nik(mEdtNIK.getText().toString());
                    mData.getCourses().add(mStudentCourseEntity);
                }
                mRealm.copyToRealmOrUpdate(mData);
                mRealm.commitTransaction();
                finish();
            }
        }

        if (mButtonCourseAdd.equals(v)) {
            this.mCourses = new ArrayList<>();
            mCourseBuilder = new AlertDialog.Builder(CreateStudentActivity.this);
            mSpinner = new Spinner(CreateStudentActivity.this);
            mCourseBuilder.setCancelable(false);
            mCourseBuilder.setTitle(titleDialog);
            mCourseBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mSpinner.getSelectedItem().toString().equals(titleDialog)) {
                        Toast.makeText(context, titleDialog, Toast.LENGTH_SHORT).show();
                    } else {
                        if (mStudentCourses.size() != 0) {
                            for (StudentCoursePojo mCourses : mStudentCourses) {
                                if (mCourses.getCourse_name().equals(mSpinner.getSelectedItem().toString()))
                                    isCourseAvail = true;
                            }
                        }

                        if (!isCourseAvail) {
                            mCourses.add(mSpinner.getSelectedItem().toString());
                            StudentCoursePojo mStudentCoursePojo = new StudentCoursePojo();
                            mStudentCoursePojo.setId(System.currentTimeMillis());
                            mStudentCoursePojo.setCourse_name(mSpinner.getSelectedItem().toString());
                            mStudentCoursePojo.setCourse_id(mRealm.where(CourseEntity.class)
                                    .equalTo("name", mSpinner.getSelectedItem().toString())
                                    .findFirst()
                                    .getId()
                            );
                            mStudentCourses.add(mStudentCoursePojo);
                            dialog.dismiss();
                            isCourseAvail = false;

                            mAdapter = new CourseAdapter(mStudentCourses, CreateStudentActivity.this);
                            mAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(mAdapter);
                        } else {
                            Toast.makeText(context, titleDialog, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            mCourseBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            RealmResults<CourseEntity> mCourses = mRealm.where(CourseEntity.class).findAll();
            if (mCourses.size() != 0) {
                for (CourseEntity courseEntity : mCourses) {
                    this.mCourses.add(courseEntity.getName());
                }
            } else {
                this.mCourses.add(titleDialog);
            }

            ArrayAdapter mAdapterCard = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, this.mCourses);
            mAdapterCard.setDropDownViewResource(android.R.layout.simple_spinner_item);
            mSpinner.setAdapter(mAdapterCard);
            mCourseBuilder.setView(mSpinner);
            mCourseDialog = mCourseBuilder.create();
            mCourseDialog.show();
        }
    }

    @Override
    public void onClick(int position, long course_id) {

    }
}
