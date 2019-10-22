#!/bin/bash

container_id=`docker run -d -p :8888 jup-alp:v3`
container_name=`docker ps --no-trunc|grep $container_id|awk -F '  +' '{print $7}'`
port=`docker ps --no-trunc|grep $container_id|awk -F '  +' '{print $6}'|cut -d"-" -f1|cut -d":" -f2`
echo "{\"container_id\":\"$container_id\",\"container_name\":\"$container_name\",\"port\":$port}"

