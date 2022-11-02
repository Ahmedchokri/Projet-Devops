pipeline {
agent any
stages {
stage("Build") {
steps {
sh "mvn -f  /var/lib/jenkins/workspace/uu/Spring/pom.xml compile"
}
}
stage("Unit tests") {
steps {
sh "mvn -f  /var/lib/jenkins/workspace/uu/Spring/pom.xml test"

}}
}
}
