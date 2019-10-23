#!/bin/bash

copy_status=`sudo docker cp -r $1:/home/entptools/* /var/opt/entptools/pcapquery/jupyter-workspace/`
echo "copied"