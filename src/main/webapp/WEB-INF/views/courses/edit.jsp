<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Course - Frontier Institute</title>
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
                <a href="${pageContext.request.contextPath}/courses" class="flex items-center px-6 py-3 bg-[#2a3bab] text-white">
                    <i class="fa fa-book w-6"></i> <span class="ml-3">Courses</span>
                </a>
                <a href="${pageContext.request.contextPath}/grades" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-chart-line w-6"></i> <span class="ml-3">Grades</span>
                </a>
                <a href="${pageContext.request.contextPath}/attendance" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-calendar-check w-6"></i> <span class="ml-3">Attendance</span>
                </a>
                <a href="#" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-cog w-6"></i> <span class="ml-3">Settings</span>
                </a>
            </nav>
        </div>
        <div class="px-6 py-4">
            <a href="${pageContext.request.contextPath}/auth/logout" class="block w-full text-center bg-white text-[#221E87] font-semibold py-2 rounded hover:bg-gray-200">Log Out</a>
        </div>
    </aside>
    <!-- Main Content -->
    <main class="flex-1 p-8">
        <div class="max-w-2xl mx-auto bg-white rounded-lg shadow p-8">
            <h2 class="text-2xl font-bold mb-6">Edit Course</h2>
            <form action="${pageContext.request.contextPath}/courses/update" method="post" class="space-y-6">
                <input type="hidden" name="courseId" value="${course.courseId}">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Course Code</label>
                        <input type="text" name="courseCode" required value="${course.courseCode}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Course Name</label>
                        <input type="text" name="courseName" required value="${course.courseName}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Teacher</label>
                        <select name="teacherId" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="">Select Teacher</option>
                            <c:forEach items="${teachers}" var="teacher">
                                <option value="${teacher.teacherId}" ${course.teacherId == teacher.teacherId ? 'selected' : ''}>${teacher.firstName} ${teacher.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Credits</label>
                        <input type="number" name="credits" required value="${course.credits}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Semester</label>
                        <input type="text" name="semester" required value="${course.semester}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                        <select name="status" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="Active" ${course.status == 'Active' ? 'selected' : ''}>Active</option>
                            <option value="Inactive" ${course.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                            <option value="Upcoming" ${course.status == 'Upcoming' ? 'selected' : ''}>Upcoming</option>
                        </select>
                    </div>
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-1">Description</label>
                        <textarea name="description" rows="2" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">${course.description}</textarea>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">Reset</button>
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded">Update Course</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
