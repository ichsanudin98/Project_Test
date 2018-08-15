package id.co.ichsanudinstore.student.holder.create_student;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.co.ichsanudinstore.student.R;

/*
* Class yang mengextends ViewHolder dari RecyclerView agar dapat digunakan oleh adapter
* */
public class CourseHolder extends RecyclerView.ViewHolder {

    public LinearLayout mLayoutParent;

    public TextView mTxvCourse;

    public CourseHolder(View itemView) {
        super(itemView);

        mLayoutParent = itemView.findViewById(R.id.create_student_course_item);

        mTxvCourse = itemView.findViewById(R.id.create_student_course_name_item);
    }
}
