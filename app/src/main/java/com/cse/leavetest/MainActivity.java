package com.cse.leavetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.leavetest.MyAdapter.MyAdapter;
import com.cse.leavetest.MyAdapter.RoomAdapter;
import com.cse.leavetest.Network.LeaveApiClient;
import com.cse.leavetest.Network.LeaveSummary;
import com.cse.leavetest.Room.MyRoomDB;
import com.cse.leavetest.Room.StudentInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public MyRoomDB roomDB;
    TextView LeaveSummary;
    RecyclerView MyListView;
    MyAdapter myAdapter;
    List<StudentInfo> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeaveSummary =findViewById (R.id.leavesummary);
        MyListView = findViewById(R.id.mylistview);
        arrayList = new ArrayList<>();

        setDatabase();
        leaveSummary();
        loaddatainlistview();
    }

    private void loaddatainlistview() {
        arrayList = roomDB.studentDAO().getAllStudent();
        RoomAdapter adapter = new RoomAdapter(arrayList, this);
        MyListView.setLayoutManager(new LinearLayoutManager(this));
        MyListView.setAdapter(adapter);
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    private void leaveSummary() {
        Call<List<com.cse.leavetest.Network.LeaveSummary>> call = LeaveApiClient.getUserService().leavesummary("BAN31016","laila");



        call.enqueue(new Callback<List<LeaveSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveSummary>> call, Response<List<LeaveSummary>> response) {

                if (response.isSuccessful()) {

                    List<LeaveSummary> nlist = response.body();

                    Toast.makeText(getApplicationContext(), "Retrive successfull", Toast.LENGTH_SHORT).show();

                   // Log.d("LeaveResponse", nlist.get(0).getLeaveTypeName());




                    for (LeaveSummary post : nlist) {
                        String content = "";
                        content += "Leave ID: " + post.getLeaveId() + "\n";
                        content += "Leave Type Name: " + post.getLeaveTypeName() + "\n";
                        content += "From Date: " + post.getFromDate() + "\n";
                        content += "To Date: " + post.getToDate() + "\n";
                        content += "Total Day :" + post.getTotalDay() + "\n";
                        content += "Total Hours: " + post.getTotalHours() + "\n";
                        content += "Entry By: " + post.getEntryBy() + "\n";
                        content += "Entry Date Time: " + post.getEntryDateTime() + "\n";
                        content += "Leave Status Id: " + post.getLeaveStatusId() + "\n";
                        content += "Leave Satus Name: " + post.getLeaveStatusName() + "\n\n";
//                        StudentInfo studentInfo = new StudentInfo(post.getLeaveTypeName());
//                        roomDB.studentDAO().insertStudent(studentInfo);
                        
                            StudentInfo studentInfo = new StudentInfo(post.getLeaveId(), post.getLeaveTypeName(),post.getFromDate()
                            ,post.getToDate(),post.getTotalDay(),post.getTotalHours(),post.getEntryBy(),post.getEntryDateTime(),
                                    post.getLeaveStatusId(),post.getLeaveStatusName());
                            roomDB.studentDAO().insertStudent(studentInfo);



                        LeaveSummary.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveSummary>> call, Throwable t) {
                LeaveSummary.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setDatabase(){
        roomDB = Room.databaseBuilder(MainActivity.this, MyRoomDB.class,"RoomStudentDB.db")
                .allowMainThreadQueries().build();
    }
//    private void showDataFromDb() {
//
//        List<StudentInfo> studentInfoList = roomDB.studentDAO().getAllStudent();
//        for (int i = 0; i<studentInfoList.size(); i++){
//            Log.d("StuentInfo", studentInfoList.get(i).getId()+"\t"
//                    +studentInfoList.get(i).getName()+"\t"
//                    +studentInfoList.get(i).getSubject()+"\t"
//                    +studentInfoList.get(i).getDepartment());
//
//            Toast.makeText(getApplicationContext(), "Data is:"+studentInfoList.get(i).getId()+"\t"
//                    +studentInfoList.get(i).getName()+"\t"
//                    +studentInfoList.get(i).getSubject()+"\t"
//                    +studentInfoList.get(i).getDepartment(), Toast.LENGTH_SHORT).show();
//        }
//    }



}