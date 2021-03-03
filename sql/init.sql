
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `password` varchar(32) NOT NULL COMMENT '加密密码',
  `salt` varchar(32) NOT NULL COMMENT '密码加盐参数',
  `status` tinyint(2) NOT NULL COMMENT '用户状态',
  `super_admin` tinyint(1) DEFAULT '0' COMMENT '是否是超级管理员 1超级 0普通',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_id` bigint(11) NOT NULL COMMENT '创建者id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(11) DEFAULT NULL COMMENT '最近更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户信息表';
ALTER TABLE sys_user_info ADD INDEX index_del_flag (del_flag);


DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色标识',
  `role_name` varchar(30) NOT NULL COMMENT '角色描述',
  `status` int(2) NOT NULL COMMENT '权限状态',
  `create_id` bigint(11) NOT NULL COMMENT '创建者id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(11) DEFAULT NULL COMMENT '最近更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
ALTER TABLE sys_role_info ADD INDEX index_del_flag (del_flag);


DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_id` bigint(11) NOT NULL COMMENT '创建者id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关系表';
ALTER TABLE sys_user_role_relation ADD INDEX index_del_flag (del_flag);


DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `menu_name` varchar(16) NOT NULL COMMENT '菜单名称',
  `menu_level` int(3) unsigned NOT NULL COMMENT '菜单级别',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `parent_menu_id` bigint(11) unsigned DEFAULT '0' COMMENT '父菜单id',
  `priority` int(5) unsigned NOT NULL COMMENT '显示优先级',
  `code` varchar(64) DEFAULT NULL COMMENT '权限code',
  `target` varchar(10) default 'menuItem' comment '打开方式（menuItem页签 menuBlank新窗口）',
  `visible` tinyint(1) default 0 comment '菜单状态（0显示 1隐藏）',
  `is_refresh` tinyint(1) default 1 comment '是否刷新（0刷新 1不刷新）',
  `icon` varchar(32) default '#' comment '菜单图标',
  `create_id` bigint(11) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(11) DEFAULT NULL COMMENT '最近更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='菜单表';
ALTER TABLE sys_menu ADD INDEX index_del_flag (del_flag);


DROP TABLE IF EXISTS `sys_menu_role_relation`;
CREATE TABLE `sys_menu_role_relation` (
  `menu_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_id` bigint(11) NOT NULL COMMENT '创建者id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单-角色关系表';



INSERT INTO `sys_user_info` (`id`, `user_name`, `real_name`, `password`, `salt`, `status`, `super_admin`, `last_login_time`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('1', 'admin', 'admin', 'c10f62b80ee938d246d84559229271a9', '1a4bb8055471cf5c65213dd21058a1bc', '1', '1', NULL, '1', '2020-09-11 23:40:18', NULL, NULL, '0');

INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('1', '用户管理', '1', '', '0', '100', '', 'menuItem', '0', '1', 'fa fa-user', NULL, '2019-12-08 13:29:27', '1', '2021-02-24 13:43:17', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('2', '用户列表', '2', '/system/user/index', '1', '101', 'system:user:index', 'menuItem', '0', '1', 'fa fa-user', NULL, '2019-12-08 13:29:49', '1', '2021-03-01 14:12:19', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('3', '菜单管理', '1', '', '0', '200', '', 'menuItem', '0', '1', 'fa fa-reorder', NULL, '2019-12-08 14:20:46', '1', '2021-02-24 13:44:37', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('4', '菜单列表', '2', '/system/menu/index', '3', '201', 'system:menu:index', 'menuItem', '0', '1', '#', NULL, '2019-12-08 14:21:08', '1', '2021-03-01 14:23:46', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('8', '权限管理', '1', '', '0', '400', '', 'menuItem', '0', '1', 'fa fa-user-secret', NULL, '2019-12-08 15:56:09', '1', '2021-02-24 13:46:14', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('9', '权限列表', '2', '/system/role/index', '8', '401', 'system:role:index', 'menuItem', '0', '1', '#', NULL, '2019-12-08 15:56:37', '1', '2021-03-01 14:24:06', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('13', '用户列表-修改密码', '3', '', '2', '102', 'system:user:resetPwd', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:43:55', '1', '2021-02-24 11:48:55', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('14', '用户列表-新增用户', '3', '', '2', '103', 'system:user:add', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:45:05', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('15', '用户列表-编辑用户', '3', '', '2', '104', 'system:user:edit', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:45:41', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('16', '用户列表-删除用户', '3', '', '2', '105', 'system:user:del', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:54:11', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('17', '用户列表-更新用户', '3', '', '2', '106', 'system:user:update', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:55:55', '1', '2020-09-12 22:39:10', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('18', '用户列表-查询全部', '3', '', '2', '107', 'system:user:page', 'menuItem', '0', '1', '#', '1', '2020-09-12 21:56:51', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('19', '菜单列表-新增', '3', '', '4', '202', 'system:menu:add', 'menuItem', '0', '1', '#', '1', '2020-09-12 22:34:05', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('20', '菜单列表-更新', '3', '', '4', '203', 'system:menu:update', 'menuItem', '0', '1', '#', '1', '2020-09-12 22:35:35', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('21', '菜单列表-删除', '3', '', '4', '204', 'system:menu:del', 'menuItem', '0', '1', '#', '1', '2020-09-12 22:36:17', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('22', '菜单列表-查看详情', '3', '', '4', '205', 'system:menu:info', 'menuItem', '0', '1', '#', '1', '2020-09-12 22:37:23', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('23', '菜单列表-分页列表', '3', '', '4', '206', 'system:menu:page', 'menuItem', '0', '1', '#', '1', '2020-09-12 22:37:59', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('34', '权限列表-列表分页', '3', '', '9', '402', 'system:role:page', 'menuItem', '0', '1', '#', '1', '2020-09-12 23:32:52', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('35', '权限列表-查看详情', '3', '', '9', '403', 'system:role:info', 'menuItem', '0', '1', '#', '1', '2020-09-12 23:33:24', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('36', '权限列表-新增', '3', '', '9', '404', 'system:role:add', 'menuItem', '0', '1', '#', '1', '2020-09-12 23:33:56', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('37', '权限列表-更新', '3', '', '9', '405', 'system:role:update', 'menuItem', '0', '1', '#', '1', '2020-09-12 23:34:24', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('38', '权限列表-删除', '3', '', '9', '406', 'system:role:del', 'menuItem', '0', '1', '#', '1', '2020-09-12 23:35:00', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('45', '日志管理', '1', '', '0', '500', '', 'menuItem', '0', '1', 'fa fa-pencil-square', '1', '2021-02-24 10:30:33', '1', '2021-02-24 10:32:27', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('46', '日志列表', '2', '/system/log/index', '45', '501', 'system:log:index', 'menuItem', '0', '0', 'fa fa-pencil-square-o', '1', '2021-02-24 10:32:08', '1', '2021-03-01 14:38:33', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('47', '日志-删除', '3', '', '46', '502', 'system:log:del', 'menuItem', '0', '1', '', '1', '2021-02-24 15:20:12', NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('48', '日志-查看详情', '3', '', '46', '503', 'system:log:index:detail', 'menuItem', '0', '1', '', '1', '2021-02-24 15:20:41', '1', '2021-03-01 14:37:59', '0');
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_level`, `menu_url`, `parent_menu_id`, `priority`, `code`, `target`, `visible`, `is_refresh`, `icon`, `create_id`, `create_date`, `update_id`, `update_date`, `del_flag`) VALUES ('49', '日志-数据分页', '3', '', '46', '504', 'system:log:page', 'menuItem', '0', '1', '', '1', '2021-03-01 14:47:05', NULL, NULL, '0');



CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `request_desc` varchar(100) DEFAULT NULL COMMENT '请求描述',
  `request_url` varchar(100) DEFAULT NULL COMMENT '请求url',
  `request_method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `request_ip` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `request_param` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `target_method` varchar(32) DEFAULT NULL COMMENT '接口方法',
  `log_type` tinyint(1) NOT NULL COMMENT '1 请求日志 2:异常日志',
  `interface_type` tinyint(1) NOT NULL COMMENT '接口类型 1:后台日志 2:机构后台日志 3:web 端日志',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `create_name` varchar(20) DEFAULT NULL COMMENT '操作者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `exception` varchar(1000) DEFAULT NULL COMMENT '异常日志',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统日志';
ALTER TABLE sys_log ADD INDEX index_del_flagAndinterface_type (del_flag,interface_type);
ALTER TABLE sys_log ADD INDEX index_create_id (create_id);