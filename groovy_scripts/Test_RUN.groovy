pipeline {
    test1()
    def node_check = env.baseline.contains("pi") ? "pi" : "master"
    agent {
        label(node_check)
    }
    options {
        timestamps()
        ansiColor('xterm')
    }
    stages {
        stage("XX") {
            steps {
                shell("ls /home/pi")
                shell(readFileFromWorkspace("bash_scripts/prepare_test.sh"))
                customPythonBuilder {
                    home('/usr/bin/python')
                    command(readFileFromWorkspace("python_scripts/test.py"))
                    nature('python')
                    ignoreExitCode(false)
                }
            }
        }
    }
}


def test1() {
    echo "baseline: ${env.baseline}"
}
