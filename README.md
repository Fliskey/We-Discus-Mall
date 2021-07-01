[TOC]

# We-Discus-Mall

a Second-Hand Trading System for project practice



## 项目组简介

组名：咱讨论一下

组长：王宇翔

组员：何赟、林纪元、刘云骢、王子夏

项目：校园二手交易系统

项目开始时间：6月28日



### github账号列表

| 姓名   | github地址         |
| ------ | ------------------ |
| 王宇翔 | github.com/Fliskey |



## 需求

### 第一阶段

数据库建表，服务器平台搭建，网页界面设计

用户登录，包括管理员、用户 

修改个人信息，电话，邮箱，密码等

管理员可对用户进行管理：增加、修改、删除用户

用户发布待转让物品信息

1.物品名称 2.物品类型（书籍、生活用品、票券）

3.物品数量  4 .物品单价  

用户浏览各类待转让物品信息，下单预订

待转让物品的修改、删除

根据类型或关键字筛选相关物品

### 第二阶段

用户登录，选择物品加入购物车

购物车物品数量的修改、删除

订单提交，库存数量修改

历史订单查询，根据用户号或者用户信息查询用户历史订单

用户可以按分类查询待转让物品，可以按价格或者按数量等对物品进行排序

系统可显示物品图片



## 使用技术

Springboot

Vue

MySQL

MyBatis



## 数据库设计

### 第一批实现的表

1. um_user
2. 

### 数据库用户与权限

- admin
  - 全局增删改查权限
- normal_user
  - um_user表改查权限
  - gm_goods表增删改查user_id相同的记录

### um:用户管理

- um_user(第一批实现)
  - id
  - name
  - password
  - tel_number
  - email
  - （creation_time）讨论有无必要
- um_user_logistic(未安排批次)
  - id
  - user_id
  - recip_address
  - recip_name
  - recip_tel
- um_user_edit_record(未安排批次)

### gm:二手商品管理

- gm_goods(第一批实现)
  - id
  - user_id(外键)
  - name
  - type
  - quantity
  - unit_price
  - （creation_time）讨论有无必要
- gm_goods_edit_record(未安排批次)

### om:订单管理

- om_order
  - id
  - goods_id
  - seller_id
  - buyer_id
  - actual_pay_amout
  - （creation_time）讨论有无必要
  - （payment_voucher）暂不记录

