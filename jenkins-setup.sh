#!/bin/bash

echo "Updating Homebrew..."
brew update

# Install Java (OpenJDK 17)
echo "Installing OpenJDK 17..."
brew install openjdk@17
sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"

# Install Maven
echo "Installing Maven..."
brew install maven

# Install Docker Desktop (manual step)
echo "Please install Docker Desktop from: https://www.docker.com/products/docker-desktop/"
echo "Ensure Docker is running before proceeding."

# Install Jenkins
echo "Installing Jenkins LTS..."
brew install jenkins-lts

echo "Starting Jenkins as a macOS service..."
brew services start jenkins-lts

# Print Jenkins admin password location
echo "Initial Jenkins admin password location:"
echo "cat /Users/$(whoami)/.jenkins/secrets/initialAdminPassword"

# Install Docker Compose (already included in Docker Desktop for Mac)
echo "Docker Compose is bundled with Docker Desktop on macOS."

echo "Setup complete! Access Jenkins at http://localhost:8080"
