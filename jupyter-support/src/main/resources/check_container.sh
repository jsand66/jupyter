#!/bin/bash
check=`sudo docker ps --no-trunc|grep jupyter-datascience:base`
if [ -z $check ];then
	echo "no"
else
	container_id=`sudo docker ps --no-trunc|grep jupyter-datascience:base|awk -F '  +' '{print $1}'`
	container_name=`sudo docker ps --no-trunc|grep jupyter-datascience:base|awk -F '  +' '{print $7}'`
	container_port=`sudo docker ps --no-trunc|grep jupyter-datascience:base|awk -F '  +' '{print $6}'|cut -d"-" -f1|cut -d":" -f2`
	echo "$container_id,$container_name,$container_port"
fi	