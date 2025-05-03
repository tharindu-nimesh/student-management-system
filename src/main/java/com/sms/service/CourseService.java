package com.sms.service;

import com.sms.dao.CourseDAO;
import com.sms.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public void addCourse(Course course) throws SQLException {
        courseDAO.addCourse(course);
    }

    public void updateCourse(Course course) throws SQLException {
        courseDAO.updateCourse(course);
    }

    public void deleteCourse(int courseId) throws SQLException {
        courseDAO.deleteCourse(courseId);
    }

    public Course getCourseById(int courseId) throws SQLException {
        return courseDAO.getCourseById(courseId);
    }

    public List<Course> getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    public List<Course> getCoursesByTeacher(int teacherId) throws SQLException {
        return courseDAO.getCoursesByTeacher(teacherId);
    }

    public List<Course> getCoursesByStudent(int studentId) throws SQLException {
        return courseDAO.getCoursesByStudent(studentId);
    }

    public List<Course> getAvailableCoursesForStudent(int studentId) throws SQLException {
        return courseDAO.getAvailableCoursesForStudent(studentId);
    }

    public int getCourseCount() throws SQLException {
        return courseDAO.getCourseCount();
    }

    public void enrollStudent(int studentId, int courseId) throws SQLException {
        courseDAO.enrollStudent(studentId, courseId);
    }

    public void unenrollStudent(int studentId, int courseId) throws SQLException {
        courseDAO.unenrollStudent(studentId, courseId);
    }
}
