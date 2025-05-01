pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-credentials-id') // ✅ set to exact ID
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vishwakant1602/orderinventorysystem--1-', branch: 'main'
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    // Run npm ci with --legacy-peer-deps to avoid peer dependency issues
                    sh 'npm ci --legacy-peer-deps'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry('', DOCKER_HUB_CREDENTIALS) {
                        def app = docker.build("vishwakant1602/orderinventory")
                        app.push("latest")
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh 'kubectl apply -f k8s/'
                }
            }
        }
    }

    post {
        always {
            script {
                echo "Pipeline finished."
            }
        }
        failure {
            script {
                echo "Build failed. Check Jenkins logs."
            }
        }
    }
}



// pipeline {
//     agent any

//     environment {
//         DOCKER_HUB_CREDENTIALS = credentials('docker-credentials-id') // ✅ set to exact ID
//         // SONAR_TOKEN = credentials('sonar-token') // ❌ Remove if not using SonarQube
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 git url: 'https://github.com/vishwakant1602/orderinventorysystem--1-', branch: 'main'
//             }
//         }

//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     docker.withRegistry('', DOCKER_HUB_CREDENTIALS) {
//                         def app = docker.build("vishwakant1602/orderinventory")
//                         app.push("latest")
//                     }
//                 }
//             }
//         }

//         stage('Deploy to Kubernetes') {
//             steps {
//                 script {
//                     sh 'kubectl apply -f k8s/'
//                 }
//             }
//         }
//     }

//     post {
//         always {
//             script {
//                 // You can add cleanup or logging here if needed
//                 echo "Pipeline finished."
//             }
//         }
//         failure {
//             script {
//                 echo "Build failed. Check Jenkins logs."
//                 // Remove or comment out email step if not configured
//                 // emailext(to: 'singhvishwakant6910@gmail.com',
//                 //          subject: "Jenkins Job Failed",
//                 //          body: "Check the Jenkins job logs.")
//             }
//         }
//     }
// }





// pipeline {
//     agent any

//     tools {
//         maven 'Maven 3.8.6'
//         jdk 'JDK 17'
//     }

//     environment {
//         DOCKER_REGISTRY = credentials('docker-registry-url')
//         VERSION = "${env.BUILD_NUMBER}"
//         DOCKER_COMPOSE_VERSION = "2.17.2"
//         SONAR_URL = credentials('sonar-url')
//         SONAR_TOKEN = credentials('sonar-token')
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Install Dependencies') {
//             steps {
//                 sh '''
//                     if ! command -v docker &> /dev/null; then
//                         echo "Docker not found, installing..."
//                         curl -fsSL https://get.docker.com -o get-docker.sh
//                         sh get-docker.sh
//                     fi

//                     if ! command -v docker-compose &> /dev/null; then
//                         echo "Docker Compose not found, installing..."
//                         curl -L "https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
//                         chmod +x /usr/local/bin/docker-compose
//                     fi
//                 '''
//             }
//         }

//         stage('Build') {
//             steps {
//                 sh 'mvn clean package -DskipTests'
//             }
//         }

//         stage('Unit Tests') {
//             steps {
//                 sh 'mvn test'
//             }
//             post {
//                 always {
//                     junit '**/target/surefire-reports/*.xml'
//                 }
//             }
//         }

//         stage('SonarQube Analysis') {
//             steps {
//                 withSonarQubeEnv('SonarQube') {
//                     sh """
//                         mvn sonar:sonar \
//                         -Dsonar.host.url=${SONAR_URL} \
//                         -Dsonar.login=${SONAR_TOKEN} \
//                         -Dsonar.projectKey=orderinventorysystem \
//                         -Dsonar.projectName='Order Inventory System' \
//                         -Dsonar.java.binaries=target/classes
//                     """
//                 }
//             }
//         }

//         stage('Build Docker Images') {
//             steps {
//                 script {
//                     withCredentials([usernamePassword(credentialsId: 'docker-credentials-id', usernameVariable: 'DOCKER_CREDENTIALS_USR', passwordVariable: 'DOCKER_CREDENTIALS_PSW')]) {
//                         sh "echo ${DOCKER_CREDENTIALS_PSW} | docker login ${DOCKER_REGISTRY} -u ${DOCKER_CREDENTIALS_USR} --password-stdin"
//                     }

//                     sh "docker build -t ${DOCKER_REGISTRY}/api-gateway:${VERSION} -f api-gateway/Dockerfile ."
//                     sh "docker build -t ${DOCKER_REGISTRY}/order-service:${VERSION} -f order-service/Dockerfile ."
//                     sh "docker build -t ${DOCKER_REGISTRY}/inventory-service:${VERSION} -f inventory-service/Dockerfile ."
//                     sh "docker build -t ${DOCKER_REGISTRY}/payment-service:${VERSION} -f payment-service/Dockerfile ."
//                     sh "docker build -t ${DOCKER_REGISTRY}/frontend:${VERSION} -f frontend/Dockerfile ."
//                 }
//             }
//         }

//         stage('Push Docker Images') {
//             steps {
//                 script {
//                     sh "docker push ${DOCKER_REGISTRY}/api-gateway:${VERSION}"
//                     sh "docker push ${DOCKER_REGISTRY}/order-service:${VERSION}"
//                     sh "docker push ${DOCKER_REGISTRY}/inventory-service:${VERSION}"
//                     sh "docker push ${DOCKER_REGISTRY}/payment-service:${VERSION}"
//                     sh "docker push ${DOCKER_REGISTRY}/frontend:${VERSION}"

//                     sh "docker tag ${DOCKER_REGISTRY}/api-gateway:${VERSION} ${DOCKER_REGISTRY}/api-gateway:latest"
//                     sh "docker tag ${DOCKER_REGISTRY}/order-service:${VERSION} ${DOCKER_REGISTRY}/order-service:latest"
//                     sh "docker tag ${DOCKER_REGISTRY}/inventory-service:${VERSION} ${DOCKER_REGISTRY}/inventory-service:latest"
//                     sh "docker tag ${DOCKER_REGISTRY}/payment-service:${VERSION} ${DOCKER_REGISTRY}/payment-service:latest"
//                     sh "docker tag ${DOCKER_REGISTRY}/frontend:${VERSION} ${DOCKER_REGISTRY}/frontend:latest"

//                     sh "docker push ${DOCKER_REGISTRY}/api-gateway:latest"
//                     sh "docker push ${DOCKER_REGISTRY}/order-service:latest"
//                     sh "docker push ${DOCKER_REGISTRY}/inventory-service:latest"
//                     sh "docker push ${DOCKER_REGISTRY}/payment-service:latest"
//                     sh "docker push ${DOCKER_REGISTRY}/frontend:latest"
//                 }
//             }
//         }

//         stage('Deploy to Development') {
//             steps {
//                 script {
//                     sh """
//                         sed -i 's|image: .*api-gateway:.*|image: ${DOCKER_REGISTRY}/api-gateway:${VERSION}|g' docker-compose.yml
//                         sed -i 's|image: .*order-service:.*|image: ${DOCKER_REGISTRY}/order-service:${VERSION}|g' docker-compose.yml
//                         sed -i 's|image: .*inventory-service:.*|image: ${DOCKER_REGISTRY}/inventory-service:${VERSION}|g' docker-compose.yml
//                         sed -i 's|image: .*payment-service:.*|image: ${DOCKER_REGISTRY}/payment-service:${VERSION}|g' docker-compose.yml
//                         sed -i 's|image: .*frontend:.*|image: ${DOCKER_REGISTRY}/frontend:${VERSION}|g' docker-compose.yml
//                     """
//                     sh "docker-compose up -d"
//                 }
//             }
//         }

//         stage('Integration Tests') {
//             steps {
//                 script {
//                     sh "sleep 30"
//                     sh "mvn verify -Pintegration-tests"
//                 }
//             }
//         }

//         stage('Deploy to Staging') {
//             when {
//                 branch 'develop'
//             }
//             steps {
//                 script {
//                     sshagent(['staging-server-credentials']) {
//                         sh """
//                             scp docker-compose.yml user@staging-server:/opt/orderinventorysystem/
//                             scp mongo-init.js user@staging-server:/opt/orderinventorysystem/
//                             ssh user@staging-server 'cd /opt/orderinventorysystem && docker-compose pull && docker-compose up -d'
//                         """
//                     }
//                 }
//             }
//         }

//         stage('Deploy to Production') {
//             when {
//                 branch 'main'
//             }
//             steps {
//                 input message: 'Deploy to production?', ok: 'Yes'
//                 script {
//                     sshagent(['production-server-credentials']) {
//                         sh """
//                             scp docker-compose.yml user@production-server:/opt/orderinventorysystem/
//                             scp mongo-init.js user@production-server:/opt/orderinventorysystem/
//                             ssh user@production-server 'cd /opt/orderinventorysystem && docker-compose pull && docker-compose up -d'
//                         """
//                     }
//                 }
//             }
//         }
//     }

//     post {
//         always {
//             node {
//                 sh "docker system prune -f"
//             }
//         }
//         success {
//             node {
//                 emailext (
//                     subject: "Build Successful: ${currentBuild.fullDisplayName}",
//                     body: "The build was successful. Check the results at: ${env.BUILD_URL}",
//                     recipientProviders: [developers(), requestor()]
//                 )
//             }
//         }
//         failure {
//             node {
//                 emailext (
//                     subject: "Build Failed: ${currentBuild.fullDisplayName}",
//                     body: "The build failed. Check the logs at: ${env.BUILD_URL}console",
//                     recipientProviders: [developers(), requestor()]
//                 )
//             }
//         }
//     }
// }
