pipeline {

agent any
tools{
nodejs '14.17.6'
}
stages {
stage("Build") {
steps {
sh " mvn -f  /var/lib/jenkins/workspace/uu/Spring/pom.xml compile"
}
}
stage("Unit tests") {
steps {
sh " mvn -f  /var/lib/jenkins/workspace/uu/Spring/pom.xml test"

}}
stage("Nexus") {
steps {
sh " mvn -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=0.0.1-SNAPSHOT -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.100.170:8081/repository/maven-snapshots/ -Dfile=target/tpAchatProject-0.0.1-SNAPSHOT.jar"

}}
stage("Sonar") {
steps {
sh " mvn  -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean install sonar:sonar -Dsonar.projectKey=test -Dsonar.host.url=http://192.168.100.170:9000 -Dsonar.login=deadd25fab00777ef14b0a4bbc057b3a8921f4d6"


}}
stage('Building image docker-compose') {
          steps {
          sh "npm --prefix /var/lib/jenkins/workspace/uu/Angular/ run build --watch=true"
              sh "docker-compose up -d"
          }
      }
stage('Deploy our image') {
         steps {
              withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
              sh "docker tag uu_app ahmedchokri/uu_app:uu_app"
              sh "docker push ahmedchokri/uu_app:uu_app"
              sh "docker tag uu_frontend ahmedchokri/uu_frontend:uu_frontend"
              sh "docker push ahmedchokri/uu_frontend:uu_frontend"
         }}
     }
stage('Cleaning up') {
         steps {
            sh "docker rmi -f uu_app" 
            sh "docker rmi -f uu_frontend"
         }
     }
     stage("Email"){
           steps{
               emailext attachLog: true, body: "${env.BUILD_URL} has result ${currentBuild.result}", compressLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: 'ahmed.chokri@esprit.tn'
           }
       }
}
}
