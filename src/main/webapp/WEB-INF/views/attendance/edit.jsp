<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Attendance - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <!-- Main Content -->
    <main class="flex-1 p-8">
        <div class="max-w-2xl mx-auto bg-white rounded-lg shadow p-8">
            <h2 class="text-2xl font-bold mb-6">Edit Attendance</h2>
            <form action="${pageContext.request.contextPath}/attendance/update" method="post" class="space-y-6">
                <input type="hidden" name="attendanceId" value="${attendance.attendanceId}">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Student</label>
                        <select name="studentId" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="">Select Student</option>
                            <c:forEach items="${students}" var="student">
                                <option value="${student.studentId}" ${attendance.studentId == student.studentId ? 'selected' : ''}>${student.firstName} ${student.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Course</label>
                        <select name="courseId" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="">Select Course</option>
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.courseId}" ${attendance.courseId == course.courseId ? 'selected' : ''}>${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Attendance Date</label>
                        <input type="date" name="attendanceDate" required value="<fmt:formatDate value='${attendance.attendanceDate}' pattern='yyyy-MM-dd'/>" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                        <select name="status" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="Present" ${attendance.status == 'Present' ? 'selected' : ''}>Present</option>
                            <option value="Absent" ${attendance.status == 'Absent' ? 'selected' : ''}>Absent</option>
                            <option value="Late" ${attendance.status == 'Late' ? 'selected' : ''}>Late</option>
                            <option value="Excused" ${attendance.status == 'Excused' ? 'selected' : ''}>Excused</option>
                        </select>
                    </div>
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-1">Remarks</label>
                        <textarea name="remarks" rows="2" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">${attendance.remarks}</textarea>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">Reset</button>
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded">Update Attendance</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
