package com.ktb.impl;

import com.ktb.dao.User;
import com.ktb.dao.recordTime;
import com.ktb.dao.studentAttInfo;
import com.ktb.dao.tableData;
import com.ktb.mapper.teacherMapper;
import com.ktb.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherI implements teacherService {

    @Autowired
    private teacherMapper teaMapper;

    @Override
    public String signIn(String user_id, String user_password) {
//        System.out.println("----------------------"+this.mapper.getTeacherInfo(user_id,user_password));
        String tList = this.teaMapper.getTeacherInfo(user_id,user_password);
        return tList;
    }

    @Override
    public List<tableData> getClassInfo(String user_id,String choose) {
        List<tableData> tableData = this.teaMapper.getClassInfo(user_id,choose);
        System.out.println(tableData);
        return tableData;
    }

    @Override
    public String proCode(String code,String class_no, String class_year,String courseName) {
        this.teaMapper.proCode(code,class_no,class_year,courseName);
        return code;
    }

    @Override
    public void endAttendance(String class_no, String class_year) {
        this.teaMapper.endAttendance(class_no,class_year);
    }

    @Override
    public void insterInfo(String class_no, String class_year, String course_name,String Time,String className, User user) {
        String name = this.teaMapper.getTeacherInfo(user.getUser_id(),user.getUser_password());
        this.teaMapper.insertInfo(class_no,course_name,name,class_year,Time,className,user.getUser_id());
    }

    @Override
    public List<recordTime> getRecordTime(String teacher_number, String class_no, String class_year, String class_name) {
        List<recordTime> recouseTime = this.teaMapper.getCourseTimeInfo(class_no,class_name,class_year);
        return recouseTime;
    }

    @Override
    public List<studentAttInfo> getAttendanceInfo(String teacher_number, String class_no, String time) {
        List<studentAttInfo> AttendanceInfo = this.teaMapper.getAttendanceInfo(time,class_no,teacher_number);
        return AttendanceInfo;
    }

    @Override
    public void updateState(String teacher_number, String time, String student_number, String class_no, String state) {
        this.teaMapper.changeState(state,time,class_no,student_number,teacher_number);
    }

    @Override
    public void deleteRecord(String theacher_number, String time, String class_no) {
        this.teaMapper.deleteRecord(time,class_no,theacher_number);
    }

    @Override
    public void insterAttendanceInfo(String class_no, String class_year, String course_name, String Time, User user) {
        String name = this.teaMapper.getTeacherInfo(user.getUser_id(),user.getUser_password());
        this.teaMapper.insetAttendanceInfo(class_no,course_name,name,class_year,Time,user.getUser_id());
    }

}
