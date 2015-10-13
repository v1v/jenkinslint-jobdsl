package org.v1v.jenkins.jobdsl.jenkinslint

import org.v1v.jenkins.jobdsl.jenkinslint.rule.DescriptionRule
import org.v1v.jenkins.jobdsl.jenkinslint.rule.LabelRule
import org.v1v.jenkins.jobdsl.jenkinslint.rule.Rule

/**
 * @author victor.martinez.
 */
class RuleSet {

    private final rules = []

    RuleSet () {
        addRule(new DescriptionRule())
        addRule(new LabelRule())
    }

    /**
     * Add a single Rule to this RuleSet
     * @param rule - the Rule to add
     */
    void addRule(Rule rule) {
        assert rule != null
        rules << rule
    }

    /**
     * @return a List of Rule objects. The returned List is immutable.
     */
    List getRules() {
        rules.asImmutable()
    }
}
