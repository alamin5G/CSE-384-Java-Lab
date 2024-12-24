-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 24, 2024 at 09:21 AM
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
-- Database: `home_budget_tracker`
--
CREATE DATABASE IF NOT EXISTS `home_budget_tracker` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `home_budget_tracker`;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `categories`
--

TRUNCATE TABLE `categories`;
--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `user_id`) VALUES
(9, 'Rahim', 1),
(8, 'Look', 2),
(6, 'Food', 2),
(10, 'Karim', 1),
(15, 'White Rice', 4),
(12, 'Samuchaaaa', 3),
(13, 'Rice', 3),
(14, 'Vegetables', 3),
(16, 'Fruits', 4),
(17, 'Tea', 4);

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE IF NOT EXISTS `expenses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category_id` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `expense_date` date NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `expenses`
--

TRUNCATE TABLE `expenses`;
--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`id`, `user_id`, `category_id`, `amount`, `expense_date`, `description`) VALUES
(1, 1, 1, 500.00, '2024-12-24', 'ki kheychi'),
(2, 1, 4, 25.00, '2024-12-24', 'drinking tea'),
(3, 2, 6, 200.00, '2024-12-24', 'khaichilam'),
(4, 2, 8, 10.00, '2024-12-24', 'emni tea'),
(5, 1, 10, 5.00, '2024-12-24', 'tea'),
(6, 3, 13, 15.00, '2024-12-24', 'white rice half plate'),
(7, 3, 12, 5.00, '2024-11-04', '1 pc'),
(8, 4, 16, 100.00, '2024-12-01', 'half kg apple\r\n'),
(9, 4, 17, 15.00, '2024-12-23', '3 friends '),
(10, 4, 15, 20.00, '2024-11-05', 'lunch'),
(11, 4, 17, 5.00, '2024-10-24', 'single'),
(12, 4, 16, 10.00, '2024-11-21', 'Jalpai');

-- --------------------------------------------------------

--
-- Table structure for table `family_members`
--

DROP TABLE IF EXISTS `family_members`;
CREATE TABLE IF NOT EXISTS `family_members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `family_members`
--

TRUNCATE TABLE `family_members`;
--
-- Dumping data for table `family_members`
--

INSERT INTO `family_members` (`id`, `user_id`, `name`, `relationship`, `age`) VALUES
(1, 1, 'Md. Alamin', 'me', 22),
(2, 1, 'Nuha', 'sister', 16),
(3, 2, 'Md. Alamin', 'me', 22);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `created_at`) VALUES
(1, 'alamin', '$2y$10$eoFbwMCf.Sc4CZHYzZHNzOwfJgoBHynZ/kxjCYcy0ru83SPy/sD.O', 'admin@stores.com', '2024-12-23 20:44:13'),
(2, 'alamins', '$2y$10$baOHccsvvIo8RjCtNvnaIOPzpOnVAKuZh7SRdC3rV1gypPf3MDcNe', 'admin@store.com', '2024-12-23 21:32:47'),
(3, 'nadia', '$2y$10$AiRrMqYG1TXNJsHv0RqtaeDFOd6eDjaFOPRU/DwPyWDp0lCQ7xdOK', 'nadia@a.com', '2024-12-24 06:54:07'),
(4, 'admin', '$2y$10$8I35WGBDJlP/sayZwUSxCufBXZN.8/.r.z056KRjyHdmgU3LLyIIC', 'admin@admin.com', '2024-12-24 08:24:48');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
