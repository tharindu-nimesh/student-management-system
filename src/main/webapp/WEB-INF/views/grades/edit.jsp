<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Grade - Nimna Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
        <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
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
                    <button type="submit" class="bg-[#3047D5] hover:bg-[#2536a9] text-white font-semibold py-2 px-6 rounded">Update Grade</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
