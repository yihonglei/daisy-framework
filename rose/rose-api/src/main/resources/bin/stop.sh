#!/bin/bash
cd /u01/projectCAR/service/rose-api/

kill -9 `ps -ef|grep rose-api-1.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'`