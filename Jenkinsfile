pipeline{
    agent any
    tools {maven 'Maven'}
    stages {
        stage('Git Clone'){
            steps {
                git branch: 'main', url: 'https://github.com/100git/java-sample-app.git'
            }
        }
        
        stage('Maven Build'){
            steps{
                sh 'mvn clean install'
            }
        }

        stage('Sonar Scanning'){
            environment {
                scanner = tool 'SonarScanner'
            }
            steps{
                withSonarQubeEnv('SonarCloud'){
                    sh '''
                        ${scanner}/bin/sonar-scanner -Dsonar.projectKey=JavaSampleApp \
                        -Dsonar.sources=. \
                        -Dsonar.organization=rajan-organization \
                        -Dsonar.projectName=JavaSampleAppProject \
                        -Dsonar.java.binaries=target/
                    '''
                }
            }
        }
        stage('Quality Gate Check'){
            when {
                expression {params.RUN_SONAR}
            }
            steps{
                waitForQualityGate abortPipeline: true
            }
        }
        stage('Artifact Upload')
        {
            steps{
                script{
                    rtUpload (
                        serverId: 'jfrog-server',
                        spec: '''{
                                    "files": [
                                        {
                                            "pattern": "target/*.jar",
                                            "target": "java-sample-app-generic-local/"
                                        }
                                    ]
                        }'''
                    )
                }
            }
        }
        
    }
}
