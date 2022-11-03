pipeline {
agent any
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
sh " mvn -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.100.170:9003/repository/maven-releases/ -Dfile=target/tpAchatProject-1.0.jar"

}}
stage("Sonar") {
steps {
sh " mvn  -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean sonar:sonar -Dsonar.projectKey=test -Dsonar.host.url=http://192.168.100.170:9000 -Dsonar.login=deadd25fab00777ef14b0a4bbc057b3a8921f4d6"


}}
}
}
