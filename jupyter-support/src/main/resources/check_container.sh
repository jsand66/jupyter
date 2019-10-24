#!/bin/bash
check=`docker ps|grep $1`
if [ -z $check ];then
	echo "no"
else
	echo "yes"
fi	