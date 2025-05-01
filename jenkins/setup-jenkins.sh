#!/bin/bash

# Create a network for Jenkins
docker network create jenkins

# Create a volume for Jenkins data
docker volume create jenkins-data

# Run Jenkins container
docker run --name jenkins-server --restart=always --detach \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  jenkins/jenkins:lts

# Wait for Jenkins to start
echo "Waiting for Jenkins to start..."
sleep 30

# Get the initial admin password
JENKINS_PASSWORD=$(docker exec jenkins-server cat /var/jenkins_home/secrets/initialAdminPassword)
echo "Jenkins initial admin password: $JENKINS_PASSWORD"
echo "Access Jenkins at http://localhost:8080"

# Install suggested plugins and create admin user
# This would typically be done through the Jenkins Configuration as Code plugin
# or through the Jenkins UI setup wizard
