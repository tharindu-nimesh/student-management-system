# Student Management System

A full-featured Java web application for managing students, teachers, courses, attendance, and grades at an educational institution.

## Project Structure

student-management-system/
│
├── .idea/ # IntelliJ project settings (IDE-specific, optional)
├── .mvn/wrapper/ # Maven wrapper scripts
├── src/
│ └── main/
│ ├── java/
│ │ └── com/sms/
│ │ ├── controller/ # Servlets/controllers (e.g. StudentController.java)
│ │ ├── dao/ # Data Access Objects (e.g. StudentDAO.java)
│ │ ├── model/ # Entity classes (e.g. Student.java)
│ │ ├── service/ # Business logic (e.g. StudentService.java)
│ │ └── util/ # Utilities (e.g. DBUtil.java)
│ ├── resources/
│ │ └── db.properties # Database configuration
│ └── webapp/
│ ├── images/ # Static images (logo, etc.)
│ ├── css/ # Custom CSS files
│ └── WEB-INF/
│ └── views/
│ ├── dashboard.jsp
│ └── students/
│ ├── list.jsp
│ ├── add.jsp
│ ├── edit.jsp
│ └── view.jsp
│
├── pom.xml # Maven build and dependency file
├── README.md # Project documentation
├── .gitignore # Git ignore file
├── mvnw, mvnw.cmd # Maven wrapper scripts
└── web.xml # Servlet configuration (if present)

- **pom.xml**: Maven build file for dependencies, plugins, and project info.
- **src/main/java**: All Java source code, organized by package.
- **src/main/resources**: Resource/configuration files.
- **src/main/webapp**: Web assets (JSPs, images, CSS, etc.).
- **README.md**: Project documentation and setup instructions.

---

**Tip:**  
If you want to auto-generate this in the future, you can use the `tree` command in your project root:

Or use an online Markdown folder structure generator.

---

This structure will help anyone understand and navigate your project easily on GitHub.



## Prerequisites

- Java 17 or newer
- Apache Maven 3.x
- MySQL Server
- Apache Tomcat 10.x (or compatible Jakarta EE 10 servlet container)
- (Optional) IDE such as IntelliJ IDEA or Eclipse

## Setup Instructions

### 1. Clone the Repository


### 2. Configure the Database

- Create a MySQL database, e.g. `student_management_system`.
- Import the provided schema and sample data if available.
- Edit `src/main/resources/db.properties` with your database credentials:

    ```
    driver=com.mysql.cj.jdbc.Driver
    url=jdbc:mysql://localhost:3306/student_management_system?useSSL=false&serverTimezone=UTC
    user=your_mysql_user
    password=your_mysql_password
    ```

### 3. Build the Project


### 4. Deploy to Tomcat

- Copy the generated `student-management-system.war` from `target/` to your Tomcat `webapps/` directory.
- Or, if using an IDE, configure Tomcat as a run configuration and deploy directly.

### 5. Access the Application

Open your browser and go to:


- The default login page will appear.
- Use credentials you have set up in your database (e.g., `admin` / `admin123`).

## Features

- User authentication with role-based access (Admin, Teacher, Student)
- Student CRUD (Create, Read, Update, Delete)
- Teacher and Course management
- Attendance and Grade tracking
- Responsive dashboard with summary cards and recent activity
- Search and filter functionality
- Modern UI with Tailwind CSS

## Notes

- **Security**: For demonstration, passwords may be stored in plain text. For production, use a secure hash (e.g., BCrypt).
- **Foreign Key Constraints**: When deleting a student, ensure related records (attendance, grades) are handled to avoid integrity errors.
- **JSTL**: JSTL libraries are included via Maven dependencies.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](LICENSE)
