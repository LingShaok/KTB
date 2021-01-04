package com.ktb.mapper;

import com.ktb.dao.classNo;
import com.ktb.dao.tableData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface studentMapper {
    //学生登陆账号信息查询(返回课程编号)
    @Select("SELECT classNo from student where #{studentNumber} = studentNumber and #{Password} = Password")
    List<classNo> getStudentInfo(@Param("studentNumber")String studentNumber, @Param("Password")String Password);

//    获取学生名字
    @Select("SELECT studentName from student where #{studentNumber} = studentNumber and #{Password} = Password")
    List<String> getName(@Param("studentNumber")String studentNumber, @Param("Password")String Password);

    //学生获取课程表信息
    @Select("SELECT * from classtable where #{classNo} = classNo")
    tableData getclassInfo(@Param("classNo") String classNo);

    //签到码的合法性检测
    @Select("SELECT * from classtable where courseName = #{courseName} and classNo = #{classNo} and classyear = #{classYear}" +
            "and attendanceCode = #{attendanceCode}")
    String codeCheck(@Param("courseName")String courseName,@Param("classNo")String classNo,@Param("classYear")String classYear,
                   @Param("attendanceCode")String attendanceCode);

    //修改签到状态
    @Update("UPDATE attendance SET situation='4', lockSituation='已上锁' WHERE Time >=#{time} - " +
            "INTERVAL 3 MINUTE AND classNo=#{class_no} " +
            "AND studentNumber=#{student_number}")
    void checkIn(@Param("time")String time,@Param("student_number")String studentNumber,@Param("class_no")String classNo);
}
