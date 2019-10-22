#!/bin/bash
container_id=$1
docker stop $container_id
docker rm $container_id
