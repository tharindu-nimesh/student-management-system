<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-red-50 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-lg rounded-xl p-10 text-center max-w-md w-full">
    <h1 class="text-3xl font-bold text-red-600 mb-4">Oops!</h1>
    <p class="text-gray-700 mb-6">
        ${empty errorMessage ? "Something went wrong during the registration process." : errorMessage}
    </p>
    <div class="flex justify-center space-x-4">
        <a href="javascript:history.back()"
           class="text-white bg-red-600 hover:bg-red-700 px-6 py-2 rounded transition">
            Try Again
        </a>
        <a href="${pageContext.request.contextPath}/teachers"
           class="text-white bg-gray-600 hover:bg-gray-700 px-6 py-2 rounded transition">
            Back to Teachers
        </a>
    </div>
</div>
</body>
</html>
