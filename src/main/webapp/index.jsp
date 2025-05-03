<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Redirect to the login page
    response.sendRedirect(request.getContextPath() + "/auth/login");
%>
