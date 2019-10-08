#!/bin/bash
echo "Building WAR with maven..."
mvn clean install

echo "Building and running docker images..."
docker-compose up --build --force-recreate -d