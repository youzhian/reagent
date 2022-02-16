-- 创建user_info
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` varchar(100) NULL COMMENT '名称',
  `password` varchar(250) NULL COMMENT '密码',
  PRIMARY KEY (`id`)
);

-- 创建试剂表
CREATE TABLE `reagent_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `reagent_name` varchar(255) NOT NULL COMMENT '试剂名称',
  `del_flg` varchar(10) NULL DEFAULT 1 COMMENT '删除标识，0为已删除，1为未删除',
  `remark` varchar(255) NULL COMMENT '描述',
  `create_on` timestamp(6) NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `create_by` varchar(100) NULL COMMENT '创建人名称',
  `modify_on` timestamp(6) NULL COMMENT '修改时间',
  `modify_by` varchar(100) NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
);

CREATE TABLE `stock_detail`  (
  `id` int NOT NULL COMMENT '物理主键',
  `reagent_id` int NULL DEFAULT NULL COMMENT '试剂ID',
  `reagent_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '试剂名称',
  `stock_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL DEFAULT '0' COMMENT '出入库类型，1为入库，0为出库',
  `num` int NOT NULL COMMENT '出入库数量',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '操作人名称',
  `operator_id` int NULL DEFAULT NULL COMMENT '操作人ID',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出入库时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- 字典类型
CREATE TABLE `dict_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '字典类型显示名称',
  `dic_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '字典类型',
  `dic_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '字典类型描述',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;
-- 字典信息
CREATE TABLE `dict_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `dict_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '字典显示名称',
  `dict_value` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '字典值',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL COMMENT '字典类型',
  `order_num` int NULL DEFAULT NULL COMMENT '字典排序',
  `dict_remark` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;