流程设计：
![ProcessDesign.png](src%2Fmain%2Fresources%2FProcessDesign.png)

数据库表设计：
```
CREATE DATABASE `book_management`;

USE `book_management`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
`id`        BIGINT(20)  NOT     NULL COMMENT 'primary_key',
`user_name` VARCHAR(30) DEFAULT NULL COMMENT 'username',
`password`  VARCHAR(30) DEFAULT NULL COMMENT 'password',
`identity`  VARCHAR(30) DEFAULT NULL COMMENT 'identity',
`telephone` VARCHAR(50) DEFAULT NULL COMMENT 'telephone',
PRIMARY KEY(`id`)
);

-- insert testdata
INSERT INTO `user` (id,user_name,password,identity,telephone) VALUES
(1,'dddadmin','test1234','admin','0123456789'),
(2,'dddreader','test1234','reader','0123456789'),
(3,'dddauthor','test1234','author','0123456789');

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book`(
`id`             BIGINT(20)  NOT     NULL COMMENT 'primary_key',
`name`           VARCHAR(30) DEFAULT NULL COMMENT 'bookname',
`status`         INT(11)     DEFAULT NULL COMMENT 'status',
`borrowed_count` INT(11)     DEFAULT NULL COMMENT 'borrowedCount',
`position`       VARCHAR(50) DEFAULT NULL COMMENT 'position',
PRIMARY KEY(`id`)
);

-- insert testdata
INSERT INTO `book` (id,name,status,borrowed_count,position) VALUES
(1,'the Shawshank Redemption',0,0,'4-1-0010'),
(2,'a Little Princess',1,1,'4-3-0006'),
(3,'Bel-ami',0,2,'4-4-0021');
```

接口设计：

- 用户：
  - 登录
  - 注册
  - 查询图书信息
  - 借取图书
  - 归还图书
  - 更改信息（密码、电话等）
  - 管理其他用户权限（仅管理员能调用）# BooksMngSys
