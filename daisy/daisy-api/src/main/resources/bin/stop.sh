#!/bin/bash
cd /u01/project/service/daisy-api/

kill -9 `ps -ef|grep daisy-api-1.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'`