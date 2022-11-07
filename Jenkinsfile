pipeline {

agent any
stages {
stage("Build") {
steps {
sh " mvn -f  Spring/pom.xml compile"
}
}
stage("Unit tests") {
steps {
sh " mvn -f  Spring/pom.xml test"

}}
stage("Sonar") {
steps {
sh " mvn  -f Spring/pom.xml clean install sonar:sonar -Dsonar.host.url=http://192.168.56.55:9000 -Dsonar.login=caceadcb3c22af7f34ee10a65e17ce92c603418e"


}}
stage('Building image docker-compose') {
          steps {
              sh "docker-compose up -d"
              sh "docker-compose stop"
          }
      }

stage('Deploy our image') {
         steps {
              withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
              sh "docker tag ahmedzaghdoudi2_app zaghdoudiahmed/ahmedzaghdoudi2_app:ahmedzg"
              sh "docker push zaghdoudiahmed/ahmedzaghdoudi2_app:ahmedzg"
         }}
     }
stage('pull and run app') {
              steps {
                  sh "docker-compose -f Spring/docker-compose.yml up -d  "
              }
              }

     
}
}