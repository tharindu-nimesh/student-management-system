<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Course - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>
    <!-- Main Content -->
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Add New Course</h1>
                    <p class="text-sm text-gray-600">Create a new Course record</p>
                </div>

                <div>
                    <a href="${pageContext.request.contextPath}/courses" class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded-md transition duration-300">
                        <i class="fas fa-arrow-left mr-2"></i> Back to List
                    </a>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto p-6 bg-gray-100">
            <div class="max-w-3xl mx-auto bg-white rounded-lg shadow p-6">
                <form action="${pageContext.request.contextPath}/courses/add" method="post" class="space-y-6">
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Course Code</label>
                            <input type="text" name="courseCode" required
                                   class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Course Name</label>
                            <input type="text" name="courseName" required
                                   class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Teacher</label>
                            <select name="teacherId"
                                    class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                                <option value="">Select Teacher</option>
                                <c:forEach items="${teachers}" var="teacher">
                                    <option value="${teacher.teacherId}">${teacher.firstName} ${teacher.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Credits</label>
                            <input type="number" name="credits" required
                                   class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Semester</label>
                            <input type="text" name="semester" required
                                   class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                            <select name="status"
                                    class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                                <option value="Active" selected>Active</option>
                                <option value="Inactive">Inactive</option>
                                <option value="Upcoming">Upcoming</option>
                            </select>
                        </div>
                        <div class="md:col-span-2">
                            <label class="block text-sm font-medium text-gray-700 mb-1">Description</label>
                            <textarea name="description" rows="2"
                                      class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]"></textarea>
                        </div>
                    </div>
                    <div class="flex justify-end">
                        <button type="reset"
                                class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">
                            Reset
                        </button>
                        <button type="submit"
                                class="bg-[#3047D5] hover:bg-[#2536a9] text-white font-semibold py-2 px-6 rounded">
                            Save Course
                        </button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>
