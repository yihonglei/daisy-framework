# A、daisy-springboot-framework

基于 SpringBoot 分布式架构，以及各种互联网技术。

## 开发环境

- Mac + JDK8

## 技术栈

- Web框架：Spring Boot

- 数据库：MySql，druid 连接池

- ORM框架：MyBatis

- 缓存中间件：Redis

- 消息中间件：RocketMQ

- 分布式锁：支持 Redis 或 Zookeeper

- 分布式事务：柔性事务，RocketMQ 最终一致性，定时任务做双层防护

- 定时任务：elastic-job（当当分布式）

- 系统日志：logback，elk

- 系统监控：[sunflower](https://github.com/jchry/sunflower)

- 工程化：idea，Maven，Git，jenkins，Docker&k8s

- 运维部署：tomcat，linux

## 模块说明

### 基础依赖

daisy-parent：基础依赖

### 基础组件

- http：daisy-okhttp

- redis：daisy-redis-spring-boot-starter

- mongodb：daisy-mongodb-spring-boot-starter

- rocketmq：daisy-rocketmq-spring-boot-starter

- 分布式锁：daisy-distributed-lock

在实际应用中基础依赖，基础组件按版本维护，发布到私服。

### 应用程序

1、daisy

提供API访问，由 daisy-api，daisy-common，daisy-core组成。

- api 负责对外提供接口访问；

- common 公用代码和工具包；

- core 负责核心业务的编写；

2、daisy-canal

监控binlog，发送MQ。

3、daisy-canal-consumer

消费binlog消息，触发业务。

4、daisy-job

基于elastic-job实现分布式job。

## 项目构建

### 基础组件构建

从上到下clean install构建。

- daisy-parent

- daisy-rocketmq-spring-boot-starter

- daisy-mongodb-spring-boot-starter

- daisy-redis-spring-boot-starter

### daisy 应用

1、配置修改

daisy-api/resources/application-dev.yml 修改相关配置地址。

- MySql 地址

- RocketMQ 地址

2、工程构建、启动、访问

- 构建: clean install确保能编译打包通过

- 启动: daisy-api下com/jpeony/api/ApiApplication.java，main方法运行即可启动

- 访问: http://localhost:8765/test/test, 返回结果: {"code":1,"data":null,"msg":"daisy启动成功了!"}

# B、daisy-dubbo-framework

基于 Dubbo 微服务架构脚手架。

# C、daisy-springcloud-framework

基于 SpringCloud 微服务架构脚手架。

# D、技术博客

## 1、技术选型

[技术选型之微服务框架]

[技术选型之API网关]

[技术选型之注册中心]

[技术选型之配置中心]

[技术选型之ORM框架]

[技术选型之JSON框架]

[技术选型之分布式通信]

[技术选型之限流降级框架]

[技术选型之数据库连接池]

[技术选型之定时任务框架]

[技术选型之分库分表中间件]

[技术选型之缓存中间件](https://jpeony.blog.csdn.net/article/details/105379126)

[技术选型之消息中间件](https://jpeony.blog.csdn.net/article/details/105350892)

[技术选型之系统监控框架]

[技术选型之日志框架]

[技术选型之海量数据存储]

## 2、开发实战

[项目命名规范](https://blog.csdn.net/yhl_jxy/article/details/103946580)

[代码和工程规范]

[MySQL 开发规范](https://jpeony.blog.csdn.net/article/details/111880983)

[ThreadPoolExecutror 线程池]

[Fastjson 序列化]

[Druid 连接池]

[OkHttp 分布式通信]

[RocketMQ 集成](https://blog.csdn.net/yhl_jxy/article/details/103968635)

[Event Listener 事件监听](https://blog.csdn.net/yhl_jxy/article/details/106317858)

[Delayed 延时任务](https://blog.csdn.net/yhl_jxy/article/details/106750450)

[SpringBoot+MyBatis+Druid 动态多数据源](https://jpeony.blog.csdn.net/article/details/112360427)

[分布式事务](https://jpeony.blog.csdn.net/article/details/107828429)

[Nacos 注册中心]

[Sentinel 限流降级]

[Redis 集群模式]

[SpringBoot+shardingsphere 动态分表]

[Elastic-job+Zookeeper 分布式定时任务]

[logback 日志]

[Kafka+Filebeat+ELK 日志处理和数据分析] 

[Sunflower 系统监控]

