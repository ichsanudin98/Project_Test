package id.co.ichsanudinstore.student.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import id.co.ichsanudinstore.student.R;

public class MainHolder extends RecyclerView.ViewHolder {

    public CardView mLayoutParent;

    public TextView mTxvName, mTxvNIK, mTxvMajor;

    public MainHolder(View itemView) {
        super(itemView);

        mLayoutParent = itemView.findViewById(R.id.main_student_item);

        mTxvName = itemView.findViewById(R.id.main_name_item);
        mTxvNIK = itemView.findViewById(R.id.main_nik_item);
        mTxvMajor = itemView.findViewById(R.id.main_course_item);
    }
}
