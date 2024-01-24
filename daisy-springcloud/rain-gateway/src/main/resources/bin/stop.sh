#!/bin/bash
cd /u01/projectCAR/service/rain-gateway

kill -9 `ps -ef|grep rain-gateway-1.0.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'`
