#!/bin/bash
sudo docker cp /tmp/$2 $1:/home/entptools/
rm -rf /tmp/$2
