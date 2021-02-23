pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/gdelgadoh/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}