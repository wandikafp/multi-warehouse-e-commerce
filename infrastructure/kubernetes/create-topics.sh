#local-confluent-kafka-cp-zookeeper-headless
kafka-topics --zookeeper $1:2181 --topic test-request --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic register-user --delete --if-exists

kafka-topics --zookeeper $1:2181 --topic test-request --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic register-user --create --partitions 3 --replication-factor 3 --if-not-exists

