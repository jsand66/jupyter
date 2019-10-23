#!/bin/bash
sed -i 's/#c.NotebookApp.tornado_settings/c.NotebookApp.tornado_settings/' ~/.jupyter/jupyter_notebook_config.py
sed -i "/^c.NotebookApp.tornado_settings/s/=.*$/={'headers': {'Content-Security-Policy': \"frame-ancestors * \"}}/" ~/.jupyter/jupyter_notebook_config.py

jupyter lab --ip=0.0.0.0 --port=8888 --NotebookApp.token='' --NotebookApp.password='' --allow-root &
while true
do
        sleep 60s
done
