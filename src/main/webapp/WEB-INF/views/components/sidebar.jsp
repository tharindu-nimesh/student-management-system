<aside class="w-72 md:w-64 h-screen flex flex-col justify-between bg-gradient-to-b from-indigo-700 to-indigo-900 shadow-xl relative">
    <!-- Background Elements -->
    <div class="absolute inset-0 overflow-hidden z-0">
        <div class="absolute top-20 -right-10 w-40 h-40 rounded-full bg-indigo-400 opacity-10 animate-pulse-slow"></div>
        <div class="absolute bottom-40 -left-20 w-60 h-60 rounded-full bg-purple-500 opacity-10 animate-pulse-slow" style="animation-delay: 1.5s;"></div>
    </div>

    <div class="relative z-10 flex-grow overflow-y-auto">
        <!-- Logo & Title -->
        <div class="flex flex-col items-center px-6 py-8 bg-gradient-to-r from-indigo-600 to-indigo-800">
            <div class="w-16 h-16 rounded-full overflow-hidden border-2 border-white border-opacity-30 shadow-lg mb-3 transform transition-all hover:scale-110 duration-300">
                <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="Logo" class="h-full w-full object-cover">
            </div>
            <span class="text-white font-bold text-lg text-center">NIMNA INSTITUTE</span>
            <div class="h-1 w-16 bg-white bg-opacity-30 mt-3"></div>
        </div>

        <!-- Navigation -->
        <nav class="mt-6 px-4">
            <a href="${pageContext.request.contextPath}/dashboard"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-tachometer-alt"></i>
                </div>
                <span class="ml-3 font-medium">Dashboard</span>
            </a>

            <a href="${pageContext.request.contextPath}/students"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-user-graduate"></i>
                </div>
                <span class="ml-3 font-medium">Students</span>
            </a>

            <a href="${pageContext.request.contextPath}/teachers"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-chalkboard-teacher"></i>
                </div>
                <span class="ml-3 font-medium">Teachers</span>
            </a>

            <a href="${pageContext.request.contextPath}/courses"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-book"></i>
                </div>
                <span class="ml-3 font-medium">Courses</span>
            </a>

            <a href="${pageContext.request.contextPath}/grades"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-graduation-cap"></i>
                </div>
                <span class="ml-3 font-medium">Grades</span>
            </a>

            <a href="${pageContext.request.contextPath}/attendance"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-calendar-check"></i>
                </div>
                <span class="ml-3 font-medium">Attendance</span>
            </a>

            <a href="${pageContext.request.contextPath}/settings"
               class="flex items-center px-4 py-3 my-1 text-white hover:bg-white hover:bg-opacity-10 rounded-xl transition-all duration-300 group">
                <div class="w-8 h-8 flex items-center justify-center bg-indigo-500 bg-opacity-20 rounded-lg text-indigo-100 group-hover:bg-indigo-100 group-hover:text-indigo-600 transition-all">
                    <i class="fa fa-cog"></i>
                </div>
                <span class="ml-3 font-medium">Settings</span>
            </a>
        </nav>
    </div>

    <!-- User Profile & Logout -->
    <div class="relative z-10 px-5 py-6 mt-auto">
        <div class="p-4 bg-indigo-800 bg-opacity-50 rounded-xl mb-4 backdrop-blur-sm">
            <div class="flex items-center">
                <div class="w-10 h-10 rounded-full bg-indigo-200 flex items-center justify-center text-indigo-700">
                    <i class="fa fa-user"></i>
                </div>
                <div class="ml-3">
                    <p class="text-white text-sm font-medium">${user.username}</p>
                    <p class="text-indigo-200 text-xs">Administrator</p>
                </div>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/auth/logout"
           class="block w-full text-center bg-white hover:bg-gray-100 text-indigo-700 font-semibold py-3 px-4 rounded-xl transition-all duration-300 transform hover:scale-[1.02] hover:shadow-lg focus:outline-none focus:ring-2 focus:ring-indigo-300 focus:ring-opacity-50">
            <i class="fa fa-sign-out-alt mr-2"></i> Log Out
        </a>
    </div>

    <style>
        @keyframes pulse-slow {
            0% { opacity: 0.05; }
            50% { opacity: 0.15; }
            100% { opacity: 0.05; }
        }
        .animate-pulse-slow {
            animation: pulse-slow 6s ease-in-out infinite;
        }
    </style>
</aside>