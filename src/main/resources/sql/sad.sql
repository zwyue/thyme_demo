/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : sad

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 27/11/2018 09:44:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for faculty_role
-- ----------------------------
DROP TABLE IF EXISTS `faculty_role`;
CREATE TABLE `faculty_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `faculty_id` int(11) NOT NULL COMMENT '职工id',
  `role_ids` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id,以逗号拼接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职工角色对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of faculty_role
-- ----------------------------
INSERT INTO `faculty_role` VALUES (1, 1, '1');

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `perm_ids` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id,以逗号拼接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES (1, 1, '1');

-- ----------------------------
-- Table structure for user_faculty
-- ----------------------------
DROP TABLE IF EXISTS `user_faculty`;
CREATE TABLE `user_faculty`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `faculty_no` int(11) NOT NULL COMMENT '职工编号',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建人，如果是系统管理员，则id为0',
  `status` int(11) NULL DEFAULT 0 COMMENT '职工状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_faculty
-- ----------------------------
INSERT INTO `user_faculty` VALUES (1, 'admin', 2018010201, '2018010201admin', 'ef470a5baa79beacd8d4a15360f1c7d5', '2018-11-16 13:32:13', '2018-11-16 13:32:13', 0, NULL);
INSERT INTO `user_faculty` VALUES (2, 'aaaa', 2018111602, NULL, NULL, '2018-11-16 18:02:29', '2018-11-16 18:02:29', 0, 0);
INSERT INTO `user_faculty` VALUES (3, 'bbbbb', 2018111903, NULL, NULL, '2018-11-19 18:02:02', '2018-11-19 18:02:02', 0, 0);
INSERT INTO `user_faculty` VALUES (4, 'ddddd', 2018111904, NULL, NULL, '2018-11-19 18:02:15', '2018-11-19 18:02:15', 0, 0);

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `perm_status` int(11) NOT NULL DEFAULT 1 COMMENT '权限状态1.正常2.停用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `perm_form` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'menu' COMMENT '权限形式：menu菜单\nbutton按钮',
  `parent_node` int(11) NOT NULL COMMENT '父节点',
  `perm_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (1, 'perm1', 1, '2018-11-19 15:39:24', '2018-11-19 15:39:24', 'button', 0, 'perm:addPerm');
INSERT INTO `user_permission` VALUES (2, 'perm2', 1, '2018-11-19 15:42:37', '2018-11-19 15:42:37', 'button', 0, 'perm:deletePerm');
INSERT INTO `user_permission` VALUES (3, '测试1', 1, '2018-11-26 14:08:15', '2018-11-26 14:08:15', 'c:c', 1, 'ceshi1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_status` int(11) NOT NULL DEFAULT 1 COMMENT '角色状态1.正常2.停用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_role_role_name_uindex`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '角色1', 1, '2018-11-19 10:50:18', '2018-11-19 10:50:18');
INSERT INTO `user_role` VALUES (2, '角色2', 1, '2018-11-19 15:49:14', '2018-11-19 15:49:14');
INSERT INTO `user_role` VALUES (3, '角色3', 1, '2018-11-19 17:53:17', '2018-11-19 17:53:17');
INSERT INTO `user_role` VALUES (5, '角色4', 1, '2018-11-19 18:51:02', '2018-11-19 18:51:02');

-- ----------------------------
-- Table structure for user_student
-- ----------------------------
DROP TABLE IF EXISTS `user_student`;
CREATE TABLE `user_student`  (
  `id` int(11) NOT NULL,
  `stu_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生编号',
  `stu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
