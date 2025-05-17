<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>

    <div class="flex-1 flex flex-col overflow-hidden">
        <main class="flex-1 p-8">
            <!-- Page Header -->
            <div class="mb-8">
                <h1 class="text-2xl font-bold">Settings</h1>
                <p class="text-gray-500">Manage your profile and institute settings</p>
            </div>

            <!-- Admin Profile Card -->
            <div class="bg-white rounded-lg shadow p-6 mb-8">
                <h2 class="text-xl font-semibold mb-4">Admin Profile</h2>
                <form method="post" action="${pageContext.request.contextPath}/settings" class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <input type="hidden" name="action" value="updateProfile" />

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Name</label>
                        <input type="text" name="adminName" value="${settings.adminName}" class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-600 focus:border-blue-600" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Email</label>
                        <input type="email" name="adminEmail" value="${settings.adminEmail}" class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-600 focus:border-blue-600" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700">Phone</label>
                        <input type="text" name="adminPhone" value="${settings.adminPhone}" class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-600 focus:border-blue-600" required>
                    </div>

                    <div class="flex items-end">
                        <button type="submit" class="mt-4 bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded font-semibold w-full">
                            <i class="fa fa-save mr-2"></i> Save Changes
                        </button>
                    </div>
                </form>
            </div>

            <!-- Password Update Card -->
            <div class="bg-white rounded-lg shadow p-6 mb-8">
                <h2 class="text-xl font-semibold mb-4">Change Password</h2>
                <form method="post" action="${pageContext.request.contextPath}/settings" class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <input type="hidden" name="action" value="updatePassword" />

                    <div>
                        <label class="block text-sm font-medium text-gray-700">New Password</label>
                        <input type="password" name="newPassword" class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-600 focus:border-blue-600" required>
                    </div>

                    <div class="flex items-end">
                        <button type="submit" class="mt-4 bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded font-semibold w-full">
                            <i class="fa fa-lock mr-2"></i> Update Password
                        </button>
                    </div>
                </form>
            </div>

            <!-- Institute Info Card -->
            <div class="bg-white rounded-lg shadow p-6">
                <h2 class="text-xl font-semibold mb-4">Institute Information</h2>
                <p><strong>Name:</strong> ${settings.instituteName}</p>
                <p><strong>Academic Year:</strong> ${settings.academicYear}</p>
                <div class="mt-4">
                    <img src="${pageContext.request.contextPath}/images/logo.png"  alt="Logo" class="w-32 border rounded shadow" />
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
