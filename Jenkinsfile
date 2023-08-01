pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './gradlew build'
            }
        }
        stage('Test') {
            steps {
                bat './gradlew test'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat './gradlew sonarqube'
                }
            }
        }
        stage('Deploy') {
            steps {
                bat './gradlew deploy'
            }
        }
    }
}