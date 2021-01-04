package com.ktb.impl;

import com.ktb.dao.classNo;
import com.ktb.dao.tableData;
import com.ktb.mapper.studentMapper;
import com.ktb.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class StudentI implements studentService {
    @Autowired
    private studentMapper stuMapper;

    @Override
    public String getName(String student_number, String Password) {
        List<String> Name = stuMapper.getName(student_number,Password);
        int longth=Name.size();
        System.out.println(longth);
        String flag = null;
        if (longth==0){
            flag="401";
            return flag;
        }else {
            flag="200";
            return flag;
        }
    }

    @Override
    public List<tableData> getclassInfo(String student_number, String Password) {
//        获取课程代号集合
        List<classNo> classNoList = stuMapper.getStudentInfo(student_number,Password);
//        提取对象集合中的某个字段
        List<String> number = classNoList.stream().map(p -> p.getClassNo()).collect(Collectors.toList());
//        声明课程信息集合
        List<tableData> classInfo = new ArrayList<>();
//        遍历提取
        for (int i=0;i<number.size();i++){
            String classNo = number.get(i);
            classInfo.add(stuMapper.getclassInfo(classNo));
        }
        return classInfo;
    }

    @Override
    public String codeCheck(String courseName, String classNo, String classYear, String attendanceCode) {
        String flag = stuMapper.codeCheck(courseName,classNo,classYear,attendanceCode);
        if (flag!=null)
            return "1";//合法
        else
            return "0";//不合法
    }

    @Override
    public void checkIn(String time, String studentNumber, String classNo) {
        stuMapper.checkIn(time,studentNumber,classNo);
    }
}
