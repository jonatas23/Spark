#!/bin/bash
echo "Building WAR with maven..."
mvn clean install

echo "Spark Master"
cd ./sparkDocker/master
docker-compose up --build --force-recreate -d

echo "Spark Worker"
cd ../worker
docker-compose up --build --force-recreate -d

cd ../../

echo "Building and running docker images..."
docker-compose up --build --force-recreate -d