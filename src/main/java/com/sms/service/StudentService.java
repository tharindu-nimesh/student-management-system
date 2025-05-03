package com.sms.service;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public void addStudent(Student student) throws SQLException {
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) throws SQLException {
        studentDAO.deleteStudent(studentId);
    }

    public Student getStudentById(int studentId) throws SQLException {
        return studentDAO.getStudentById(studentId);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public List<Student> searchStudents(String searchTerm) throws SQLException {
        return studentDAO.searchStudents(searchTerm);
    }

    public List<Student> getStudentsByCourse(int courseId) throws SQLException {
        return studentDAO.getStudentsByCourse(courseId);
    }

    public List<Student> getAvailableStudentsForCourse(int courseId) throws SQLException {
        return studentDAO.getAvailableStudentsForCourse(courseId);
    }

    public int getStudentCount() throws SQLException {
        return studentDAO.getStudentCount();
    }

    public List<Student> getRecentStudents(int limit) throws SQLException {
        return studentDAO.getRecentStudents(limit);
    }
}
