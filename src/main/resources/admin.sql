create database if not exists `project`;

use project;

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student`
(
  `studentId`   int(11)      NOT NULL,
  `studentName` varchar(200) NOT NULL,
  `age`         int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student`
  DISABLE KEYS */;
INSERT INTO `student`
VALUES (15041225, 'chen', 18),
       (15041311, 'yan yu', 18);
/*!40000 ALTER TABLE `student`
  ENABLE KEYS */;
UNLOCK TABLES;

# 用户账户等级表
create table if not exists `identity`
(
  `id`          int auto_increment not null primary key comment 'id',
  `name`        varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255)       not null comment '等级说明'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 插入基本数据
insert into identity(description)
values ('admin'),
       ('user');
# insert into identity (description) values ("");
# select id, description from identity where id = id;
# update identity set description = description where id = id;

# 用户表
create table if not exists `user`
(
  `id`        int auto_increment not null comment '用户Id',
  `username`  varchar(200)       not null comment '用户姓名',
  `password`  varchar(200)       not null comment '用户密码',
  `nickname`  varchar(200) comment '用户昵称',
  `school`    varchar(200) comment '学校',
  `studentId` int                not null comment '学号',
  `email`     varchar(50)        not null comment '邮箱',
  `picture`   mediumblob comment '用户头像',
  `signature` varchar(255) comment '个性签名',
  `identity`  int                not null default 4 comment '用户账户等级',
  `point`     int                not null default 0 comment '用户积分',
  primary key (id, studentId),
  key (username),
  CONSTRAINT `user_identity` FOREIGN KEY (`identity`) REFERENCES `identity` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# delete from user where id = id;
# insert into user (username, password, nickname, school, studentId, email, picture, signature, identity) values (username, password, nickname, school, studentId, email, picture, signature, identity);
# select id, username, password, nickname, school, studentId, email, picture, signature, identity, point from user where id = ;
# update user set id = id, username = username, password = password, nickname = nickname, school = school, studentId = studentId, email = email, picture = picture, signature =signature, identity = identity
# update user set point = point + integral where id = id;

# 权限表
DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission`
(
  `id`   bigint(20) NOT NULL AUTO_INCREMENT,
  `url`  varchar(255) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64)  DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

# 角色权限表
DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission`
(
  `identity`     int(20)    DEFAULT NULL COMMENT '角色id',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '权限id',
  KEY `identity` (`identity`),
  KEY `permissionId` (`permissionId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`identity`) REFERENCES `identity` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

# 板块表
create table if not exists `plate`
(
  `id`       int          not null auto_increment primary key comment '板块ID',
  `name`     varchar(255) not null comment '板块名称',
  `referral` varchar(255) not null comment '板块介绍',
  `admin`    int          not null comment '板块负责人id',
  key (admin),
  CONSTRAINT `plate_user` FOREIGN KEY (`admin`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('文科专业', '文科专业课板块', 1);
INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('理科专业', '理科专业课板块', 1);
INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('工科专业', '工科专业课板块', 1);
INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('外语课程', '外语课程板块', 1);
INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('研究方向', '研究方向板块', 1);
INSERT INTO `project`.`plate` (`name`, `referral`, `admin`)
VALUES ('关于学校', '关于学校板块', 1);

# delete from  plate where id = id;
# insert into plate (name, referral, admin) values (name, referral, admin);
# select id, name, referral, admin from plate where id = id;
# update plate set name = #{admin}, referral = #{referral}, admin = #{admin} where id = #{id}

# 板块助理管理表
create table if not exists `plateAdmin`
(
  `id`      int not null auto_increment primary key comment 'id',
  `plateId` int not null comment '板块id',
  `userId`  int not null comment '板块助理id',
  CONSTRAINT `plateAdmin_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `plateAdmin_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# insert into plateAdmin(plateId, userId) values ();
# select id, plateId, userId from plateAdmin where id = 0;

# 板块信息表
create table if not exists `plateMessage`
(
  `id`           int          not null auto_increment primary key,
  `title`        varchar(255) not null comment '板块消息标题',
  `content`      text         not null comment '板块消息内容',
  `time`         timestamp             default current_timestamp comment '板块消息添加时间',
  `plateId`      int          not null comment '所属板块id',
  `instructions` int                   default 0 comment '板块消息的访问量',
  `replies`      int                   default 0 comment '板块消息回复量',
  `priority`     int                   default 10 comment '板块消息的优先级，影响板块消息的排名',
  `block`        tinyint(1)            default 0 comment '该消息时候允许显示，0不显示，1，显示，没通过审核为0',
  `userId`       int          not null comment '发布该消息的用户id',
  `value`        int          not null default 0 comment '该消息的价值，价值大于0的时候为有偿信息，需要支付积分进行查看',
  CONSTRAINT `plateMessage_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`),
  CONSTRAINT `plateMessage_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
# insert into plateMessage(title, content, plateId, block, userId, value) values (title, content, plateId, block, userId, value);
# select id, title, content, time, plateId, instructions, replies, priority, block, userId, value from plateMessage where id = 0;
# c测试数据
INSERT INTO `project`.`plateMessage` (`title`, `content`, `time`, `plateId`, `instructions`, `replies`, `priority`,
                                      `block`, `userId`, `value`)
VALUES ('测试标题3', '测试消息内容', DEFAULT, 3, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT);
INSERT INTO `project`.`plateMessage` (`title`, `content`, `time`, `plateId`, `instructions`, `replies`, `priority`,
                                      `block`, `userId`, `value`)
VALUES ('测试标题4', '测试消息内容', DEFAULT, 4, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT);
INSERT INTO `project`.`plateMessage` (`title`, `content`, `time`, `plateId`, `instructions`, `replies`, `priority`,
                                      `block`, `userId`, `value`)
VALUES ('测试标题5', '测试消息内容', DEFAULT, 5, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT);
INSERT INTO `project`.`plateMessage` (`title`, `content`, `time`, `plateId`, `instructions`, `replies`, `priority`,
                                      `block`, `userId`, `value`)
VALUES ('测试标题6', '测试消息内容', DEFAULT, 6, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, DEFAULT);


#  do here

# 积分消费记录
create table if not exists `integralConsumption`
(
  `id`          int not null auto_increment primary key comment '消费记录id',
  `consumer`    int not null comment '消费积分的人',
  `beneficiary` int not null comment '积分受益者',
  `value`       int not null comment '消费积分价值',
  `time`        timestamp default current_timestamp comment '积分消费时间',
  CONSTRAINT `integralConsumption_user1` FOREIGN KEY (`consumer`) REFERENCES `user` (`id`),
  CONSTRAINT `integralConsumption_user2` FOREIGN KEY (`beneficiary`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
# delete from integralConsumption where id = 0;
# insert into  integralConsumption (consumer, beneficiary, value) values (consumer, beneficiary, value);
# select id, consumer, beneficiary, value, time from integralConsumption where id = id;
# update integralConsumption set value = #{value} where id = #{id}

# 记录用户已经查看的数据
Create table downloaded
(
  `id`        int not null auto_increment primary key comment '消费记录id',
  `userId`    int not null comment '查看者id',
  `messageId` int not null comment '查看的消息id',
  `time`      timestamp default current_timestamp comment '查看时间',
  CONSTRAINT `downloaded_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `downloaded_plateMessage` FOREIGN KEY (`messageId`) REFERENCES `plateMessage` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
# delete  from downloaded where id = 9;
# insert into downloaded(userId, messageId) values ();
# select id, userId, messageId, time from downloaded where id = 0;
# update downloaded set time = current_timestamp where id = 0;


# 记录用户已经上传的数据
create table if not exists uploaded
(
  `id`      int          not null auto_increment primary key comment '上传记录id',
  `title`   varchar(255) not null comment '板块消息标题',
  `content` text         not null comment '板块消息内容',
  `time`    timestamp    not null default current_timestamp comment '板块消息添加时间',
  `plateId` int          not null comment '所属板块id',
  `userId`  int          not null comment '上传者id',
  `value`   int          not null default 0 comment '消息价值',
  `status`  varchar(20)  not null default '未处理' comment '消息处理状态',
  CONSTRAINT `uploaded_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `uploaded_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# insert into uploaded(title, content, time, plateId, userId, value, status) values (title, content, time, plateId, userId, value, status);
# select id, title, content, time, plateId, userId, value, status from uploaded where id = 0;
# update uploaded set `title` = title, `content` = content, `plateId` = plateId, `userId` = userId, `value` = value where `id` = id;


# 留言表
create table if not exists `message`
(
  `id`      int          not null auto_increment primary key comment '留言id',
  `author`  int          not null comment '留言人id',
  #   `receiver`       int          not null comment '被留言人id',
  `message` varchar(255) not null comment '留言信息',
  `time`    timestamp default current_timestamp comment '留言时间',
  `plateId` int          not null comment '留言板块消息的id',
  CONSTRAINT `message_user1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  #   CONSTRAINT `message_user2` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`),
  CONSTRAINT `message_plate` FOREIGN KEY (`plateId`) REFERENCES `plate` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 私信表
create table if not exists `privateLetter`
(
  `id`       int          not null auto_increment primary key comment '留言id',
  `author`   int          not null comment '发件人id',
  `receiver` int          not null comment '收件人id',
  `message`  varchar(255) not null comment '发件信息',
  `time`     timestamp  default current_timestamp comment '发件时间',
  `sign`     tinyint(1) default 0 comment '是否查看，0未查看，1已查看',
  CONSTRAINT `privateLetter_user1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `privateLetter_user2` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;