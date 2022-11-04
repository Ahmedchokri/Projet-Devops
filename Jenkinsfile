pipeline {

agent any
tools{
nodejs '17.3.1'
}
stages {
stage("Build") {
steps {
sh " mvn -f  /var/lib/jenkins/workspace/uu/Spring/pom.xml compile"
}
}

stage('Building image docker-compose') {
          steps {
          sh "npm --prefix /var/lib/jenkins/workspace/uu/Angular/ install"
          sh "npm --prefix /var/lib/jenkins/workspace/uu/Angular/ run build"
              sh "docker-compose up -d"
          }
      }
stage('Deploy our image') {
         steps {
              withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
              sh "docker tag uu_app ahmedchokri/uu_app:uu_app"
              sh "docker push ahmedchokri/uu_app:uu_app"
         }}
     }
stage('Cleaning up') {
         steps {
            sh "docker rmi -f uu_app"
         }
     }
     stage("Email"){
           steps{
               emailext attachLog: true, body: "${env.BUILD_URL} has result ${currentBuild.result}", compressLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: 'ahmed.chokri@esprit.tn'
           }
       }
}
}
