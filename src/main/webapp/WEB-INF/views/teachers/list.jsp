<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teachers - Nimna Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: {
                            DEFAULT: '#3047D5',
                            dark: '#2a3bab',
                        },
                    },
                },
            },
        }
    </script>

</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">

        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Teachers</h1>
                    <p class="text-sm text-gray-600">Manage all teachers</p>
                </div>

                <div class="flex">
                    <div class="relative mr-4">
                        <form action="${pageContext.request.contextPath}/teachers/search" method="get">
                            <input type="text" name="searchTerm" value="${searchTerm}" placeholder="Search teachers..."
                                   class="pl-10 pr-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                            <button type="submit" class="absolute inset-y-0 left-0 flex items-center pl-3">
                                <i class="fas fa-search text-gray-400"></i>
                            </button>
                        </form>
                    </div>
                    <a href="${pageContext.request.contextPath}/teachers/new"
                       class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-md transition duration-300">
                        <i class="fas fa-plus mr-2"></i> Add Teacher
                    </a>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto p-6 bg-gray-100">
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
                                <a href="${pageContext.request.contextPath}/teachers/edit?id=${teacher.teacherId}"
                                   class="text-blue-600 hover:underline mr-3"><i class="fa fa-edit"></i> Edit</a>
                                <a href="${pageContext.request.contextPath}/teachers/delete?id=${teacher.teacherId}"
                                   class="text-red-600 hover:underline"
                                   onclick="return confirm('Delete this teacher?');"><i class="fa fa-trash"></i> Delete</a>
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
