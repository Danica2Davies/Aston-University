-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 04, 2021 at 11:17 AM
-- Server version: 5.7.33-0ubuntu0.18.04.1
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u_190141121_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `animals`
--

CREATE TABLE `animals` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(250) NOT NULL,
  `avalibility` varchar(250) NOT NULL,
  `animalname` varchar(250) NOT NULL,
  `image` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `animals`
--

INSERT INTO `animals` (`id`, `name`, `date`, `description`, `avalibility`, `animalname`, `image`) VALUES
(1, '83a876f1585f920.png', '1000-10-10', '', '', 'test', ''),
(4, '401e59a2f5033dc7a63db0c608ae6bc3.png', '2000-08-25', 'test', '', 'test', ''),
(5, 'Screenshot_20191205_162745.jpg', '2000-08-25', 'test', '', 'test', ''),
(6, 'Screenshot_20191205_162745.jpg', '2000-08-25', 'test', '', 'test', ''),
(7, 'Screenshot_20191205_162745.jpg', '2000-08-25', 'test', '', 'test', ''),
(8, '401e59a2f5033dc7a63db0c608ae6bc3.png', '0302-02-10', 'Black Labour doodle, Friendly', 'yes', 'omphalumpaasdasd', ''),
(9, '401e59a2f5033dc7a63db0c608ae6bc3.png', '0302-02-10', 'Black Labour doodle, Friendly', 'yes', 'omphalumpaasdasd', ''),
(10, '401e59a2f5033dc7a63db0c608ae6bc3.png', '0302-02-10', 'Black Labour doodle, Friendly', 'yes', 'omphalumpaasdasd', ''),
(11, 'hoodie dudes.png', '0020-08-25', 'test', 'yes', 'testcrrsuerh', ''),
(12, 'hoodie dudes.png', '0020-08-25', 'test', 'yes', 'testcrrsuerh', '');

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `requestsid` int(11) NOT NULL,
  `accountid` int(11) NOT NULL,
  `animalid` int(11) NOT NULL,
  `status` varchar(250) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`requestsid`, `accountid`, `animalid`, `status`, `created_at`) VALUES
(84, 42, 24, 'Request Pending', '2021-05-04 01:34:19'),
(85, 32, 27, 'Request Pending', '2021-05-04 01:43:09'),
(88, 45, 24, 'Request Pending', '2021-05-04 10:43:38');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `Animalid` int(11) NOT NULL,
  `animalname` varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `avalibility` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `image` longtext COLLATE utf8mb4_unicode_520_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`Animalid`, `animalname`, `date`, `description`, `avalibility`, `name`, `image`) VALUES
(24, 'Eman', '2020-09-08', 'Turtle, loves to play but a bit slow', 'yes', '3m2nrm82aqe41.jpg', NULL),
(25, 'Converse', '2017-09-06', 'Lovely tabby cat, big personality to have around!', 'yes', 'tabbycat2.jpg', NULL),
(27, 'Danica', '2021-05-03', 'She is load and cranky. Does not play well with others.', 'Yes', 'cat1.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Admin`, `email`, `password`, `created_at`) VALUES
(23, 1, 'admin@gmail.co.uk', '$2y$10$UYAIadko7yVPp5nA9/AP9eKDIhPzy2yE1r9SktxwGd/gvoZZ.xNde', '2021-04-30 14:26:40'),
(32, 0, 'danicadavies@hotmail.co.uk', '$2y$10$oXnQHmvM5xqPECA3keezvejOgc31pwwXjCQJ.GHEIi6k7l4XW48ce', '2021-04-30 18:13:14'),
(33, 0, 'ravus.torn@gmail.com', '$2y$10$zlCLyZ2vcX9LR42igw4qf.0R/TktcPjHBkwmk86AUpPw/Ja2fecLq', '2021-04-30 18:26:07'),
(34, 0, 'danicasasnake@hotmail.com', '$2y$10$WGsbL.U8G55uaFH03dTui.YXpxZbzced7iNs70r.G3Yp5uC.eey2G', '2021-04-30 20:28:51'),
(36, 0, 'abbasieman@gmail.com', '$2y$10$yEy9NuHP.5OtP53OxMcCxOtTU7JTMcL5dxHbAZvyFizhcK5R6paNm', '2021-05-03 22:45:11'),
(38, 0, 'ilovesunil@hotmail.com', '$2y$10$uG7tsQO0OY.d8afn5b15OuzYquMzRCGuZ16s8krKBhBdrw8oX9f56', '2021-05-03 23:09:25'),
(39, 0, 'danica2davies@gmail.co.uk', '$2y$10$unY1pdaCVQJKpN9eVJkw5ezChz6ATcm4n248zdqD04d8KyPFr4YsK', '2021-05-04 00:11:45'),
(40, 0, 'thejellywolf@hotmail.co.uk', '$2y$10$/VSNd41ER83Z0qLPb0Y3TONW8Nj4p5kiA1lzTnBSUP/nSHLfuP306', '2021-05-04 00:46:00'),
(41, 0, 'danicafdavies@gmail.com', '$2y$10$zoFsY/9GWtpj144nGJnFaOHvsZPfiCVteBMrK7Q4iDHz3iC6ZZyzq', '2021-05-04 01:29:11'),
(42, 0, '190224882@aston.ac.uk', '$2y$10$UeJQ1HNefrfKpY.Ht92G9efqdH8Quw1xhsSDD0kGMF/rUhKGxjSpy', '2021-05-04 01:32:43');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`requestsid`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`Animalid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `animals`
--
ALTER TABLE `animals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `requestsid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `Animalid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
