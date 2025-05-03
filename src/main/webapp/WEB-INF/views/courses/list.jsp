<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses - Frontier Institute</title>
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
                <h1 class="text-2xl font-bold">Courses</h1>
                <p class="text-gray-500">Manage all courses</p>
            </div>
            <a href="${pageContext.request.contextPath}/courses/new" class="bg-red-600 hover:bg-red-700 text-white px-6 py-2 rounded font-semibold flex items-center">
                <i class="fa fa-plus mr-2"></i> Add Course
            </a>
        </div>
        <div class="bg-white rounded-lg shadow p-6">
            <table class="min-w-full divide-y divide-gray-200">
                <thead>
                <tr>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Code</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Teacher</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Credits</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Semester</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courses}" var="course">
                    <tr class="border-t">
                        <td class="px-4 py-3">${course.courseCode}</td>
                        <td class="px-4 py-3">${course.courseName}</td>
                        <td class="px-4 py-3">${course.teacherName}</td>
                        <td class="px-4 py-3">${course.credits}</td>
                        <td class="px-4 py-3">${course.semester}</td>
                        <td class="px-4 py-3">
                            <span class="px-2 py-1 rounded-full text-xs
                                <c:choose>
                                    <c:when test="${course.status == 'Active'}">bg-green-100 text-green-800</c:when>
                                    <c:otherwise>bg-yellow-100 text-yellow-800</c:otherwise>
                                </c:choose>">
                                    ${course.status}
                            </span>
                        </td>
                        <td class="px-4 py-3">
                            <a href="${pageContext.request.contextPath}/courses/edit?id=${course.courseId}" class="text-blue-600 hover:underline mr-3"><i class="fa fa-edit"></i> Edit</a>
                            <a href="${pageContext.request.contextPath}/courses/delete?id=${course.courseId}" class="text-red-600 hover:underline" onclick="return confirm('Delete this course?');"><i class="fa fa-trash"></i> Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty courses}">
                    <tr>
                        <td colspan="7" class="text-center py-4 text-gray-400">No courses found.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
