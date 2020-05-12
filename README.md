# 持续完善中......

# 一 daisy-framework技术架构

## 开发环境

Mac + JDK8

## 统一架构

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

系统监控：sunflower https://github.com/jchry/sunflower

工程化：idea，Maven，Git，jenkins，Docker&k8s

项目部署：tomcat，linux

# 二 daisy-framework模块说明

## 基础依赖

daisy-parent：基础依赖

## 公共组件

http：daisy-okhttp

redis：daisy-redis-spring-boot-starter

mongodb：daisy-mongodb-spring-boot-starter

rocketmq：daisy-rocketmq-spring-boot-starter

分布式锁：daisy-distributed-lock

......

在实际应用中基础依赖，公共组件都发布到私服，别人要用直接引用即可。

## 应用程序

daisy：由daisy-api，daisy-common，daisy-core组成。

api负责controller层，对外提供接口；

common公用代码和工具包；

core负责核心业务的编写；

# 三 daisy-framework项目构建

1、基础组件构建顺序(clean install)

.daisy-parent

.daisy-rocketmq-spring-boot-starter

.daisy-mongodb-spring-boot-starter

.daisy-redis-spring-boot-starter

2、daisy工程构建(clean install)

daisy-api下com/jpeony/api/ApiApplication.java，main方法运行即可启动！

# 四 daisy-framework技术博客

[daisy-framework开发规范](https://blog.csdn.net/yhl_jxy/article/details/103946580)

[daisy-framework集成rocketmq](https://blog.csdn.net/yhl_jxy/article/details/103968635)


