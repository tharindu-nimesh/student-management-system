<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Attendance - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <!-- Main Content -->
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Add Attendance</h1>
                    <p class="text-sm text-gray-600">Record a new attendance entry</p>
                </div>

                <div>
                    <a href="${pageContext.request.contextPath}/attendance" class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded-md transition duration-300">
                        <i class="fas fa-arrow-left mr-2"></i> Back to List
                    </a>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 p-8">
            <div class="bg-white rounded-lg shadow p-6">
                <form action="${pageContext.request.contextPath}/attendance/add" method="post">
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label for="studentId" class="block text-sm font-medium text-gray-700 mb-2">Student</label>
                            <select id="studentId" name="studentId" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500" required>
                                <option value="">Select Student</option>
                                <c:forEach items="${students}" var="student">
                                    <option value="${student.studentId}">${student.firstName} ${student.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div>
                            <label for="courseId" class="block text-sm font-medium text-gray-700 mb-2">Course</label>
                            <select id="courseId" name="courseId" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500" required>
                                <option value="">Select Course</option>
                                <c:forEach items="${courses}" var="course">
                                    <option value="${course.courseId}">${course.courseName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div>
                            <label for="attendanceDate" class="block text-sm font-medium text-gray-700 mb-2">Date</label>
                            <input type="date" id="attendanceDate" name="attendanceDate"
                                   value="<fmt:formatDate value='${today}' pattern='yyyy-MM-dd'/>"
                                   class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500" required>
                        </div>

                        <div>
                            <label for="status" class="block text-sm font-medium text-gray-700 mb-2">Status</label>
                            <select id="status" name="status" class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500" required>
                                <option value="Present">Present</option>
                                <option value="Absent">Absent</option>
                                <option value="Late">Late</option>
                                <option value="Excused">Excused</option>
                            </select>
                        </div>

                        <div class="md:col-span-2">
                            <label for="remarks" class="block text-sm font-medium text-gray-700 mb-2">Remarks</label>
                            <textarea id="remarks" name="remarks" rows="3"
                                      class="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-500"
                                      placeholder="Add any additional notes here..."></textarea>
                        </div>
                    </div>

                    <div class="mt-6 flex justify-end">
                        <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 px-6 py-2 rounded font-semibold mr-2">
                            Reset
                        </button>
                        <button type="submit" class="bg-[#3047D5] hover:bg-[#2536a9] text-white px-6 py-2 rounded font-semibold">
                            Save Attendance
                        </button>

                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

<script>
    // Set default date to today
    document.addEventListener('DOMContentLoaded', function() {
        if (!document.getElementById('attendanceDate').value) {
            const today = new Date();
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0');
            const day = String(today.getDate()).padStart(2, '0');
            document.getElementById('attendanceDate').value = `${year}-${month}-${day}`;
        }
    });
</script>
</body>
</html>
