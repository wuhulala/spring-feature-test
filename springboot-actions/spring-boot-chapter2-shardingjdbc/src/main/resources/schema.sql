DROP TABLE  IF EXISTS userinfo;

CREATE TABLE `userinfo` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)