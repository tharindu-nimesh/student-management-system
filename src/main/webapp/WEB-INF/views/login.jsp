<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Student Management System</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: {
                            light: '#4969f5',
                            DEFAULT: '#3047D5',
                            dark: '#2a3bab',
                        },
                    },
                },
            },
        }
    </script>
    <style>
        .login-container {
            min-height: 100vh;
            background-image: linear-gradient(to right, rgba(0, 0, 0, 0.5), rgba(42, 59, 171, 0.8)), url('${pageContext.request.contextPath}/images/students.jpg');
            background-size: cover;
            background-position: center;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="flex flex-row login-container">
    <!-- Left Section (Logo and Welcome) -->
    <div class="w-3/5 flex flex-col justify-center items-center px-8">
        <div class="text-center">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Frontier Institute Logo" class="w-32 h-32 mx-auto">
            <h1 class="text-white text-5xl font-bold mt-4">FRONTIER INSTITUTE</h1>
            <h2 class="text-white text-6xl font-bold mt-10">WELCOME</h2>
            <p class="text-white text-xl mt-4">Student Management System</p>
        </div>
    </div>

    <!-- Right Section (Login Form) -->
    <div class="w-2/5 bg-primary-dark flex items-center justify-center">
        <div class="bg-gray-200 p-8 rounded-lg shadow-lg w-4/5 max-w-md">
            <h3 class="text-2xl font-bold mb-6 text-center">Sign In</h3>

            <%-- Error Message Display --%>
            <c:if test="${not empty errorMessage}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                        ${errorMessage}
                </div>
            </c:if>

            <!-- Login Form -->
            <form action="${pageContext.request.contextPath}/auth/login" method="POST" class="space-y-6">
                <!-- Username -->
                <div>
                    <label for="username" class="block text-sm font-medium text-gray-700 mb-1">Username</label>
                    <input
                            type="text"
                            id="username"
                            name="username"
                            required
                            class="w-full px-4 py-3 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary"
                    >
                </div>

                <!-- Password -->
                <div>
                    <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Password</label>
                    <input
                            type="password"
                            id="password"
                            name="password"
                            required
                            class="w-full px-4 py-3 rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary"
                    >
                </div>

                <!-- Remember Me & Forgot Password -->
                <div class="flex items-center justify-between">
                    <div class="flex items-center">
                        <input
                                id="remember-me"
                                name="remember-me"
                                type="checkbox"
                                class="h-4 w-4 text-primary focus:ring-primary border-gray-300 rounded"
                        >
                        <label for="remember-me" class="ml-2 block text-sm text-gray-700">
                            Remember me
                        </label>
                    </div>
                    <div class="text-sm">
                        <a href="#" class="text-primary-light hover:underline">Forgot password?</a>
                    </div>
                </div>

                <!-- Submit Button -->
                <button
                        type="submit"
                        class="w-full bg-red-600 hover:bg-red-700 text-white font-semibold py-3 px-4 rounded-md transition duration-300"
                >
                    SIGN IN
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
