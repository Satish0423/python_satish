pipeline {
    agent any
    parameters {
        choice(name: 'FORMAT', choices: ['No', 'Yes'], description: 'if you want format?')
    }
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
        stage('code format') {
            when {
                expression {
                    params.FORMAT == 'Yes'
                }
            }
            steps {
                script {
                    sh """
                    autopep8 --in-place --aggressive --aggressive python.py
                    """
                }
            }
        }
        stage('commit and push to repoitory') {
            when {
                expression {
                    params.FORMAT == 'Yes'
                }
            }
            steps {
                script {
                    sh """
                    git checkout feature/test
                    git add python.py
                    git commit -m "format using script"
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