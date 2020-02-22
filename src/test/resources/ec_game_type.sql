/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50611
 Source Host           : localhost
 Source Database       : ecommerce

 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 07/28/2013 16:18:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ec_game_type`
-- ----------------------------
DROP TABLE IF EXISTS `ec_game_type`;
CREATE TABLE `ec_game_type` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `typename` varchar(255) DEFAULT NULL COMMENT '游戏类型名称',
  `createtime` datetime DEFAULT NULL COMMENT '添加时间',
  `orderBy` int(11) DEFAULT NULL COMMENT '排序',
  `category` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ec_game_type`
-- ----------------------------
BEGIN;
INSERT INTO `ec_game_type` VALUES ('6eca5da6-ab82-4421-a069-f60566639e20', '战争策略', '2013-06-06 17:49:51', '999', '2'), ('96eb782f-12fe-474a-a8d5-5c88df983c4e', '模拟经营', '2013-06-08 16:37:21', '999', '1'), ('de9aa47a-a5ec-486f-bcc8-27d0b18cc3e9', '角色扮演', '2013-06-06 17:49:51', '999', '2'), ('eeaa5196-b927-4712-aeec-46df3ee04558', '休闲竞技', '2013-06-07 09:40:35', '999', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
