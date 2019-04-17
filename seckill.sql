/*
 Navicat Premium Data Transfer

 Source Server         : remote
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/04/2019 15:06:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`
(
    `goods_id`       bigint(11)                                                    NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `description`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品描述详情',
    `goods_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品名称',
    `goods_picture`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品图片',
    `goods_price`    int(11)                                                       NULL DEFAULT 0 COMMENT '商品价格',
    `goods_status`   int(11)                                                       NULL DEFAULT 0 COMMENT '商品状态',
    `goods_stock`    int(11)                                                       NULL DEFAULT 0 COMMENT '商品库存',
    `goods_title`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品标题',
    `discount_price` int(11)                                                       NULL DEFAULT 0 COMMENT '打折价格',
    `start_time`     timestamp(0)                                                  NULL DEFAULT NULL COMMENT '秒杀开始时间',
    `end_time`       timestamp(0)                                                  NULL DEFAULT NULL COMMENT '秒杀结束时间',
    PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `order_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `user_id`      bigint(20)  NULL DEFAULT NULL COMMENT '用户id',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `pay_time`     datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
    `status`       int(11)     NULL DEFAULT 0 COMMENT '订单状态',
    `total_amount` int(11)     NULL DEFAULT 0 COMMENT '订单总额',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`
(
    `order_item_id`     bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `goods_id`          bigint(20)                                                    NULL DEFAULT NULL COMMENT '商品id',
    `order_id`          int(11)                                                       NULL DEFAULT NULL COMMENT '订单id',
    `goods_price`       int(11)                                                       NULL DEFAULT NULL COMMENT '订单价格',
    `goods_count`       int(11)                                                       NULL DEFAULT NULL COMMENT '商品数量',
    `goods_picture`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品价格',
    `goods_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品详情',
    `goods_title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品标题',
    `create_time`       datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `user_id`     bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
    `password`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
    `age`         int(11)                                                       NOT NULL COMMENT '用户年龄',
    `create_time` datetime(0)                                                   NOT NULL COMMENT '创建时间',
    `deleted`     bit(1)                                                        NOT NULL COMMENT '逻辑删除',
    `phone`       varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '联系方式',
    `username`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
    `address`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户地址',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
