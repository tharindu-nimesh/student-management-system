<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Student Management System</title>
    <script src="https://cdn.tailwindcss.com"></script>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100">
<div class="flex h-screen">
    <%@ include file="components/sidebar.jsp" %>
    <!-- Main Content -->
    <div class="flex-1 flex flex-col overflow-hidden">
        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Admin Dashboard</h1>
                    <p class="text-sm text-gray-600">Welcome, ${user.username}</p>
                </div>

                <div class="relative">
                    <input type="text" placeholder="Search" class="pl-10 pr-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                    <i class="fas fa-search absolute left-3 top-3 text-gray-400"></i>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto p-6 bg-gray-100">
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                <!-- Students Card -->
                <div class="bg-white rounded-lg shadow p-6 flex items-center">
                    <div class="rounded-full bg-green-100 p-4">
                        <i class="fas fa-user-graduate text-2xl text-green-600"></i>
                    </div>
                    <div class="ml-4">
                        <h3 class="text-gray-600">Students</h3>
                        <p class="text-2xl font-bold">${studentCount}</p>
                    </div>
                </div>

                <!-- Teachers Card -->
                <div class="bg-white rounded-lg shadow p-6 flex items-center">
                    <div class="rounded-full bg-blue-100 p-4">
                        <i class="fas fa-chalkboard-teacher text-2xl text-blue-600"></i>
                    </div>
                    <div class="ml-4">
                        <h3 class="text-gray-600">Teachers</h3>
                        <p class="text-2xl font-bold">${teacherCount}</p>
                    </div>
                </div>

                <!-- Courses Card -->
                <div class="bg-white rounded-lg shadow p-6 flex items-center">
                    <div class="rounded-full bg-yellow-100 p-4">
                        <i class="fas fa-book text-2xl text-yellow-600"></i>
                    </div>
                    <div class="ml-4">
                        <h3 class="text-gray-600">Courses</h3>
                        <p class="text-2xl font-bold">${courseCount}</p>
                    </div>
                </div>
            </div>

            <!-- Recent Students and Activities Section -->
            <div class="grid grid-cols-1 lg:grid-cols-2 gap-6 mt-8">
                <!-- Recent Students -->
                <div class="bg-white rounded-lg shadow">
                    <div class="p-4 border-b flex justify-between items-center">
                        <h2 class="text-lg font-semibold">Recent Students</h2>
                        <a href="${pageContext.request.contextPath}/students" class="text-blue-600 hover:underline text-sm">View All</a>
                    </div>
                    <div class="p-4">
                        <table class="w-full">
                            <thead>
                            <tr class="text-left text-gray-500">
                                <th class="pb-3">Name</th>
                                <th class="pb-3">ID</th>
                                <th class="pb-3">Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${recentStudents}" var="student">
                                <tr class="border-t">
                                    <td class="py-3">
                                        <div class="flex items-center">
                                            <div class="w-8 h-8 bg-gray-200 rounded-full mr-3 flex items-center justify-center text-gray-700">
                                                    ${fn:substring(student.firstName, 0, 1)}${fn:substring(student.lastName, 0, 1)}
                                            </div>
                                            <span>${student.firstName} ${student.lastName}</span>
                                        </div>
                                    </td>
                                    <td class="py-3">${student.studentId}</td>
                                    <td class="py-3">
                                        <span class="px-2 py-1 rounded-full text-xs ${student.status == 'Active' ? 'bg-green-100 text-green-800' : 'bg-yellow-100 text-yellow-800'}">${student.status}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Recent Activities -->
                <div class="bg-white rounded-lg shadow">
                    <div class="p-4 border-b">
                        <h2 class="text-lg font-semibold">Recent Activities</h2>
                    </div>
                    <div class="p-4">
                        <ul class="space-y-4">
                            <c:forEach items="${recentActivities}" var="activity">
                                <li class="flex">
                                    <div class="flex-shrink-0 w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center mr-3">
                                        <i class="fas ${activity.icon} text-blue-600"></i>
                                    </div>
                                    <div>
                                        <p class="font-medium">${activity.description}</p>
                                        <p class="text-sm text-gray-500">${activity.timeAgo}</p>
                                    </div>
                                </li>
                            </c:forEach>

                            <!-- Sample data if no recent activities -->
                            <c:if test="${empty recentActivities}">
                                <li class="flex">
                                    <div class="flex-shrink-0 w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center mr-3">
                                        <i class="fas fa-user-plus text-blue-600"></i>
                                    </div>
                                    <div>
                                        <p class="font-medium">New student registered</p>
                                        <p class="text-sm text-gray-500">2 hours ago</p>
                                    </div>
                                </li>
                                <li class="flex">
                                    <div class="flex-shrink-0 w-10 h-10 rounded-full bg-green-100 flex items-center justify-center mr-3">
                                        <i class="fas fa-book text-green-600"></i>
                                    </div>
                                    <div>
                                        <p class="font-medium">New course added</p>
                                        <p class="text-sm text-gray-500">4 hours ago</p>
                                    </div>
                                </li>
                                <li class="flex">
                                    <div class="flex-shrink-0 w-10 h-10 rounded-full bg-red-100 flex items-center justify-center mr-3">
                                        <i class="fas fa-user-check text-red-600"></i>
                                    </div>
                                    <div>
                                        <p class="font-medium">Attendance updated</p>
                                        <p class="text-sm text-gray-500">Yesterday</p>
                                    </div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
