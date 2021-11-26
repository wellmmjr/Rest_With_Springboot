CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `first_name` varchar(20) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `second_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);