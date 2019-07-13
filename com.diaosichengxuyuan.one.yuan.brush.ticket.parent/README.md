#简介
一元抢票网站的后端，基于Spring boot构建

#启动
配置JVM参数：jasypt.encryptor.password=数据库密钥
配置JVM参数：encrypt.algorithm.key=前端密码密钥

##测试
com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test包是用来测试的
在ticket_test表中插入测试数据
CREATE TABLE IF NOT EXISTS `ticket_test` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `modify_time` DATETIME NOT NULL COMMENT '变更时间',
  `start_sale_time` DATETIME NOT NULL COMMENT '车票起售时间',
  `end_sale_time` DATETIME NOT NULL COMMENT '截止售票时间',
  `date` DATE NOT NULL COMMENT '哪一天的车次',
  `start_place` VARCHAR(45) NOT NULL COMMENT '出发地',
  `end_place` VARCHAR(45) NOT NULL COMMENT '目的地',
  `train` VARCHAR(45) NOT NULL COMMENT '车次，如G102',
  `price` VARCHAR(45) NOT NULL COMMENT '价格',
  `remain` INT(10) NOT NULL COMMENT '余票数量',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
