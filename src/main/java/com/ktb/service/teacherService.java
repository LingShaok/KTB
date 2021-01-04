package com.ktb.service;

import com.ktb.dao.User;
import com.ktb.dao.recordTime;
import com.ktb.dao.studentAttInfo;
import com.ktb.dao.tableData;

import java.util.List;

public interface teacherService {

    String signIn(String user_id, String user_password);
    List<tableData> getClassInfo(String user_id,String choose);
    String proCode(String code,String class_no,String class_year,String courseName);
    void endAttendance(String class_no,String class_year);
    void insterInfo(String class_no, String class_year, String course_name,String Time,String className, User user);
    List<recordTime> getRecordTime(String teacher_number, String class_no, String class_year, String class_name);
    List<studentAttInfo> getAttendanceInfo(String teacher_number,String class_no,String time);
    void updateState(String teacher_number,String time,String student_number,String class_no,String state);
    void deleteRecord(String theacher_number,String time,String class_no);
    void insterAttendanceInfo(String class_no, String class_year, String course_name,String Time, User user);

}
