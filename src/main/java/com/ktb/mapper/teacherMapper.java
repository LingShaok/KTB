package com.ktb.mapper;

import com.ktb.dao.recordTime;
import com.ktb.dao.studentAttInfo;
import com.ktb.dao.tableData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface teacherMapper {

    @Select("select teacherName from teacher where teacherNumber=#{user_id} and Password = #{user_password}")
    String getTeacherInfo(@Param("user_id")String user_id, @Param("user_password")String user_password);

    @Select("select * from classtable where teacherNumber=#{user_id} and classYear=#{choose}")
    List<tableData> getClassInfo(@Param("user_id") String user_id,@Param("choose") String choose);

    @Update("update classtable set attendanceCode=#{code} where classNo=#{class_no} and classYear=#{class_year} " +
            "and courseName=#{courseName}")
    void proCode(@Param("code")String Code,@Param("class_no")String classNo,@Param("class_year")String classYear,
                 @Param("courseName")String courseName);

    @Update("update classtable set attendanceCode=null where classNo=#{class_no} and classYear=#{class_year}")
    void endAttendance(@Param("class_no")String classNo,@Param("class_year")String classYear);

    @Insert("insert into attendance ( classNo,courseName,Teacher,classYear,Time,className,teacherNumber)" +
            " values ( #{class_no},#{course_name},#{Teacher},#{class_year},#{Time},#{class_name},#{teacher_number})")
    void insertInfo(@Param("class_no")String classNo,@Param("course_name")String courseName,
                    @Param("Teacher")String Teacher,@Param("class_year")String classYear, @Param("Time")String Time,
                    @Param("class_name")String className,@Param("teacher_number")String teacherNumber);

    @Insert("INSERT INTO attendance (classNo,courseName,Teacher,classYear,Time,studentNumber,studentName," +
            "className,situation,lockSituation,teacherNumber) SELECT classNo,#{course_name},#{Teacher},#{class_year}," +
            "#{Time},studentNumber," +
            "studentName,className,'1','未上锁',#{teacher_number}" +
            " FROM student where classNo=#{class_no}")
    void insetAttendanceInfo(@Param("class_no")String classNo,@Param("course_name")String courseName,
                             @Param("Teacher")String Teacher,@Param("class_year")String classYear, @Param("Time")String Time,
                             @Param("teacher_number")String teacherNumber);

    @Select("select Time from attendance where classNo=#{class_no} and className=#{class_name} " +
            "and classYear=#{class_year} and studentNumber is null")
    List<recordTime> getCourseTimeInfo(@Param("class_no")String classNo, @Param("class_name")String className,
                                       @Param("class_year")String classYear);

    @Select("select studentNumber,studentName,className,lockSituation,situation from attendance WHERE #{time} " +
            "<= Time AND Time <= #{time} + INTERVAL 9 MINUTE AND classNo=#{class_no} AND teacherNumber=#{teacher_number}" +
            " AND studentNumber IS NOT NULL")
    List<studentAttInfo> getAttendanceInfo(@Param("time")String time,@Param("class_no")String classNo,
                                           @Param("teacher_number")String teacherNumber);

    @Update("UPDATE attendance SET situation=#{state} WHERE #{time} <= Time AND Time <=#{time} + INTERVAL 10 MINUTE AND classNo=#{class_no} " +
            "AND studentNumber=#{student_number} AND teacherNumber=#{teacher_number}")
    void changeState(@Param("state")String State,@Param("time")String Time,
                                         @Param("class_no")String classNo,@Param("student_number")String studentNumber,
                                         @Param("teacher_number")String teachetNumber);

    @Delete("DELETE FROM attendance WHERE #{time} <= Time AND Time <= #{time} + INTERVAL 10 MINUTE AND classNo=#{class_no}" +
            " AND teacherNumber=#{teacher_number}")
    void deleteRecord(@Param("time")String Time,@Param("class_no")String classNo,
                      @Param("teacher_number")String teacherNumber);

}


