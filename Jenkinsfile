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
                sh 'mvn clean compile'
                //echo "Nombre de branch: ${env.BRANCH_NAME}"

                echo 'Quality Gate'                
                withSonarQubeEnv('SonarServer') {
	        		sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.branch.name=${env.BRANCH_NAME} "
		       	}	
                sleep(30)	       	
		       	timeout(time: 1, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
		       		
	        		waitForQualityGate abortPipeline: true
	        		
		       	}
            }
        }
    }
}