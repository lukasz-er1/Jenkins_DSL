freeStyleJob('F1/Test_RUN') {
    description('It is a job for downloading software packages')
    logRotator {
        daysToKeep(30)
        numToKeep(100)
    }
    parameters {
        stringParam('baseline', '', 'Tested software baseline')
        booleanParam("reporting", defaultValue=false, "send report or not")
    }

    test1()
    label(node)
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


def test1() {
    echo "baseline: ${env.baseline}"
}
