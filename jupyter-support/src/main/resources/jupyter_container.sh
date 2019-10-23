#!/bin/bash
container_name=`sudo docker ps --no-trunc|grep $1|awk -F '  +' '{print $7}'`
echo "$container_name"
