pipeline {
agent any
stages {
stage('Cloning Project from Git') {steps { git url :'https://github.com/Ahmedchokri/Projet-Devops.git',branch : 'main'|
sh "chmod +x -R ./jenkins"
sh "./jenkins/script/scripted_pipeline_es_2.sh"
}}
stage("Build") {steps {bat "mvn compile"}}
stage("Unit tests") {steps {bat "mvn test"}}
}
}