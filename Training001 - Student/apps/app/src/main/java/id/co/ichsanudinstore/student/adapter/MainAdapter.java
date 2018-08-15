package id.co.ichsanudinstore.student.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.ichsanudinstore.student.R;
import id.co.ichsanudinstore.student.entity.StudentEntity;
import id.co.ichsanudinstore.student.holder.MainHolder;
import id.co.ichsanudinstore.student.interfaces.OnMainItemClick;
import io.realm.RealmResults;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<StudentEntity> mStudents;

    private Activity activity;

    private OnMainItemClick mListener;

    public MainAdapter(RealmResults<StudentEntity> mStudents, Activity activity,
                       OnMainItemClick mListener) {
        super();
        this.mStudents = mStudents;
        this.activity = activity;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final StudentEntity mData = mStudents.get(position);
        if (mData != null) {
            ((MainHolder) holder).mTxvName.setText(mData.getName());
            ((MainHolder) holder).mTxvMajor.setText(mData.getMajors());
            ((MainHolder) holder).mTxvNIK.setText("( " + mData.getNik() + " )");
            ((MainHolder) holder).mLayoutParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(position, mData.getNik());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
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
