#!/bin/bash
cd /u01/project/service/daisy-api/
project=daisy-api-1.0-SNAPSHOT.jar

#kill -9
pid=`ps -ef|grep ${project}|grep -v grep|awk '{print $2}'`
if [ ! -n "$pid" ]; then
    printf "not found $pid\n"
else
    printf "$pid stopping ....\n"
    kill -9 $pid
    printf "kill -9 $pid....\n"
fi

export JAVA_OPTS="
  -server
  -Xms1024M
  -Xmx1024M
  -Xmn512M
  -Xss256K
  -XX:SurvivorRatio=8
  -XX:MetaspaceSize=256m
  -XX:MaxMetaspaceSize=256m
  -XX:TargetSurvivorRatio=80
  -XX:MaxTenuringThreshold=10
  -XX:+UseParNewGC
  -XX:+UseConcMarkSweepGC
  -XX:+CMSClassUnloadingEnabled
  -XX:+UseCMSCompactAtFullCollection
  -XX:CMSFullGCsBeforeCompaction=2
  -XX:-CMSParallelRemarkEnabled
  -XX:+DisableExplicitGC
  -XX:CMSInitiatingOccupancyFraction=80
  -XX:SoftRefLRUPolicyMSPerMB=0
  -XX:+DisableExplicitGC
  -XX:+PrintGC
  -XX:+PrintGCDetails
  -XX:+PrintGCDateStamps
  -XX:+PrintGCTimeStamps
  -XX:+PrintGCApplicationConcurrentTime
  -XX:+PrintGCApplicationStoppedTime
  -XX:+PrintHeapAtGC
  -XX:+UnlockDiagnosticVMOptions
  -XX:ParGCCardsPerStrideChunk=32768
  -XX:+PrintTenuringDistribution
  -Xloggc:/u01/daisy-api/log/gc.log
  "

export JAVA_HOME=/usr/local/java
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
nohup /usr/local/java/bin/java ${JAVA_OPTS} -jar ${project} --spring.profiles.active=dev > nohup.out 2>&1 &