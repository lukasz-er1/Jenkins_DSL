pipeline {
    agent {
        label("pi")
    }
    options {
        timestamps()
        ansiColor('xterm')
    }
    stages {
        stage ("PREPARE ENV") {
            steps {
                stage_title("PREPARE ENV")
                echo "WFT BRANCH: branch"
            }
        }

        stage ("INITIAL REPORTING") {
            // when { expression { params.reporting } }
            steps {
                stage_title("INITIAL REPORTING")
                script {
                    node('master') {
                        initial_reporting()
                    }
                    
                }
            }
        }

        stage ("RUN TEST") {
            steps {
                stage_title("RUN TEST")
                script {
                    println("\u001B[34m RUNNING TEST \u001B[0m")
                }
                shell(readFileFromWorkspace("bash_scripts/prepare_test.sh"))
                customPythonBuilder {
                    home('/usr/bin/python')
                    command(readFileFromWorkspace("python_scripts/test.py"))
                    nature('python')
                    ignoreExitCode(false)
                }
            }
        }
        
        stage ("LOG COLLECTING") {
            steps {
                stage_title("LOG COLLECTING")
                script {
                    println("LOG COLLECTING")
                }
            }
        }
    }

}

def stage_title(title) {
    println """
    \033[32m=========================================================================================================================\033[0m
    \033[1m\033[34m                                                     ${title}    \033[0m
    \033[32m=========================================================================================================================\033[0m
    """
}

def initial_reporting() {
    echo "REPORTING!"
    echo "baseline: $env.baseline"

    build job: 'Test job 2',
        wait: true
        parameters: [
            string(name: "reporting", value: true)
        ]
}

// test