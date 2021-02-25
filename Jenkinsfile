pipeline {
    agent none

    environment {
		registry = "gdelgadoh/devops" 
	    registryCredential = 'dockerhub'
	    dockerImage = ''
        branchName = ''
	}
    options {
        timestamps()
        skipDefaultCheckout()      // Don't checkout automatically
    }



    stages {
        stage('Checkout') {
            gent { label 'master' }
            steps {
                checkout scm
            }
        }
        stage('Build') {
            agent {
		        docker {
		            image 'maven:3-alpine' 
		            args '-v /Users/gdelgadoh/.m2:/root/.m2' 
		        }
    		}  
            steps {
                script {
                    if (!env.BRANCH_NAME.contains("main")) {
                        branchName = env.BRANCH_NAME
                    }
                 }
                sh 'mvn clean compile'
                echo "Nombre de branch: ${branchName}"

                echo 'Quality Gate'                
                withSonarQubeEnv('SonarServer') {
	        		sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.branch.name=${branchName} "
		       	}	
                sleep(30)	       	
		       	timeout(time: 1, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
		       		
	        		waitForQualityGate abortPipeline: true
	        		
		       	}
            }
        }
    }
}