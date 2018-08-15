package id.co.ichsanudinstore.student.adapter.create_student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.co.ichsanudinstore.student.R;
import id.co.ichsanudinstore.student.holder.create_student.CourseHolder;
import id.co.ichsanudinstore.student.interfaces.OnStudentCourseItem;
import id.co.ichsanudinstore.student.pojo.StudentCoursePojo;

/*
* Class yang extend class ViewHolder dari RecyclerView
* */
public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StudentCoursePojo> mCourses;

    private OnStudentCourseItem mListener;

    /*
    * Membuat constructor di adapter dengan meminta parameter
    * List<StudentCoursePojo> dan OnStudentCourseItem
    * */
    public CourseAdapter(List<StudentCoursePojo> mCourses,
                         OnStudentCourseItem mListener) {
        super();
        this.mCourses = mCourses;
        this.mListener = mListener;
    }

    /*
     * Deklarasi layout per item untuk sebuah list
     * */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_create_student_course, parent, false);
        return new CourseHolder(view);
    }

    /*
     * Mengambil nilai per item dari list yang dikirimkan oleh constructor
     * dan untuk set nilai per item dan memberikan aksi semisal berupa click
     * */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentCoursePojo mData = mCourses.get(position);
        if (mData != null) {
            ((CourseHolder) holder).mTxvCourse.setText(mData.getCourse_name());
        }
    }

    /*
     * Memberitahukan recyclerview untuk membuat list dari jumlah banyaknya data
     * */
    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
