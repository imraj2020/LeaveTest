package com.cse.leavetest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.leavetest.R;
import com.cse.leavetest.Room.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private final List<StudentInfo> list;
    private Context context;

    public RoomAdapter(List<StudentInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycustomlistview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentInfo data = list.get(position);

        holder.t1_id.setText(data.getLeaveid());
        holder.t2_name.setText(data.getLeavetypename());
        holder.t3_age.setText(data.getEntryby());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView t1_id, t2_name, t3_age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t1_id = (TextView)itemView.findViewById(R.id.id_txt);
            t2_name = (TextView)itemView.findViewById(R.id.id_name);
            t3_age = (TextView)itemView.findViewById(R.id.id_age);
        }
    }
}
