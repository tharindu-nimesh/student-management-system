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

  <!-- Dynamic success message -->
  <p class="text-gray-700 mb-6">
    ${empty successMessage ? "Operation completed successfully." : successMessage}
  </p>

  <!-- Dynamic return links -->
  <div class="flex justify-center space-x-4">
    <!-- Primary action button -->
    <a href="${empty returnUrl ? pageContext.request.contextPath : returnUrl}"
       class="text-white bg-green-600 hover:bg-green-700 px-6 py-2 rounded transition">
      ${empty returnLabel ? "Return" : returnLabel}
    </a>

    <!-- Secondary action button (optional) -->
    <% if (request.getAttribute("secondaryUrl") != null) { %>
    <a href="${secondaryUrl}"
       class="text-white bg-blue-600 hover:bg-blue-700 px-6 py-2 rounded transition">
      ${empty secondaryLabel ? "View All" : secondaryLabel}
    </a>
    <% } %>
  </div>
</div>
</body>
</html>
