<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grades - Nimna Institute</title>
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
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>
    <!-- Main Content -->
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Grades</h1>
                    <p class="text-sm text-gray-600">View and manage all grades</p>
                </div>

                <div class="flex">
                    <div class="relative mr-4">
                        <form action="${pageContext.request.contextPath}/grades/search" method="get">
                            <input type="text" name="searchTerm" value="${searchTerm}" placeholder="Search students..."
                                   class="pl-10 pr-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                            <button type="submit" class="absolute inset-y-0 left-0 flex items-center pl-3">
                                <i class="fas fa-search text-gray-400"></i>
                            </button>
                        </form>
                    </div>
                    <a href="${pageContext.request.contextPath}/grades/new"
                       class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-md transition duration-300">
                        <i class="fas fa-plus mr-2"></i> Add Grade
                    </a>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto p-6 bg-gray-100">
            <div class="bg-white shadow rounded-lg overflow-hidden">
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
                                <a href="${pageContext.request.contextPath}/grades/edit?id=${grade.gradeId}"
                                   class="text-blue-600 hover:underline mr-3"><i class="fa fa-edit"></i> Edit</a>
                                <a href="${pageContext.request.contextPath}/grades/delete?id=${grade.gradeId}"
                                   class="text-red-600 hover:underline" onclick="return confirm('Delete this grade?');"><i
                                        class="fa fa-trash"></i> Delete</a>
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
