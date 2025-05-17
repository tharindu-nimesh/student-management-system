<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grades - Frontier Institute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
        <!-- Main Content -->
        <main class="flex-1 p-8">
            <div class="flex justify-between items-center mb-6">
                <div>
                    <h1 class="text-2xl font-bold">Settings</h1>
                    <p class="text-gray-500">Customize your preferences</p>
                </div>
            </div>

            <div class="space-y-6">

                <!-- Theme Switch -->
                <div class="bg-white shadow rounded-lg p-6">
                    <h2 class="text-lg font-semibold mb-3">Theme</h2>
                    <div class="flex items-center space-x-6">
                        <label class="flex items-center">
                            <input type="radio" name="theme" class="mr-2">
                            Light
                        </label>
                        <label class="flex items-center">
                            <input type="radio" name="theme" class="mr-2">
                            Dark
                        </label>
                    </div>
                </div>



                <!-- Notifications -->
                <div class="bg-white shadow rounded-lg p-6">
                    <h2 class="text-lg font-semibold mb-3">Notifications</h2>
                    <label class="flex items-center">
                        <input type="checkbox" class="mr-2">
                        Enable notifications
                    </label>
                </div>


                <!-- Two-Factor Auth -->
                <div class="bg-white shadow rounded-lg p-6">
                    <h2 class="text-lg font-semibold mb-3">Two-Factor Authentication</h2>
                    <label class="flex items-center">
                        <input type="checkbox" class="mr-2">
                        Enable 2FA
                    </label>
                </div>


                <!-- Backup Option -->
                <div class="bg-white shadow rounded-lg p-6">
                    <h2 class="text-lg font-semibold mb-3">Data Backup</h2>
                    <button class="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700">
                        Backup Now
                    </button>
                </div>

            </div>
        </main>
    </div>
</div>
</body>

</html>
