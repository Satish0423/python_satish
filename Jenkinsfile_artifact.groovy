pipeline {
    agent any 
    stages {
        stage('clone repository') {
            steps {
                script {
                    sh """
                    git clone git@github.com:Satish0423/python_satish.git
                    cd python_satish
                    """
                }
            }
        }
        stage('compie c file') {
            steps {
                script {
                    sh """
                    gcc hello.c -Wall -o hello
                    ./hello
                    """
                }
            }
        }
    }
    archiveartifacts artifacts: 'python_satish/hello'
}