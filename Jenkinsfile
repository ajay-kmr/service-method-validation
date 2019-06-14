pipeline {
  agent any
  stages {
    stage('common-build-pipeline') {
      steps {
        sh '''echo "Starting Step 1"
sh ./gradlew clean
sh ./gradlew build'''
        sleep 2
      }
    }
    stage('stage-2') {
      steps {
        echo 'In Stage 2 of pipeline'
      }
    }
  }
}