pipelineJob('pipeline job 1') {
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
        stringParam('baseline', '', 'Tested software baseline')
        booleanParam("reporting", defaultValue=false, "send report or not")
    }
    definition {
        cps {
            script(readFileFromWorkspace('pipeline_job.groovy'))
            sandbox()
        }
    }
}
