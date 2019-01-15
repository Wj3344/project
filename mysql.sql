-- MySQL dump 10.13  Distrib 5.7.24-27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.7.24-27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!50717 SELECT COUNT(*) INTO @rocksdb_has_p_s_session_variables FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'performance_schema' AND TABLE_NAME = 'session_variables' */;
/*!50717 SET @rocksdb_get_is_supported = IF (@rocksdb_has_p_s_session_variables, 'SELECT COUNT(*) INTO @rocksdb_is_supported FROM performance_schema.session_variables WHERE VARIABLE_NAME=\'rocksdb_bulk_load\'', 'SELECT 0') */;
/*!50717 PREPARE s FROM @rocksdb_get_is_supported */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;
/*!50717 SET @rocksdb_enable_bulk_load = IF (@rocksdb_is_supported, 'SET SESSION rocksdb_bulk_load = 1', 'SET @rocksdb_dummy_bulk_load = 0') */;
/*!50717 PREPARE s FROM @rocksdb_enable_bulk_load */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;

--
-- Table structure for table `downloaded`
--

DROP TABLE IF EXISTS `downloaded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `downloaded` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消费记录id',
  `userId` int(11) NOT NULL COMMENT '查看者id',
  `messageId` int(11) NOT NULL COMMENT '查看的消息id',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '查看时间',
  PRIMARY KEY (`id`),
  KEY `downloaded_user` (`userId`),
  KEY `downloaded_plateMessage` (`messageId`),
  CONSTRAINT `downloaded_plateMessage` FOREIGN KEY (`messageId`) REFERENCES `plateMessage` (`id`),
  CONSTRAINT `downloaded_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `downloaded`
--

LOCK TABLES `downloaded` WRITE;
/*!40000 ALTER TABLE `downloaded` DISABLE KEYS */;
/*!40000 ALTER TABLE `downloaded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity`
--

DROP TABLE IF EXISTS `identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `description` varchar(255) NOT NULL COMMENT '等级说明',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity`
--

LOCK TABLES `identity` WRITE;
/*!40000 ALTER TABLE `identity` DISABLE KEYS */;
INSERT INTO `identity` VALUES (1,'超级管理员','admin'),(2,'板块管理员','boardManager'),(3,'板块助理','plateAssistant'),(4,'一般用户','user');
/*!40000 ALTER TABLE `identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `integralConsumption`
--

DROP TABLE IF EXISTS `integralConsumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `integralConsumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消费记录id',
  `consumer` int(11) NOT NULL COMMENT '消费积分的人',
  `beneficiary` int(11) NOT NULL COMMENT '积分受益者',
  `value` int(11) NOT NULL COMMENT '消费积分价值',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '积分消费时间',
  PRIMARY KEY (`id`),
  KEY `integralConsumption_user1` (`consumer`),
  KEY `integralConsumption_user2` (`beneficiary`),
  CONSTRAINT `integralConsumption_user1` FOREIGN KEY (`consumer`) REFERENCES `user` (`id`),
  CONSTRAINT `integralConsumption_user2` FOREIGN KEY (`beneficiary`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `integralConsumption`
--

LOCK TABLES `integralConsumption` WRITE;
/*!40000 ALTER TABLE `integralConsumption` DISABLE KEYS */;
/*!40000 ALTER TABLE `integralConsumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `author` int(11) NOT NULL COMMENT '留言人id',
  `message` varchar(255) NOT NULL COMMENT '留言信息',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
  `plateId` int(11) NOT NULL COMMENT '留言板块消息的id',
  PRIMARY KEY (`id`),
  KEY `message_user1` (`author`),
  KEY `message_plate` (`plateId`),
  CONSTRAINT `message_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`),
  CONSTRAINT `message_user1` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plate`
--

DROP TABLE IF EXISTS `plate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '板块ID',
  `name` varchar(255) NOT NULL COMMENT '板块名称',
  `referral` varchar(255) NOT NULL COMMENT '板块介绍',
  `admin` int(11) NOT NULL COMMENT '板块负责人id',
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `plate_user` FOREIGN KEY (`admin`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plate`
--

LOCK TABLES `plate` WRITE;
/*!40000 ALTER TABLE `plate` DISABLE KEYS */;
INSERT INTO `plate` VALUES (1,'文科专业','文科专业课板块_修改',2),(2,'理科专业','理科专业课板块',1),(3,'工科专业','工科专业课板块',1),(4,'外语课程','外语课程板块',1),(5,'研究方向','研究方向板块',1),(6,'关于学校','关于学校板块',1),(7,'测试添加板块标题','测试添加板块内容介绍',3);
/*!40000 ALTER TABLE `plate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plateAdmin`
--

DROP TABLE IF EXISTS `plateAdmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plateAdmin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `plateId` int(11) NOT NULL COMMENT '板块id',
  `userId` int(11) NOT NULL COMMENT '板块助理id',
  PRIMARY KEY (`id`),
  KEY `plateAdmin_user` (`userId`),
  KEY `plateAdmin_plate` (`plateId`),
  CONSTRAINT `plateAdmin_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`),
  CONSTRAINT `plateAdmin_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plateAdmin`
--

LOCK TABLES `plateAdmin` WRITE;
/*!40000 ALTER TABLE `plateAdmin` DISABLE KEYS */;
INSERT INTO `plateAdmin` VALUES (1,2,2);
/*!40000 ALTER TABLE `plateAdmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plateMessage`
--

DROP TABLE IF EXISTS `plateMessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plateMessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '板块消息标题',
  `content` text NOT NULL COMMENT '板块消息内容',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '板块消息添加时间',
  `plateId` int(11) NOT NULL COMMENT '所属板块id',
  `instructions` int(11) DEFAULT '0' COMMENT '板块消息的访问量',
  `replies` int(11) DEFAULT '0' COMMENT '板块消息回复量',
  `priority` int(11) DEFAULT '10' COMMENT '板块消息的优先级，影响板块消息的排名',
  `block` tinyint(1) DEFAULT '0' COMMENT '该消息时候允许显示，0不显示，1，显示，没通过审核为0',
  `userId` int(11) NOT NULL COMMENT '发布该消息的用户id',
  `value` int(11) NOT NULL DEFAULT '0' COMMENT '该消息的价值，价值大于0的时候为有偿信息，需要支付积分进行查看',
  PRIMARY KEY (`id`),
  KEY `plateMessage_plate` (`plateId`),
  KEY `plateMessage_user` (`userId`),
  CONSTRAINT `plateMessage_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`),
  CONSTRAINT `plateMessage_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plateMessage`
--

LOCK TABLES `plateMessage` WRITE;
/*!40000 ALTER TABLE `plateMessage` DISABLE KEYS */;
INSERT INTO `plateMessage` VALUES (1,'测试标题1','测试消息内容','2019-01-13 08:56:12',1,2,0,10,0,2,0),(2,'测试标题2','测试消息内容','2019-01-13 08:57:16',2,2,0,10,0,2,0),(3,'测试标题3','测试消息内容','2019-01-13 08:58:01',3,0,0,10,0,2,0),(4,'测试标题4','测试消息内容','2019-01-13 08:58:01',4,0,0,10,0,2,0),(5,'测试标题5','测试消息内容','2019-01-13 08:58:01',5,0,0,10,0,2,0),(6,'测试标题6','测试消息内容','2019-01-13 08:58:01',6,0,0,10,0,2,0),(7,'勾股定理','sasasas','2019-01-13 19:30:51',2,3,0,10,0,2,3),(8,'勾股定理','sasasa','2019-01-13 19:33:09',2,0,0,10,0,2,3);
/*!40000 ALTER TABLE `plateMessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privateLetter`
--

DROP TABLE IF EXISTS `privateLetter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privateLetter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `author` int(11) NOT NULL COMMENT '发件人id',
  `receiver` int(11) NOT NULL COMMENT '收件人id',
  `message` varchar(255) NOT NULL COMMENT '发件信息',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发件时间',
  `sign` tinyint(1) DEFAULT '0' COMMENT '是否查看，0未查看，1已查看',
  PRIMARY KEY (`id`),
  KEY `privateLetter_user1` (`author`),
  KEY `privateLetter_user2` (`receiver`),
  CONSTRAINT `privateLetter_user1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `privateLetter_user2` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privateLetter`
--

LOCK TABLES `privateLetter` WRITE;
/*!40000 ALTER TABLE `privateLetter` DISABLE KEYS */;
INSERT INTO `privateLetter` VALUES (1,1,2,'你好这是一封测试信件','2019-01-13 11:17:11',1);
/*!40000 ALTER TABLE `privateLetter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `identity` int(20) DEFAULT NULL COMMENT '角色id',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '权限id',
  KEY `identity` (`identity`),
  KEY `permissionId` (`permissionId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`identity`) REFERENCES `identity` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(200) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (15041225,'chen',18),(15041311,'yan yu',18);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploaded`
--

DROP TABLE IF EXISTS `uploaded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploaded` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '上传记录id',
  `title` varchar(255) NOT NULL COMMENT '板块消息标题',
  `content` text NOT NULL COMMENT '板块消息内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '板块消息添加时间',
  `plateId` int(11) NOT NULL COMMENT '所属板块id',
  `userId` int(11) NOT NULL COMMENT '上传者id',
  `value` int(11) NOT NULL DEFAULT '0' COMMENT '消息价值',
  `status` varchar(20) NOT NULL DEFAULT '未处理' COMMENT '消息处理状态',
  PRIMARY KEY (`id`),
  KEY `uploaded_user` (`userId`),
  KEY `uploaded_plate` (`plateId`),
  CONSTRAINT `uploaded_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`),
  CONSTRAINT `uploaded_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploaded`
--

LOCK TABLES `uploaded` WRITE;
/*!40000 ALTER TABLE `uploaded` DISABLE KEYS */;
INSERT INTO `uploaded` VALUES (1,'勾股定理','sasasas','2019-01-13 13:47:37',2,2,3,'通过'),(2,'勾股定理','sasasa','2019-01-13 13:49:24',2,2,3,'通过');
/*!40000 ALTER TABLE `uploaded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `username` varchar(200) NOT NULL COMMENT '用户姓名',
  `password` varchar(200) NOT NULL COMMENT '用户密码',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户昵称',
  `school` varchar(200) DEFAULT NULL COMMENT '学校',
  `studentId` int(11) NOT NULL COMMENT '学号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `picture` mediumblob COMMENT '用户头像',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `identity` int(11) NOT NULL DEFAULT '4' COMMENT '用户账户等级',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '用户积分',
  PRIMARY KEY (`id`,`studentId`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `user_studentId_uindex` (`studentId`),
  KEY `user_identity` (`identity`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','haha','南昌航空大学',1,'1427122032@qq.com',_binary '2019-01-10 20-31-32 的屏幕截图.png','sasasa',1,0),(2,'fanchen','123456',NULL,'南昌航空大学',15041225,'1441088980@qq.com',NULL,NULL,2,0),(3,'haha','123456',NULL,'南昌航空大学',15041311,'1522520@qq.com',NULL,NULL,4,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50112 SET @disable_bulk_load = IF (@is_rocksdb_supported, 'SET SESSION rocksdb_bulk_load = @old_rocksdb_bulk_load', 'SET @dummy_rocksdb_bulk_load = 0') */;
/*!50112 PREPARE s FROM @disable_bulk_load */;
/*!50112 EXECUTE s */;
/*!50112 DEALLOCATE PREPARE s */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-15 11:11:26
