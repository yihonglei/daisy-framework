# 一、下载
https://redis.io/

# 二 安装
tar zxvf redis-5.0.4.tar.gz
将解压后文件夹放到 /usr/local 下，并重命名为 redis

mv redis-5.0.4 /usr/local/redis
切换到相应目录

cd /usr/local/redis
编译测试

sudo make test
编译安装
sudo make install
# 三、配置
在 redis 目录下建立 bin

sudo mkdir  /usr/local/redis/bin
把 /usr/local/redis/src 目录下的 mkreleasehdr.sh，redis-benchmark， redis-check-rdb， redis-cli， redis-server 拷贝到 bin 目录

cp /usr/local/redis/src/mkreleasehdr.sh
cp /usr/local/redis/src/redis-benchmark
cp /usr/local/redis/src/redis-check-rdb
cp /usr/local/redis/src/redis-cli
cp /usr/local/redis/src/redis-server
新建 /u01/redis/6379，在 6379 目录下分别新建 conf，data，log，pid 目录，拷贝 redis.conf 到 /u01/redis/6379/conf 下，

重命名为 redis_6379.conf，搭建主从、哨兵、集群时参考 6379 的建立方式依次建立 6380 等等端口实例，基于实例做主从架构，哨兵模式和集群模式。

cp /usr/local/redis/redis.conf /u01/redis/6379/conf/
修改 redis_6379.conf

#修改为守护模式
daemonize yes
#绑定
bind 0.0.0.0
#设置进程锁文件
pidfile /u01/redis/6379/pid/redis_6379.pid
#端口
port 6379
#客户端超时时间
timeout 300
#日志级别
loglevel debug
#日志文件位置
logfile /u01/redis/6379/log/redis_6379.log
#设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id
databases 16
##指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合
#save <seconds> <changes>
#Redis默认配置文件中提供了三个条件：
save 900 1
save 300 10
save 60 10000
#指定存储至本地数据库时是否压缩数据，默认为yes，Redis采用LZF压缩，如果为了节省CPU时间，
#可以关闭该#选项，但会导致数据库文件变的巨大
rdbcompression yes
#指定本地数据库文件名（启动时候没有，写入数据时才有备份文件）
dbfilename redis_6379_dump.rdb
#指定本地数据库路径
dir /u01/redis/6379/data/
#指定是否在每次更新操作后进行日志记录，Redis在默认情况下是异步的把数据写入磁盘，如果不开启，可能
#会在断电时导致一段时间内的数据丢失。因为 redis本身同步数据文件是按上面save条件来同步的，所以有
#的数据会在一段时间内只存在于内存中
appendonly no
#指定更新日志条件，共有3个可选值：
#no：表示等操作系统进行数据缓存同步到磁盘（快）
#always：表示每次更新操作后手动调用fsync()将数据写到磁盘（慢，安全）
#everysec：表示每秒同步一次（折衷，默认值）
appendfsync everysec
启动服务

./bin/redis-server /u01/redis/6379/conf/redis_6379.conf
查看日志

tail -f /u01/redis/6379/log/redis_6379.log
redis 安装错误处理：
(error) MISCONF Redis is configured to save RDB snapshots, but it is currently not able to persist on disk. 
Commands that may modify the data set are disabled, because this instance is configured to report errors 
during writes if RDB snapshotting fails (stop-writes-on-bgsave-error option). Please check the Redis logs for 
details about the RDB error.
redis-cli 进入客户端报错后，通过如下设置解决。
127.0.0.1:6379> config set stop-writes-on-bgsave-error no
# 四、redis 卸载
1. 停止 redis 服务器

1.1 首先,通过下面的命令查看 redis 服务是否运行

    ps aux|grep redis

    cd /usr/local/

1.2 停止 redis 服务器

    redis-cli shutdown

    ps aux|grep redis

1.3 删除 make 的时候生成的几个 redis 文件

    rm -f /usr/local/bin/redis*

1.4 删除解压后的文件目录和所有文件
    rm -rf redis-4.0.10
    rm -rf redis-4.0.10.tar


