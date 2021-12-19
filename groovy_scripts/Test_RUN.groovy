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
                    echo "baseline: ${env.baseline}"
                    def node_name = env.baseline.contains("arm_") ? "pi" : "master"
                    node(node_name) {
                        echo "baseline: ${env.baseline}"
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
