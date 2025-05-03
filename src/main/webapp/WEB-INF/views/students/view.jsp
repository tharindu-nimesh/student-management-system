<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>View Student - Student Management System</title>
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
<body class="bg-gray-100 min-h-screen">
<div class="flex h-screen">
  <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
  <!-- Main Content -->
  <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
    <!-- Top Navbar -->
    <header class="bg-white shadow">
      <div class="flex items-center justify-between p-4">
        <div>
          <h1 class="text-xl font-bold">View Student</h1>
          <p class="text-sm text-gray-600">Student details</p>
        </div>

        <div>
          <a href="${pageContext.request.contextPath}/students" class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-2 px-4 rounded-md transition duration-300 mr-2">
            <i class="fas fa-arrow-left mr-2"></i> Back to List
          </a>
          <a href="${pageContext.request.contextPath}/students/edit?id=${student.studentId}" class="bg-primary hover:bg-primary-dark text-white font-semibold py-2 px-4 rounded-md transition duration-300">
            <i class="fas fa-edit mr-2"></i> Edit Student
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

        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <p class="text-sm text-gray-500">Student ID</p>
            <p class="text-lg font-medium">${student.studentId}</p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Name</p>
            <p class="text-lg font-medium">${student.firstName} ${student.lastName}</p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Email</p>
            <p class="text-lg font-medium">${student.email}</p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Phone</p>
            <p class="text-lg font-medium">${student.phone}</p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Date of Birth</p>
            <p class="text-lg font-medium"><fmt:formatDate value="${student.dateOfBirth}" pattern="dd/MM/yyyy" /></p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Enrollment Date</p>
            <p class="text-lg font-medium"><fmt:formatDate value="${student.enrollmentDate}" pattern="dd/MM/yyyy" /></p>
          </div>
          <div>
            <p class="text-sm text-gray-500">Status</p>
            <p class="text-lg font-medium">
                                <span class="px-2 py-1 rounded-full text-xs
                                      ${student.status == 'Active' ? 'bg-green-100 text-green-800' :
                                        student.status == 'Inactive' ? 'bg-red-100 text-red-800' :
                                        student.status == 'Graduated' ? 'bg-blue-100 text-blue-800' : 'bg-yellow-100 text-yellow-800'}">
                                  ${student.status}
                                </span>
            </p>
          </div>
          <div class="md:col-span-2">
            <p class="text-sm text-gray-500">Address</p>
            <p class="text-lg font-medium">${student.address}</p>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>
</body>
</html>
