#!/bin/bash
container_id=$1
docker cp /tmp/$2 $container_id:/home/jupyter/
rm -rf /tmp/$2

