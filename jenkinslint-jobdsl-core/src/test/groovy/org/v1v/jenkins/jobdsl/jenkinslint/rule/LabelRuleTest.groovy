package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
import org.junit.Test

/**
 * Tests for Label Rule
 *
 * @author Victor Martinez
 */
class LabelRuleTest {

    @Test
    void testFreeStyleJobWithoutLabel() {
        Item job = new FreeStyleJob()
        LabelRule rule = new LabelRule()
        assert rule.isDefect(job)
    }

    @Test
    void testFreeStyleJobWithLabel() {
        Item job = new FreeStyleJob()
        job.label('something')
        LabelRule rule = new LabelRule()
        assert !rule.isDefect(job)
    }
}
