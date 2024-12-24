-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 24, 2024 at 08:57 AM
-- Server version: 8.3.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


--
-- Database: `leaderboard`
--
CREATE DATABASE IF NOT EXISTS `leaderboard` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `leaderboard`;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `option1` varchar(255) NOT NULL,
  `option2` varchar(255) NOT NULL,
  `option3` varchar(255) NOT NULL,
  `option4` varchar(255) NOT NULL,
  `correct_option` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `option1`, `option2`, `option3`, `option4`, `correct_option`, `created_at`) VALUES
(3, 'What date is the Victory Day of Bangladesh?', '21 December', '26 December', '26 March', '16 December', 4, '2024-12-24 05:37:09'),
(4, 'বাংলাদেশের রাজধানীর নাম কি?', 'ঢাকা', 'গাজীপুর', 'চট্টগ্রাম', 'নারায়ণগঞ্জ', 1, '2024-12-24 05:39:54'),
(5, 'বাংলাদেশের প্রথম পালিয়ে যাওয়া প্রধানমন্ত্রীর নাম কী?', 'শেখ হাসিনা', 'খালেদা জিয়া', 'ড. মুহাম্মদ ইউনুস', 'তারেক জিয়া', 1, '2024-12-24 05:41:39'),
(6, 'Which color is liked by Sakib Al Hasan?', 'Red', 'Blue', 'Yellow', 'Purple', 4, '2024-12-24 06:22:44'),
(7, 'Why did Sakib Al Hasan join in Politics?', 'For more fame', 'For money', 'For  family pressure', 'For nothing', 2, '2024-12-24 06:26:14'),
(8, 'In what kind of game a person needs to use his leg?', 'cricket', 'hockey', 'tennis', 'Football', 4, '2024-12-24 06:28:05'),
(9, 'In which year Argentina did win their 2nd world cup?', '1996', '2000', '2022', '1968', 3, '2024-12-24 06:30:38'),
(10, 'How many World Cup does Brazil have?', '4', '3', '5', '9', 3, '2024-12-24 06:31:51'),
(11, 'In which year France did win FIFA world cup?', '2012', '2018', '1996', '2008', 2, '2024-12-24 06:33:41'),
(12, 'What are the technologies used in this project?', 'php, mysql', 'java, javascript, mysql', 'dot net', 'Other', 1, '2024-12-24 06:37:19');

-- --------------------------------------------------------

--
-- Table structure for table `scores`
--

DROP TABLE IF EXISTS `scores`;
CREATE TABLE IF NOT EXISTS `scores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `score` int NOT NULL,
  `played_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `scores`
--

INSERT INTO `scores` (`id`, `user_id`, `score`, `played_at`) VALUES
(11, 6, 3, '2024-12-24 06:06:23'),
(12, 6, 2, '2024-12-24 06:07:13'),
(13, 6, 8, '2024-12-24 06:38:39'),
(14, 8, 8, '2024-12-24 08:16:02'),
(15, 8, 8, '2024-12-24 08:17:24');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `phone`, `is_admin`, `created_at`, `name`, `age`) VALUES
(8, 'admin', 'admin@admin.net', '$2y$10$HRZ0jsZWcZxM5/S69Yc16.q9ZIvxcHVhM85F3.fhnApOXpfhwkJ8S', '01823456789', 1, '2024-12-24 08:13:09', 'Admin User', 22),
(6, 'rejauna', '123@123.com', '$2y$10$l9hpfTCEeEYyySPHcGZLX.TvHZNAxdX8FF31LHgg6RiA00L5PegWq', '01628154873', 1, '2024-12-24 06:05:27', 'Rejauna Islam', 18);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
