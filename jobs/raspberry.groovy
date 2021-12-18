job("rasp check") {
    parameters {
        nodeParam('pi') {
            description('raspberry check')
        }
    }
    steps {
        shell("ls /home/pi")
    }
}