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
                            light: '#6366f1',
                            DEFAULT: '#4f46e5',
                            dark: '#4338ca',
                        },
                    },
                    fontFamily: {
                        sans: ['Inter', 'sans-serif'],
                    },
                    animation: {
                        'fade-in': 'fadeIn 0.5s ease-out',
                        'slide-up': 'slideUp 0.5s ease-out',
                        'pulse-slow': 'pulse 3s infinite',
                    },
                    keyframes: {
                        fadeIn: {
                            '0%': { opacity: '0' },
                            '100%': { opacity: '1' },
                        },
                        slideUp: {
                            '0%': { transform: 'translateY(20px)', opacity: '0' },
                            '100%': { transform: 'translateY(0)', opacity: '1' },
                        },
                    },
                },
            },
        }
    </script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        @keyframes floatAnimation {
            0% { transform: translateY(0px); }
            50% { transform: translateY(-10px); }
            100% { transform: translateY(0px); }
        }
        .float-animation {
            animation: floatAnimation 3s ease-in-out infinite;
        }
        .gradient-bg {
            background: linear-gradient(135deg, #4f46e5 0%, #8b5cf6 100%);
        }
        .glass-effect {
            background: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(12px);
            -webkit-backdrop-filter: blur(12px);
            border: 1px solid rgba(255, 255, 255, 0.18);
        }
        .input-transition {
            transition: all 0.3s ease;
        }
        .input-transition:focus {
            transform: translateY(-2px);
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
        }
        body {
            overflow-x: hidden;
            background-color: #f8fafc;
        }
    </style>
</head>
<body class="font-sans">
<div class="flex items-center justify-center min-h-screen p-4">
    <!-- Main Content Card -->
    <div class="w-full max-w-5xl shadow-2xl rounded-2xl overflow-hidden flex flex-col md:flex-row animate-fade-in">
        <!-- Left Section (Brand & Welcome) -->
        <div class="w-full md:w-1/2 gradient-bg flex flex-col justify-center items-center p-8 text-center md:text-left relative overflow-hidden">
            <!-- Background Shapes -->
            <div class="absolute top-0 left-0 w-full h-full overflow-hidden z-0">
                <div class="absolute top-10 left-10 w-32 h-32 rounded-full bg-indigo-400 opacity-20 animate-pulse-slow"></div>
                <div class="absolute bottom-10 right-10 w-40 h-40 rounded-full bg-purple-400 opacity-20 animate-pulse-slow" style="animation-delay: 1s;"></div>
                <div class="absolute top-1/2 left-1/3 w-24 h-24 rounded-full bg-blue-400 opacity-20 animate-pulse-slow" style="animation-delay: 2s;"></div>
            </div>

            <div class="relative z-10 animate-fade-in">
                <div class="float-animation mb-8">
                    <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="Frontier Institute Logo" class="w-28 h-28 mx-auto md:mx-0 shadow-lg rounded-2xl">
                </div>
                <h1 class="text-white text-3xl md:text-4xl font-bold mt-4">NIMNA INSTITUTE</h1>
                <div class="h-1 w-20 bg-white mx-auto md:mx-0 my-6"></div>
                <h2 class="text-white text-4xl md:text-5xl font-bold mt-6 animate-slide-up" style="animation-delay: 0.2s;">WELCOME</h2>
                <p class="text-white text-xl mt-4 opacity-90 animate-slide-up" style="animation-delay: 0.4s;">Student Management System</p>
            </div>
        </div>

        <!-- Right Section (Login Form) -->
        <div class="w-full md:w-1/2 flex items-center justify-center p-8 bg-white">
            <div class="w-full max-w-md animate-fade-in" style="animation-delay: 0.3s;">
                <div class="text-center mb-8">
                    <h3 class="text-2xl font-bold text-gray-800">Sign In</h3>
                    <p class="text-gray-500 mt-2">Access your student dashboard</p>
                </div>

                <%-- Error Message Display --%>
                <c:if test="${not empty errorMessage}">
                    <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 rounded mb-6 animate-slide-up">
                        <div class="flex items-center">
                            <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                                <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zm-1 9a1 1 0 001-1v-4a1 1 0 10-2 0v4a1 1 0 001 1z" clip-rule="evenodd"></path>
                            </svg>
                                ${errorMessage}
                        </div>
                    </div>
                </c:if>

                <!-- Login Form -->
                <form action="${pageContext.request.contextPath}/auth/login" method="POST" class="space-y-6">
                    <!-- Username -->
                    <div class="group">
                        <label for="username" class="block text-sm font-medium text-gray-700 mb-1">Username</label>
                        <div class="relative">
                            <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <svg class="h-5 w-5 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                                </svg>
                            </div>
                            <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    required
                                    class="input-transition pl-10 w-full px-4 py-3 rounded-lg border border-gray-300 focus:border-indigo-500 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                                    placeholder="Enter your username"
                            >
                        </div>
                    </div>

                    <!-- Password -->
                    <div class="group">
                        <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Password</label>
                        <div class="relative">
                            <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <svg class="h-5 w-5 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
                                </svg>
                            </div>
                            <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    required
                                    class="input-transition pl-10 w-full px-4 py-3 rounded-lg border border-gray-300 focus:border-indigo-500 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                                    placeholder="Enter your password"
                            >
                        </div>
                    </div>

                    <!-- Remember Me & Forgot Password -->
                    <div class="flex items-center justify-between">
                        <div class="flex items-center">
                            <input
                                    id="remember-me"
                                    name="remember-me"
                                    type="checkbox"
                                    class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                            >
                            <label for="remember-me" class="ml-2 block text-sm text-gray-700">
                                Remember me
                            </label>
                        </div>
                        <div class="text-sm">
                            <a href="#" class="text-indigo-600 hover:text-indigo-500 hover:underline transition-all">
                                Forgot password?
                            </a>
                        </div>
                    </div>

                    <!-- Submit Button -->
                    <button
                            type="submit"
                            class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-3 px-4 rounded-lg transition-all duration-300 transform hover:scale-[1.02] hover:shadow-lg focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-opacity-50"
                    >
                        SIGN IN
                    </button>
                </form>

                <!-- Additional options -->
                <div class="mt-8 pt-6 border-t border-gray-200 text-center text-sm text-gray-600">
                    <p>Need an account? <a href="#" class="text-indigo-600 hover:text-indigo-500 font-medium">Contact administrator</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>