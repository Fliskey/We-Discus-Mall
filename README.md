[TOC]

# We-Discus-Mall

a Second-Hand Trading System for project practice



## 在线任务

[在线任务清单 (qq.com)](https://docs.qq.com/sheet/DTUNQU3dMUWJBcWtU?tab=BB08J2)



## 项目组简介

组名：咱讨论一下

组长：王宇翔

组员：何赟、林纪元、刘云骢、王子夏

项目：校园二手交易系统

项目开始时间：6月28日



### github账号列表

| 姓名   | github地址                                         |
| ------ | -------------------------------------------------- |
| 王宇翔 | [Fliskey](http://github.com/Fliskey)               |
| 何赟   | [YueMing-github](http://github.com/YueMIng-github) |
| 林纪元 | [纪元](https://github.com/JaneThis)                |
| 刘云骢 | [ycsxh](https://github.com/ycsxh)                  |
| 王子夏 | [小鱼](https://github.com/kytzly)                  |



## 需求

### 第一阶段

- [x] 数据库建表，服务器平台搭建，网页界面设计

- [x] 用户登录，包括管理员、用户；修改个人信息，电话，邮箱，密码等

- [x] 管理员可对用户进行管理：增加、修改、删除用户

- [ ] 用户发布待转让物品信息

  1.物品名称 2.物品类型（书籍、生活用品、票券）3.物品数量  4 .物品单价  

- [x] 用户浏览各类待转让物品信息，下单预订

- [x] 待转让物品的修改、删除

- [x] 根据类型或关键字筛选相关物品


### 第二阶段

- [ ] 用户登录，选择物品加入购物车

- [ ] 购物车物品数量的修改、删除

- [ ] 订单提交，库存数量修改

- [ ] 历史订单查询，根据用户号或者用户信息查询用户历史订单

- [ ] 用户可以按分类查询待转让物品，可以按价格或者按数量等对物品进行排序

- [ ] 系统可显示物品图片




## 使用技术

Springboot

Vue

MySQL

MyBatis



## 数据库设计

### 数据库名web_mall_building

#### 数据库用户与权限

- admin
  - id
  - tel_number
  - password

#### admin:管理员表

- admin
  - id
  - tel_number
  - password

#### um:用户管理

- um_user
  - id
  - name
  - password
  - tel_number
  - email
- um_login
  - id
  - password
  - salt

#### gm:二手商品管理

- gm_goods
  - id
  - user_id
  - name
  - type
  - quantity
  - price

#### om:订单管理

- om_order
  - id
  - goods_id
  - seller_id
  - buyer_id
  - actual_pay_amout
