package com.ktb.controller;

import com.ktb.dao.tableData;
import com.ktb.impl.StudentI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST})
@RequestMapping("/student")
public class studentCtrl {

    @Autowired
    StudentI studentI;


    /**
     * @Description  ：学生登陆接口
     * http://localhost:7777/student/getStuInfo?student_number=2017441581&Password=123456
     * @author       : Ling_Shao
     * @param        :
     * @return       :200成功，401失败
     * @exception    :
     * @date         : 2020/12/29 13:15
     */
    @ResponseBody
    @RequestMapping("/getStuInfo")
    public int getStudentInfo(String student_number,String Password){
        String flag1 = studentI.getName(student_number,Password);
        int flag = Integer.valueOf(flag1);
        return flag;
    }

    /**
     * @Description  ：获取学生课程信息接口
     * http://localhost:7777/student/getClassInfo?student_number=2017441581&Password=123456
     * @author       : Ling_Shao
     * @param        :
     * @return       : 
     * @exception    : 
     * @date         : 2020/12/29 13:27
     */
    @ResponseBody
    @RequestMapping("/getClassInfo")
    public List<tableData> getclassInfo(String student_number,String Password){
        List<tableData> classInfo = studentI.getclassInfo(student_number,Password);
        return  classInfo;
    }

    /**
     * @Description  ：签到 http://localhost:7777
     * /student/checkIn?courseName=Java开发&classNo=1AB0001A.02&classYear=2017-2018-2
     * &attendanceCode=555234&studentNumber=2017441520
     * @author       : Ling_Shao
     * @param        :
     * @return       : 200签到成功，402签到失败
     * @exception    :
     * @date         : 2020/12/29 17:47
     */
    @ResponseBody
    @RequestMapping("/checkIn")
    public int checkIn(String courseName, String classNo, String classYear, String attendanceCode,String studentNumber){
        String flag = studentI.codeCheck(courseName,classNo,classYear,attendanceCode);
        if (flag.equals("1")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            studentI.checkIn(time,studentNumber,classNo);
            return 200;
        }else {
            return 402;
        }
    }


}
