pipeline {
agent any
stages {
stage('Cloning Project from Git') {steps { git CLONE 'https://github.com/Ahmedchokri/Projet-Devops.git'}}
stage("Build") {steps {bat "mvn compile"}}
stage("Unit tests") {steps {bat "mvn test"}}
}
}