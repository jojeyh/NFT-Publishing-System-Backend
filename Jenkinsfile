pipeline {
  agent {
    docker {
      image 'gradle:latest'
    }
  }
  stages {
    stage('Build') {
      steps {
        sh './gradlew build'
      }
    }
    stage('Test') {
      steps {
        sh './gradlew test'
      }
    }
  }
}
