-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2020 at 12:57 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitaldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountant`
--

CREATE TABLE `accountant` (
  `FirstName` text NOT NULL,
  `Secondname` text NOT NULL,
  `DOB` date NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `PhoneNumber` int(10) NOT NULL,
  `AccountantID` int(10) NOT NULL,
  `EducationLevel` enum('Diploma','Degree','Masters','Phd') NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountant`
--

INSERT INTO `accountant` (`FirstName`, `Secondname`, `DOB`, `IDNumber`, `Gender`, `PhoneNumber`, `AccountantID`, `EducationLevel`, `RecruitmentDate`, `Password`) VALUES
('George', 'Okal', '1954-03-23', 284268674, 'Male', 768485959, 100, 'Degree', '1989-07-06', '11598421'),
('George', 'felix', '1984-07-02', 57690435, 'Male', 791655666, 101, 'Masters', '1989-07-06', '1111');

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `PatientName` text NOT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `DoctorName` text NOT NULL,
  `DoctorID` int(11) DEFAULT NULL,
  `BillNO` int(10) NOT NULL,
  `BillAmount` int(10) NOT NULL,
  `BillDetails` text NOT NULL,
  `AccountantName` text,
  `AccountantID` int(11) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`PatientName`, `PatientID`, `DoctorName`, `DoctorID`, `BillNO`, `BillAmount`, `BillDetails`, `AccountantName`, `AccountantID`, `Time`) VALUES
('felix otieno', 2, 'marvin omare', 1, 1, 2000, 'labtest', '', 100, '2019-07-21 01:51:37'),
('herbert obonyo', 1, 'marvin omare', 1, 2, 200, 'test', '', 101, '2019-07-21 01:51:37'),
('herbert obonyo', 1, 'marvin omare', 1, 3, 200, 'test', NULL, 101, '2019-07-21 01:51:37'),
('herbert obonyo', 1, 'marvin omare', 1, 4, 200, 'test', NULL, 101, '2019-07-21 01:51:37'),
('felix otieno', 2, 'marvin omare', 1, 5, 3000, 'malaria test', NULL, NULL, '2019-07-21 01:51:37');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `Department` varchar(20) NOT NULL,
  `Incharge` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `DOB` date NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `PhoneNumber` int(11) NOT NULL,
  `DoctorID` int(10) NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `EducationLevel` enum('Diploma','Degree','Masters','Phd') NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Ward` varchar(20) DEFAULT NULL,
  `Department` varchar(20) DEFAULT NULL,
  `Password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Firstname`, `Secondname`, `DOB`, `IDNumber`, `PhoneNumber`, `DoctorID`, `Gender`, `EducationLevel`, `RecruitmentDate`, `Ward`, `Department`, `Password`) VALUES
('Marvin ', 'Omare', '1845-02-20', 29846648, 764616516, 1, 'Male', 'Masters', '0000-00-00', NULL, NULL, 1111);

-- --------------------------------------------------------

--
-- Table structure for table `inpatient`
--

CREATE TABLE `inpatient` (
  `InpatientID` int(10) NOT NULL,
  `Patientname` text NOT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `PhoneNumber` int(11) NOT NULL,
  `Ward` varchar(20) DEFAULT NULL,
  `BedNo` int(10) NOT NULL,
  `MedicalCondition` text,
  `PatientMedication` text NOT NULL,
  `KinName` varchar(20) DEFAULT NULL,
  `KinNo` int(10) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inpatient`
--

INSERT INTO `inpatient` (`InpatientID`, `Patientname`, `PatientID`, `PhoneNumber`, `Ward`, `BedNo`, `MedicalCondition`, `PatientMedication`, `KinName`, `KinNo`, `Time`) VALUES
(1, 'herbert obonyo', 1, 702107343, 'Adults', 11, 'malaria', 'antimalarials', 'teddy marvin', 744545488, '2019-07-21 01:52:08'),
(2, 'seydney ', 15, 745863536, 'adults', 5, 'headache', 'panadol', 'pauline', 702107343, '2019-07-30 09:08:01');

-- --------------------------------------------------------

--
-- Table structure for table `labaratory`
--

CREATE TABLE `labaratory` (
  `PatientName` text NOT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `DoctorName` text NOT NULL,
  `DoctorID` int(10) NOT NULL,
  `TestName` text NOT NULL,
  `TestID` int(10) NOT NULL,
  `TestResult` text NOT NULL,
  `LabtechName` text,
  `LabtechID` int(11) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labaratory`
--

INSERT INTO `labaratory` (`PatientName`, `PatientID`, `DoctorName`, `DoctorID`, `TestName`, `TestID`, `TestResult`, `LabtechName`, `LabtechID`, `Time`) VALUES
('herbert obonyo', 1, 'marvin omare', 1, 'malaria', 1, 'negative', NULL, 1, '2019-07-29 13:57:07');

-- --------------------------------------------------------

--
-- Table structure for table `labtech`
--

CREATE TABLE `labtech` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `PhoneNumber` int(10) NOT NULL,
  `DOB` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `LabtechID` int(10) NOT NULL,
  `EducationLevel` enum('Diploma','Degree','Masters','Phd') NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labtech`
--

INSERT INTO `labtech` (`Firstname`, `Secondname`, `PhoneNumber`, `DOB`, `Gender`, `IDNumber`, `LabtechID`, `EducationLevel`, `RecruitmentDate`, `Password`) VALUES
('Elizabeth', 'Mukuyu', 763419966, '1994-04-17', 'Female', 34684685, 1, 'Diploma', '1998-08-18', 1113);

-- --------------------------------------------------------

--
-- Table structure for table `nurse`
--

CREATE TABLE `nurse` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `DOB` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `NurseID` int(10) NOT NULL,
  `PhoneNumber` int(11) NOT NULL,
  `EducationLevel` enum('Diploma','Degree','Masters','Phd') NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Ward` varchar(20) DEFAULT NULL,
  `Department` varchar(20) DEFAULT NULL,
  `Password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nurse`
--

INSERT INTO `nurse` (`Firstname`, `Secondname`, `DOB`, `Gender`, `IDNumber`, `NurseID`, `PhoneNumber`, `EducationLevel`, `RecruitmentDate`, `Ward`, `Department`, `Password`) VALUES
('Yvonne', 'Presca', '1993-04-13', 'Female', 2587257, 1, 7546869, 'Masters', '2016-04-16', NULL, NULL, 1111);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `DOB` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Phonenumber` int(10) NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `PatientID` int(10) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`Firstname`, `Secondname`, `DOB`, `Gender`, `Phonenumber`, `IDNumber`, `PatientID`, `Time`) VALUES
('herbert', 'obonyo', '1997-06-06', 'Male', 768945988, 0, 1, '2019-07-21 01:53:56'),
('felix', 'otieno', '1994-05-24', 'Male', 728254564, 0, 2, '2019-07-21 01:53:56'),
('mary', 'keeem', '1993-10-23', 'Female', 728254564, 0, 6, '2019-07-21 01:53:56'),
('her', 'nck', '1998-04-23', 'Male', 76545854, 32545748, 7, '2019-07-21 01:53:56'),
('her', 'nck', '1998-04-23', 'Male', 76545854, 32545748, 8, '2019-07-21 01:53:56'),
('herbert', 'otieno', '1994-02-03', 'Male', 788945988, 15603053, 10, '2019-07-29 19:14:42'),
('seydney', 'achala', '1998-02-14', 'Female', 745863536, 34691426, 15, '2019-07-30 09:01:35');

-- --------------------------------------------------------

--
-- Table structure for table `patientdiagnosis`
--

CREATE TABLE `patientdiagnosis` (
  `PatientName` text NOT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `DoctorName` text NOT NULL,
  `DoctorID` int(11) DEFAULT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Age` int(10) NOT NULL,
  `DiagnosisNo` int(10) NOT NULL,
  `DiagDetails` text NOT NULL,
  `Remarks` text,
  `Other` text,
  `PatientType` enum('IN','OUT') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientdiagnosis`
--

INSERT INTO `patientdiagnosis` (`PatientName`, `PatientID`, `DoctorName`, `DoctorID`, `Gender`, `Age`, `DiagnosisNo`, `DiagDetails`, `Remarks`, `Other`, `PatientType`) VALUES
('sydney', 15, 'Marvin', 1, 'Female', 16, 1, 'headaches', 'get panadol', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist`
--

CREATE TABLE `pharmacist` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `DOB` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `PharmacistID` int(10) NOT NULL,
  `PhoneNumber` int(10) NOT NULL,
  `EducationLevel` enum('Diploma','Degree','Masters','Phd') NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacist`
--

INSERT INTO `pharmacist` (`Firstname`, `Secondname`, `DOB`, `Gender`, `IDNumber`, `PharmacistID`, `PhoneNumber`, `EducationLevel`, `RecruitmentDate`, `Password`) VALUES
('clyde', 'wanjiku', '1993-03-19', 'Female', 35268445, 1, 79419495, 'Masters', '1998-03-27', 1114);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `PatientName` text NOT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `DoctorName` text NOT NULL,
  `DoctorID` int(11) DEFAULT NULL,
  `DrugName` varchar(50) DEFAULT NULL,
  `DrugAmount` int(10) NOT NULL,
  `Type` enum('boxes','pieces') NOT NULL,
  `PharmacistName` text,
  `PharmacistID` int(11) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`PatientName`, `PatientID`, `DoctorName`, `DoctorID`, `DrugName`, `DrugAmount`, `Type`, `PharmacistName`, `PharmacistID`, `Time`) VALUES
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23'),
('herbert obonyo', 1, 'marvin omare', 1, 'panadol', 10, 'pieces', NULL, 1, '2019-07-21 01:55:23');

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `DrugID` int(10) NOT NULL,
  `DrugName` varchar(50) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Type` enum('Pieces','Boxes') NOT NULL,
  `StorekeeperName` text,
  `StorekeeperID` int(11) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`DrugID`, `DrugName`, `Amount`, `Type`, `StorekeeperName`, `StorekeeperID`, `Time`) VALUES
(1, 'paracetamol', 40, 'Boxes', NULL, NULL, '2019-07-21 01:57:35'),
(2, 'panadol', 30, 'Boxes', NULL, 1, '2019-07-25 14:02:55'),
(3, 'coughcyrup', 20, 'Boxes', NULL, 1, '2019-07-25 15:26:32');

-- --------------------------------------------------------

--
-- Table structure for table `storekeeper`
--

CREATE TABLE `storekeeper` (
  `Firstname` text NOT NULL,
  `Secondname` text NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `DOB` date NOT NULL,
  `IDNumber` int(10) NOT NULL,
  `PhoneNumber` int(10) NOT NULL,
  `StorekeeperID` int(10) NOT NULL,
  `RecruitmentDate` date NOT NULL,
  `Password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storekeeper`
--

INSERT INTO `storekeeper` (`Firstname`, `Secondname`, `Gender`, `DOB`, `IDNumber`, `PhoneNumber`, `StorekeeperID`, `RecruitmentDate`, `Password`) VALUES
('Kevin', ' John', 'Male', '1987-02-24', 287643689, 764568546, 1, '1998-07-02', 1112);

-- --------------------------------------------------------

--
-- Table structure for table `ward`
--

CREATE TABLE `ward` (
  `Ward` varchar(20) NOT NULL,
  `Incharge` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ward`
--

INSERT INTO `ward` (`Ward`, `Incharge`) VALUES
('adults', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountant`
--
ALTER TABLE `accountant`
  ADD PRIMARY KEY (`AccountantID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`);

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`BillNO`),
  ADD KEY `AccountantID` (`AccountantID`),
  ADD KEY `DoctorID` (`DoctorID`),
  ADD KEY `PatientID` (`PatientID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`Department`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`DoctorID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`),
  ADD KEY `Ward` (`Ward`),
  ADD KEY `Department` (`Department`);

--
-- Indexes for table `inpatient`
--
ALTER TABLE `inpatient`
  ADD PRIMARY KEY (`InpatientID`),
  ADD UNIQUE KEY `BedNo` (`BedNo`),
  ADD KEY `Ward` (`Ward`),
  ADD KEY `PatientID` (`PatientID`);

--
-- Indexes for table `labaratory`
--
ALTER TABLE `labaratory`
  ADD PRIMARY KEY (`TestID`),
  ADD UNIQUE KEY `PatientID` (`DoctorID`,`TestID`),
  ADD KEY `LabtechID` (`LabtechID`),
  ADD KEY `PatientID_2` (`PatientID`);

--
-- Indexes for table `labtech`
--
ALTER TABLE `labtech`
  ADD PRIMARY KEY (`LabtechID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`);

--
-- Indexes for table `nurse`
--
ALTER TABLE `nurse`
  ADD PRIMARY KEY (`NurseID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`),
  ADD KEY `Ward` (`Ward`),
  ADD KEY `Department` (`Department`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`PatientID`);

--
-- Indexes for table `patientdiagnosis`
--
ALTER TABLE `patientdiagnosis`
  ADD PRIMARY KEY (`DiagnosisNo`),
  ADD KEY `DoctorID` (`DoctorID`),
  ADD KEY `PatientID` (`PatientID`);

--
-- Indexes for table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`PharmacistID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD KEY `DoctorID` (`DoctorID`),
  ADD KEY `PharmacistID` (`PharmacistID`),
  ADD KEY `PatientID` (`PatientID`),
  ADD KEY `DrugName` (`DrugName`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`DrugID`),
  ADD UNIQUE KEY `Drug` (`DrugName`),
  ADD KEY `StorekeeperID` (`StorekeeperID`);

--
-- Indexes for table `storekeeper`
--
ALTER TABLE `storekeeper`
  ADD PRIMARY KEY (`StorekeeperID`),
  ADD UNIQUE KEY `IDNumber` (`IDNumber`);

--
-- Indexes for table `ward`
--
ALTER TABLE `ward`
  ADD PRIMARY KEY (`Ward`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `BillNO` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `inpatient`
--
ALTER TABLE `inpatient`
  MODIFY `InpatientID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `labaratory`
--
ALTER TABLE `labaratory`
  MODIFY `TestID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `nurse`
--
ALTER TABLE `nurse`
  MODIFY `NurseID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `PatientID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `patientdiagnosis`
--
ALTER TABLE `patientdiagnosis`
  MODIFY `DiagnosisNo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `store`
--
ALTER TABLE `store`
  MODIFY `DrugID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`AccountantID`) REFERENCES `accountant` (`AccountantID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `accounts_ibfk_3` FOREIGN KEY (`DoctorID`) REFERENCES `doctor` (`DoctorID`),
  ADD CONSTRAINT `accounts_ibfk_4` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`Ward`) REFERENCES `ward` (`Ward`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`Department`) REFERENCES `department` (`Department`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `inpatient`
--
ALTER TABLE `inpatient`
  ADD CONSTRAINT `inpatient_ibfk_2` FOREIGN KEY (`Ward`) REFERENCES `ward` (`Ward`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `inpatient_ibfk_3` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`);

--
-- Constraints for table `labaratory`
--
ALTER TABLE `labaratory`
  ADD CONSTRAINT `labaratory_ibfk_1` FOREIGN KEY (`LabtechID`) REFERENCES `labtech` (`LabtechID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `labaratory_ibfk_2` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`);

--
-- Constraints for table `nurse`
--
ALTER TABLE `nurse`
  ADD CONSTRAINT `nurse_ibfk_1` FOREIGN KEY (`Ward`) REFERENCES `ward` (`Ward`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `nurse_ibfk_2` FOREIGN KEY (`Department`) REFERENCES `department` (`Department`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `patientdiagnosis`
--
ALTER TABLE `patientdiagnosis`
  ADD CONSTRAINT `patientdiagnosis_ibfk_2` FOREIGN KEY (`DoctorID`) REFERENCES `doctor` (`DoctorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `patientdiagnosis_ibfk_3` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`);

--
-- Constraints for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD CONSTRAINT `pharmacy_ibfk_2` FOREIGN KEY (`DoctorID`) REFERENCES `doctor` (`DoctorID`),
  ADD CONSTRAINT `pharmacy_ibfk_3` FOREIGN KEY (`PharmacistID`) REFERENCES `pharmacist` (`PharmacistID`),
  ADD CONSTRAINT `pharmacy_ibfk_5` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`),
  ADD CONSTRAINT `pharmacy_ibfk_6` FOREIGN KEY (`DrugName`) REFERENCES `store` (`DrugName`);

--
-- Constraints for table `store`
--
ALTER TABLE `store`
  ADD CONSTRAINT `store_ibfk_1` FOREIGN KEY (`StorekeeperID`) REFERENCES `storekeeper` (`StorekeeperID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
