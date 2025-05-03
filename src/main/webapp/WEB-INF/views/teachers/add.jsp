<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Teacher - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="flex min-h-screen">
    <!-- Sidebar (same as list.jsp) -->
    <aside class="w-64 bg-[#221E87] flex flex-col justify-between">
        <div>
            <div class="bg-red-600 flex items-center px-6 py-4">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="h-12 mr-2">
                <span class="text-white font-bold text-lg">FRONTIER INSTITUTE</span>
            </div>
            <nav class="mt-2">
                <a href="${pageContext.request.contextPath}/dashboard" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-chart-bar w-6"></i> <span class="ml-3">Dashboard</span>
                </a>
                <a href="${pageContext.request.contextPath}/students" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab]">
                    <i class="fa fa-user-graduate w-6"></i> <span class="ml-3">Students</span>
                </a>
                <a href="${pageContext.request.contextPath}/teachers" class="flex items-center px-6 py-3 bg-[#2a3bab] text-white">
                    <i class="fa fa-chalkboard-teacher w-6"></i> <span class="ml-3">Teachers</span>
                </a>
                <!-- ... other nav items ... -->
            </nav>
        </div>
        <div class="px-6 py-4">
            <a href="${pageContext.request.contextPath}/auth/logout" class="block w-full text-center bg-white text-[#221E87] font-semibold py-2 rounded hover:bg-gray-200">Log Out</a>
        </div>
    </aside>
    <!-- Main Content -->
    <main class="flex-1 p-8">
        <div class="max-w-2xl mx-auto bg-white rounded-lg shadow p-8">
            <h2 class="text-2xl font-bold mb-6">Add New Teacher</h2>
            <form action="${pageContext.request.contextPath}/teachers/add" method="post" class="space-y-6">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">First Name</label>
                        <input type="text" name="firstName" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Last Name</label>
                        <input type="text" name="lastName" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                        <input type="email" name="email" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Phone</label>
                        <input type="text" name="phone" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Department</label>
                        <input type="text" name="department" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Hire Date</label>
                        <input type="date" name="hireDate" required class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
                        <select name="status" class="w-full px-4 py-2 rounded border border-gray-300 focus:ring-2 focus:ring-[#3047D5]">
                            <option value="Active" selected>Active</option>
                            <option value="Inactive">Inactive</option>
                            <option value="On Leave">On Leave</option>
                        </select>
                    </div>
                </div>
                <div class="flex justify-end">
                    <button type="reset" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded mr-3">Reset</button>
                    <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded">Save Teacher</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
