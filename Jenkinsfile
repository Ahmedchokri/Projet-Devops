pipeline {
agent any
stages {
stage("Build") {
steps {
sh "mvn - f "//var//lib//jenkins//workspace//testdevops//Spring//pom.xml" compile"
}
}
stage("Unit tests") {
steps {
sh "mvn test"

}}
}
}
