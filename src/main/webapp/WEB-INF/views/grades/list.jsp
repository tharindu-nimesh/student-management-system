<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grades - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
    <!-- Main Content -->
    <main class="flex-1 p-8">
        <div class="flex justify-between items-center mb-6">
            <div>
                <h1 class="text-2xl font-bold">Grades</h1>
                <p class="text-gray-500">View and manage all grades</p>
            </div>
            <a href="${pageContext.request.contextPath}/grades/new" class="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded font-semibold flex items-center">
                <i class="fa fa-plus mr-2"></i> Add Grade
            </a>
        </div>
        <div class="bg-white rounded-lg shadow p-6">
            <table class="min-w-full divide-y divide-gray-200">
                <thead>
                <tr>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Student</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Course</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Assignment</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Grade</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Date</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${grades}" var="grade">
                    <tr class="border-t">
                        <td class="px-4 py-3">${grade.studentName}</td>
                        <td class="px-4 py-3">${grade.courseName}</td>
                        <td class="px-4 py-3">${grade.assignmentName}</td>
                        <td class="px-4 py-3">
                            <span class="font-semibold">${grade.gradeValue}</span> / ${grade.maxGrade}
                            <span class="ml-2 text-xs text-gray-500">(${grade.percentage}%)
                        </td>
                        <td class="px-4 py-3">
                            <fmt:formatDate value="${grade.gradeDate}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="px-4 py-3">
                            <a href="${pageContext.request.contextPath}/grades/edit?id=${grade.gradeId}" class="text-blue-600 hover:underline mr-3"><i class="fa fa-edit"></i> Edit</a>
                            <a href="${pageContext.request.contextPath}/grades/delete?id=${grade.gradeId}" class="text-red-600 hover:underline" onclick="return confirm('Delete this grade?');"><i class="fa fa-trash"></i> Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty grades}">
                    <tr>
                        <td colspan="6" class="text-center py-4 text-gray-400">No grades found.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
