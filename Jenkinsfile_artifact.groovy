pipeline {
    agent any 
    stages {
        stage('clone repository') {
            steps {
                script {
                    sh """
                    git clone git@github.com:Satish0423/python_satish.git
                    """
                }
            }
        }
        stage('compie c file') {
            steps {
                script {
                    sh """
                    cd python_satish
                    git checkout feature/test
                    gcc hello.c -Wall -o hello
                    ./hello
                    """
                    archiveArtifacts artifacts: 'python_satish/hello'
                }
            }
        }
        stage('commit arcive file') {
            steps {
                script {
                    sh """
                    git clone git@github.com:Satish0423/archive_files.git
                    cd archive_files
                    cp ../python_satish/hello ./
                    git add hello
                    git commit -m "init commit"
                    git push
                    """
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}