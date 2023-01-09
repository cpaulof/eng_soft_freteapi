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

        stage('deploy dev') {
            when{
                branch 'dev'
            }
            steps{
                echo "entrega para dev"
                // startar uma intancia da build.
                bat "java -jar target\\freteapi-0.0.1-SNAPSHOT.war --server.port=8081" //local
            }
        }

        stage('deploy prod') {
            when{
                branch 'main'
            }
            steps{
                echo "entrega na producao"
                // upload para servidor de producao e execucao.
            }
        }
    }
    post {
        
        always {
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
    }
}