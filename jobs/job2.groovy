job("Test job 2") {
    node("pi") {
        steps {
            shell("ls /home/pi")
        }
    }
}