gcloud auth login

docker tag com.anw/frontend:latest asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/frontend:latest

docker tag com.anw/order.service:$1 asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/order.service:$1
docker tag com.anw/payment.service:$1 asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/payment.service:$1
docker tag com.anw/product.service:$1 asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/product.service:$1
docker tag com.anw/user.service:$1 asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/user.service:$1
docker tag com.anw/warehouse.service:$1 asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/warehouse.service:$1

docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/frontend:latest

docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/order.service:$1
docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/payment.service:$1
docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/product.service:$1
docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/user.service:$1
docker push asia-southeast2-docker.pkg.dev/wandika-project/com-multiwarehouse-e-commerce-repository/warehouse.service:$1
