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
sh " mvn -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.100.170:8081/repository/maven-releases/ -Dfile=target/tpAchatProject-1.0.jar"

}}
stage("Sonar") {
steps {
sh " mvn  -f /var/lib/jenkins/workspace/uu/Spring/pom.xml clean sonar:sonar -Dsonar.projectKey=Timesheet2 -Dsonar.host.url=http://192.168.100.170:9000 -Dsonar.login=f958ecd6eaebfc29eb930a3daa31868e70ae5ce8"


}}
}
}
