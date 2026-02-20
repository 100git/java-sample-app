pipeline{
    agent any
    tools {
        maven 'Maven'
        jfrog 'jfrog-cli'
        snyk 'snyk'
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
        /*stage('Snyk Security Scan') {
            environment {
                SNYK_TOKEN = credentials('snyk-token')
            }
            steps {
                sh '''
                    snyk auth $SNYK_TOKEN
                    snyk test --severity-threshold=high
                '''
            }
        }*/
        stage('Snyk Security Scan') {
            environment {
                SNYK_TOKEN = credentials('snyk-token')
            }
            steps {
                sh '''
                    snyk test --severity-threshold=high
                    snyk monitor --project-name=java-sample-app
                '''
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
                  jf config add myserver \
                    --url=$JF_URL \
                    --access-token=$JF_TOKEN \
                    --interactive=false \
                    --overwrite=true

                  jf rt upload "target/*.jar" java-sample-app-generic-local/
                '''
            }
        }        
        stage('Xray Scan') {
            steps {
                sh '''
                  jf --version
                  jf rt build-collect-env my-build 1
                  jf rt build-add-dependencies my-build 1 "target/*.jar"
                  jf rt build-publish my-build 1
                  jf build-scan my-build 1 --fail=true
                  #jf xr scan-build my-build 1 --fail=true
                '''
            }
        }        
    }
}
