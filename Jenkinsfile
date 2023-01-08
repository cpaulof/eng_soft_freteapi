pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvnw -B -DskipTests clean package'
            }
        }
        stage('Testes Unitários') {
            steps {
                bat 'mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage("Teste de Integração"){
            steps{
                bat 'mvnw verify -Pfailsafe'
            }
            post {
                always {
                    junit 'target/failsafe-reports/*.xml'
                }
            }
        }
        stage('Inicialização DEV') {
            steps {
                bat 'for %i in (target\\*.war) do java -jar "%i" --server.port=8081'
                bat 'echo "selenium tests com python"'
            }
        }
    }
}