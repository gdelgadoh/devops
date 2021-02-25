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
            agent any
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
                echo 'Compilar'
                sh 'mvn clean compile'
                //echo "Nombre de branch: ${branchName}"

                echo 'Cobertura'
                sh 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent install' 
                jacoco execPattern: '**/target/**.exec'
                junit '**/target/surefire-reports/*.xml'


                echo 'Quality Gate'                
                withSonarQubeEnv('SonarServer') {
	        		sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.branch.name=${branchName}"
		       	}	
                sleep(30)	       	
		       	timeout(time: 1, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
	        		waitForQualityGate abortPipeline: true
		       	}

                echo 'Build'
	       		sh 'mvn clean package -Dmaven.test.skip=true' 
            }
        }
        stage('Build Docker Image') { 
       		agent any
            steps {
            	script {
            	    /*def now = new Date()
            	    year = now.format("yyyy", TimeZone.getTimeZone('GMT-06'))
                	month = now.format("MM", TimeZone.getTimeZone('GMT-06'))
                	day = now.format("dd", TimeZone.getTimeZone('GMT-06'))*/
                	dockerImageName = registry + ":$BUILD_NUMBER"
                	
                	dockerImage = docker.build "${dockerImageName}"
                	docker.withRegistry( '', registryCredential ) {
                		dockerImage.push()
                		//dockerImage.push('	')
                	}
            	} 
            	sh "docker rmi $registry:$BUILD_NUMBER"	         
            }
        } 

    }
}