# Order Inventory System

A microservices-based order and inventory management system built with Spring Boot and MongoDB.

## Architecture

The system consists of the following microservices:

- **API Gateway**: Entry point for all client requests
- **Order Service**: Manages orders and order items
- **Inventory Service**: Manages inventory items and stock levels
- **Payment Service**: Handles payment processing
- **Frontend**: Next.js web application

## Prerequisites

- Java 17
- Maven 3.8+
- Docker and Docker Compose
- MongoDB (or use the provided Docker container)

## Local Development Setup

### 1. Clone the repository

\`\`\`bash
git clone https://github.com/yourusername/orderinventorysystem.git
cd orderinventorysystem
\`\`\`

### 2. Build the project

\`\`\`bash
mvn clean package -DskipTests
\`\`\`

### 3. Run with Docker Compose

\`\`\`bash
docker-compose up -d
\`\`\`

### 4. Access the application

- Frontend: http://localhost:80
- API Gateway: http://localhost:8080
- Order Service: http://localhost:8081
- Inventory Service: http://localhost:8082
- Payment Service: http://localhost:8084
- MongoDB: mongodb://localhost:27017

## CI/CD Pipeline

The project includes a Jenkins pipeline for continuous integration and deployment. The pipeline:

1. Builds the application
2. Runs unit tests
3. Performs SonarQube analysis
4. Builds Docker images
5. Pushes Docker images to a registry
6. Deploys to development environment
7. Runs integration tests
8. Deploys to staging (on develop branch)
9. Deploys to production (on main branch, with manual approval)

### Setting up Jenkins

1. Install Jenkins using the provided script:

\`\`\`bash
chmod +x jenkins-setup.sh
./jenkins-setup.sh
\`\`\`

2. Configure Jenkins credentials:
   - Docker registry credentials
   - SonarQube URL and token
   - SSH credentials for staging and production servers

3. Create a Jenkins pipeline job using the provided Jenkinsfile

### Pipeline Workflow

1. **Checkout**: Retrieves the source code from the repository.
2. **Frontend Build**: Installs dependencies, runs linting, tests, and builds the Next.js application.
3. **Backend Build**: Compiles and tests the Spring Boot microservices.
4. **Code Quality**: Analyzes code quality using SonarQube.
5. **Docker Build**: Creates Docker images for all components.
6. **Deploy to Dev**: Deploys the application to the development environment.
7. **Integration Tests**: Runs integration tests against the development environment.
8. **Deploy to Staging**: Deploys the application to the staging environment.
9. **Performance Tests**: Runs performance tests against the staging environment.
10. **Deploy to Production**: Deploys the application to the production environment (requires manual approval).

### Environments

- **Development**: Automatic deployment on every commit to the develop branch.
- **Staging**: Automatic deployment on every commit to the staging branch.
- **Production**: Manual approval required for deployment from the main branch.

## API Documentation

API documentation is available via Swagger UI:

- API Gateway: http://localhost:8080/swagger-ui.html
- Order Service: http://localhost:8081/swagger-ui.html
- Inventory Service: http://localhost:8082/swagger-ui.html
- Payment Service: http://localhost:8084/swagger-ui.html

## Monitoring and Alerts

The pipeline includes Slack notifications for build status updates.

## Troubleshooting

If you encounter issues with the pipeline, check:
1. Jenkins logs
2. Docker container logs
3. Kubernetes pod logs

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

Please follow the branching strategy:
- `feature/*` for new features
- `bugfix/*` for bug fixes
- `develop` for development
- `staging` for staging
- `main` for production

## License

This project is licensed under the MIT License - see the LICENSE file for details.
