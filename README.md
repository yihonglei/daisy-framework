# 系统架构
开发环境：Mac + JDK8

架构：Spring Boot、MyBatis、MySql

数据库连接池：druid

缓存中间件：Redis，MongoDB

消息中间件：RocketMQ，RabbitMQ

分布式锁：支持Redis或Zookeeper

分布式事务：最终一致性

定时任务：elastic-job（当当分布式）

系统日志：logback

系统监控：待完成

项目部署：linux

# rose-framework说明
## 基础依赖
rose-parent：基础依赖
## 公共组件
http：rose-http-spring-boot-starter
redis：rose-redis-spring-boot-starter
mongodb：rose-mongodb-spring-boot-starter
rabbitmq：rose-rabbitmq-spring-boot-starter
rocketmq：rose-rocketmq-spring-boot-starter

## 基础服务


## 应用服务
rose：核心api提供，分为api、common、core三个结构

