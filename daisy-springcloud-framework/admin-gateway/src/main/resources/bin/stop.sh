#!/bin/bash
cd /u01/projectCAR/service/admin-gateway

kill -9 `ps -ef|grep admin-gateway-1.0.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'`
