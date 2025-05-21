-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2025 at 03:13 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int(11) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `penulis` varchar(255) NOT NULL,
  `penerbit` varchar(255) NOT NULL,
  `lokasi` varchar(100) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `judul`, `genre`, `penulis`, `penerbit`, `lokasi`, `stok`) VALUES
(2, 'Bumi Manusiaaaa', 'Novel Sejarah', 'Pramoedya Ananta Toer', 'Hasta Mitra', 'Rak A-2', 5),
(3, 'Filosofi Teras', 'Filsafat', 'Henry Manampiring', 'Kompas', 'Rak B-3', 8),
(4, 'Sang Pemimpi', 'Novel', 'Andrea Hirata', 'Bentang Pustaka', 'Rak A-1', 7),
(5, 'Negeri 5 Menara', 'Novel', 'Ahmad Fuadi', 'Gramedia', 'Rak A-3', 12),
(6, 'Atomic Habits', 'Self-Help', 'James Clear', 'Gramedia', 'Rak C-2', 3),
(7, 'Sapiens', 'Sejarah', 'Yuval Noah Harari', 'Gramedia', 'Rak B-1', 4),
(8, 'Tentang Kamu', 'Novel', 'Tere Liye', 'Republika', 'Rak A-4', 6),
(9, 'Sejarah Indonesia Modern', 'Sejarah', 'M.C. Ricklefs', 'Serambi', 'Rak B-2', 2),
(10, 'Madilog', 'Filsafat', 'Tan Malaka', 'LPPM Tan Malaka', 'Rak B-4', 3),
(11, 'elpandra', 'sejarah', 'elpandra', 'erlangga', 'cirebon', 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
