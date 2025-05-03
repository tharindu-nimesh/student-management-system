package com.sms.service;

import com.sms.dao.TeacherDAO;
import com.sms.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherService {
    private TeacherDAO teacherDAO;

    public TeacherService() {
        this.teacherDAO = new TeacherDAO();
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        teacherDAO.addTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        teacherDAO.updateTeacher(teacher);
    }

    public void deleteTeacher(int teacherId) throws SQLException {
        teacherDAO.deleteTeacher(teacherId);
    }

    public Teacher getTeacherById(int teacherId) throws SQLException {
        return teacherDAO.getTeacherById(teacherId);
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        return teacherDAO.getAllTeachers();
    }

    public List<Teacher> searchTeachers(String searchTerm) throws SQLException {
        return teacherDAO.searchTeachers(searchTerm);
    }

    public int getTeacherCount() throws SQLException {
        return teacherDAO.getTeacherCount();
    }
}
