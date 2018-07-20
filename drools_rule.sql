/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.6.39 : Database - drools_rule
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drools_rule` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `drools_rule`;

/*Table structure for table `ACT_EVT_LOG` */

DROP TABLE IF EXISTS `ACT_EVT_LOG`;

CREATE TABLE `ACT_EVT_LOG` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_GE_BYTEARRAY` */

DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;

CREATE TABLE `ACT_GE_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_GE_PROPERTY` */

DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;

CREATE TABLE `ACT_GE_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_ACTINST` */

DROP TABLE IF EXISTS `ACT_HI_ACTINST`;

CREATE TABLE `ACT_HI_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_ATTACHMENT` */

DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;

CREATE TABLE `ACT_HI_ATTACHMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_COMMENT` */

DROP TABLE IF EXISTS `ACT_HI_COMMENT`;

CREATE TABLE `ACT_HI_COMMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_DETAIL` */

DROP TABLE IF EXISTS `ACT_HI_DETAIL`;

CREATE TABLE `ACT_HI_DETAIL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_IDENTITYLINK` */

DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;

CREATE TABLE `ACT_HI_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_PROCINST` */

DROP TABLE IF EXISTS `ACT_HI_PROCINST`;

CREATE TABLE `ACT_HI_PROCINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_TASKINST` */

DROP TABLE IF EXISTS `ACT_HI_TASKINST`;

CREATE TABLE `ACT_HI_TASKINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_HI_VARINST` */

DROP TABLE IF EXISTS `ACT_HI_VARINST`;

CREATE TABLE `ACT_HI_VARINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_ID_GROUP` */

DROP TABLE IF EXISTS `ACT_ID_GROUP`;

CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_ID_INFO` */

DROP TABLE IF EXISTS `ACT_ID_INFO`;

CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_ID_MEMBERSHIP` */

DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;

CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_ID_USER` */

DROP TABLE IF EXISTS `ACT_ID_USER`;

CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_PROCDEF_INFO` */

DROP TABLE IF EXISTS `ACT_PROCDEF_INFO`;

CREATE TABLE `ACT_PROCDEF_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RE_DEPLOYMENT` */

DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;

CREATE TABLE `ACT_RE_DEPLOYMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RE_MODEL` */

DROP TABLE IF EXISTS `ACT_RE_MODEL`;

CREATE TABLE `ACT_RE_MODEL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RE_PROCDEF` */

DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;

CREATE TABLE `ACT_RE_PROCDEF` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_EVENT_SUBSCR` */

DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;

CREATE TABLE `ACT_RU_EVENT_SUBSCR` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_EXECUTION` */

DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;

CREATE TABLE `ACT_RU_EXECUTION` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_IDENTITYLINK` */

DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;

CREATE TABLE `ACT_RU_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_JOB` */

DROP TABLE IF EXISTS `ACT_RU_JOB`;

CREATE TABLE `ACT_RU_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_TASK` */

DROP TABLE IF EXISTS `ACT_RU_TASK`;

CREATE TABLE `ACT_RU_TASK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `ACT_RU_VARIABLE` */

DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;

CREATE TABLE `ACT_RU_VARIABLE` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_excute_task` */

DROP TABLE IF EXISTS `act_excute_task`;

CREATE TABLE `act_excute_task` (
  `id` varchar(60) NOT NULL COMMENT '主键',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '批次号，验证任务调用时存在',
  `proc_release_id` bigint(20) NOT NULL COMMENT '模型版本id',
  `proc_inst_id` varchar(64) DEFAULT NULL COMMENT '流程运行实例id',
  `status` varchar(32) DEFAULT NULL COMMENT '任务状态，0-待执行，1-启动成功，2-执行完成，3-执行异常',
  `type` varchar(32) DEFAULT NULL COMMENT '任务类型，0-验证任务，1-业务系统调用',
  `in_paramter` longtext COMMENT '入参',
  `out_paramter` longtext COMMENT '出参，MQ message内容',
  `spend_time` bigint(20) DEFAULT NULL COMMENT '花费时间',
  `remark` varchar(5000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模型执行记录表';

/*Table structure for table `act_model_definition` */

DROP TABLE IF EXISTS `act_model_definition`;

CREATE TABLE `act_model_definition` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '模型id',
  `model_code` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '模型编码',
  `model_name` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '模型名称',
  `belong_system` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '所属系统',
  `business_id` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '业务线',
  `model_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '模型描述',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `cre_user_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `cre_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_proc_release` */

DROP TABLE IF EXISTS `act_proc_release`;

CREATE TABLE `act_proc_release` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '模型id，与 act_re_model.id_ 关联',
  `model_code` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '模型编码，与 act_re_model.key_ 关联',
  `model_procdef_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '模型定义id，与 act_re_procdef.id_ 关联,act_re_procdef 表中有模型部署id',
  `model_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '模型名称',
  `model_version` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '模型版本',
  `model_category` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '模型分类',
  `version_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '版本类型，0-测试版，1-正式版',
  `is_bind` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否绑定： 0-未绑定，1-已绑定;',
  `is_validate` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;',
  `is_auto_validate` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否自动验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;',
  `is_manual_validate` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否手动验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;',
  `is_approve` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;',
  `approve_task_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模型版本控制表';

/*Table structure for table `act_validate_batch` */

DROP TABLE IF EXISTS `act_validate_batch`;

CREATE TABLE `act_validate_batch` (
  `id` bigint(20) NOT NULL COMMENT '主键,批次号',
  `proc_release_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '流程部署id，与 act_re_procdef.deployment_id 关联',
  `batch_size` int(12) NOT NULL COMMENT '批次大小',
  `status` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '批次状态，0-待执行，1-正在执行，2-执行完成，3-执行异常',
  `complete_count` int(11) DEFAULT NULL COMMENT '已执行次数',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模型执行批次表';

/*Table structure for table `act_validate_batch_copy1` */

DROP TABLE IF EXISTS `act_validate_batch_copy1`;

CREATE TABLE `act_validate_batch_copy1` (
  `id` bigint(20) NOT NULL COMMENT '主键,批次号',
  `proc_release_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '流程部署id，与 act_re_procdef.deployment_id 关联',
  `batch_size` int(12) NOT NULL COMMENT '批次大小',
  `status` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '批次状态，0-待执行，1-正在执行，2-执行完成，3-执行异常',
  `complete_count` int(11) DEFAULT NULL COMMENT '已执行次数',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `cityType` */

DROP TABLE IF EXISTS `cityType`;

CREATE TABLE `cityType` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `city_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '城市名',
  `city_type` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '城市类别，1-1线城市，2-二线城市，3-三线城市，4-4线城市，5-5线城市，6-新一线城市',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `risk_business` */

DROP TABLE IF EXISTS `risk_business`;

CREATE TABLE `risk_business` (
  `business_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_name` varchar(32) DEFAULT NULL COMMENT '业务线名',
  `business_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态：1正常0禁用',
  `cre_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='业务线表';

/*Table structure for table `risk_data_converter` */

DROP TABLE IF EXISTS `risk_data_converter`;

CREATE TABLE `risk_data_converter` (
  `id` bigint(64) NOT NULL COMMENT 'ID',
  `converter_name` varchar(200) DEFAULT NULL COMMENT '转换器名',
  `converter_class` varchar(200) DEFAULT NULL COMMENT '转换器处理类',
  `converter_method` varchar(200) DEFAULT NULL COMMENT '转换器处理方法',
  `converter_remark` varchar(200) DEFAULT NULL COMMENT '转换器处理描述',
  `converter_return_type` varchar(16) DEFAULT NULL COMMENT '转换器返回类型1String ,2int',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_man` varchar(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_modify_man` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据转换器配置';

/*Table structure for table `risk_data_converter_params` */

DROP TABLE IF EXISTS `risk_data_converter_params`;

CREATE TABLE `risk_data_converter_params` (
  `id` bigint(64) NOT NULL COMMENT 'ID',
  `converter_id` bigint(64) DEFAULT NULL COMMENT '所属转换器id',
  `param_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数名',
  `param_type` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数类型1string  2int  3日期',
  `param_source_type` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数来源类型1.变量值  2.页面输入',
  `param_remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_man` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_modify_man` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='风控转换器参数';

/*Table structure for table `risk_data_module_define` */

DROP TABLE IF EXISTS `risk_data_module_define`;

CREATE TABLE `risk_data_module_define` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `data_module_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '组件名',
  `data_module_remark` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组件描述',
  `select_sql` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'select语句',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_man` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_modiry_man` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='风控数据模块定义';

/*Table structure for table `risk_drools_detail_log` */

DROP TABLE IF EXISTS `risk_drools_detail_log`;

CREATE TABLE `risk_drools_detail_log` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `drools_logid` varchar(32) DEFAULT NULL,
  `execute_rulename` varchar(100) DEFAULT NULL COMMENT '命中的规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则执行日志表，规则命中详细信息';

/*Table structure for table `risk_drools_log` */

DROP TABLE IF EXISTS `risk_drools_log`;

CREATE TABLE `risk_drools_log` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '验证批次号',
  `procinst_id` varchar(32) DEFAULT NULL,
  `model_name` varchar(100) DEFAULT NULL COMMENT '模型名',
  `sence_versionid` varchar(64) NOT NULL COMMENT '決策版本流水',
  `in_paramter` mediumtext,
  `out_paramter` mediumtext,
  `execute_total` int(11) DEFAULT NULL COMMENT '命中规则总数',
  `type` varchar(2) DEFAULT NULL COMMENT '决策执行类型：0-直接调用，1-模型调用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `execute_time` bigint(20) DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则执行日志表';

/*Table structure for table `risk_face` */

DROP TABLE IF EXISTS `risk_face`;

CREATE TABLE `risk_face` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `face_url` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '接口地址',
  `face_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '接口名字',
  `face_param_obj` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '接口参数对象',
  `is_effect` varchar(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_user` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-1' COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `risk_face_param` */

DROP TABLE IF EXISTS `risk_face_param`;

CREATE TABLE `risk_face_param` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `foreign_id` bigint(32) NOT NULL COMMENT '外键',
  `param_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '参数名',
  `param_code` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '参数code',
  `param_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '参数类型',
  `is_effect` varchar(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_user` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-1' COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `risk_model_release` */

DROP TABLE IF EXISTS `risk_model_release`;

CREATE TABLE `risk_model_release` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `model_procdef_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '模型定义id，与 act_re_procdef.id_ 关联',
  `model_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '模型名称',
  `model_version` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '模型版本',
  `model_category` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '模型分类',
  `version_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '版本类型，0-测试版，1-正式版',
  `is_validate` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;',
  `is_approve` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `risk_model_sence` */

DROP TABLE IF EXISTS `risk_model_sence`;

CREATE TABLE `risk_model_sence` (
  `id` varchar(60) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '主键id',
  `model_procdef_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '模型定义id',
  `sence_version_id` bigint(20) NOT NULL COMMENT '決策版本流水号',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模型与决策关联关系表，模型发布后插入';

/*Table structure for table `risk_model_task` */

DROP TABLE IF EXISTS `risk_model_task`;

CREATE TABLE `risk_model_task` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_procdef_id` varchar(64) NOT NULL COMMENT '模型id',
  `corn_text` varchar(200) DEFAULT NULL COMMENT 'corn表达式',
  `task_status` varchar(2) DEFAULT NULL COMMENT '任务状态1停止2启动',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控模型离线任务表';

/*Table structure for table `risk_rule_action_version` */

DROP TABLE IF EXISTS `risk_rule_action_version`;

CREATE TABLE `risk_rule_action_version` (
  `risk_rule_action_version_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动作版本关联id',
  `version_id` bigint(20) NOT NULL COMMENT '版本id',
  `action_class` varchar(168) NOT NULL COMMENT '实体类的名',
  `action_id` bigint(20) DEFAULT NULL COMMENT '动作id',
  PRIMARY KEY (`risk_rule_action_version_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='规则版本记录表';

/*Table structure for table `risk_rule_his_version` */

DROP TABLE IF EXISTS `risk_rule_his_version`;

CREATE TABLE `risk_rule_his_version` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sence_version_id` bigint(20) NOT NULL COMMENT '決策版本流水',
  `rule_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '规则名称',
  `rule_desc` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '规则描述',
  `is_effect` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规则历史记录表，规则发布版本后插入';

/*Table structure for table `risk_sence_verfication_batch` */

DROP TABLE IF EXISTS `risk_sence_verfication_batch`;

CREATE TABLE `risk_sence_verfication_batch` (
  `id` bigint(20) NOT NULL COMMENT '主键,批次号',
  `sence_version_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '決策版本流水',
  `batch_size` int(12) NOT NULL COMMENT '批次大小',
  `verfication_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否生效：0-手动，1-自动',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规则验证批次表';

/*Table structure for table `risk_test_drools_detail_log` */

DROP TABLE IF EXISTS `risk_test_drools_detail_log`;

CREATE TABLE `risk_test_drools_detail_log` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `drools_logid` varchar(32) DEFAULT NULL,
  `execute_rulename` varchar(100) DEFAULT NULL COMMENT '命中的规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则验证日志详情表';

/*Table structure for table `risk_test_drools_log` */

DROP TABLE IF EXISTS `risk_test_drools_log`;

CREATE TABLE `risk_test_drools_log` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '验证批次号',
  `procinst_id` varchar(32) DEFAULT NULL,
  `model_name` varchar(100) DEFAULT NULL COMMENT '模型名',
  `sence_versionid` varchar(64) NOT NULL COMMENT '決策版本流水',
  `in_paramter` mediumtext,
  `out_paramter` mediumtext,
  `execute_total` int(11) DEFAULT NULL COMMENT '命中规则总数',
  `type` varchar(2) DEFAULT NULL COMMENT '决策执行类型：0-直接调用，1-模型调用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则验证日志表';

/*Table structure for table `risk_variable_bind` */

DROP TABLE IF EXISTS `risk_variable_bind`;

CREATE TABLE `risk_variable_bind` (
  `id` bigint(20) NOT NULL COMMENT '主键,流水号',
  `sence_version_id` bigint(20) NOT NULL COMMENT '決策版本流水',
  `variable_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '变量编码',
  `variable_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '变量名称',
  `data_type` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '变量类型，与rule_entity_item_info.data_type 一致',
  `constant_id` bigint(20) DEFAULT NULL COMMENT '常量id，与rule_entity_item_info.constant_id 一致',
  `bind_table` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '绑定数据表',
  `bind_column` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '绑定数据表字段',
  `is_effect` varchar(2) COLLATE utf8_bin DEFAULT '0' COMMENT '是否生效：0-有效，1-无效',
  `tmp_value` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户输入值，只保存最后一次的',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `converter_id` bigint(20) DEFAULT NULL COMMENT '转换器id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规则变量绑定表';

/*Table structure for table `risk_variable_converter_param` */

DROP TABLE IF EXISTS `risk_variable_converter_param`;

CREATE TABLE `risk_variable_converter_param` (
  `id` bigint(20) DEFAULT NULL COMMENT '主键,流水号',
  `bind_id` bigint(20) DEFAULT NULL COMMENT '变量绑定id',
  `converter_id` bigint(20) DEFAULT NULL COMMENT '转换器id',
  `param_id` bigint(20) DEFAULT NULL COMMENT '参数id',
  `param_value` varchar(300) DEFAULT NULL COMMENT '参数值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_man` varchar(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_modify_man` varchar(20) DEFAULT NULL COMMENT '最后修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控转换器参数';

/*Table structure for table `rule_action_info` */

DROP TABLE IF EXISTS `rule_action_info`;

CREATE TABLE `rule_action_info` (
  `action_id` bigint(20) NOT NULL COMMENT '主键',
  `action_type` int(11) NOT NULL COMMENT '动作类型(1实现2自身)',
  `action_name` varchar(200) NOT NULL COMMENT '动作名称',
  `action_desc` varchar(3000) DEFAULT NULL COMMENT '动作描述',
  `action_class` varchar(200) NOT NULL COMMENT '动作实现类(包路径)',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `business_id` varchar(32) DEFAULT '0' COMMENT '业务线id',
  `action_method` varchar(255) DEFAULT NULL COMMENT '动作执行方法',
  PRIMARY KEY (`action_id`),
  KEY `action_type` (`action_type`),
  KEY `action_name` (`action_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则动作定义信息';

/*Table structure for table `rule_action_param_info` */

DROP TABLE IF EXISTS `rule_action_param_info`;

CREATE TABLE `rule_action_param_info` (
  `action_param_id` bigint(20) NOT NULL COMMENT '主键',
  `action_id` bigint(20) NOT NULL COMMENT '动作id',
  `action_param_name` varchar(200) NOT NULL COMMENT '参数名称',
  `action_param_desc` varchar(3000) DEFAULT NULL COMMENT '参数描述',
  `param_identify` varchar(200) NOT NULL COMMENT '标识',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`action_param_id`),
  KEY `action_id` (`action_id`),
  KEY `action_param_name` (`action_param_name`),
  KEY `param_identify` (`param_identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动作参数信息表';

/*Table structure for table `rule_action_param_value_info` */

DROP TABLE IF EXISTS `rule_action_param_value_info`;

CREATE TABLE `rule_action_param_value_info` (
  `action_param_value_id` bigint(20) NOT NULL COMMENT '主键',
  `rule_action_rel_id` bigint(20) NOT NULL COMMENT '动作规则关系主键',
  `action_param_id` bigint(20) NOT NULL COMMENT '动作参数',
  `param_value` varchar(200) NOT NULL COMMENT '参数值',
  `param_text` varchar(255) DEFAULT NULL COMMENT '参数文字描述',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`action_param_value_id`),
  KEY `rule_action_rel_id` (`rule_action_rel_id`),
  KEY `action_param_id` (`action_param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动作参数对应的属性值信息表';

/*Table structure for table `rule_action_rule_rel` */

DROP TABLE IF EXISTS `rule_action_rule_rel`;

CREATE TABLE `rule_action_rule_rel` (
  `rule_action_rel_id` bigint(20) NOT NULL COMMENT '主键',
  `action_id` bigint(20) NOT NULL COMMENT '动作',
  `rule_id` bigint(20) NOT NULL COMMENT '规则',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`rule_action_rel_id`),
  KEY `action_id` (`action_id`),
  KEY `rule_id` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动作与规则信息关系表';

/*Table structure for table `rule_condition_info` */

DROP TABLE IF EXISTS `rule_condition_info`;

CREATE TABLE `rule_condition_info` (
  `condition_id` bigint(20) NOT NULL COMMENT '主键',
  `rule_id` bigint(20) NOT NULL COMMENT '规则',
  `condition_name` varchar(3000) NOT NULL COMMENT '条件名称',
  `condition_expression` varchar(3000) NOT NULL COMMENT '条件表达式',
  `condition_desc` varchar(3000) NOT NULL COMMENT '条件描述',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `val` varchar(255) DEFAULT NULL COMMENT '值',
  `hasvariable` tinyint(3) DEFAULT '0' COMMENT '1常量0输入值',
  PRIMARY KEY (`condition_id`),
  KEY `rule_id` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则条件信息表';

/*Table structure for table `rule_constant_info` */

DROP TABLE IF EXISTS `rule_constant_info`;

CREATE TABLE `rule_constant_info` (
  `con_id` bigint(20) NOT NULL COMMENT '主键',
  `con_key` varchar(200) NOT NULL COMMENT '常量类别',
  `con_name` varchar(200) NOT NULL COMMENT '常量名',
  `con_type` varchar(200) NOT NULL DEFAULT '0' COMMENT '常量类型',
  `con_code` varchar(200) NOT NULL COMMENT '变量code',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(3000) NOT NULL DEFAULT '' COMMENT '备注',
  `business_id` varchar(32) DEFAULT '0' COMMENT '业务线id',
  PRIMARY KEY (`con_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常量表';

/*Table structure for table `rule_entity_info` */

DROP TABLE IF EXISTS `rule_entity_info`;

CREATE TABLE `rule_entity_info` (
  `entity_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `entity_name` varchar(50) NOT NULL COMMENT '名称',
  `entity_desc` varchar(3000) NOT NULL COMMENT '描述',
  `entity_identify` varchar(50) NOT NULL COMMENT '标识',
  `pkg_name` varchar(200) DEFAULT NULL COMMENT '包路径',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效(1是0否)',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `business_id` varchar(32) DEFAULT '0' COMMENT '业务线id',
  PRIMARY KEY (`entity_id`),
  KEY `entity_identify` (`entity_identify`),
  KEY `entity_name` (`entity_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1016235482970607618 DEFAULT CHARSET=utf8 COMMENT='规则引擎实体信息表';

/*Table structure for table `rule_entity_item_info` */

DROP TABLE IF EXISTS `rule_entity_item_info`;

CREATE TABLE `rule_entity_item_info` (
  `item_id` bigint(20) NOT NULL COMMENT '主键',
  `entity_id` bigint(20) NOT NULL COMMENT '实体id',
  `item_name` varchar(50) NOT NULL COMMENT '字段名称',
  `item_identify` varchar(50) NOT NULL COMMENT '字段标识',
  `item_desc` varchar(50) DEFAULT NULL COMMENT '属性描述',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `data_type` varchar(16) DEFAULT NULL COMMENT '数据类型 ',
  `constant_id` bigint(20) DEFAULT NULL COMMENT '常量id',
  PRIMARY KEY (`item_id`),
  KEY `entity_id` (`entity_id`),
  KEY `item_identify` (`item_identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体属性信息';

/*Table structure for table `rule_group` */

DROP TABLE IF EXISTS `rule_group`;

CREATE TABLE `rule_group` (
  `rule_group_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规则分组id',
  `rule_id` bigint(20) NOT NULL COMMENT '规则id',
  `index` int(6) NOT NULL COMMENT '序号，排序',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `weight` double(11,2) DEFAULT '0.00' COMMENT '权值',
  `scene_id` bigint(20) DEFAULT NULL COMMENT '场景id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`rule_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2011 DEFAULT CHARSET=utf8 COMMENT='评分卡分组表';

/*Table structure for table `rule_info` */

DROP TABLE IF EXISTS `rule_info`;

CREATE TABLE `rule_info` (
  `rule_id` bigint(20) NOT NULL COMMENT '主键',
  `scene_id` bigint(20) NOT NULL COMMENT '场景',
  `rule_name` varchar(50) NOT NULL COMMENT '名称',
  `rule_desc` varchar(3000) DEFAULT NULL COMMENT '描述',
  `rule_enabled` int(11) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`rule_id`),
  KEY `scene_id` (`scene_id`),
  KEY `rule_name` (`rule_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则信息';

/*Table structure for table `rule_property_info` */

DROP TABLE IF EXISTS `rule_property_info`;

CREATE TABLE `rule_property_info` (
  `rule_property_id` bigint(20) NOT NULL COMMENT '主键',
  `rule_property_identify` varchar(200) NOT NULL COMMENT '标识',
  `rule_property_name` varchar(200) NOT NULL COMMENT '名称',
  `rule_property_desc` varchar(3000) DEFAULT NULL COMMENT '描述',
  `default_value` varchar(200) DEFAULT NULL COMMENT '默认值',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`rule_property_id`),
  KEY `rule_property_identify` (`rule_property_identify`),
  KEY `rule_property_name` (`rule_property_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则基础属性信息表';

/*Table structure for table `rule_property_rel` */

DROP TABLE IF EXISTS `rule_property_rel`;

CREATE TABLE `rule_property_rel` (
  `rule_pro_rel_id` bigint(20) NOT NULL COMMENT '主键',
  `rule_id` bigint(20) NOT NULL COMMENT '规则',
  `rule_property_id` bigint(20) NOT NULL COMMENT '规则属性',
  `rule_property_value` varchar(200) NOT NULL COMMENT '规则属性值',
  PRIMARY KEY (`rule_pro_rel_id`),
  KEY `rule_id` (`rule_id`),
  KEY `rule_property_id` (`rule_property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则属性配置表';

/*Table structure for table `rule_scene_entity_rel` */

DROP TABLE IF EXISTS `rule_scene_entity_rel`;

CREATE TABLE `rule_scene_entity_rel` (
  `scene_entity_rel_id` bigint(20) NOT NULL COMMENT '主键',
  `scene_id` bigint(20) DEFAULT NULL COMMENT '场景',
  `entity_id` bigint(20) DEFAULT NULL COMMENT '实体',
  PRIMARY KEY (`scene_entity_rel_id`),
  KEY `scene_id` (`scene_id`),
  KEY `entity_id` (`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景实体关联表';

/*Table structure for table `rule_scene_info` */

DROP TABLE IF EXISTS `rule_scene_info`;

CREATE TABLE `rule_scene_info` (
  `scene_id` bigint(20) NOT NULL COMMENT '主键',
  `scene_identify` varchar(50) NOT NULL COMMENT '标识',
  `scene_type` int(11) DEFAULT NULL COMMENT '类型(暂不使用)',
  `scene_name` varchar(50) NOT NULL COMMENT '名称',
  `scene_desc` varchar(3000) DEFAULT NULL COMMENT '描述',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `version` varchar(32) DEFAULT NULL COMMENT '版本号',
  `business_id` varchar(32) DEFAULT '0' COMMENT '业务线id',
  PRIMARY KEY (`scene_id`),
  KEY `scene_identify` (`scene_identify`),
  KEY `scene_type` (`scene_type`),
  KEY `scene_name` (`scene_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎使用场景';

/*Table structure for table `rule_scene_item_rel` */

DROP TABLE IF EXISTS `rule_scene_item_rel`;

CREATE TABLE `rule_scene_item_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(20) DEFAULT NULL COMMENT '场景id',
  `entity_item_id` bigint(20) DEFAULT NULL COMMENT '变量id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `cre_time` timestamp NULL DEFAULT NULL,
  `entity_id` bigint(20) DEFAULT NULL COMMENT '实体类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=985 DEFAULT CHARSET=utf8 COMMENT='策略表';

/*Table structure for table `rule_scene_version` */

DROP TABLE IF EXISTS `rule_scene_version`;

CREATE TABLE `rule_scene_version` (
  `version_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '版本记录id',
  `version` varchar(32) NOT NULL COMMENT '版本号 ',
  `official_version` varchar(6) DEFAULT NULL COMMENT '正式版本号',
  `type` int(6) DEFAULT NULL COMMENT '0表示测试版 1表示正式版',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `comment` varchar(512) DEFAULT NULL COMMENT '详细描述',
  `scene_identify` varchar(64) DEFAULT NULL COMMENT '场景code',
  `scene_id` varchar(32) DEFAULT NULL COMMENT '业务id',
  `cre_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `rule_div` mediumtext COMMENT '规则内容',
  `rule_drl` mediumtext COMMENT '规则内容',
  `status` tinyint(3) DEFAULT '1' COMMENT '是否启用',
  `test_status` tinyint(3) DEFAULT NULL COMMENT '测试是否通过，1-通过，0-待验证，2-不通过',
  `business_type` varchar(100) DEFAULT NULL COMMENT '业务类型，1-评分卡，2-决策表',
  `business_line` varchar(100) DEFAULT NULL COMMENT '业务线，1-房速贷，2-现金贷',
  `is_bind_var` varchar(2) DEFAULT NULL COMMENT '是否绑定变量，1-绑定，0-未绑定',
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='策略版本控制表';

/*Table structure for table `rule_variable` */

DROP TABLE IF EXISTS `rule_variable`;

CREATE TABLE `rule_variable` (
  `variable_id` bigint(20) NOT NULL COMMENT '主键',
  `variable_name` varchar(200) NOT NULL COMMENT '变量名称',
  `variable_type` int(11) NOT NULL COMMENT '变量类型（1条件2动作）',
  `default_value` varchar(200) NOT NULL COMMENT '默认值',
  `value_type` int(11) NOT NULL COMMENT '数值类型（ 1字符型 2数字型 3 日期型）',
  `variable_value` varchar(200) NOT NULL COMMENT '变量值',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`variable_id`),
  KEY `variable_type` (`variable_type`),
  KEY `value_type` (`value_type`),
  KEY `variable_name` (`variable_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎常用变量';

/*Table structure for table `t_aa` */

DROP TABLE IF EXISTS `t_aa`;

CREATE TABLE `t_aa` (
  `ac1` bigint(20) DEFAULT NULL,
  `ac2` varchar(50) DEFAULT NULL,
  `ac3` datetime DEFAULT NULL,
  `ac4` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_dd` */

DROP TABLE IF EXISTS `t_dd`;

CREATE TABLE `t_dd` (
  `id` int(11) DEFAULT NULL,
  `aa1` int(11) DEFAULT NULL,
  `aa2` int(11) DEFAULT NULL,
  `cc1` int(11) DEFAULT NULL,
  `cc2` int(11) DEFAULT NULL,
  `bb1` int(11) DEFAULT NULL,
  `bb2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `zipkin_annotations` */

DROP TABLE IF EXISTS `zipkin_annotations`;

CREATE TABLE `zipkin_annotations` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.trace_id',
  `span_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.id',
  `a_key` varchar(255) NOT NULL COMMENT 'BinaryAnnotation.key or Annotation.value if type == -1',
  `a_value` blob COMMENT 'BinaryAnnotation.value(), which must be smaller than 64KB',
  `a_type` int(11) NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if Annotation',
  `a_timestamp` bigint(20) DEFAULT NULL COMMENT 'Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp',
  `endpoint_ipv4` int(11) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_ipv6` binary(16) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null, or no IPv6 address',
  `endpoint_port` smallint(6) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_service_name` varchar(255) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`span_id`,`a_key`,`a_timestamp`) COMMENT 'Ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`span_id`) COMMENT 'for joining with zipkin_spans',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTraces/ByIds',
  KEY `endpoint_service_name` (`endpoint_service_name`) COMMENT 'for getTraces and getServiceNames',
  KEY `a_type` (`a_type`) COMMENT 'for getTraces',
  KEY `a_key` (`a_key`) COMMENT 'for getTraces',
  KEY `trace_id` (`trace_id`,`span_id`,`a_key`) COMMENT 'for dependencies job'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*Table structure for table `zipkin_dependencies` */

DROP TABLE IF EXISTS `zipkin_dependencies`;

CREATE TABLE `zipkin_dependencies` (
  `day` date NOT NULL,
  `parent` varchar(255) NOT NULL,
  `child` varchar(255) NOT NULL,
  `call_count` bigint(20) DEFAULT NULL,
  `error_count` bigint(20) DEFAULT NULL,
  UNIQUE KEY `day` (`day`,`parent`,`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*Table structure for table `zipkin_spans` */

DROP TABLE IF EXISTS `zipkin_spans`;

CREATE TABLE `zipkin_spans` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `debug` bit(1) DEFAULT NULL,
  `start_ts` bigint(20) DEFAULT NULL COMMENT 'Span.timestamp(): epoch micros used for endTs query and to implement TTL',
  `duration` bigint(20) DEFAULT NULL COMMENT 'Span.duration(): micros used for minDuration and maxDuration query',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`id`) COMMENT 'ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`id`) COMMENT 'for joining with zipkin_annotations',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTracesByIds',
  KEY `name` (`name`) COMMENT 'for getTraces and getSpanNames',
  KEY `start_ts` (`start_ts`) COMMENT 'for getTraces ordering and range'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
