pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }

    stages {
        stage('1. Prepare Environment) {
            steps {
                echo 'Verificando código fuente en el espacio de trabajo de Jenkins...'
                sh 'ls -la'
            }
        }
        stage('2. Build & Unit Tests') {
            steps {
                echo 'Compilando código y ejecutando pruebas unitarias con JUnit 5...'
                sh 'mvn clean verify'
            }
        }
        stage('3. SonarQube Code Analysis, incluye variable de entorno con el token de SONAR') {
            steps {
                echo 'Auditando deuda técnica y cobertura de código (>70%)...'
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.7.0.6970:sonar \
                          -Dsonar.projectKey=consulta-saldo-service \
                          -Dsonar.host.url=http://host.docker.internal:9000 \
                          -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }
        stage('4. Package Artifact (.war)') {
            steps {
                echo 'Generando empaquetado final .war...'
                sh 'mvn package -DskipTests'
            }
        }
        stage('5. Build Docker Image (RC)') {
            steps {
                echo 'Construyendo imagen Docker para Release Candidate...'
                sh 'docker build -t consulta-saldo-service:1.0.0-RC .'
            }
        }
        stage('6. Deploy Container') {
            steps {
                echo 'Desplegando contenedor en entorno local...'
                sh '''
                    docker rm -f consulta-saldo-container-jenkins || true
                    docker run -d \
                      -p 8082:8080 \
                      --name consulta-saldo-container-jenkins \
                      consulta-saldo-service:1.0.0-RC
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline CI/CD finalizado.'
        }
    }
}
