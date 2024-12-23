-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 23, 2024 at 07:45 PM
-- Server version: 9.1.0
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `option1`, `option2`, `option3`, `option4`, `correct_option`, `created_at`) VALUES
(1, 'what is my name?', 'Alaminn', 'Rakib', 'Alamin', 'Md. Alamina', 3, '2024-12-23 18:49:00');

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
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `scores`
--

INSERT INTO `scores` (`id`, `user_id`, `score`, `played_at`) VALUES
(1, 1, 1, '2024-12-23 19:06:42'),
(2, 1, 1, '2024-12-23 19:07:04'),
(3, 1, 0, '2024-12-23 19:07:16'),
(4, 1, 1, '2024-12-23 19:13:49'),
(5, 5, 1, '2024-12-23 19:44:20'),
(6, 5, 1, '2024-12-23 19:44:33'),
(7, 5, 1, '2024-12-23 19:44:40'),
(8, 5, 1, '2024-12-23 19:44:49');

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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `phone`, `is_admin`, `created_at`, `name`, `age`) VALUES
(1, 'alamin', 'admin@admin.com', '$2y$10$TjcCHhMfxXBWhCWDmnldPePpADjTf6sAU29Q/Ucy0Ul294QaoZ5zq', '01822679672', 1, '2024-12-23 17:57:13', 'Md. Alamin', 22),
(2, 'alaminvai', 'admin@store.com', '$2y$10$zNjAoLGpNajdL6Q6TrG0F.FfY1eXnts8i4nP/GbHKVKLrbelp9lVi', '01822679672', 0, '2024-12-23 18:00:16', 'Md. Alamin', 22),
(3, 'rakib', 'ala@alamin.com', '$2y$10$HBgUj5WWAgeZPOjQ8tX6bupKMcKgjz0SGoVhw/zybXClWfiBJG.92', '01822679672', 0, '2024-12-23 18:01:04', 'Md. Alamin', 23),
(4, 'rakibv', 'admin@stores.com', '$2y$10$bmscblNItVCz3XIdOjs1bOc6L1DJtOqWnI9WMO3WTHDw/kW8UUsay', '01822679672', 0, '2024-12-23 18:02:42', 'Md. Alamin', 22),
(5, '21303136', 'admin@storse.com', '$2y$10$ocbZJ0A9hiGxxLAxH6CiXOmOYIStiyZYA/dOdsKBaKdxbqNSDRgNy', '01822679672', 0, '2024-12-23 18:08:59', 'Md. Alamin', 22);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
