<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Attendance - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="flex min-h-screen">
    <!-- Sidebar (same as above) -->
    <aside class="w-64 bg-[#221E87] flex flex-col justify-between">
        <div>
            <div class="bg-red-600 flex items-center px-6 py-4">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="h-12 mr-2">
                <span class="text-white font-bold text-lg">FRONTIER INSTITUTE</span>
            </div>
            <nav class="mt-2">
                <a href="${pageContext.request.contextPath}/dashboard" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-chart-bar w-6"></i> <span class="ml-3">Dashboard</span>
                </a>
                <a href="${pageContext.request.contextPath}/students" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-user-graduate w-6"></i> <span class="ml-3">Students</span>
                </a>
                <a href="${pageContext.request.contextPath}/teachers" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-chalkboard-teacher w-6"></i> <span class="ml-3">Teachers</span>
                </a>
                <a href="${pageContext.request.contextPath}/courses" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-book w-6"></i> <span class="ml-3">Courses</span>
                </a>
                <a href="${pageContext.request.contextPath}/grades" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-chart-line w-6"></i> <span class="ml-3">Grades</span>
                </a>
                <a href="${pageContext.request.contextPath}/attendance" class="flex items-center px-6 py-3 bg-[#2a3bab] text-white">
                    <i class="fa fa-calendar-check w-6"></i> <span class="ml-3">Attendance</span>
                </a>
                <a href="#" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-cog w-6"></i> <span class="ml-3">Settings</span>
                </a>
            </nav>
        </div>
        <div class="px-6 py-4">
            <a href="${pageContext.request.contextPath}/auth/logout" class="block w-full text-center bg-white text-[#221E87] font-semibold py-2 roun
