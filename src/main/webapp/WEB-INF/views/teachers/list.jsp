<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teachers - Frontier Institute</title>
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
                <h1 class="text-2xl font-bold">Teachers</h1>
                <p class="text-gray-500">Manage all teachers</p>
            </div>
            <a href="${pageContext.request.contextPath}/teachers/new" class="bg-red-600 hover:bg-red-700 text-white px-6 py-2 rounded font-semibold flex items-center">
                <i class="fa fa-plus mr-2"></i> Add Teacher
            </a>
        </div>
        <div class="bg-white rounded-lg shadow p-6">
            <table class="min-w-full divide-y divide-gray-200">
                <thead>
                <tr>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Email</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Department</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
                    <th class="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teachers}" var="teacher">
                    <tr class="border-t">
                        <td class="px-4 py-3">${teacher.teacherId}</td>
                        <td class="px-4 py-3">${teacher.firstName} ${teacher.lastName}</td>
                        <td class="px-4 py-3">${teacher.email}</td>
                        <td class="px-4 py-3">${teacher.department}</td>
                        <td class="px-4 py-3">
                                <span class="px-2 py-1 rounded-full text-xs
                                    <c:choose>
                                        <c:when test="${teacher.status == 'Active'}">bg-green-100 text-green-800</c:when>
                                        <c:otherwise>bg-yellow-100 text-yellow-800</c:otherwise>
                                    </c:choose>">
                                        ${teacher.status}
                                </span>
                        </td>
                        <td class="px-4 py-3">
                            <a href="${pageContext.request.contextPath}/teachers/edit?id=${teacher.teacherId}" class="text-blue-600 hover:underline mr-3"><i class="fa fa-edit"></i> Edit</a>
                            <a href="${pageContext.request.contextPath}/teachers/delete?id=${teacher.teacherId}" class="text-red-600 hover:underline" onclick="return confirm('Delete this teacher?');"><i class="fa fa-trash"></i> Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty teachers}">
                    <tr>
                        <td colspan="6" class="text-center py-4 text-gray-400">No teachers found.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
