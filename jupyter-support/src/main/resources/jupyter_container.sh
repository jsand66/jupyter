#!/bin/bash
container_name=`sudo docker ps --no-trunc|grep $1|awk -F '  +' '{print $7}'`
port=`sudo docker ps --no-trunc|grep $1|awk -F '  +' '{print $6}'|cut -d"-" -f1|cut -d":" -f2`
echo "${container_name},${port}"