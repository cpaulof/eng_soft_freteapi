pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvnw -B -DskipTests clean package'
            }
        }
        stage('Testes Unitarios') {
            steps {
                bat 'mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage("Teste de Integracao"){
            steps{
                bat 'mvnw verify -Pfailsafe'
            }
            post {
                always {
                    junit 'target/failsafe-reports/*.xml'
                }
            }
        }
        // stage('Inicializacao DEV') {
        //     steps {
        //         bat "java -jar target\\freteapi-0.0.1-SNAPSHOT.war --server.port=8081"
        //         bat 'echo "selenium tests com python"'
        //     }
        // } 
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
    }
}