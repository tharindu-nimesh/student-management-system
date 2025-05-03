<aside class="w-72 md:w-64 h-screen overflow-hidden bg-gradient-to-b from-indigo-600 via-purple-600 to-indigo-700 text-white fixed left-0 top-0 z-50 flex flex-col shadow-2xl">
    <div class="flex-grow overflow-y-auto flex flex-col justify-between">
        <!-- Logo & Title -->
        <div class="flex items-center gap-3 px-6 py-5 border-b border-indigo-500">
            <div class="bg-white bg-opacity-20 rounded-xl p-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6l4 2" />
                </svg>
            </div>
            <span class="font-extrabold text-2xl tracking-wide">FRONTIER INSTITUTE</span>
        </div>

        <!-- Navigation -->
        <nav class="mt-2 flex-grow">
            <ul class="space-y-1 px-4">
                <!-- Dashboard -->
                <li>
                    <a href="${pageContext.request.contextPath}/dashboard" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6" />
                        </svg>
                        Dashboard
                    </a>
                </li>
                <!-- Students -->
                <li>
                    <a href="${pageContext.request.contextPath}/students" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M12 12a4 4 0 100-8 4 4 0 000 8z M12 12v8m0 0H5a2 2 0 01-2-2v-5a2 2 0 012-2h14a2 2 0 012 2v5a2 2 0 01-2 2h-7z" />
                        </svg>
                        Students
                    </a>
                </li>
                <!-- Teachers -->
                <li>
                    <a href="${pageContext.request.contextPath}/teachers" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M12 12a4 4 0 100-8 4 4 0 000 8z" />
                            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18v-2a6 6 0 0112 0v2" />
                        </svg>
                        Teachers
                    </a>
                </li>
                <!-- Courses -->
                <li>
                    <a href="${pageContext.request.contextPath}/courses" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M4 19.5A2.5 2.5 0 016.5 17h11a2.5 2.5 0 012.5 2.5V21H4v-1.5zM6 17V7a2 2 0 012-2h8a2 2 0 012 2v10" />
                        </svg>
                        Courses
                    </a>
                </li>
                <!-- Grades -->
                <li>
                    <a href="${pageContext.request.contextPath}/grades" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M9 17v-6a2 2 0 012-2h2a2 2 0 012 2v6m-2 4h.01" />
                        </svg>
                        Grades
                    </a>
                </li>
                <!-- Attendance -->
                <li>
                    <a href="${pageContext.request.contextPath}/attendance" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
                        </svg>
                        Attendance
                    </a>
                </li>
                <!-- Settings -->
                <li>
                    <a href="${pageContext.request.contextPath}/settings" class="flex items-center gap-3 py-2 px-4 rounded-lg hover:bg-white/10 transition">
                        <svg class="h-5 w-5" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M12 8v4l3 3" />
                        </svg>
                        Settings
                    </a>
                </li>
            </ul>
        </nav>

        <!-- Footer & Logout -->
        <div class="p-4 border-t border-indigo-700">
            <a href="${pageContext.request.contextPath}/auth/logout" class="flex items-center justify-center py-2 px-4 bg-pink-600 hover:bg-pink-700 rounded-lg text-white font-semibold transition w-full">
                <svg class="h-5 w-5 mr-2" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M17 16l4-4m0 0l-4-4m4 4H7" />
                </svg>
                Log Out
            </a>
        </div>
    </div>
</aside>
