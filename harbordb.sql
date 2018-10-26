/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.44 : Database - harbordb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`harbordb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `harbordb`;

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `dpt_id` varchar(20) NOT NULL,
  `dpt_name` varchar(50) DEFAULT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `dpt_location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dpt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `departments` */

/*Table structure for table `doctor_specialities` */

DROP TABLE IF EXISTS `doctor_specialities`;

CREATE TABLE `doctor_specialities` (
  `doc_spec_id` varchar(20) DEFAULT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `doctor_id` varchar(20) DEFAULT NULL,
  `specialities` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctor_specialities` */

/*Table structure for table `doctors` */

DROP TABLE IF EXISTS `doctors`;

CREATE TABLE `doctors` (
  `doctor_id` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `specialities` varchar(100) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctors` */

/*Table structure for table `hospital_admin` */

DROP TABLE IF EXISTS `hospital_admin`;

CREATE TABLE `hospital_admin` (
  `admin_id` varchar(20) NOT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `gender` varchar(15) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `photo` varchar(50) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospital_admin` */

insert  into `hospital_admin`(`admin_id`,`hid`,`fname`,`lname`,`username`,`password`,`role`,`nick_name`,`gender`,`address`,`contact`,`last_login`,`photo`,`creation_date`) values ('1','hid1','kunal','birbal','admin','admin','admin','kunal','male','nagpur','9876543210','2018-08-22 12:22:52',NULL,NULL);

/*Table structure for table `hospital_diseases` */

DROP TABLE IF EXISTS `hospital_diseases`;

CREATE TABLE `hospital_diseases` (
  `hdis_id` varchar(20) NOT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `dis_name` varchar(100) DEFAULT NULL,
  `dis_desc` text,
  `dis_img` varchar(50) DEFAULT NULL,
  `dis_videos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hdis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospital_diseases` */

/*Table structure for table `hospital_dos_donts` */

DROP TABLE IF EXISTS `hospital_dos_donts`;

CREATE TABLE `hospital_dos_donts` (
  `hos_do_id` varchar(20) NOT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `do_dont_name` varchar(50) DEFAULT NULL,
  `do_dont_desc` text,
  `do_dont_img` varchar(50) DEFAULT NULL,
  `do_dont_videos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hos_do_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospital_dos_donts` */

/*Table structure for table `hospitals` */

DROP TABLE IF EXISTS `hospitals`;

CREATE TABLE `hospitals` (
  `hid` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `pincode` int(10) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `reg_number` varchar(30) DEFAULT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospitals` */

/*Table structure for table `login_history` */

DROP TABLE IF EXISTS `login_history`;

CREATE TABLE `login_history` (
  `lid` varchar(20) NOT NULL,
  `admin_id` varchar(20) DEFAULT NULL,
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `logout_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login_history` */

/*Table structure for table `rate_charts` */

DROP TABLE IF EXISTS `rate_charts`;

CREATE TABLE `rate_charts` (
  `rate_id` varchar(20) NOT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `tariff_name` varchar(50) DEFAULT NULL,
  `service_name` varchar(50) DEFAULT NULL,
  `service_category` varchar(50) DEFAULT NULL,
  `rates` varchar(20) DEFAULT NULL,
  `doctor_name` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `is_mandatory` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rate_charts` */

/*Table structure for table `tariffs` */

DROP TABLE IF EXISTS `tariffs`;

CREATE TABLE `tariffs` (
  `tariff_id` varchar(20) NOT NULL,
  `hid` varchar(20) DEFAULT NULL,
  `tariff_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tariff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tariffs` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
