pipeline {
agent any
stages {
stage("Build") {
steps {
bat "mvn compile"
}
}
stage("Unit tests") {
steps {
bat "mvn test"

}}
}
}
