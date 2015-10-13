package org.v1v.jenkins.jobdsl.jenkinslint

import java.util.logging.Logger

class LintRunner {

    private static final LOG = Logger.getLogger(LintRunner.class.getName())

    void execute() {
        def startTime = System.currentTimeMillis()
        def ruleSet = createRuleSet()
        def elapsedTime = System.currentTimeMillis() - startTime
        ruleSet.getRules().each {
            println it
        }

        def resultsMessage = "Lint completed: ${elapsedTime}ms"
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