#!/bin/bash
cd /u01/projectCAR/service/user-server

kill -9 `ps -ef|grep user-server-1.0.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'`
