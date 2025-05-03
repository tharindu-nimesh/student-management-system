<aside class="w-72 md:w-64 h-screen overflow-hidden flex flex-col justify-between bg-[#221E87] ">
    <!-- Logo & Title -->
    <div>
        <div class="bg-red-600 flex flex-col items-center px-6 py-6">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="h-14 mb-2">
            <span class="text-white font-bold text-lg text-center">FRONTIER INSTITUTE</span>
        </div>
        <!-- Navigation -->
        <nav class="mt-2">
            <a href="${pageContext.request.contextPath}/dashboard" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-tachometer-alt w-5"></i>
                <span class="ml-3">Dashboard</span>
            </a>
            <a href="${pageContext.request.contextPath}/students" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-user-graduate w-5"></i>
                <span class="ml-3">Students</span>
            </a>
            <a href="${pageContext.request.contextPath}/teachers" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-chalkboard-teacher w-5"></i>
                <span class="ml-3">Teachers</span>
            </a>
            <a href="${pageContext.request.contextPath}/courses" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-book w-5"></i>
                <span class="ml-3">Courses</span>
            </a>
            <a href="${pageContext.request.contextPath}/grades" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-graduation-cap w-5"></i>
                <span class="ml-3">Grades</span>
            </a>
            <a href="${pageContext.request.contextPath}/attendance" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-calendar-check w-5"></i>
                <span class="ml-3">Attendance</span>
            </a>
            <a href="${pageContext.request.contextPath}/settings" class="flex items-center px-6 py-3 text-white hover:bg-[#2a3bab] rounded transition">
                <i class="fa fa-cog w-5"></i>
                <span class="ml-3">Settings</span>
            </a>
        </nav>
    </div>
    <!-- Logout -->
    <div class="px-6 py-4">
        <a href="${pageContext.request.contextPath}/auth/logout" class="block w-full text-center bg-white text-[#221E87] font-semibold py-2 rounded hover:bg-gray-200 transition">
            Log Out
        </a>
    </div>
</aside>
