#!/bin/bash

cont=`sudo docker run -d -p 8000:8888 jupyter-datascience:base`
#container_name=`sudo docker ps --no-trunc|grep $container_id|awk -F '  +' '{print $7}'`
#port=`sudo docker ps --no-trunc|grep $container_id|awk -F '  +' '{print $6}'|cut -d"-" -f1|cut -d":" -f2`
echo "$cont"