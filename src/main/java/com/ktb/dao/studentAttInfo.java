package com.ktb.dao;

public class studentAttInfo {

    private String studentNumber;
    private String pictureUrl;
    private String className;
    private String lockSituation;
    private String Situation;
    private String studentName;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLockSituation() {
        return lockSituation;
    }

    public void setLockSituation(String lockSituation) {
        this.lockSituation = lockSituation;
    }

    public String getSituation() {
        return Situation;
    }

    public void setSituation(String situation) {
        Situation = situation;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
