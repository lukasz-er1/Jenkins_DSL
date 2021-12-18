pipeline {
    agent { 
        node {
            label "pi"
            customWorkspace ((node.contains('UTE') && node.contains('5G')) ? "workspace/${env.JOB_BASE_NAME}/${env.BUILD_NUMBER}" : "")
        }
    }
    options {
        timestamps()
        // ansiColor('xterm')
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
                    initial_reporting()
                }
            }
        }

        stage ("RUN TEST") {
            steps {
                stage_title("RUN TEST")
                script {
                    println("\u001B[34m RUNNING TEST \u001B[0m")
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
    echo "${node}"

    build job: 'seed',
        wait: true
        // parameters: [
        //     string(name: "", value: "")
        // ]
}
