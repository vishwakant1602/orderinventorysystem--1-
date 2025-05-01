#!/bin/bash

# Create directories
mkdir -p jenkins kubernetes/{dev,staging,production} integration-tests performance-tests

# Copy files to their respective directories
cp Jenkinsfile ./
cp frontend/Dockerfile frontend/
cp backend/*/Dockerfile backend/*/
cp kubernetes/dev/*.yaml kubernetes/dev/
cp jenkins/*.sh jenkins/
cp jenkins/jenkins-config.yaml jenkins/

# Set up Jenkins
cd jenkins
chmod +x setup-jenkins.sh install-plugins.sh
./setup-jenkins.sh
./install-plugins.sh

echo "CI/CD environment setup complete!"
echo "Next steps:"
echo "1. Access Jenkins at http://localhost:8080"
echo "2. Configure Jenkins using the provided jenkins-config.yaml"
echo "3. Set up your Kubernetes clusters for dev, staging, and production"
echo "4. Create your integration and performance tests"
