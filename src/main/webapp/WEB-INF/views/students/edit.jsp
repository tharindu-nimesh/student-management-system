<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student - Student Management System</title>
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
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <!-- Main Content -->
    <div class="flex-1 flex flex-col overflow-hidden">
        <!-- Top Navbar -->
        <header class="bg-white shadow">
            <div class="flex items-center justify-between p-4">
                <div>
                    <h1 class="text-xl font-bold">Edit Student</h1>
                    <p class="text-sm text-gray-600">Update student information</p>
                </div>

                <div>
                    <a href="${pageContext.request.contextPath}/students" class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded-md transition duration-300">
                        <i class="fas fa-arrow-left mr-2"></i> Back to List
                    </a>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto p-6 bg-gray-100">
            <div class="max-w-3xl mx-auto bg-white rounded-lg shadow p-6">
                <c:if test="${not empty errorMessage}">
                    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                            ${errorMessage}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/students/update" method="post" class="space-y-6">
                    <input type="hidden" name="studentId" value="${student.studentId}">
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label for="firstName" class="block text-sm font-medium text-gray-700 mb-1">First Name</label>
                            <input type="text" id="firstName" name="firstName" required value="${student.firstName}"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="lastName" class="block text-sm font-medium text-gray-700 mb-1">Last Name</label>
                            <input type="text" id="lastName" name="lastName" required value="${student.lastName}"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                            <input type="email" id="email" name="email" required value="${student.email}"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="phone" class="block text-sm font-medium text-gray-700 mb-1">Phone</label>
                            <input type="tel" id="phone" name="phone" value="${student.phone}"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="dateOfBirth" class="block text-sm font-medium text-gray-700 mb-1">Date of Birth</label>
                            <input type="date" id="dateOfBirth" name="dateOfBirth" required value="<fmt:formatDate value='${student.dateOfBirth}' pattern='yyyy-MM-dd'/>"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="address" class="block text-sm font-medium text-gray-700 mb-1">Address</label>
                            <input type="text" id="address" name="address" value="${student.address}"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="enrollmentDate" class="block text-sm font-medium text-gray-700 mb-1">Enrollment Date</label>
                            <input type="date" id="enrollmentDate" name="enrollmentDate" required value="<fmt:formatDate value='${student.enrollmentDate}' pattern='yyyy-MM-dd'/>"
                                   class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                        </div>

                        <div>
                            <label for="status" class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                            <select id="status" name="status" required
                                    class="w-full px-4 py-2 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary">
                                <option value="Active" ${student.status == 'Active' ? 'selected' : ''}>Active</option>
                                <option value="Inactive" ${student.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                                <option value="Graduated" ${student.status == 'Graduated' ? 'selected' : ''}>Graduated</option>
                                <option value="On Leave" ${student.status == 'On Leave' ? 'selected' : ''}>On Leave</option>
                            </select>
                        </div>
                    </div>

                    <div class="flex justify-end">
                        <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-md mr-3">
                            Reset
                        </button>
                        <button type="submit" class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-md">
                            Update Student
                        </button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

<script>
    // Client-side validation
    document.querySelector('form').addEventListener('submit', function(e) {
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            e.preventDefault();
            alert('Please enter a valid email address');
            return;
        }

        // Phone validation (simple format check)
        const phoneRegex = /^[0-9\-\+\s\(\)]{10,15}$/;
        if (phone && !phoneRegex.test(phone)) {
            e.preventDefault();
            alert('Please enter a valid phone number');
            return;
        }
    });
</script>
</body>
</html>
