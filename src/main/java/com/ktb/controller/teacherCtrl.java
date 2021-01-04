package com.ktb.controller;

import com.ktb.dao.*;
import com.ktb.service.teacherService;
import com.ktb.tool.countDown;
import com.ktb.tool.produceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/teacher")
public class teacherCtrl {
    @Autowired
    private teacherService teaService;

    /**
     * @param : user_id user_password
     * @return : 失败:401  成功:教师姓名
     * @throws : 无
     * @Description ：教师登录接口http://localhost:7777/teacher/signIn?user_id=2017001&user_password=123456
     * @author : Ling_Shao
     * @date : 2020/4/16 2:38
     */
    @ResponseBody
    @RequestMapping("/signIn")
    public Object signIn(String user_id, String user_password) {
//        System.out.println(user_id);
//        System.out.println(user_password);
        String name = this.teaService.signIn(user_id, user_password);
        Data data = new Data();
        if (name != null) {
            System.out.println(name);
            return name;
        } else {
            data.setCode("401");
            data.setMsg("账号密码不匹配");
            return data;
        }

    }

    /**
     * @param : Choose(学期)
     * @return :  tableData
     * @throws :
     * @Description ：根据传来的学期返回课程表接口
     * http://localhost:7777/teacher/getCurrentTermClassInfo
     * ?user_id=2017001&user_password=123456&Choose=2017-2018-1
     * @author : Ling_Shao
     * @date : 2020/4/25 15:59
     */
    @ResponseBody
    @RequestMapping("/getCurrentTermClassInfo")
    public Object getClassInfo(User user, String Choose) {
        System.out.println(user.getUser_id() + Choose);
        List<tableData> tableData = this.teaService.getClassInfo(user.getUser_id(), Choose);
        return tableData;
    }

    /**
     * @param : user用户信息（id和密码） classNo课程编号 classYear课程学年 className班级名字  courseName课程名字
     * @return : code考勤码
     * @throws :
     * @Description ：发起考勤
     * http://localhost:7777/teacher/getManageAbsenceInfo
     * ?user_id=2017001&user_password=123456&classNo=1AB0001A.01
     * &classYear=2017-2018-2&courseName=Android开发&className=软件2017-01
     * @author : Ling_Shao
     * @date : 2020/4/25 16:48
     */
    @ResponseBody
    @RequestMapping("/getManageAbsenceInfo")
    public Object getManageAbsenceInfo(User user, String classNo, String classYear,
                                       String courseName, String className)
            throws InterruptedException {

        produceCode pCode = new produceCode();
        String code = pCode.initCode();
        //将考勤码存入数据库
        this.teaService.proCode(code, classNo, classYear,courseName);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        System.out.println("现在时间是:" + time);

        this.teaService.insterInfo(classNo, classYear, courseName, time, className, user);//记录签到时间
        this.teaService.insterAttendanceInfo(classNo,classYear,courseName,time,user);//新增学生考勤信息，默认缺勤
        Thread t = new countDown(teaService, classNo, classYear);
        t.start();

        return code;

    }

    /**
     * @param : classNo课程编号 classYear课程学年
     * @return : 无
     * @throws :
     * @Description ：结束考勤
     * http://localhost:7777/teacher/endAttendance?classNo=1AB0001A.01&classYear=2017-2018-2
     * @author : Ling_Shao
     * @date : 2020/4/25 19:41
     */
    @ResponseBody
    @RequestMapping("/endAttendance")
    public void endAttendance(String classNo, String classYear) {
        this.teaService.endAttendance(classNo, classYear);
    }

    /**
     * @param :user classNo classYear className
     * @return :[{"time":"2020-04-27 12:52:45"},{"time":"2020-04-26 12:52:45"}]
     * @throws :
     * @Description ：获取历史每次考勤的时间
     * http://localhost:7777/teacher/getAbsenceManageCountInfo
     * ?user_id=2017001&user_password=123456&classNo=1AB0001A.01
     * &classYear=2017-2018-2&className=%E8%BD%AF%E4%BB%B62017-01
     * @author : Ling_Shao
     * @date : 2020/4/26 13:22
     */
    @ResponseBody
    @RequestMapping("/getAbsenceManageCountInfo")
    public List<recordTime> getAbsenceManageCountInfo(User user, String classNo, String classYear, String className) {
        List<recordTime> recordTime = this.teaService.getRecordTime(user.getUser_id(), classNo, classYear, className);
        return recordTime;
    }

    /**
     * @param :user(用户账号和密码),classNo(课程id),register_time(具体考勤时间)
     * @return :
     * [{"studentNumber":"2017441568","pictureUrl":null,"className":"软件2017-01","lockSituation":"未上锁",
     * "studentName":"谢金桥","situation":"2"},
     * {"studentNumber":"2017441520","pictureUrl":null,"className":"软件2017-01","lockSituation":"未上锁",
     * "studentName":"刘新航","situation":"4"}]
     * @throws :
     * @Description ：获取单次考勤的考勤情况信息
     * http://localhost:7777/teacher/getStudentRegisterInfo
     * ?user_id=2017001&user_password=123456&classNo=1AB0001A.02&register_time=2020-04-05%2010:02:00
     * @author : Ling_Shao
     * @date : 2020/4/26 15:25
     */
    @ResponseBody
    @RequestMapping("/getStudentRegisterInfo")
    public List<studentAttInfo> getStudentRegisterInfo(String register_time, String classNo, User user) {
        List<studentAttInfo> attendanceInfo = this.teaService.getAttendanceInfo(user.getUser_id(), classNo, register_time);
        return attendanceInfo;
    }

    /**
     * @param : user(用户id和密码) every_register_sit(要修改的状态) classNo(课程编码),
     *          student_id(学号),register_time(签到时间戳)
     * @return :无
     * @throws :
     * @Description ：修改学生签到状态
     * http://localhost:7777/teacher/modifySingleStudentRegisterInfo
     * ?user_id=2017001&user_password=123456&classNo=1AB0001A.02
     * &register_time=2020-04-05%2010:02:00&every_register_sit=9&student_id=2017441581
     * @author : Ling_Shao
     * @date : 2020/4/26 16:10
     */
    @ResponseBody
    @RequestMapping("/modifySingleStudentRegisterInfo")
    public void changeStuState(User user, String every_register_sit, String classNo, String student_id, String register_time) {
        this.teaService.updateState(user.getUser_id(), register_time, student_id, classNo, every_register_sit);
    }

    /**
     * @param : user(用户信息，账号和密码） time（考勤时间） classNo（课程编号）
     * @return : 无
     * @throws :无
     * @Description ：删除考勤记录
     * http://localhost:7777/teacher/deleteRecord?
     * user_id=2017001&user_password=123456&
     * classNo=1AB0001A.03&
     * time=2020-04-27%2020:00:00
     * @author : Ling_Shao
     * @date : 2020/4/30 18:08
     */
    @ResponseBody
    @RequestMapping("/deleteRecord")
    public void daleteRecord(User user, String time, String classNo) {
        this.teaService.deleteRecord(user.getUser_id(), time, classNo);
    }
}
