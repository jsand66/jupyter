#!/bin/bash
check=`sudo docker ps --no-trunc|grep $1`
if [ -z $check ];then
	echo "no"
else
	echo "yes"
fi	