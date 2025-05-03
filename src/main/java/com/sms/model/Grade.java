package com.sms.model;

import java.util.Date;

public class Grade {
    private int gradeId;
    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private String assignmentName;
    private double gradeValue;
    private double maxGrade;
    private Date gradeDate;
    private String comments;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Grade() {}

    public Grade(int gradeId, int studentId, int courseId, String assignmentName,
                 double gradeValue, double maxGrade, Date gradeDate, String comments) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.assignmentName = assignmentName;
        this.gradeValue = gradeValue;
        this.maxGrade = maxGrade;
        this.gradeDate = gradeDate;
        this.comments = comments;
    }

    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public double getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(double maxGrade) {
        this.maxGrade = maxGrade;
    }

    public double getPercentage() {
        return (gradeValue / maxGrade) * 100;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", assignmentName='" + assignmentName + '\'' +
                ", gradeValue=" + gradeValue +
                ", maxGrade=" + maxGrade +
                '}';
    }
}
