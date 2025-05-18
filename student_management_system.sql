-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2025 at 02:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `attendance_date` date NOT NULL,
  `status` enum('Present','Absent','Late','Excused') NOT NULL,
  `remarks` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`attendance_id`, `student_id`, `course_id`, `attendance_date`, `status`, `remarks`, `created_at`, `updated_at`) VALUES
(1, 2, 20, '2023-02-24', 'Late', '', '2025-05-18 09:57:00', '2025-05-18 09:57:16'),
(2, 25, 19, '2023-02-12', 'Present', '', '2025-05-18 10:32:01', '2025-05-18 10:32:01'),
(3, 1, 14, '2025-02-03', 'Present', '', '2025-05-18 10:32:19', '2025-05-18 10:45:50');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL,
  `course_code` varchar(20) NOT NULL,
  `course_name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `credits` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL,
  `status` enum('Active','Inactive','Upcoming') DEFAULT 'Active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_code`, `course_name`, `description`, `credits`, `teacher_id`, `semester`, `status`, `created_at`, `updated_at`) VALUES
(11, 'MATH101', 'Calculus I', 'Introduction to differential and integral calculus.', 3, 10, 'Fall 2024', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:38'),
(12, 'SCI201', 'General Science', 'Covers basic scientific concepts from physics, chemistry, and biology.', 4, 9, 'Spring 2025', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:31'),
(13, 'ENG102', 'English Literature', 'Study of classic and modern English literature.', 3, 8, 'Fall 2024', 'Inactive', '2025-05-18 09:54:09', '2025-05-18 09:55:26'),
(14, 'HIS110', 'World History', 'Survey of major historical events and civilizations.', 3, 7, 'Spring 2025', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:22'),
(15, 'GEO120', 'Physical Geography', 'Covers earth processes, landscapes, and environment.', 2, 6, 'Fall 2024', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:18'),
(16, 'BIO210', 'Human Biology', 'Exploration of the human body systems and functions.', 4, 5, 'Spring 2025', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:13'),
(17, 'CHE101', 'Intro to Chemistry', 'Basic concepts in chemical structure and reactions.', 3, 4, 'Fall 2024', 'Inactive', '2025-05-18 09:54:09', '2025-05-18 09:55:09'),
(18, 'PHY130', 'Fundamentals of Physics', 'Concepts of motion, forces, and energy.', 3, 3, 'Spring 2025', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:05'),
(19, 'CS150', 'Intro to Programming', 'Learn the basics of programming using Java.', 4, 2, 'Fall 2024', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:55:01'),
(20, 'ART100', 'Visual Arts', 'Introduction to artistic concepts and creative expression.', 2, 1, 'Spring 2025', 'Active', '2025-05-18 09:54:09', '2025-05-18 09:54:54'),
(21, 'AI101.3', 'Artificial Interligence', '', 3, 8, '4', 'Active', '2025-05-18 10:42:14', '2025-05-18 10:42:14');

-- --------------------------------------------------------

--
-- Table structure for table `grades`
--

CREATE TABLE `grades` (
  `grade_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `assignment_name` varchar(100) NOT NULL,
  `grade_value` decimal(5,2) NOT NULL,
  `max_grade` decimal(5,2) NOT NULL,
  `grade_date` date NOT NULL,
  `comments` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grades`
--

INSERT INTO `grades` (`grade_id`, `student_id`, `course_id`, `assignment_name`, `grade_value`, `max_grade`, `grade_date`, `comments`, `created_at`, `updated_at`) VALUES
(1, 24, 16, 'Assignment - 1', 60.00, 100.00, '2024-01-11', '', '2025-05-18 10:12:06', '2025-05-18 10:12:06'),
(2, 6, 19, 'Inclass - 1', 34.00, 50.00, '2025-12-03', '', '2025-05-18 10:28:50', '2025-05-18 10:28:50');

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `id` int(11) NOT NULL,
  `admin_name` varchar(100) DEFAULT NULL,
  `admin_email` varchar(100) DEFAULT NULL,
  `admin_phone` varchar(20) DEFAULT NULL,
  `institute_name` varchar(100) DEFAULT NULL,
  `logo_url` text DEFAULT NULL,
  `academic_year` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enrollment_date` date NOT NULL,
  `status` enum('Active','Inactive','Graduated','On Leave') DEFAULT 'Active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `first_name`, `last_name`, `email`, `phone`, `date_of_birth`, `address`, `enrollment_date`, `status`, `created_at`, `updated_at`) VALUES
(1, 'Alan', 'Devinda', 'alan@example.com', '0836454647', '2000-05-06', 'Manila Street, Philipines', '2020-03-04', 'Active', '2025-05-18 09:49:21', '2025-05-18 09:59:13'),
(2, 'Alice', 'Fernando', 'alicef@example.com', '0711111111', '2005-04-15', '123 Main St, Colombo', '2022-01-10', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(3, 'Brian', 'Silva', 'brians@example.com', '0722222222', '2004-06-22', '45 Lake Rd, Kandy', '2021-09-01', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(4, 'Chathura', 'Perera', 'chathurap@example.com', '0733333333', '2003-12-10', '88 Flower Ln, Galle', '2020-05-15', 'Inactive', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(5, 'Dilani', 'Jayasinghe', 'dilanij@example.com', '0744444444', '2005-03-03', '9 River St, Matara', '2022-06-20', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(6, 'Eshan', 'Fernando', 'eshanf@example.com', '0755555555', '2004-08-30', '77 Hill View, Kurunegala', '2021-11-05', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(7, 'Fathima', 'Nazeer', 'fathiman@example.com', '0766666666', '2005-01-12', '55 Mosque Rd, Batticaloa', '2022-02-18', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(8, 'Gayan', 'Wijesekara', 'gayanw@example.com', '0777777777', '2003-07-07', '12 Lotus Garden, Negombo', '2020-08-10', 'Inactive', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(9, 'Harsha', 'Kumara', 'harshak@example.com', '0788888888', '2004-05-19', '5 Sunset Ave, Nuwara Eliya', '2021-03-25', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(10, 'Iresha', 'Wickramasinghe', 'ireshaw@example.com', '0799999999', '2005-09-14', '3 Green Park, Anuradhapura', '2022-09-05', 'Active', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(11, 'Janith', 'Ranasinghe', 'janithr@example.com', '0700000000', '2003-10-28', '66 Ocean View, Trincomalee', '2020-01-30', 'Inactive', '2025-05-18 09:53:09', '2025-05-18 09:53:09'),
(12, 'Kasun', 'Jayawardena', 'kasunj@example.com', '0712341111', '2004-02-20', '101 Temple Rd, Colombo', '2021-01-15', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(13, 'Tharushi', 'Senanayake', 'tharushis@example.com', '0723452222', '2005-05-12', '54 King St, Gampaha', '2022-03-10', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(14, 'Nipun', 'Wijeratne', 'nipunw@example.com', '0734563333', '2003-11-23', '88 Rose Ln, Kandy', '2020-08-01', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(15, 'Anushka', 'De Silva', 'anushkad@example.com', '0745674444', '2004-07-18', '65 Palm Dr, Colombo', '2021-06-05', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(16, 'Kavindu', 'Ranathunga', 'kavindur@example.com', '0756785555', '2005-10-10', '32 Sunrise Ave, Jaffna', '2022-07-20', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(17, 'Ruwani', 'Gunasekara', 'ruwanag@example.com', '0767896666', '2003-03-30', '24 Central Rd, Kalutara', '2020-12-11', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(18, 'Dineth', 'Fonseka', 'dinethf@example.com', '0778907777', '2004-06-25', '46 Lake View, Galle', '2021-09-15', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(19, 'Ishara', 'Fernando', 'isharaf@example.com', '0789018888', '2005-08-08', '88 Ocean Blvd, Matara', '2022-01-05', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(20, 'Nadeesha', 'Jayalath', 'nadeeshaj@example.com', '0790129999', '2003-12-01', '9 Mountain Rd, Kegalle', '2020-04-22', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(21, 'Sanduni', 'Peris', 'sandunip@example.com', '0701230000', '2004-09-09', '23 Lotus Rd, Colombo', '2021-10-18', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(22, 'Amal', 'Karunaratne', 'amalk@example.com', '0711341111', '2004-01-14', '34 Victoria Rd, Nuwara Eliya', '2021-05-01', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(23, 'Rashmi', 'Silva', 'rashmis@example.com', '0722452222', '2005-03-19', '11 Royal St, Batticaloa', '2022-04-10', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(24, 'Isuru', 'Abeysekara', 'isurua@example.com', '0733563333', '2003-10-13', '92 Moonlight Ln, Kurunegala', '2020-06-08', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(25, 'Menaka', 'Ekanayake', 'menakae@example.com', '0744674444', '2004-12-28', '56 Rose Garden, Gampaha', '2021-07-06', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(26, 'Dulanjali', 'Rajapaksha', 'dulanjalir@example.com', '0755785555', '2005-05-20', '44 Elm St, Trincomalee', '2022-09-12', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(27, 'Sahan', 'Wickramage', 'sahanw@example.com', '0766896666', '2003-02-18', '78 Kings Rd, Polonnaruwa', '2020-11-23', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(28, 'Gayani', 'Dias', 'gayanid@example.com', '0777907777', '2004-08-04', '31 Temple View, Ratnapura', '2021-08-10', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(29, 'Madushan', 'Liyanage', 'madushanl@example.com', '0788018888', '2005-07-16', '61 Sunset Dr, Ampara', '2022-02-01', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(30, 'Nilakshi', 'Fernando', 'nilakshif@example.com', '0799129999', '2003-09-07', '15 Green Park, Hambantota', '2020-03-29', 'Inactive', '2025-05-18 09:58:30', '2025-05-18 09:58:30'),
(31, 'Chamod', 'Rathnayake', 'chamodr@example.com', '0702230000', '2004-11-11', '100 Forest Ln, Badulla', '2021-12-03', 'Active', '2025-05-18 09:58:30', '2025-05-18 09:58:30');

-- --------------------------------------------------------

--
-- Table structure for table `student_course`
--

CREATE TABLE `student_course` (
  `enrollment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `enrollment_date` date NOT NULL,
  `status` enum('Enrolled','Completed','Dropped','Failed') DEFAULT 'Enrolled',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `hire_date` date NOT NULL,
  `status` enum('Active','Inactive','On Leave') DEFAULT 'Active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `first_name`, `last_name`, `email`, `phone`, `department`, `hire_date`, `status`, `created_at`, `updated_at`) VALUES
(1, 'John', 'Doe', 'johndoe@example.com', '0712345678', 'Mathematics', '2020-01-15', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(2, 'Jane', 'Smith', 'janesmith@example.com', '0723456789', 'Science', '2019-03-22', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(3, 'Michael', 'Brown', 'mbrown@example.com', '0734567890', 'English', '2021-08-01', 'Inactive', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(4, 'Emily', 'Davis', 'emilyd@example.com', '0745678901', 'History', '2018-06-10', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(5, 'Daniel', 'Wilson', 'dwilson@example.com', '0756789012', 'Geography', '2022-02-14', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(6, 'Olivia', 'Martin', 'oliviam@example.com', '0767890123', 'Biology', '2020-09-30', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(7, 'William', 'Taylor', 'wtaylor@example.com', '0778901234', 'Chemistry', '2017-11-25', 'Inactive', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(8, 'Sophia', 'Anderson', 'sophiaa@example.com', '0789012345', 'Physics', '2023-01-09', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(9, 'James', 'Thomas', 'jamest@example.com', '0790123456', 'Computer Science', '2021-05-17', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02'),
(10, 'Isabella', 'Jackson', 'isabellaj@example.com', '0701234567', 'Art', '2016-12-03', 'Active', '2025-05-18 09:44:02', '2025-05-18 09:44:02');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Teacher','Student') NOT NULL,
  `related_id` int(11) DEFAULT NULL,
  `last_login` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` enum('Active','Inactive','Locked') DEFAULT 'Active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `related_id`, `last_login`, `status`, `created_at`, `updated_at`, `email`, `phone`) VALUES
(1, 'admin', 'admin123', 'Admin', 1, '2025-05-18 12:45:11', 'Active', '2025-05-18 09:45:21', '2025-05-18 12:45:11', 'admin@gmail.com', '+94782425361'),
(2, 'teacher', 'teacher123', 'Teacher', 2, '2025-05-18 12:46:12', 'Active', '2025-05-18 12:45:38', '2025-05-18 12:46:12', 'teacher@gmail.com', '+94624251621');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`),
  ADD UNIQUE KEY `student_id` (`student_id`,`course_id`,`attendance_date`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_id`),
  ADD UNIQUE KEY `course_code` (`course_code`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indexes for table `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
  ADD PRIMARY KEY (`enrollment_id`),
  ADD UNIQUE KEY `student_id` (`student_id`,`course_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`teacher_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `grades`
--
ALTER TABLE `grades`
  MODIFY `grade_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
  MODIFY `enrollment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`);

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`);

--
-- Constraints for table `grades`
--
ALTER TABLE `grades`
  ADD CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`);

--
-- Constraints for table `student_course`
--
ALTER TABLE `student_course`
  ADD CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
