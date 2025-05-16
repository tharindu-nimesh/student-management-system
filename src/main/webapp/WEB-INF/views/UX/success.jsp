<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Success</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-green-50 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-lg rounded-xl p-10 text-center max-w-md w-full">
  <h1 class="text-3xl font-bold text-green-600 mb-4">Success!</h1>
  <p class="text-gray-700 mb-6">
    ${empty successMessage ? "Teacher registration was successful." : successMessage}
  </p>
  <div class="flex justify-center space-x-4">
    <a href="${pageContext.request.contextPath}/teachers/new"
       class="text-white bg-green-600 hover:bg-green-700 px-6 py-2 rounded transition">
      Register Another
    </a>
    <a href="${pageContext.request.contextPath}/teachers"
       class="text-white bg-blue-600 hover:bg-blue-700 px-6 py-2 rounded transition">
      View All Teachers
    </a>
  </div>
</div>
</body>
</html>
