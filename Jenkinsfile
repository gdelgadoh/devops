pipeline {
    agent none

    environment {
        registry = "gdelgadoh/devops" 
        registryCredential = 'dockerhub'
        dockerImage = ''
        branchName = ''
        version = ''
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
                    branchName = ""
                    if (!env.BRANCH_NAME.contains("main")) {
                        branchName = "-Dsonar.branch.name=${env.BRANCH_NAME}"
                    }
                 }
                echo 'Compilar'
                sh 'mvn clean compile'
                echo "Valor para sonar.branch.name: ${branchName}"

                echo 'Cobertura'
                sh 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent install' 
                jacoco execPattern: '**/target/*.exec'
                junit '**/target/surefire-reports/*.xml'


                echo 'Quality Gate'                
                withSonarQubeEnv('SonarServer') {
	        		sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ${branchName}"
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

                    if ( env.BRANCH_NAME.equals("main") ) {

                        version = ":$BUILD_NUMBER"

                    } else {
                        version = ":" + env.BRANCH_NAME.replace("/", "-") + "-$BUILD_NUMBER"
                    }

                	dockerImageName = registry + version
                	dockerImage = docker.build "${dockerImageName}"
                	docker.withRegistry( '', registryCredential ) {
                		dockerImage.push()
                	}

                    if (env.BRANCH_NAME.equals("master")) {
                        docker.withRegistry( '', registryCredential ) {
                            dockerImage.push('latest')
                	    }
                        sh "docker rmi " + ${registry} + "latest"
                    }
                    sh "docker rmi $registry$version"	 
            	} 
            	
                	         
            }
        } 

    }
}