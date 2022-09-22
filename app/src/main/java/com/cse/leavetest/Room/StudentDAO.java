package com.cse.leavetest.Room;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cse.leavetest.MainActivity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertStudent(StudentInfo studentInfo);

    @Query("SELECT * FROM StudentInfo")
    public List<StudentInfo> getAllStudent();

//    @Query("SELECT * FROM StudentInfo")
//    public List<StudentInfo> getAllStudent();

    @Query("SELECT leavetypename FROM StudentInfo")
    public List<String> getAllName();

//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);
}
