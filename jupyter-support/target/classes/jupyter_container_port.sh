#!/bin/bash
port=`sudo docker ps --no-trunc|grep $1|awk -F '  +' '{print $6}'|cut -d"-" -f1|cut -d":" -f2`
echo "$port"