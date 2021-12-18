job("Test job 2") {
    steps {
        node("pi") {
            shell("ls /home/pi")
        }
    }
}