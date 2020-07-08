pipeline {
    agent any 
    stages {
        stage('clone repository') {
            steps {
                script {
                    sh """
                    git clone git@github.com:Satish0423/python_satish.git
                    cd python_satish
                    git checkout feature/test
                    """
                }
            }
        }
        stage('code format') {
            steps {
                script {
                    sh """
                    autopep8 --in-place --aggressive --aggressive python.py
                    """
                }
            }
        }
        stage('commit and push to repoitory') {
            steps {
                script {
                    sh """
                    git add python.py
                    git commit -m "format using script"
                    git push -u origin feature/test
                    """
                }
            }
        }
    }
    post {
        allways {
            cleanWs()
        }
    }
}