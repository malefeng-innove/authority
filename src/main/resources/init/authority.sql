-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: authority
-- ------------------------------------------------------
-- Server version	5.7.17-log

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

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(32) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `parent_menu` varchar(32) DEFAULT NULL COMMENT '父菜单编号',
  `menu_status` tinyint(4) DEFAULT NULL COMMENT '菜单状态(1-正常 -1-删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) DEFAULT NULL COMMENT '操作用户编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (1,'qc_data','质控数据','top',1,'2020-01-07 15:49:06',NULL,NULL,NULL),(2,'bi_all','BI报表','top',1,'2020-01-07 15:49:08',NULL,NULL,NULL),(3,'bi_data_chart','检验数据报表','bi_all',1,'2020-01-07 15:49:10',NULL,NULL,NULL),(4,'staff_all','人员组织架构','top',1,'2020-01-07 15:49:12',NULL,NULL,NULL),(7,'system_all','系统设置','top',1,'2020-01-07 15:49:21',NULL,NULL,NULL),(8,'user_list','账户管理','system_all',1,'2020-01-07 15:49:23',NULL,NULL,NULL),(9,'role_list','角色管理','system_all',1,'2020-01-07 15:49:25',NULL,NULL,NULL),(10,'dic_list','数据字典','system_all',1,'2020-01-07 15:49:28',NULL,NULL,NULL),(15,'equip_list','设备管理','system_all',1,'2020-01-21 10:11:24',NULL,NULL,NULL),(16,'qc_chart','质控报表','qc_data',1,NULL,NULL,NULL,NULL),(17,'qc_statistics','质控统计','qc_data',1,NULL,NULL,NULL,NULL),(18,'qc_environment','质控环境','qc_data',1,NULL,NULL,NULL,NULL),(19,'bi_item_chart','检验项目报表','bi_all',1,NULL,NULL,NULL,NULL),(20,'year_qc_plan','年度质控计划','bi_all',1,NULL,NULL,NULL,NULL),(21,'check_item','检验项目维护','system_all',1,NULL,NULL,NULL,NULL),(23,'data_exchange_center','数据交互中心','top',1,NULL,NULL,NULL,NULL),(24,'check_data_search','检验数据查询','top',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL COMMENT '角色编号',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_status` tinyint(4) DEFAULT NULL COMMENT '角色状态(1-正常 -1-删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) DEFAULT NULL COMMENT '操作用户编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (2,'2','操作员',1,'2020-01-07 13:59:17','2020-02-10 16:27:29','test1',NULL),(8,'41J20200107173540963w359622560','检验科主任',1,'2020-01-07 16:51:49','2020-02-10 16:27:55','test1',NULL),(11,'41F20200108165714526o570133263','管理员',1,'2020-01-08 16:57:14','2020-02-07 11:38:38','test1','string');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL COMMENT '角色编号',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) DEFAULT NULL COMMENT '操作用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='角色菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` VALUES (14,'null','bi_all','2020-01-07 17:19:51',NULL),(15,'null','bi_detail','2020-01-07 17:19:51',NULL),(16,'null','staff_all','2020-01-07 17:19:51',NULL),(17,'null','staff_list','2020-01-07 17:19:51',NULL),(18,'null','organization_list','2020-01-07 17:19:51',NULL),(19,'null','bi_all','2020-01-07 17:31:24',NULL),(20,'null','bi_detail','2020-01-07 17:31:24',NULL),(21,'null','staff_list','2020-01-07 17:31:24',NULL),(22,'41J20200107173540963w359622560','bi_all','2020-01-07 17:35:41',NULL),(24,'41J20200107173540963w359622560','staff_all','2020-01-07 17:35:41',NULL),(35,'41F20200108165714526o570133263','bi_all','2020-01-08 16:57:15',NULL),(38,'41F20200108165714526o570133263','system_all','2020-01-08 16:57:15',NULL),(39,'41F20200108165714526o570133263','user_list','2020-01-08 16:57:15',NULL),(40,'41F20200108165714526o570133263','role_list','2020-01-08 16:57:15',NULL),(41,'41F20200108165714526o570133263','dic_list','2020-01-08 16:57:15',NULL),(44,'1','user_list',NULL,NULL),(69,'41F20200108165714526o570133263','qc_statistics','2020-02-06 19:50:57',NULL),(70,'41F20200108165714526o570133263','qc_environment','2020-02-06 19:50:57',NULL),(71,'41F20200108165714526o570133263','bi_data_chart','2020-02-06 19:50:57',NULL),(72,'41F20200108165714526o570133263','bi_item_chart','2020-02-06 19:50:57',NULL),(73,'41F20200108165714526o570133263','year_qc_plan','2020-02-06 19:50:57',NULL),(74,'41F20200108165714526o570133263','staff_all','2020-02-06 19:50:57',NULL),(75,'41F20200108165714526o570133263','equip_list','2020-02-06 19:50:57',NULL),(76,'41F20200108165714526o570133263','check_item','2020-02-06 19:50:57',NULL),(77,'41F20200108165714526o570133263','qc_data','2020-02-06 20:14:04',NULL),(78,'41F20200108165714526o570133263','qc_chart','2020-02-06 20:14:04',NULL),(79,'41F20200108165714526o570133263','data_exchange_center','2020-02-07 11:38:38',NULL),(80,'41F20200108165714526o570133263','check_data_search','2020-02-07 11:38:38',NULL),(85,'2','qc_data','2020-02-10 16:27:29',NULL),(86,'2','qc_chart','2020-02-10 16:27:29',NULL),(87,'2','qc_statistics','2020-02-10 16:27:29',NULL),(88,'2','qc_environment','2020-02-10 16:27:29',NULL),(89,'2','data_exchange_center','2020-02-10 16:27:29',NULL),(90,'2','check_data_search','2020-02-10 16:27:29',NULL),(91,'41J20200107173540963w359622560','qc_data','2020-02-10 16:27:55',NULL),(92,'41J20200107173540963w359622560','qc_chart','2020-02-10 16:27:55',NULL),(93,'41J20200107173540963w359622560','qc_statistics','2020-02-10 16:27:55',NULL),(94,'41J20200107173540963w359622560','qc_environment','2020-02-10 16:27:55',NULL),(95,'41J20200107173540963w359622560','bi_data_chart','2020-02-10 16:27:55',NULL),(96,'41J20200107173540963w359622560','bi_item_chart','2020-02-10 16:27:55',NULL),(97,'41J20200107173540963w359622560','year_qc_plan','2020-02-10 16:27:55',NULL),(98,'41J20200107173540963w359622560','system_all','2020-02-10 16:27:55',NULL),(99,'41J20200107173540963w359622560','user_list','2020-02-10 16:27:55',NULL),(100,'41J20200107173540963w359622560','role_list','2020-02-10 16:27:55',NULL),(101,'41J20200107173540963w359622560','dic_list','2020-02-10 16:27:55',NULL),(102,'41J20200107173540963w359622560','equip_list','2020-02-10 16:27:55',NULL),(103,'41J20200107173540963w359622560','check_item','2020-02-10 16:27:55',NULL),(104,'41J20200107173540963w359622560','data_exchange_center','2020-02-10 16:27:55',NULL),(105,'41J20200107173540963w359622560','check_data_search','2020-02-10 16:27:55',NULL);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `staff_code` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `user_tel` varchar(32) DEFAULT NULL COMMENT '用户电话',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `user_status` tinyint(4) DEFAULT NULL COMMENT '用户状态(1-正常 -1-删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间(yyyy-MM-dd HH:mm:ss)',
  `open_user` varchar(32) DEFAULT NULL COMMENT '操作用户编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (30,'test1','C4CA4238A0B923820DCC509A6F75849B','5',NULL,'41F20200108165714526o570133263',1,'2020-02-10 06:51:30',NULL,NULL,NULL),(32,'test','098F6BCD4621D373CADE4E832627B4F6',NULL,NULL,'41F20200108165714526o570133263',1,'2020-02-10 10:18:04',NULL,NULL,NULL),(35,'test2','C4CA4238A0B923820DCC509A6F75849B',NULL,NULL,'41F20200108165714526o570133263',1,'2020-02-10 10:18:04',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-10 21:28:29
