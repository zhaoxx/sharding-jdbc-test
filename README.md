分库分表
1、t_order 和 t_order_item 为绑定表，相同订单id数据落到同一个数据库节点，表序列号相同。
2、t_address为广播表，每个数据节点存储全量数据。
3、http://localhost:8080/druid/index.html 
账号：admin
密码：123456

一、创建订单
1）购买人id偶数落入sharding_test_01库，奇数落入sharding_test_02库
2）订单id（雪花算法生成）偶数落入t_order_0表，奇数落入t_order_1表
http://localhost:8080/order/createOrder?buyerId=12

二、查询订单
1）指定购买人id
http://localhost:8080/order/getOrder?buyerId=3
指定数据库节点查询主数据：
SELECT order_id, buyer_id
FROM t_order_0
WHERE buyer_id = 3
UNION ALL
SELECT order_id, buyer_id
FROM t_order_1
WHERE buyer_id = 3

绑定表t_order_item，根据order_id查询数据时（未指定buyer_id），所有数据库节点。
绑定表t_order_item，根据order_id查询数据时（同时指定buyer_id），指定数据库节点。

2）指定订单id
http://localhost:8080/order/getOrder?orderId=1656542944004079617
每个数据库节点都会执行，指定一个表查数据。绑定表同样。
SELECT order_id, buyer_id
FROM t_order_1
WHERE order_id = 1656542944004079617

SELECT order_item_id, sku_code, buyer_id, order_id
FROM t_order_item_1
WHERE order_id IN (1656542944004079617)

3）指定购买人id、订单id
http://localhost:8080/order/getOrder?buyerId=3&orderId=1656542944004079617
指定数据库节点，指定表查询



读写分离
1、插入数据
INSERT INTO `t_order_1` (`order_id`, `buyer_id`, `create_time`, `update_time`) VALUES (1656542774898130945, 1, '2023-05-11 14:12:29', '2023-05-11 14:12:29');