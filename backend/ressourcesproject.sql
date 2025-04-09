-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 09 avr. 2025 à 18:09
-- Version du serveur : 5.7.24
-- Version de PHP : 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ressourcesproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `computers`
--

CREATE TABLE `computers` (
  `id` bigint(20) NOT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `hard_disk` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `screen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `departments`
--

CREATE TABLE `departments` (
  `head_user_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `maintenance_reports`
--

CREATE TABLE `maintenance_reports` (
  `frequency` tinyint(4) DEFAULT NULL,
  `issue_appearance_date` date DEFAULT NULL,
  `issue_type` tinyint(4) DEFAULT NULL,
  `report_date` date DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `maintenance_request_id` bigint(20) DEFAULT NULL,
  `technician_id` bigint(20) DEFAULT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `recommendation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `maintenance_requests`
--

CREATE TABLE `maintenance_requests` (
  `request_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `requester_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `notifications`
--

CREATE TABLE `notifications` (
  `is_read` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) DEFAULT NULL,
  `content` varchar(1000) NOT NULL,
  `sender` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `email_status` enum('DELIVERED','READ','SENT') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `printers`
--

CREATE TABLE `printers` (
  `print_speed` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `resolution` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `resources`
--

CREATE TABLE `resources` (
  `acquisition_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `warranty_end_date` date DEFAULT NULL,
  `assigned_to_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `inventory_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `resource_requests`
--

CREATE TABLE `resource_requests` (
  `print_speed` int(11) DEFAULT NULL,
  `resource_type` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `tender_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `hard_disk` varchar(255) DEFAULT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `resolution` varchar(255) DEFAULT NULL,
  `screen` varchar(255) DEFAULT NULL,
  `specifications` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `submission_items`
--

CREATE TABLE `submission_items` (
  `price` decimal(38,2) DEFAULT NULL,
  `print_speed` int(11) DEFAULT NULL,
  `resource_type` tinyint(4) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `request_id` bigint(20) DEFAULT NULL,
  `submission_id` bigint(20) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `hard_disk` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `resolution` varchar(255) DEFAULT NULL,
  `screen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `suppliers`
--

CREATE TABLE `suppliers` (
  `blacklisted` bit(1) NOT NULL,
  `date_blacklisted` date DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `blacklist_reason` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tenders`
--

CREATE TABLE `tenders` (
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tender_submissions`
--

CREATE TABLE `tender_submissions` (
  `delivery_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  `warranty_duration_months` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `tender_id` bigint(20) DEFAULT NULL,
  `rejection_reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `department_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','DEPARTMENT_HEAD','MAINTENANCE_TECHNICIAN','RESOURCE_MANAGER','SUPPLIER','TEACHER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `computers`
--
ALTER TABLE `computers`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKl0w6ki2kcpisp2k0rbfj2qt1j` (`head_user_id`);

--
-- Index pour la table `maintenance_reports`
--
ALTER TABLE `maintenance_reports`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKotilaegedujigd7equvu44vqv` (`maintenance_request_id`),
  ADD KEY `FKgle5rqs9fnlcv5rxb5tasrdv5` (`technician_id`);

--
-- Index pour la table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9kavl8ynpo26macokw8x63i1n` (`requester_id`),
  ADD KEY `FKtexsgm8yf5t4xvqjx74jowr6o` (`resource_id`);

--
-- Index pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqqnsjxlwleyjbxlmm213jaj3f` (`recipient_id`);

--
-- Index pour la table `printers`
--
ALTER TABLE `printers`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `resources`
--
ALTER TABLE `resources`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKl1toiehujvshdttk3h1dj81nm` (`inventory_number`),
  ADD KEY `FK2qtxj71ukey4borp6tkyqfhl7` (`assigned_to_user_id`),
  ADD KEY `FKklxgeebpym64ybmler4lfyl1f` (`department_id`),
  ADD KEY `FKjnwf1t0q7vglv8rob81apitrb` (`supplier_id`);

--
-- Index pour la table `resource_requests`
--
ALTER TABLE `resource_requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2gua22t0rpq6yvr85fkckii0e` (`department_id`),
  ADD KEY `FKr9b7nimrn0ufv9yfgutk5uy29` (`tender_id`),
  ADD KEY `FKlowlsnui9dvkqvlmnu5h909va` (`user_id`);

--
-- Index pour la table `submission_items`
--
ALTER TABLE `submission_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK43k25frap55jj12jkijc28krx` (`request_id`),
  ADD KEY `FKp0hc4t70p5f2sq9jjwdeg5o13` (`submission_id`);

--
-- Index pour la table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbchau2evg2tdp9dv3if1gblur` (`user_id`);

--
-- Index pour la table `tenders`
--
ALTER TABLE `tenders`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tender_submissions`
--
ALTER TABLE `tender_submissions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkxkr9ofa47w10hggo5iixs9nq` (`supplier_id`),
  ADD KEY `FK41u3un6qi2ilp3kclxoj840q1` (`tender_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsbg59w8q63i0oo53rlgvlcnjq` (`department_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `maintenance_reports`
--
ALTER TABLE `maintenance_reports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `resources`
--
ALTER TABLE `resources`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `resource_requests`
--
ALTER TABLE `resource_requests`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `submission_items`
--
ALTER TABLE `submission_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tenders`
--
ALTER TABLE `tenders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tender_submissions`
--
ALTER TABLE `tender_submissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `computers`
--
ALTER TABLE `computers`
  ADD CONSTRAINT `FK2s1j9pbmcx2q05nr68ypsux6j` FOREIGN KEY (`id`) REFERENCES `resources` (`id`);

--
-- Contraintes pour la table `departments`
--
ALTER TABLE `departments`
  ADD CONSTRAINT `FKokgb4wgt31shae42nsf1h0g57` FOREIGN KEY (`head_user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `maintenance_reports`
--
ALTER TABLE `maintenance_reports`
  ADD CONSTRAINT `FK9y71137gle2fgprk3ut8dekw1` FOREIGN KEY (`maintenance_request_id`) REFERENCES `maintenance_requests` (`id`),
  ADD CONSTRAINT `FKgle5rqs9fnlcv5rxb5tasrdv5` FOREIGN KEY (`technician_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `maintenance_requests`
--
ALTER TABLE `maintenance_requests`
  ADD CONSTRAINT `FK9kavl8ynpo26macokw8x63i1n` FOREIGN KEY (`requester_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKtexsgm8yf5t4xvqjx74jowr6o` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`);

--
-- Contraintes pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FKqqnsjxlwleyjbxlmm213jaj3f` FOREIGN KEY (`recipient_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `printers`
--
ALTER TABLE `printers`
  ADD CONSTRAINT `FKel6sr5tbxasvviw0j2a2vtsnq` FOREIGN KEY (`id`) REFERENCES `resources` (`id`);

--
-- Contraintes pour la table `resources`
--
ALTER TABLE `resources`
  ADD CONSTRAINT `FK2qtxj71ukey4borp6tkyqfhl7` FOREIGN KEY (`assigned_to_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKjnwf1t0q7vglv8rob81apitrb` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `FKklxgeebpym64ybmler4lfyl1f` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Contraintes pour la table `resource_requests`
--
ALTER TABLE `resource_requests`
  ADD CONSTRAINT `FK2gua22t0rpq6yvr85fkckii0e` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
  ADD CONSTRAINT `FKlowlsnui9dvkqvlmnu5h909va` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKr9b7nimrn0ufv9yfgutk5uy29` FOREIGN KEY (`tender_id`) REFERENCES `tenders` (`id`);

--
-- Contraintes pour la table `submission_items`
--
ALTER TABLE `submission_items`
  ADD CONSTRAINT `FK43k25frap55jj12jkijc28krx` FOREIGN KEY (`request_id`) REFERENCES `resource_requests` (`id`),
  ADD CONSTRAINT `FKp0hc4t70p5f2sq9jjwdeg5o13` FOREIGN KEY (`submission_id`) REFERENCES `tender_submissions` (`id`);

--
-- Contraintes pour la table `suppliers`
--
ALTER TABLE `suppliers`
  ADD CONSTRAINT `FKja3xaqihwgllahrmo5op9ks4d` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `tender_submissions`
--
ALTER TABLE `tender_submissions`
  ADD CONSTRAINT `FK41u3un6qi2ilp3kclxoj840q1` FOREIGN KEY (`tender_id`) REFERENCES `tenders` (`id`),
  ADD CONSTRAINT `FKkxkr9ofa47w10hggo5iixs9nq` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKsbg59w8q63i0oo53rlgvlcnjq` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
