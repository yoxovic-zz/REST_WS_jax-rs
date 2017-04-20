-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2016 at 02:12 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rest_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `auto`
--

CREATE TABLE IF NOT EXISTS `auto` (
  `auto_id` int(11) NOT NULL AUTO_INCREMENT,
  `marka` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `godiste` int(4) NOT NULL,
  `kubikaza` float NOT NULL,
  `boja` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cena` float NOT NULL,
  PRIMARY KEY (`auto_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `auto`
--

INSERT INTO `auto` (`auto_id`, `marka`, `model`, `godiste`, `kubikaza`, `boja`, `cena`) VALUES
(1, 'Peugeot', '206', 2002, 1600, 'siva-metalik', 2500),
(2, 'FIAT', '500L', 2013, 1300, 'crvena-metalik', 7900),
(3, 'Škoda', 'Octavia 1.6 TDI', 2011, 1598, 'crna', 7999),
(4, 'Porsche', 'Cayenne', 2007, 4511, 'crna', 23900),
(5, 'Citroen', 'Xsara', 2002, 2000, 'crna', 1850);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
