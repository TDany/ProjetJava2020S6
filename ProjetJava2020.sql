-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 22, 2020 at 05:25 PM
-- Server version: 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ProjetJava2020`
--

-- --------------------------------------------------------

--
-- Table structure for table `Cours`
--

CREATE TABLE `Cours` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Cours`
--

INSERT INTO `Cours` (`ID`, `Nom`) VALUES
(1, 'JAVA'),
(2, 'MOOC-NANO'),
(3, 'ANGLAIS'),
(4, 'WEB'),
(5, 'BUSINESS'),
(6, 'ANATOMIE');

-- --------------------------------------------------------

--
-- Table structure for table `Enseignant`
--

CREATE TABLE `Enseignant` (
  `ID_Utilisateur` int(11) NOT NULL,
  `ID_Cours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Enseignant`
--

INSERT INTO `Enseignant` (`ID_Utilisateur`, `ID_Cours`) VALUES
(2, 1),
(18, 2),
(17, 3),
(2, 4),
(17, 5),
(18, 6);

-- --------------------------------------------------------

--
-- Table structure for table `Etudiant`
--

CREATE TABLE `Etudiant` (
  `ID_Utilisateur` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Etudiant`
--

INSERT INTO `Etudiant` (`ID_Utilisateur`, `Numero`, `ID_Groupe`) VALUES
(1, 1011, 1),
(5, 1052, 2),
(6, 1061, 1),
(7, 1073, 3),
(8, 1083, 3),
(9, 1094, 4),
(10, 10104, 4),
(11, 10115, 5),
(12, 10122, 2),
(13, 10135, 5),
(14, 10146, 6),
(15, 10156, 6);

-- --------------------------------------------------------

--
-- Table structure for table `Groupe`
--

CREATE TABLE `Groupe` (
  `ID` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `ID_Promotion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Groupe`
--

INSERT INTO `Groupe` (`ID`, `nom`, `ID_Promotion`) VALUES
(1, 'Grp01', 2),
(2, 'Grp02', 2),
(3, 'Grp01', 1),
(4, 'Grp02', 1),
(5, 'Grp01', 3),
(6, 'Grp02', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Promotion`
--

CREATE TABLE `Promotion` (
  `ID` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Promotion`
--

INSERT INTO `Promotion` (`ID`, `nom`) VALUES
(1, 'PROMO2022'),
(2, 'PROMO2021'),
(3, 'PROMO2023');

-- --------------------------------------------------------

--
-- Table structure for table `Salle`
--

CREATE TABLE `Salle` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `ID_Site` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Salle`
--

INSERT INTO `Salle` (`ID`, `Nom`, `Capacite`, `ID_Site`) VALUES
(1, 'P101', 155, 1),
(2, 'P102', 155, 1),
(3, 'P201', 35, 2),
(4, 'P202', 35, 2),
(5, 'P301', 25, 3),
(6, 'P302', 30, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Seance`
--

CREATE TABLE `Seance` (
  `ID` int(11) NOT NULL,
  `Semaine` int(11) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `Heure_Debut` varchar(255) NOT NULL,
  `Heure_Fin` varchar(255) NOT NULL,
  `Etat` int(11) NOT NULL,
  `ID_Cours` int(11) NOT NULL,
  `ID_Type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Seance_Enseignants`
--

CREATE TABLE `Seance_Enseignants` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Enseignant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Seance_Groupes`
--

CREATE TABLE `Seance_Groupes` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Seance_Salles`
--

CREATE TABLE `Seance_Salles` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Salle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Site`
--

CREATE TABLE `Site` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Site`
--

INSERT INTO `Site` (`ID`, `Nom`) VALUES
(1, 'E1'),
(2, 'E2'),
(3, 'E3');

-- --------------------------------------------------------

--
-- Table structure for table `Type_Cours`
--

CREATE TABLE `Type_Cours` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Type_Cours`
--

INSERT INTO `Type_Cours` (`ID`, `Nom`) VALUES
(1, 'TD'),
(2, 'AMPHI'),
(3, 'MOOC'),
(4, 'TP'),
(5, 'PROJET');

-- --------------------------------------------------------

--
-- Table structure for table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `ID` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Passwd` varchar(255) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Droit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`ID`, `Email`, `Passwd`, `Nom`, `Prenom`, `Droit`) VALUES
(1, 'Timothée.bois@edu.ece.fr', 'timtimbois2', 'Bois', 'Timothee', 4),
(2, 'segado@edu.ece.fr', 'segsegado', 'Segado', 'JP', 3),
(3, 'admin@edu.ece.fr', 'admin', 'Admin', 'Jean', 1),
(4, 'pedago@edu.ece.fr', 'pedago2', 'pedago', 'Jeanne', 2),
(5, 'dany@edu.ece.fr', 'dandany', 'Tadrous', 'Dany', 4),
(6, 'adrien@edu.ece.fr', 'adadrien', 'Guieysse', 'Adrien', 4),
(7, 'clemence@edu.ece.fr', 'clemclemence', 'Jldm ', 'Clémence ', 4),
(8, 'alice@edu.ece.fr', 'alalice', 'David', 'Alice', 4),
(9, 'paul@edu.ece.fr', 'papaul', 'Mrq', 'Paul', 4),
(10, 'marie@edu.ece.fr', 'marmarie', 'Leygue', 'Marie', 4),
(11, 'chloe@edu.ece.fr', 'chlchloe', 'Lamotte', 'Chloe', 4),
(12, 'Jessica@edu.ece.fr', 'jessjessica', 'Trez', 'Jessica', 4),
(13, 'johnny@edu.ece.fr', 'jojohnny', 'Ashrah', 'Johnny', 4),
(14, 'mahmood@edu.ece.fr', 'mahmahmood', 'Mood', 'Mahmood', 4),
(15, 'murphy@edu.ece.fr', 'murmurphy', 'Murphy', 'Ryan', 4),
(16, 'maleficent@edu.ece.fr', 'malmaleficent', 'Jolie', 'Maleficent', 4),
(17, 'disney@edu.ece.fr', 'didisney', 'Disney', 'Walt', 3),
(18, 'trieste@edu.ece.fr', 'tritrieste', 'Tekha', 'Trieste', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Cours`
--
ALTER TABLE `Cours`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Enseignant`
--
ALTER TABLE `Enseignant`
  ADD PRIMARY KEY (`ID_Utilisateur`,`ID_Cours`),
  ADD KEY `ID_Cours` (`ID_Cours`);

--
-- Indexes for table `Etudiant`
--
ALTER TABLE `Etudiant`
  ADD PRIMARY KEY (`ID_Utilisateur`),
  ADD KEY `ID_Groupe` (`ID_Groupe`);

--
-- Indexes for table `Groupe`
--
ALTER TABLE `Groupe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Promotion` (`ID_Promotion`);

--
-- Indexes for table `Promotion`
--
ALTER TABLE `Promotion`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Salle`
--
ALTER TABLE `Salle`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Site` (`ID_Site`);

--
-- Indexes for table `Seance`
--
ALTER TABLE `Seance`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_CoursS` (`ID_Cours`),
  ADD KEY `ID_TypeS` (`ID_Type`);

--
-- Indexes for table `Seance_Enseignants`
--
ALTER TABLE `Seance_Enseignants`
  ADD PRIMARY KEY (`ID_Seance`,`ID_Enseignant`),
  ADD KEY `ID_EnseignantSE` (`ID_Enseignant`);

--
-- Indexes for table `Seance_Groupes`
--
ALTER TABLE `Seance_Groupes`
  ADD PRIMARY KEY (`ID_Seance`,`ID_Groupe`),
  ADD KEY `ID_GroupeSG` (`ID_Groupe`);

--
-- Indexes for table `Seance_Salles`
--
ALTER TABLE `Seance_Salles`
  ADD PRIMARY KEY (`ID_Seance`,`ID_Salle`),
  ADD KEY `ID_SalleSS` (`ID_Salle`);

--
-- Indexes for table `Site`
--
ALTER TABLE `Site`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Type_Cours`
--
ALTER TABLE `Type_Cours`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Cours`
--
ALTER TABLE `Cours`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Groupe`
--
ALTER TABLE `Groupe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Promotion`
--
ALTER TABLE `Promotion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Salle`
--
ALTER TABLE `Salle`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Seance`
--
ALTER TABLE `Seance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Site`
--
ALTER TABLE `Site`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Type_Cours`
--
ALTER TABLE `Type_Cours`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Enseignant`
--
ALTER TABLE `Enseignant`
  ADD CONSTRAINT `ID_Cours` FOREIGN KEY (`ID_Cours`) REFERENCES `Cours` (`ID`),
  ADD CONSTRAINT `ID_UtilisateurE` FOREIGN KEY (`ID_Utilisateur`) REFERENCES `Utilisateur` (`ID`);

--
-- Constraints for table `Etudiant`
--
ALTER TABLE `Etudiant`
  ADD CONSTRAINT `ID_Groupe` FOREIGN KEY (`ID_Groupe`) REFERENCES `Groupe` (`ID`),
  ADD CONSTRAINT `ID_Utilisateur` FOREIGN KEY (`ID_Utilisateur`) REFERENCES `Utilisateur` (`ID`);

--
-- Constraints for table `Groupe`
--
ALTER TABLE `Groupe`
  ADD CONSTRAINT `ID_Promotion` FOREIGN KEY (`ID_Promotion`) REFERENCES `Promotion` (`ID`);

--
-- Constraints for table `Salle`
--
ALTER TABLE `Salle`
  ADD CONSTRAINT `ID_Site` FOREIGN KEY (`ID_Site`) REFERENCES `Site` (`ID`);

--
-- Constraints for table `Seance`
--
ALTER TABLE `Seance`
  ADD CONSTRAINT `ID_CoursS` FOREIGN KEY (`ID_Cours`) REFERENCES `Cours` (`ID`),
  ADD CONSTRAINT `ID_TypeS` FOREIGN KEY (`ID_Type`) REFERENCES `Type_Cours` (`ID`);

--
-- Constraints for table `Seance_Enseignants`
--
ALTER TABLE `Seance_Enseignants`
  ADD CONSTRAINT `ID_EnseignantSE` FOREIGN KEY (`ID_Enseignant`) REFERENCES `Enseignant` (`ID_Utilisateur`),
  ADD CONSTRAINT `ID_SeanceSE` FOREIGN KEY (`ID_Seance`) REFERENCES `Seance` (`ID`);

--
-- Constraints for table `Seance_Groupes`
--
ALTER TABLE `Seance_Groupes`
  ADD CONSTRAINT `ID_GroupeSG` FOREIGN KEY (`ID_Groupe`) REFERENCES `Groupe` (`ID`),
  ADD CONSTRAINT `ID_SeanceSG` FOREIGN KEY (`ID_Seance`) REFERENCES `Seance` (`ID`);

--
-- Constraints for table `Seance_Salles`
--
ALTER TABLE `Seance_Salles`
  ADD CONSTRAINT `ID_SalleSS` FOREIGN KEY (`ID_Salle`) REFERENCES `Salle` (`ID`),
  ADD CONSTRAINT `ID_SeanceSS` FOREIGN KEY (`ID_Seance`) REFERENCES `Seance` (`ID`);
