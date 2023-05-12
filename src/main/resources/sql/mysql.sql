DROP TABLE `t_order_0`;
DROP TABLE `t_order_1`;
DROP TABLE `t_order_item_0`;
DROP TABLE `t_order_item_1`;
DROP TABLE `t_address`;

CREATE TABLE IF NOT EXISTS `t_order_0` (
	`order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `buyer_id`  bigint NOT NULL DEFAULT '0' COMMENT '购买用户id',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单表';

CREATE TABLE IF NOT EXISTS `t_order_1` (
	`order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `buyer_id`  bigint NOT NULL DEFAULT '0' COMMENT '购买用户id',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单表';

CREATE TABLE IF NOT EXISTS `t_order_item_0`  (
	`order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单id',
    `sku_code`  varchar(32) NOT NULL DEFAULT '' COMMENT '商品信息',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_item_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单明细表';

CREATE TABLE IF NOT EXISTS `t_order_item_1`  (
	`order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单id',
    `sku_code`  varchar(32) NOT NULL DEFAULT '' COMMENT '商品信息',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_item_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单明细表';

CREATE TABLE IF NOT EXISTS `t_address` (
	`address_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单id',
    `address`  varchar(100) NOT NULL DEFAULT '' COMMENT '地址信息',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`address_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单地址表';


truncate table t_order_0;
truncate table t_order_1;
truncate table t_order_item_0;
truncate table t_order_item_1;
truncate table t_address;