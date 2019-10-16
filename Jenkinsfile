pipeline {
    agent any
    stages {
        /* "Build" and "Test" stages omitted */

        stage('Build') {
            steps {
                git url : 'https://github.com/GayathriDevops/library_backend.git'
                when {
                branch 'dev'
                }
            }
        }


        stage('maven_goals_and_options') {
            steps {
                sh "/opt/maven/bin/mvn clean deploy sonar:sonar"
            }
        }
        
        stage('deploy_war_file_to_tomcat_container') {
            steps {
                sh "export JENKINS_NODE_COOKIE=dontKill; nohup java -jar $WORKSPACE/target/library.jar &"

            }
        }
    }
}
