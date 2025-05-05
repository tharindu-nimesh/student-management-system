<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Teacher - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
    <!-- Main Content -->
    <main class="flex-1 p-8">
        <div class="max-w-2xl mx-auto bg-white rounded-lg shadow p-8">
            <h2 class="text-2xl font-bold mb-6">Edit Teacher</h2>
            <form action="${pageContext.request.contextPath}/teachers/update" method="post" class="space-y-6">
                <input type="hidden" name="teacherId" value="${teacher.teacherId}">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">First Name</label>
                        <input type="text" name="firstName" required value="${teacher.firstName}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Last Name</label>
                        <input type="text" name="lastName" required value="${teacher.lastName}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                        <input type="email" name="email" required value="${teacher.email}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Phone</label>
                        <input type="text" name="phone" value="${teacher.phone}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Department</label>
                        <input type="text" name="department" required value="${teacher.department}" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Hire Date</label>
                        <input type="date" name="hireDate" required value="<fmt:formatDate value='${teacher.hireDate}' pattern='yyyy-MM-dd'/>" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                        <select name="status" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="Active" ${teacher.status == 'Active' ? 'selected' : ''}>Active</option>
                            <option value="Inactive" ${teacher.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                            <option value="On Leave" ${teacher.status == 'On Leave' ? 'selected' : ''}>On Leave</option>
                        </select>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">Reset</button>
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded">Update Teacher</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
