#!/bin/bash

echo -e "Stop Docker Compose..."
echo -e "Stop Kafka Init..."
docker-compose -f common.yml -f init_kafka.yml down

echo -e "Stop Kafka Cluster..."
docker-compose -f common.yml -f kafka_cluster.yml down

echo -e "Stop Zookeeper..."
docker-compose -f common.yml -f zookeeper.yml down



