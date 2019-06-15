pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh '''echo "Starting Step 1"
                      ./gradlew clean build'''
            }
        }
        stage('Test') {
            steps {
                sh './gradlew check'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.war', fingerprint: true
            junit 'build/test-results/**/*.xml'
        }
    }
}