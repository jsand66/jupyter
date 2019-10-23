#!/bin/bash
check_image=`sudo docker images|grep -w "jupyter-datascience"`
if [ -z "$check_image" ];then
        cd /home/entptools/jupyter-zone/
        build=`sudo docker build -t jupyter-datascience:base .`
        cont=`sudo docker run -d -p 8000:8888 jupyter-datascience:base`
		copy_workspace=`docker cp -r /var/opt/entptools/pcapquery/jupyter-workspace/* $cont:/home/entptools/`
        echo "$cont"
else
        cont=`sudo docker run -d -p 8000:8888 jupyter-datascience:base`
		copy_workspace=`docker cp -r /var/opt/entptools/pcapquery/jupyter-workspace/* $cont:/home/entptools/`
        echo "$cont"
fi