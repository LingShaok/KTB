package com.ktb.tool;
import com.ktb.service.teacherService;

public class countDown extends Thread{

    private teacherService signService;
    private String classNo;
    private String classYear;

    public static int time=60;

    public countDown(teacherService signService, String classNo, String classYear){
        this.signService = signService;
        this.classNo = classNo;
        this.classYear = classYear;
    }

    public void counttime(){
        while (time>0){
            time--;
            long hh = time/60/60%60;
            long mm = time/60%60;
            long ss = time%60;
            if (hh==0&&mm==0&&ss==0){
                this.signService.endAttendance(classNo,classYear);
                break;
            }
            System.out.println("还剩"+hh+":"+mm+":"+ss+",重置签到码");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(){
        counttime();
    }
}
