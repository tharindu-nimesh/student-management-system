<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-50 min-h-screen font-sans">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>

    <div class="flex-1 flex flex-col overflow-auto">
        <main class="flex-1 p-4 sm:p-6 lg:p-8 max-w-7xl mx-auto w-full">
            <!-- Page Header -->
            <div class="mb-6 sm:mb-8">
                <h1 class="text-xl sm:text-2xl lg:text-3xl font-bold text-gray-900">Settings</h1>
                <p class="text-sm sm:text-base text-gray-500 mt-1">Manage your profile and institute settings</p>
            </div>

            <!-- Admin Profile Card -->
            <div class="bg-white rounded-xl shadow-sm p-4 sm:p-6 mb-6 sm:mb-8">
                <h2 class="text-lg sm:text-xl font-semibold text-gray-800 mb-4">Admin Profile</h2>
                <form method="post" action="${pageContext.request.contextPath}/settings" class="grid grid-cols-1 sm:grid-cols-2 gap-4 sm:gap-6">
                    <input type="hidden" name="action" value="updateProfile" />

                    <div>
                        <label class="block text-sm font-medium text-gray-700">User Name</label>
                        <input type="text" name="adminName" value="${settings.adminName}" class="mt-1 block w-full px-3 sm:px-4 py-2 border border-gray-200 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 text-sm sm:text-base transition duration-150" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Email</label>
                        <input type="email" name="adminEmail" value="${settings.adminEmail}" class="mt-1 block w-full px-3 sm:px-4 py-2 border border-gray-200 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 text-sm sm:text-base transition duration-150" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Phone</label>
                        <input type="text" name="adminPhone" value="${settings.adminPhone}" class="mt-1 block w-full px-3 sm:px-4 py-2 border border-gray-200 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 text-sm sm:text-base transition duration-150" required>
                    </div>

                    <div class="flex items-end">
                        <button type="submit" class="mt-4 w-full sm:w-auto bg-blue-600 hover:bg-blue-700 text-white px-4 sm:px-6 py-2 rounded-lg font-semibold text-sm sm:text-base transition duration-150 flex items-center justify-center">
                            <i class="fa fa-save mr-2"></i> Save Changes
                        </button>
                    </div>
                </form>
            </div>

            <!-- Password Update Card -->
            <div class="bg-white rounded-xl shadow-sm p-4 sm:p-6 mb-6 sm:mb-8">
                <h2 class="text-lg sm:text-xl font-semibold text-gray-800 mb-4">Change Password</h2>
                <form method="post" action="${pageContext.request.contextPath}/settings" class="grid grid-cols-1 sm:grid-cols-2 gap-4 sm:gap-6">
                    <input type="hidden" name="action" value="updatePassword" />

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Admin Username</label>
                        <input type="text" name="adminUsername" class="mt-1 block w-full px-3 sm:px-4 py-2 border border-gray-200 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 text-sm sm:text-base transition duration-150" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700">New Password</label>
                        <input type="password" name="newPassword" class="mt-1 block w-full px-3 sm:px-4 py-2 border border-gray-200 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 text-sm sm:text-base transition duration-150" required>
                    </div>

                    <div class="flex items-end">
                        <button type="submit" class="mt-4 w-full sm:w-auto bg-blue-600 hover:bg-blue-700 text-white px-4 sm:px-6 py-2 rounded-lg font-semibold text-sm sm:text-base transition duration-150 flex items-center justify-center">
                            <i class="fa fa-lock mr-2"></i> Update Password
                        </button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>