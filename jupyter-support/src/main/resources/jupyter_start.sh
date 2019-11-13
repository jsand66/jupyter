#!/bin/bash
cd /var/opt/tomcat-7.0.54/theice.com/entpmonitor/webapps/jupyter-support/WEB-INF/classes/
check_image=`sudo docker-compose up --build -d`
cont=`sudo docker ps --no-trunc|grep jupyter-container|awk '{print $1}'`
echo "$cont"