/*
 Navicat Premium Data Transfer

 Source Server         : myyun
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 114.55.93.36:3306
 Source Schema         : rocketmq_trade

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 16/12/2019 16:31:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for trade_coupon
-- ----------------------------
DROP TABLE IF EXISTS `trade_coupon`;
CREATE TABLE `trade_coupon`  (
  `coupon_id` bigint(20) NOT NULL COMMENT '优惠券ID',
  `coupon_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券金额',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `is_used` int(11) NULL DEFAULT NULL COMMENT '是否使用 0未使用 1已使用',
  `used_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '使用时间',
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_goods
-- ----------------------------
DROP TABLE IF EXISTS `trade_goods`;
CREATE TABLE `trade_goods`  (
  `goods_id` bigint(20) NOT NULL COMMENT '主键',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_number` int(11) NULL DEFAULT NULL COMMENT '商品库存',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `add_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_goods_number_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_goods_number_log`;
CREATE TABLE `trade_goods_number_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `goods_number` int(11) NULL DEFAULT NULL COMMENT '库存数量',
  `log_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单商品日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_mq_consumer_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_mq_consumer_log`;
CREATE TABLE `trade_mq_consumer_log`  (
  `msg_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息ID',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消费者组名',
  `msg_tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Tag',
  `msg_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Key',
  `msg_body` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `consumer_status` int(11) NULL DEFAULT NULL COMMENT '消费状态 0正在处理 1处理成功 2处理失败',
  `consumer_times` int(11) NULL DEFAULT NULL COMMENT '消费次数',
  `consumer_timestamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '消费时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`msg_id`) USING BTREE,
  UNIQUE INDEX `uk_msg_tag_msg_key_group_name`(`msg_tag`, `msg_key`, `group_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MQ消息消费表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_mq_producer_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_mq_producer_log`;
CREATE TABLE `trade_mq_producer_log`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产者组名',
  `msg_topic` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息主题',
  `msg_tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Tag',
  `msg_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Key',
  `msg_body` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `msg_status` int(11) NULL DEFAULT NULL COMMENT '消息状态 0未处理 1已处理',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MQ消息生产表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_order
-- ----------------------------
DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order`  (
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `order_status` int(11) NULL DEFAULT NULL COMMENT '订单状态 0未确认 1已确认 2已取消 3无效 4退款',
  `pay_status` int(11) NULL DEFAULT NULL COMMENT '支付状态 0未支付 1支付中 2已支付',
  `shipping_status` int(11) NULL DEFAULT NULL COMMENT '发货状态 0未发货 1已发货 2已退货',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `goods_number` int(11) NULL DEFAULT NULL COMMENT '商品库存',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `shipping_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `order_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券ID',
  `coupon_paid` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券',
  `money_paid` decimal(10, 2) NULL DEFAULT NULL COMMENT '已付金额',
  `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `add_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `confirm_time` timestamp(0) NULL DEFAULT NULL COMMENT '确认时间',
  `pay_time` timestamp(0) NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_pay
-- ----------------------------
DROP TABLE IF EXISTS `trade_pay`;
CREATE TABLE `trade_pay`  (
  `pay_id` bigint(20) NOT NULL COMMENT '支付编号',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单编号',
  `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `is_paid` int(11) NULL DEFAULT NULL COMMENT '是否已支付 0未付款 1正在付款 2已付款',
  PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单支付表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_user
-- ----------------------------
DROP TABLE IF EXISTS `trade_user`;
CREATE TABLE `trade_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_score` int(11) NULL DEFAULT NULL COMMENT '积分',
  `user_reg_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `user_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '用户余额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_user_money_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_user_money_log`;
CREATE TABLE `trade_user_money_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户Id',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `money_log_type` int(11) NULL DEFAULT NULL COMMENT '日志类型 1订单付款 2订单退款',
  `use_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '操作金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日志时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户余额日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
