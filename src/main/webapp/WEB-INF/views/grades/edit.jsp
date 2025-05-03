<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Grade - Frontier Institute</title>
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
                <a href="${pageContext.request.contextPath}/grades" class="flex items-center px-6 py-3 bg-[#2a3bab] text-white">
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
            <h2 class="text-2xl font-bold mb-6">Edit Grade</h2>
            <form action="${pageContext.request.contextPath}/grades/update" method="post" class="space-y-6">
                <input type="hidden" name="gradeId" value="${grade.gradeId}">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Student</label>
                        <select name="studentId" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="">Select Student</option>
                            <c:forEach items="${students}" var="student">
                                <option value="${student.studentId}" ${grade.studentId == student.studentId ? 'selected' : ''}>${student.firstName} ${student.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Course</label>
                        <select name="courseId" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="">Select Course</option>
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.courseId}" ${grade.courseId == course.courseId ? 'selected' : ''}>${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Assignment Name</label>
                        <input type="text" name="assignmentName" required value="${grade.assignmentName}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Grade Value</label>
                        <input type="number" step="0.01" name="gradeValue" required value="${grade.gradeValue}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Max Grade</label>
                        <input type="number" step="0.01" name="maxGrade" required value="${grade.maxGrade}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Grade Date</label>
                        <input type="date" name="gradeDate" required value="<fmt:formatDate value='${grade.gradeDate}' pattern='yyyy-MM-dd'/>" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-1">Comments</label>
                        <textarea name="comments" rows="2" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">${grade.comments}</textarea>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">Reset</button>
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded">Update Grade</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
