pipeline{
    agent any
    tools {
        maven 'Maven'
        jfrog 'jfrog-cli'
    }
    environment {
        JF_URL = 'https://trial1kaxie.jfrog.io'
        JF_TOKEN = credentials('jfrog-token')
    }
    
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
                        -Dsonar.sources=src \
                        -Dsonar.organization=rajan-organization \
                        -Dsonar.projectName=JavaSampleAppProject \
                        -Dsonar.java.binaries=target/
                    '''
                }
            }
        }
        stage('Quality Gate Check'){
            steps{
                waitForQualityGate abortPipeline: true
            }
        }
        /*
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
        */
        stage('Publish Artifact') {
            steps {
                sh '''
                  jfrog config add myserver \
                    --url=$JF_URL \
                    --access-token=$JF_TOKEN \
                    --interactive=false

                  jfrog rt upload "target/*.jar" java-sample-app-generic-local/
                '''
            }
        }        
        stage('Xray Scan') {
            steps {
                sh '''
                  jfrog rt build-collect-env my-build 1
                  jfrog rt build-add-dependencies my-build 1
                  jfrog rt build-publish my-build 1

                  jfrog xr scan-build my-build 1 --fail=true
                '''
            }
        }        
    }
}
