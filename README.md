# 简介

目的是做一款抢票软件，暂时只是完成了部分功能，未与12306打通，用户可在ticket_test表中插入测试数据进行抢票测试。

已完成功能列表：

(1)登录/退出，账号隔离，使用spring security构建

(2)游客查询车票

(3)登录后的用户可添加抢票任务

(4)抢票和余票监控能力，使用线程池+延迟队列实现

(5)模拟12306票务数据，修改数据使用了乐观锁机制

待做功能列表：

(1)查票、订票、乘客等接口与12306打通

(2)登录、验证码等功能与12306打通

(3)通知用户的功能

# 数据库

建表语句在https://github.com/diaosichengxuyuan/one-yuan-brush-ticket/issues/6中

# 后端-spring boot构建

打spring boot全量包

配置JVM参数：jasypt.encryptor.password=数据库密钥

配置JVM参数：encrypt.algorithm.key=前端密码密钥

java -jar启动

# 前端-vue构建

前端utils.js中getSecretKey方法中配置密钥与后端一致

npm run dev启动

# 测试

访问http://localhost:8080地址
