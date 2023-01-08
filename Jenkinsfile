pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvnw -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                bat 'echo "Oi"'
            }
        }
    }
}