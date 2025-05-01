#!/bin/bash

# List of plugins to install
JENKINS_PLUGINS=(
  "workflow-aggregator"           # Pipeline
  "git"                          # Git integration
  "docker-workflow"              # Docker Pipeline
  "kubernetes"                   # Kubernetes integration
  "blueocean"                    # Blue Ocean UI
  "configuration-as-code"        # Configuration as Code
  "job-dsl"                      # Job DSL
  "credentials-binding"          # Credentials Binding
  "timestamper"                  # Timestamper
  "ws-cleanup"                   # Workspace Cleanup
  "ansicolor"                    # ANSI Color
  "nodejs"                       # NodeJS
  "sonar"                        # SonarQube
  "jacoco"                       # JaCoCo
  "junit"                        # JUnit
  "htmlpublisher"                # HTML Publisher
  "performance"                  # Performance
  "slack"                        # Slack Notification
)

# Install plugins
for plugin in "${JENKINS_PLUGINS[@]}"
do
  echo "Installing $plugin"
  docker exec jenkins-server jenkins-plugin-cli --plugins "$plugin"
done

# Restart Jenkins to apply plugins
echo "Restarting Jenkins to apply plugins..."
docker restart jenkins-server
