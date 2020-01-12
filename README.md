# 一 rose-framework技术架构

## 开发环境

Mac + JDK8

## 技术统一

开发语言：Java + JDK8

Web框架：Spring Boot

数据库：MySql，druid连接池

ORM框架：MyBatis

缓存中间件：Redis，MongoDB

消息中间件：RocketMQ

分布式锁：支持Redis、Zookeeper实现

分布式事务：RocketMQ最终一致性，定时任务做双层防护

定时任务：elastic-job（当当分布式）

系统日志：logback，elk

系统监控：sunflower https://github.com/swordslotus/sunflower

工程化：idea，Maven，Git

项目部署：jenkins，tomcat，linux

# 二 rose-framework模块说明

## 基础依赖

rose-parent：基础依赖

## 公共组件

http：rose-okhttp

redis：rose-redis-spring-boot-starter

mongodb：rose-mongodb-spring-boot-starter

rocketmq：rose-rocketmq-spring-boot-starter

分布式锁：rose-distributed-lock


## 应用服务

rose核心业务服务，rose-job定时任务服务。

# 三 rose-framework构建

1、基础组件打包 clean install

1）rose-parent

2）rose-rocketmq-spring-boot-starter

3）rose-mongodb-spring-boot-starter

4）rose-redis-spring-boot-starter

2、rose工程打包 clean install

找到com/jpeony/api/ApiApplication.java,启动main方法即可

# 四 rose-framework技术博客


