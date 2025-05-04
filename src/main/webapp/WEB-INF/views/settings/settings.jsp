<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="bg-gray-100 min-h-screen">
<div class="flex min-h-screen">

    <%@ include file="/WEB-INF/views/components/sidebar.jsp" %>
    <div class="flex-1 flex flex-col overflow-hidden ml-15 md:ml-50">
    <!-- Main Content Area -->
    <div class="flex-1 flex flex-col p-6">
        <h1 class="text-2xl font-bold mb-4">Settings</h1>
        <p>This is the Settings page</p>
    </div>
</div>
</body>
</html>
