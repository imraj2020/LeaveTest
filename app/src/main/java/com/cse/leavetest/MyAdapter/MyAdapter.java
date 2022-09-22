package com.cse.leavetest.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cse.leavetest.R;
import com.cse.leavetest.Room.StudentInfo;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<StudentInfo> arrayList;
    public MyAdapter (Context context, ArrayList<StudentInfo> arrayList){
        this.context =context;
        this.arrayList = arrayList;

    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mycustomlistview,null);
        TextView t1_id = (TextView)view.findViewById(R.id.id_txt);
        TextView t2_name = (TextView)view.findViewById(R.id.id_name);
        TextView t3_age = (TextView)view.findViewById(R.id.id_age);
        StudentInfo studentInfo =arrayList.get(i);

        t1_id.setText(studentInfo.getLeaveid());
        t2_name.setText(studentInfo.getLeavetypename());
        t3_age.setText(studentInfo.getEntryby());



        return view;
    }
}
