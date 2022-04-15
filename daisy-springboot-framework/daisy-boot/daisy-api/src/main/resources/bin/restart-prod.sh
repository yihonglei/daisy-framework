#!/bin/bash
cd /u01/projectCAR/service/daisy-api/
project=daisy-api-1.0-SNAPSHOT.jar

#kill -9
pid=`ps -ef|grep ${project}|grep -v grep|awk '{print $2}'`
if [ ! -n "$pid" ]; then
    printf "not found $pid\n"
else
    printf "$pid stopping .....\n"
    kill -9 $pid
    printf "kill -9 $pid....\n"
fi

#jmx start
service_name="daisy-api"
ip=`ifconfig eth0|grep "inet addr"|awk '{print $2}'|awk -F':' '{print $2}'`
jmx_port=3010

for i in `seq 0 5`;do
   netstat -lnpt|grep ${jmx_port} >/dev/null
   if [[ $? -ne 0 ]];then
       echo ${jmx_port}
       break
   fi
   ((jmx_port++))
done

curl -X PUT http://10.7.103.150:8500/v1/agent/service/register -d '
{
  "ID": "'${ip}:${jmx_port}'",
  "Name": "jmx_exporter",
  "Tags":["jmx_exporter"],
  "Address":"'${ip}'",
  "Port":'${jmx_port}',
  "Meta": {
    "service_name": "'${service_name}'"
  }
}'
#jmx end

export JAVA_OPTS="
  -server
  -Xms5078M
  -Xmx5078M
  -Xmn3048M
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
  -XX:-OmitStackTraceInFastThrow
  -Xloggc:/u01/daisy-api/log/gc.log
  -javaagent:/u01/project/service/jmx_prometheus_javaagent-0.12.0.jar=${jmx_port}:/u01/projectCAR/service/saf-api/bin/jmx.yml
  "

export JAVA_HOME=/usr/local/java
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
nohup /usr/local/java/bin/java ${JAVA_OPTS} -jar ${project} --spring.profiles.active=online > nohup.out 2>&1 &