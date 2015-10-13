package org.v1v.jenkins.jobdsl.jenkinslint

import java.util.logging.Logger

class JenkinsLintRunner {

    private static final LOG = Logger.getLogger(JenkinsLintRunner.class.getName())

    FileSetAnalyzer fileSetAnalyzer

    void execute() {
        assert fileSetAnalyzer, 'The fileSetAnalyzer property must be set to a valid FileSet'

        def startTime = System.currentTimeMillis()
        def ruleSet = createRuleSet()
        def elapsedTime = System.currentTimeMillis() - startTime

        fileSetAnalyzer.analyze(ruleSet.getRules())

        fileSetAnalyzer.getItems().each {
            println it.name
        }

        def resultsMessage = "JenkinsLint completed: ${elapsedTime}ms"
        println resultsMessage
    }

    /**
     * Create and return the RuleSet that provides the source of Rules to be applied.
     * The returned RuleSet may aggregate multiple underlying RuleSets.
     * @return a single RuleSet
     */
    protected RuleSet createRuleSet() {
        return new RuleSet()
    }
}