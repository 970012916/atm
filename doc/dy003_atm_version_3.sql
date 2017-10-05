# Host: 127.0.0.1  (Version 5.5.51)
# Date: 2017-08-18 18:22:00
# Generator: MySQL-Front 5.4  (Build 1.36)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "atm_card"
#

DROP TABLE IF EXISTS `atm_card`;
CREATE TABLE `atm_card` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `card_num` varchar(255) NOT NULL DEFAULT '' COMMENT '银行卡号',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态 1-可用 2-冻结 3-删除',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT '银行卡余额',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡表';

#
# Structure for table "atm_detail"
#

DROP TABLE IF EXISTS `atm_detail`;
CREATE TABLE `atm_detail` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(3) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `card_id` int(11) NOT NULL DEFAULT '0' COMMENT '银行卡ID',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '交易金额 单位：分',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `flow_desc` varchar(255) CHARACTER SET latin1 DEFAULT NULL COMMENT '交易备注',
  `flow_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '交易类型 1-存款 2-取款 3-转账支出 4-转账收入',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水表';

#
# Structure for table "atm_user"
#

DROP TABLE IF EXISTS `atm_user`;
CREATE TABLE `atm_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `uq_username` (`username`) COMMENT '用户唯一性索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
