pipeline {
    agent {
        label("master")
    }
    options {
        timestamps()
        ansiColor('xterm')
    }
    stages {
        stage("XX") {
            steps {
                script {
                    echo "baseline: ${env.SW}"
                    def node_name = env.SW.contains("arm_") ? "pi" : "master"
                    node(node_name) {
                        echo "baseline: ${env.SW}"
                        if (node_name == "pi") {
                            sh "ls /home/pi"
                        }
                        else {
                            sh "ls /home/"
                        }
                    }
                }
            }
        }
    }
}
