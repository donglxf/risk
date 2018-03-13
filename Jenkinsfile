pipeline {
    agent any
        tools {
            //工具名称必须在Jenkins 管理Jenkins → 全局工具配置中预配置。
            maven 'M3'
        }
    stages {
        stage('Example Build') {
            steps {


                 sh "mvn install"
                sh 'docker ps'
                echo 'Deploying1....'
                echo 'Deploying2....'
            }
        }
        }
}
