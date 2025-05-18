<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Details</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">
    <%@ include file="/WEB-INF/views/components/sidebar.jsp"%>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
        <!-- Main Content -->
        <main class="flex-1 p-8">
            <div class="mb-8">
                <h1 class="text-2xl font-bold text-gray-800">Admin Details</h1>
                <p class="text-gray-600">Overview of your account information</p>
            </div>

            <div class="bg-white rounded-lg shadow p-6 space-y-4">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <h2 class="text-gray-500 text-sm">Admin Name</h2>
                        <p class="text-lg font-medium text-gray-900">${settings.adminName}</p>
                    </div>
                    <div>
                        <h2 class="text-gray-500 text-sm">Admin Email</h2>
                        <p class="text-lg font-medium text-gray-900">${settings.adminEmail}</p>
                    </div>
                    <div>
                        <h2 class="text-gray-500 text-sm">Admin Phone</h2>
                        <p class="text-lg font-medium text-gray-900">${settings.adminPhone}</p>
                    </div>
                    <div>
                        <h2 class="text-gray-500 text-sm">Admin ID</h2>
                        <p class="text-lg font-medium text-gray-900">${settings.id}</p>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</body>

</html>