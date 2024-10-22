package com.example.w05_group07;

public class Student {
    String studentName;
    String studentClass;
    Double studentScore;
    public Student(){

    }
    public Student(String studentName, String studentClass, Double studentScore){
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentScore = studentScore;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }
}
