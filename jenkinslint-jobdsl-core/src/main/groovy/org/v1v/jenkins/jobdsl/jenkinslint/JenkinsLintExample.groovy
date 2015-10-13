package org.v1v.jenkins.jobdsl.jenkinslint

import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.ScriptRequest
import org.v1v.jenkins.jobdsl.jenkinslint.rule.DescriptionRule
import org.v1v.jenkins.jobdsl.jenkinslint.rule.LabelRule

import java.util.logging.Logger

class JenkinsLintExample {

    private static final Logger LOG = Logger.getLogger(JenkinsLintExample.class.getName());

    public void process (String scriptName) {
        File cwd = new File('..')
        URL cwdURL = cwd.toURI().toURL()

        def rules= []

        rules.add(new LabelRule())
        rules.add(new DescriptionRule())

        FileJobManagement jm = new FileJobManagement(cwd)
        jm.parameters.putAll(System.getenv())
        System.properties.each { def key, def value ->
            jm.parameters.put(key.toString(), value.toString())
        }

        ScriptRequest request = new ScriptRequest(scriptName, null, cwdURL, false)
        JobParent jp = DslScriptLoader.runDslEngineForParent(request, jm);
        jp.getReferencedJobs().each {
            rules.each { rule ->
                if (rule.isDefect(it)) {
                    LOG.info("From $scriptName, Rules '${rule.class.simpleName}' was found ")
                }
            }
        }
    }

    public static void main (String[] args) {
        JenkinsLintExample lint = new JenkinsLintExample()
        lint.process("resources/dsl.groovy")
    }
}
