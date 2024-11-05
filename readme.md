## Kubernetes

### Login:
- gloud auth login
- Clik the link
- Follow the login step then copy the token
- Paste the token

### Push Image:
- docker tag SOURCE-IMAGE LOCATION-docker.pkg.dev/PROJECT-ID/REPOSITORY/IMAGE:TAG
- Example: docker tag com.anw/warehouse.service:1.0-SNAPSHOT asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/warehouse.service:1.0-SNAPSHOT
- docker push LOCATION-docker.pkg.dev/PROJECT-ID/REPOSITORY/IMAGE:TAG
- Example: docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/warehouse.service:1.0-SNAPSHOT

### Deploy cp-helm-charts:
- git clone https://github.com/confluentinc/cp-helm-charts.git
- You can follow the documentation for installation and else.
- For version error please update poddisruptionbudget.yaml file inside cp-helm-charts/charts/cp-zookeeper/templates/. Update v1betav1 to v1
helm install confluentinc/cp-helm-charts --name my-confluent --version 0.6.0 (for me I use helm install {name} cp-helm-charts –version 0.6.0).
- NB: Just for course: helm install gke-confluent-kafka cp-helm-charts –version 0.6.0
- After run the command multiple pods related to kafka will be created

### Deploy Kafka client
```yaml
apiVersion: v1
kind: Pod
metadata:
 name: kafka-client
 namespace: default
spec:
 containers:
   - name: kafka-client
     image: confluentinc/cp-enterprise-kafka:6.1.0
     volumeMounts:
       - name: kafka-client-storage
         mountPath: /kafka-client-storage
     command:
       - sh
       - -c
       - "exec tail -f /dev/null"
 volumes:
   - name: kafka-client-storage
```
- kubectl apply -f {kafka-client.yml} 

### Create Topic in Kafka
- kubectl cp create-topics.sh kafka-client:/kafka-client-strorage
- kubectl exec -it kafka-client -- /bin/bash
- sh create-topics.sh gke-confluent-kafka-cp-zookeeper-headless

### Deploy postgres in k8s
```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
 name: postgres-deployment
 namespace: default
 labels:
   app: postgres-deployment
spec:
 selector:
   matchLabels:
     app: postgres-deployment
 template:
   metadata:
     labels:
       app: postgres-deployment
   spec:
     containers:
       - name: postgres
         image: postgres:latest
         env:
           - name: POSTGRES_PASSWORD
             value: "admin"
---
apiVersion: v1
kind: Service
metadata:
 name: postgres-service
 namespace: default
 labels:
   app: postgres-deployment
spec:
 selector:
   app: postgres-deployment
 ports:
   - name: postgres-service-port
     protocol: TCP
     port: 5432
 type: LoadBalancer
 loadBalancerIP: ""
```
- kubectl apply -f {postgres deployment yaml file name}

## Deploy Application
```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: warehouse-deployment
  namespace: default
  labels:
    app: warehouse-deployment
spec:
  selector:
    matchLabels:
      app: warehouse-deployment
  template:
    metadata:
      labels:
        app: warehouse-deployment
    spec:
      containers:
        - name: warehouse-service
          image: asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/wandika.service:1.0-SNAPSHOT
          env:
            - name: SPRING_DATASOURCE_URL
              value: "jdbc:postgresql://postgres-service:5432/postgres?currentSchema=warehouse&binaryTransfer=true&reWriteBatchedInserts=true&stringtype=unspecified"
            - name: SPRING_DATASOURCE_INITIALIZATION-MODE
              value: "never"
            - name: KAFKA-CONFIG_BOOTSTRAP-SERVERS
              value: "gke-confluent-kafka-cp-kafka-headless:9092"
            - name: KAFKA-CONFIG_SCHEMA-REGISTRY-URL
              value: "http://gke-confluent-kafka-cp-schema-registry:8081"
          resources:
            limits:
              cpu: "500m"
              memory: "1024Mi"
            requests:
              cpu: "200m"
              memory: "256Mi"
---
apiVersion: v1
kind: Service
metadata:
  name: warehouse-service
  namespace: default
  labels:
    app: warehouse-service
spec:
  selector:
    app: warehouse-deployment
  ports:
    - name: warehouse-service-port
      protocol: TCP
      port: 8181
  type: LoadBalancer
  loadBalancerIP: ""
---
apiVersion: autoscaling/v2beta1
kind: HorizontalPodAutoscaler
metadata:
  name: warehouse-deployment-hpa
  namespace: default
  labels:
    app: warehouse-deployment
spec:
  scaleTargetRef:
    kind: Deployment
    name: warehouse-deployment
    apiVersion: apps/v1
  minReplicas: 2
  maxReplicas: 4
  metrics:
    - type: Resource
      resource:
        name: cpu
        targetAverageUtilization: 85
```

- kubectl apply -f {application deployment yaml file}
