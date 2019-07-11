# ************************************************************
# Sequel Pro SQL dump
# バージョン 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# ホスト: 127.0.0.1 (MySQL 5.6.42)
# データベース: todolist
# 作成時刻: 2019-07-11 05:32:47 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# テーブルのダンプ todoitems
# ------------------------------------------------------------

DROP TABLE IF EXISTS `todoitems`;

CREATE TABLE `todoitems` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `todo_name` varchar(255) DEFAULT '',
  `deadline` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `todoitems` WRITE;
/*!40000 ALTER TABLE `todoitems` DISABLE KEYS */;

INSERT INTO `todoitems` (`id`, `todo_name`, `deadline`, `status`, `created_at`)
VALUES
	(92,'JavaでWebアプリ開発','2019年8月22日',0,'2019-07-11 14:16:11'),
	(93,'(更新)Pythonで機械学習','(更新)2019年7月22日',0,'2019-07-11 14:18:32'),
	(94,'Node.jsでWebアプリ開発','2019年11月6日',1,'2019-07-11 14:17:51');

/*!40000 ALTER TABLE `todoitems` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
