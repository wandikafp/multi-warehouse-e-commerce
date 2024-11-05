#!/bin/bash

# Start Zookeeper
docker-compose up -d zookeeper -f zookeeper.yml
echo "Waiting for Zookeeper..."
while ! nc -z localhost 2181; do sleep 1; done
echo "Zookeeper is ready."

# Start Kafka Broker
docker-compose up -d kafka-broker -f kafka_cluster.yml
echo "Waiting for Kafka Broker..."
while (! nc -z localhost 19092) && (! nc -z localhost 29092) && (! nc -z localhost 39092); do sleep 1; done
echo "Kafka Broker is ready."

# Start Schema Registry
docker-compose up -d schema-registry -f schema-registry.yml
echo "Waiting for Schema Registry..."
while ! nc -z localhost 8081; do sleep 1; done
echo "Schema Registry is ready."

# Start Kafka Manager
docker-compose up -d kafka-manager -f kafka-manager.yml
echo "Kafka Manager is ready."