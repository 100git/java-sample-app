## 1.0.0 -Project Description
This repository demonstrates a complete DevSecOps CI/CD pipeline for a Java Maven application.
The pipeline integrates:
Jenkins – CI/CD automation
JFrog Artifactory – Dependency & Artifact Management
JFrog Xray – Security & vulnerability scanning
SonarCloud – Code quality & quality gates
Snyk – SAST & dependency security

## 1.1.0 – Assignment Requirements Coverered
- Jenkins installed and configured
JFrog Artifactory (Local + Remote + Virtual repositories)
SonarCloud configured with Quality Gates
JFrog Xray security policies configured
Maven dependencies resolved using Artifactory Virtual Repo
Artifact published to Artifactory Local Repo
Pipeline fails on:
Sonar Quality Gate failure
Xray High/Critical vulnerabilities
Snyk High severity vulnerabilities

## 1.2.0 – Architecture Flow
- GitHub   
Jenkins Pipeline
Maven Build (Dependencies from Artifactory Virtual Repo)
SonarCloud Scan
Snyk Security Scan 
Publish Artifact to Artifactory 
Xray Security Scan

## 1.3.0 – Environment Setup
- Install Java & Maven
Install Jenkins
Access Jenkins
Installed Plugins:
Git
Maven Integration
SonarQube Scanner
Config File Provider
JFrog Plugin
Pipeline

## 1.4.0 – JFrog Artifactory Configuration
- Using JFrog Cloud Trial

## 1.4.1 Remote Repository
- Type: Maven
Name: maven-central-remote

## 1.4.2 - Local Repository
- Type: Generic
Name: java-sample-app-generic-local

## 1.4.3 - Virtual Repository
- Name: maven-virtual
includes:
maven-central-remote
local maven repositories
This ensures all dependencies are downloaded from Artifactory instead of Maven Central.

## 1.5.0 – Maven Configuration (Using Virtual Repository)
- Configured settings.xml in Jenkins Managed Files

## 1.6.0 – SonarCloud Integration
- Created project in SonarCloud
Generated authentication token
Added token in Jenkins credentials

## 1.7.0 – Snyk Security Scan
- Installed Snyk CLI in Jenkins
Added Snyk token as Jenkins credential

## 1.8.0 -JFrog Xray Security Scan
Configured:
Security Policy (High + Critical)
Watch attached to repository
Configured Sonar in Jenkins

## 1.9.0 – Failure Conditions Enforced
- Condition	Result
Sonar Quality Gate Fail	 Pipeline Aborted
Snyk High Vulnerability	Pipeline Failed
Xray High/Critical Issue	Pipeline Failed

## 2.0.0 – Deliverables
- GitHub repository with source code
Jenkinsfile with full pipeline
README with setup steps
Sonar Quality Gate
Xray scan results
Snyk scan results
Artifact upload
Dependency resolution from Virtual Repo
