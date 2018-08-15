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

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StudentCoursePojo> mCourses;

    private OnStudentCourseItem mListener;

    public CourseAdapter(List<StudentCoursePojo> mCourses,
                         OnStudentCourseItem mListener) {
        super();
        this.mCourses = mCourses;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_create_student_course, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentCoursePojo mData = mCourses.get(position);
        if (mData != null) {
            ((CourseHolder) holder).mTxvCourse.setText(mData.getCourse_name());
            ((CourseHolder) holder).mButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(position, mData.getCourse_id());
                }
            });
        }
    }

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
