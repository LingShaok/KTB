package com.ktb.service;

import com.ktb.dao.tableData;

import java.util.List;

public interface studentService {
    String getName(String student_number,String Password);
    List<tableData> getclassInfo(String student_number, String Password);
    String codeCheck(String courseName,String classNo,String classYear,String attendanceCode);
    void checkIn(String time,String studentNumber,String classNo);
}
