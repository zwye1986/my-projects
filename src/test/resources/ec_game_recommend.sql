/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50611
 Source Host           : localhost
 Source Database       : ecommerce

 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 07/28/2013 16:18:41 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ec_game_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `ec_game_recommend`;
CREATE TABLE `ec_game_recommend` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `developer` varchar(255) DEFAULT NULL COMMENT '开发者',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `theme` varchar(255) DEFAULT NULL COMMENT '题材',
  `addtime` datetime DEFAULT NULL COMMENT '发布时间',
  `gamedescrip` varchar(1000) DEFAULT NULL COMMENT '游戏描述',
  `gameurl` varchar(255) DEFAULT NULL COMMENT '游戏地址',
  `playcounts` int(11) DEFAULT '0' COMMENT '游戏次数',
  `orderBy` int(11) DEFAULT NULL COMMENT '排序',
  `createBy` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` varchar(255) DEFAULT NULL COMMENT '修改人',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `category` int(20) DEFAULT NULL COMMENT '1是小游戏2是网页游戏',
  `gamepic_url` varchar(255) DEFAULT NULL,
  `gamename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ec_game_recommend`
-- ----------------------------
BEGIN;
INSERT INTO `ec_game_recommend` VALUES ('090da3d6-a808-4144-8ac7-9cd5846dcf87', null, 'de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', null, null, null, null, 'http://dxz.37wan.com/', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'dxz_logo_n.jpg', '大侠传'), ('376ff761-a863-40df-b16f-d91a2f455194', null, 'de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', null, null, null, null, 'http://ssz.37wan.com', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'ssz_logo.jpg', '蜀山传'), ('37e6a9e2-0a4c-4c2f-b6f5-6835f097de3e', null, '6eca5da6-ab82-4421-a069-f60566639e20', null, null, null, null, 'http://dhhj.37wan.com', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'dhhj_logo.jpg', '大航海家'), ('5cfd5741-3278-4e56-ba5f-cc078eaf6194', null, 'de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', null, null, null, null, 'http://long.37wan.com/', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'long_logo.jpg', '龙将'), ('953b65d9-a322-4b57-abc9-33137b8c346c', null, 'de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', null, null, null, null, 'http://lhsg.37wan.com/', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'lhsg_logo.jpg', '龙回三国'), ('9898f93e-e5c9-4727-962c-7a8c0b63511f', null, 'de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', null, null, null, null, 'http://lc.37wan.com/', '0', '999', null, '2013-06-17 20:07:22', null, null, '2', 'lc_logo.jpg', '龙城');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
