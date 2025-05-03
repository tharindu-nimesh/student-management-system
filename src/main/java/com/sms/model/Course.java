package com.sms.model;

import java.util.Date;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private int credits;
    private int teacherId;
    private String teacherName;
    private String semester;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Course() {}

    public Course(int courseId, String courseCode, String courseName, String description,
                  int credits, int teacherId, String semester, String status) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.teacherId = teacherId;
        this.semester = semester;
        this.status = status;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", teacherId=" + teacherId +
                '}';
    }
}
