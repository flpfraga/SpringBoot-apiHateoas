CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bithday` date DEFAULT NULL,
  `first_name` varchar(40) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `last_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
)