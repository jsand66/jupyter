#!/bin/bash
id=$1
sudo docker stop $1
sudo docker rm $1