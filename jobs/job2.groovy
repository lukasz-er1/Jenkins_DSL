job("Test job 2") {
    parameters {
        stringParam('baseline', '', 'Tested software baseline')
        booleanParam("reporting", defaultValue=false, "send report or not")
    }
    steps {
        agent {
            label("pi") {
                sh "ls /home/pi"
            }
        }
    }
}