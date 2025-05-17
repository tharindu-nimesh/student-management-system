# Student Management System 🎓

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange)](https://openjdk.org/)
[![Tomcat Version](https://img.shields.io/badge/Tomcat-10.x-red)](https://tomcat.apache.org/)

A modern web application for managing student information, attendance, grades, and institutional operations in educational organizations.

![Dashboard Preview](docs/screenshots/dashboard.png)  
*Sample Dashboard Interface*

## 🌟 Features

### Role-Based Access Control
- **Administrators**  
  - Manage student/teacher records  
  - Course scheduling & assignments  
  - Generate institutional reports  
  - Configure system settings

- **Teachers**  
  - Record attendance & grades  
  - Manage course materials  
  - View student progress  
  - Submit academic reports

### Core Functionality
- 📊 Interactive dashboard with analytics
- 🔍 Advanced search & filtering
- 📅 Attendance tracking with visual calendar
- 📈 Grade management with GPA calculation
- 📦 Bulk import/export operations
- 🔔 Notification system for important updates
- 📱 Responsive mobile-first design

## 🛠 Tech Stack

**Backend**  
![Java](https://img.shields.io/badge/-Java-007396?logo=java&logoColor=white)
![JakartaEE](https://img.shields.io/badge/-Jakarta_EE-4A76B0?logo=eclipse&logoColor=white)
![MySQL](https://img.shields.io/badge/-MySQL-4479A1?logo=mysql&logoColor=white)

**Frontend**  
![JSP](https://img.shields.io/badge/-JSP-F37626?logo=apache&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/-Tailwind_CSS-06B6D4?logo=tailwindcss&logoColor=white)
![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?logo=javascript&logoColor=black)

**Tools**  
![Maven](https://img.shields.io/badge/-Maven-C71A36?logo=apache-maven&logoColor=white)
![Tomcat](https://img.shields.io/badge/-Tomcat-F8DC75?logo=apache-tomcat&logoColor=black)

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Apache Maven 3.8+
- MySQL 8.0+
- Tomcat 10.x+
- Modern web browser

### Installation

1. **Clone Repository**
git clone https://github.com/tharindu-nimesh/student-management-system.git
cd student-management-system


2. **Database Setup**
CREATE DATABASE student_management_system;
USE student_management_system;
SOURCE schema.sql; # Import provided schema


3. **Configure Database**
Edit `src/main/resources/db.properties`:
url=jdbc:mysql://localhost:3306/student_management_system?useSSL=false
user=your_db_user
password=your_secure_password


4. **Build & Deploy**
mvn clean package
cp target/student-management-system.war $TOMCAT_HOME/webapps/


5. **Access Application**  
Visit `http://localhost:8080/student-management-system`

**Default Credentials**  
- Admin: `admin@nimna.edu` / `Admin@123`  
- Teacher: `teacher@nimna.edu` / `Teach@123`  

## 🛡 Security Best Practices
- BCrypt password hashing
- Session timeout (30 minutes)
- CSRF protection
- Input validation & sanitization
- Role-based access control
- HTTPS enforcement (production)

## 🤝 Contributing

We welcome contributions! Please follow these steps:
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

See our [Contribution Guidelines](CONTRIBUTING.md) for details.

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📧 Contact

Project Maintainer: [tharindu-nimesh] 
Issue Tracker: [GitHub Issues](https://github.com/tharindu-nimesh/student-management-system/issues)

---

**Nimna Institute** © 2024 - Transforming Education Through Technology
