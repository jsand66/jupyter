#!/bin/bash
check_image=`sudo docker images|grep -w "jupyter-datascience"`
if [ -z "$check_image" ];then
        cd /var/opt/tomcat-7.0.54/theice.com/entpmonitor/webapps/jupyter-support/WEB-INF/classes/
        build=`sudo docker build -t jupyter-datascience:base .`
        cont=`sudo docker run -d -p 8000:8888 -v /var/opt/entptools/pcapquery/jupyter-workspace:/home/entptools jupyter-datascience:base`
        echo "$cont"
else
        cont=`sudo docker run -d -p 8000:8888 -v /var/opt/entptools/pcapquery/jupyter-workspace:/home/entptools jupyter-datascience:base`
        echo "$cont"
fi