folder('F1') {
    description('Folder containing all jobs for container1')
}
pipelineJob('F1/Test_RUN') {
    properties {
        buildDiscarder {
            strategy {
                logRotator {
                    daysToKeepStr('30')
                    numToKeepStr('100')
                    artifactDaysToKeepStr('30')
                    artifactNumToKeepStr('100')
                }
            }
        }
    }
    parameters {
        stringParam(defaultValue="", description='K', name='baseline')
        booleanParam("reporting", defaultValue=false, "send report or not")
    }
    definition {
        cps {
            script(readFileFromWorkspace('groovy_scripts/Test_RUN.groovy'))
            sandbox()
        }
    }
}
