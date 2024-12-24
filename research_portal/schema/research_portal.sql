-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 24, 2024 at 03:14 PM
-- Server version: 8.0.36
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `research_portal`
--
CREATE DATABASE IF NOT EXISTS `research_portal` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `research_portal`;

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `activity_type` enum('submission') NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `activity`
--

TRUNCATE TABLE `activity`;
-- --------------------------------------------------------

--
-- Table structure for table `theses`
--

DROP TABLE IF EXISTS `theses`;
CREATE TABLE IF NOT EXISTS `theses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `summary` text NOT NULL,
  `supporting_file` varchar(255) DEFAULT NULL,
  `student_id` int NOT NULL,
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `theses`
--

TRUNCATE TABLE `theses`;
--
-- Dumping data for table `theses`
--

INSERT INTO `theses` (`id`, `title`, `summary`, `supporting_file`, `student_id`, `submitted_at`) VALUES
(1, 'my first thesis titles', 'Oh to talking improve produce in limited offices fifteen an. Wicket branch to answer do we. Place are decay men hours tiled. If or of ye throwing friendly required. Marianne interest in exertion as. Offering my branched confined oh dashwood.\\\\r\\\\n\\\\r\\\\nAbilities or he perfectly pretended so strangers be exquisite. Oh to another chamber pleased imagine do in. Went me rank at last loud shot an draw. Excellent so to no sincerity smallness. Removal request delight if on he we. Unaffected in we by apartments astonished to decisively themselves. Offended ten old consider speaking.\\\\r\\\\n\\\\r\\\\nUneasy barton seeing remark happen his has. Am possible offering at contempt mr distance stronger an. Attachment excellence announcing or reasonable am on if indulgence. Exeter talked in agreed spirit no he unable do. Betrayed shutters in vicinity it unpacked in. In so impossible appearance considered mr. Mrs him left find are good.\\\\r\\\\n\\\\r\\\\nImpossible considered invitation him men instrument saw celebrated unpleasant. Put rest and must set kind next many near nay. He exquisite continued explained middleton am. Voice hours young woody has she think equal. Estate moment he at on wonder at season little. Six garden result summer set family esteem nay estate. End admiration mrs unreserved discovered comparison especially invitation.\\\\r\\\\n\\\\r\\\\nBe me shall purse my ought times. Joy years doors all would again rooms these. Solicitude announcing as to sufficient my. No my reached suppose proceed pressed perhaps he. Eagerness it delighted pronounce repulsive furniture no. Excuse few the remain highly feebly add people manner say. It high at my mind by roof. No wonder worthy in dinner.\\\\r\\\\n\\\\r\\\\nPassage its ten led hearted removal cordial. Preference any astonished unreserved mrs. Prosperous understood middletons in conviction an uncommonly do. Supposing so be resolving breakfast am or perfectly. Is drew am hill from mr. Valley by oh twenty direct me so. Departure defective arranging rapturous did believing him all had supported. Family months lasted simple set nature vulgar him. Picture for attempt joy excited ten carried manners talking how. Suspicion neglected he resolving agreement perceived at an.\\\\r\\\\n\\\\r\\\\nReal sold my in call. Invitation on an advantages collecting. But event old above shy bed noisy. Had sister see wooded favour income has. Stuff rapid since do as hence. Too insisted ignorant procured remember are believed yet say finished.\\\\r\\\\n\\\\r\\\\nIn show dull give need so held. One order all scale sense her gay style wrote. Incommode our not one ourselves residence. Shall there whose those stand she end. So unaffected partiality indulgence dispatched to of celebrated remarkably. Unfeeling are had allowance own perceived abilities.\\\\r\\\\n\\\\r\\\\nBoth rest of know draw fond post as. It agreement defective to excellent. Feebly do engage of narrow. Extensive repulsive belonging depending if promotion be zealously as. Preference inquietude ask now are dispatched led appearance. Small meant in so doubt hopes. Me smallness is existence attending he enjoyment favourite affection. Delivered is to ye belonging enjoyment preferred. Astonished and acceptance men two discretion. Law education recommend did objection how old.\\\\r\\\\n\\\\r\\\\nResolution possession discovered surrounded advantages has but few add. Yet walls times spoil put. Be it reserved contempt rendered smallest. Studied to passage it mention calling believe an. Get ten horrible remember pleasure two vicinity. Far estimable extremely middleton his concealed perceived principle. Any nay pleasure entrance prepared her.\\\\r\\\\n', 'assets/uploads/theses/Use-case for GoldLab Management System.pdf', 1, '2024-12-24 13:37:38');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `program_name` varchar(100) DEFAULT NULL,
  `institute_name` varchar(100) DEFAULT NULL,
  `role` enum('student','faculty') NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_photo` varchar(255) DEFAULT NULL,
  `semester` varchar(50) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `registered_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `city`, `country`, `program_name`, `institute_name`, `role`, `profile_picture`, `created_at`, `profile_photo`, `semester`, `designation`, `department`, `registered_at`) VALUES
(1, 'Rafiqun', 'Nahar', 'admin@store.com', '$2y$10$LmVDLplWurRJ.rD3R.CoKu4rw5fDVzhFm6qhishs/jxzITT8T/c8W', 'Dhaka', 'Bangladesh', 'BCSE', 'IUBAT', 'student', NULL, '2024-12-24 12:46:58', 'assets/uploads/profiles/Screenshot 2024-12-24 193512.png', '12', NULL, NULL, '2024-12-24 14:59:46'),
(2, 'Happy', 'Akter', 'happy@akter.com', '$2y$10$niFseLOYmTPWxOG2p3OiL.x/48H84ZR/lLZ6l4xrtkwklTXJ4tgyK', 'Dhaka', 'Bangladesh', 'BCSE', 'Brac', 'faculty', NULL, '2024-12-24 14:15:57', 'assets/uploads/profiles/Screenshot 2023-08-29 183904.png', '', NULL, NULL, '2024-12-24 14:59:46'),
(3, 'Dummy', 'User', 'dummy@user.com', '$2y$10$JcnSeFC2XhCwShhVzXGAs.GbbD1G.EyAINuoe5bPSir/v9B2N2AbW', 'Narayanganj', 'Bangladesh', 'Civil', 'IUBAT', 'student', NULL, '2024-12-24 14:27:53', 'assets/uploads/profiles/Screenshot 2024-05-08 203416.png', '8', NULL, NULL, '2024-12-24 14:59:46'),
(4, 'New', 'User', 'new@user.com', '$2y$10$r9FGBnLeSRGQBxHvBaTjw.KhP78HuH7jqAQoz8/fLmLVsNqc/djdi', 'Gazipur', 'Bangladesh', 'BsAgr', 'IUBAT', 'student', NULL, '2024-12-24 14:37:45', 'assets/uploads/profiles/Screenshot 2024-02-20 115831.png', '9', NULL, NULL, '2024-12-24 14:59:46');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `theses`
--
ALTER TABLE `theses`
  ADD CONSTRAINT `theses_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
