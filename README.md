# Student Management System

.
├── .idea/
├── .mvn/
│   └── wrapper/
├── .smarttomcat/student-management-system/components/
├── src/
│   └── main/
├── .gitignore
├── README.md
├── et --hard HEAD
├── mvnw
├── mvnw.cmd
└── pom.xml


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
