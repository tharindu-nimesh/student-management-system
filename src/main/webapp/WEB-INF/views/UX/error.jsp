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

    <!-- Dynamic error message -->
    <p class="text-gray-700 mb-6">
        ${empty errorMessage ? "Something went wrong during the operation." : errorMessage}
    </p>

    <!-- Dynamic return links -->
    <div class="flex justify-center space-x-4">
        <!-- Primary action button -->
        <a href="${empty returnUrl ? 'javascript:history.back()' : returnUrl}"
           class="text-white bg-red-600 hover:bg-red-700 px-6 py-2 rounded transition">
            ${empty returnLabel ? "Try Again" : returnLabel}
        </a>

        <!-- Secondary action button (optional) -->
        <% if (request.getAttribute("secondaryUrl") != null) { %>
        <a href="${secondaryUrl}"
           class="text-white bg-gray-600 hover:bg-gray-700 px-6 py-2 rounded transition">
            ${empty secondaryLabel ? "Back to Dashboard" : secondaryLabel}
        </a>
        <% } %>
    </div>
</div>
</body>
</html>
