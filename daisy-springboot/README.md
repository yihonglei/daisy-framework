# daisy-springboot-framework

## 1、项目说明

基于 SpringBoot 分布式架构，以及各种互联网技术。

## 2、开发环境

- Mac + JDK8

## 3、架构技术

- Web框架：Spring Boot

- 数据库：MySQL，druid 连接池

- ORM 框架：MyBatis

- 缓存中间件：Redis

- 消息中间件：RocketMQ + Canal

- 分布式锁：支持 Redis 或 Zookeeper

- 分布式事务：柔性事务，RocketMQ 最终一致性，定时任务做双层防护

- 分布式调度：elastic-job（当当分布式）

- 系统日志：logback，elk

- 系统监控：[sunflower](https://github.com/jchry/sunflower)

- 工程化：idea，Maven，Git

- 运维部署：Tomcat，Linux，Docker，k8s，jenkins

## 4、模块说明

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

提供API访问，由 daisy-api，daisy-common，daisy-core 组成。

- api 负责对外提供接口访问；

- common 公用代码和工具包；

- core 负责核心业务的编写；

2、daisy-canal

监控 binlog，发送 MQ。

3、daisy-canal-consumer

消费 binlog 消息，触发业务。

4、daisy-job

基于 elastic-job 实现分布式 job。

## 5、项目构建

### 基础组件构建

从上到下 clean install 构建。

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

- 构建: clean install 确保能编译打包通过

- 启动: daisy-api下com/jpeony/api/ApiApplication.java，main 方法运行即可启动

- 访问: http://localhost:8765/test/test, 返回结果: {"code":1,"data":null,"msg":"daisy启动成功了!"}

## 5、技术博客

### 技术选型

[微服务框架技术选型]

[API网关技术选型]

[注册中心技术选型]

[配置中心技术选型]

[ORM 框架技术选型]

[序列化框架技术选型]

[网络通信技术选型]

[集群容错技术选型]

[连接池技术选型]

[分布式事务技术选型](https://jpeony.blog.csdn.net/article/details/107828429)

[分布式锁技术选型]

[分布式定时任务技术选型]

[分库分表技术选型]

[缓存中间件技术选型](https://jpeony.blog.csdn.net/article/details/105379126)

[消息中间件技术选型](https://jpeony.blog.csdn.net/article/details/105350892)

[系统监控选型]

[日志框架技术选型]

[海量存储技术选型]

### 最佳实战

[项目命名规范](https://blog.csdn.net/yhl_jxy/article/details/103946580)

[代码和工程规范]

[MySQL 开发规范](https://jpeony.blog.csdn.net/article/details/111880983)

[ThreadPoolExecutror 线程池]

[Fastjson 序列化]

[Druid 连接池]

[OkHttp 分布式通信]

[Redis 实现分布式锁]

[Zookeeper 实现分布式锁]

[可靠消息最终一致性分布式事务]

[RocketMQ 集成](https://blog.csdn.net/yhl_jxy/article/details/103968635)

[Event Listener 事件监听](https://blog.csdn.net/yhl_jxy/article/details/106317858)

[Delayed 延时任务](https://blog.csdn.net/yhl_jxy/article/details/106750450)

[SpringBoot+MyBatis+Druid 动态多数据源](https://jpeony.blog.csdn.net/article/details/112360427)

[Nacos 注册中心]

[Sentinel 限流降级]

[Redis 集群模式]

[SpringBoot+shardingsphere 动态分表]

[Elastic-job+Zookeeper 分布式定时任务]

[logback 日志]

[Kafka+Filebeat+ELK 日志处理和数据分析] 

[Sunflower 系统监控]


