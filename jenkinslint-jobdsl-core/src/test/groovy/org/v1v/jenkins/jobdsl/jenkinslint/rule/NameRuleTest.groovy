package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.jobs.BuildFlowJob
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
import javaposse.jobdsl.dsl.jobs.IvyJob
import javaposse.jobdsl.dsl.jobs.MatrixJob
import javaposse.jobdsl.dsl.jobs.MavenJob
import javaposse.jobdsl.dsl.jobs.MultiJob
import javaposse.jobdsl.dsl.jobs.WorkflowJob
import org.junit.Before
import org.junit.Test

/**
 * Tests for Name Rule
 *
 * @author Victor Martinez
 */
class NameRuleTest {

    private rule

    @Before
    void setUp() {
        rule = new NameRule()
    }

    @Test
    void testFreeStyleJob() {
        Item job = new FreeStyleJob()
        assert !rule.isDefect(job)
    }

    @Test
    void testMavenJob() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        assert !rule.isDefect(job)
    }

    @Test
    void testMatrixJob() {
        Item job = new MatrixJob()
        assert !rule.isDefect(job)
    }

    @Test
    void testMultiJob() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        assert !rule.isDefect(job)
    }

    @Test
    void testWorkflowJob() {
        Item job = new WorkflowJob()
        assert !rule.isDefect(job)
    }

    @Test
    void testBuildFlowJob() {
        Item job = new BuildFlowJob()
        assert !rule.isDefect(job)
    }

    @Test
    void testIvyJob() {
        Item job = new IvyJob()
        assert !rule.isDefect(job)
    }

    @Test
    void testFreeStyleJobWithDefect() {
        Item job = new FreeStyleJob()
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testMavenJobWithDefect() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testMatrixJobWithDefect() {
        Item job = new MatrixJob()
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testMultiJobWithDefect() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testWorkflowJobWithDefect() {
        Item job = new WorkflowJob()
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testBuildFlowJobWithDefect() {
        Item job = new BuildFlowJob()
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testIvyJobWithDefect() {
        Item job = new IvyJob()
        job.setName('something with spaces')
        assert rule.isDefect(job)
    }

    @Test
    void testIgnoredRule() {
        testIgnoredDescription(new FreeStyleJob())
        testIgnoredDescription(new MavenJob(new FileJobManagement(new File("."))))
        testIgnoredDescription(new MatrixJob())
        testIgnoredDescription(new MultiJob(new FileJobManagement(new File("."))))
        testIgnoredDescription(new WorkflowJob())
        testIgnoredDescription(new BuildFlowJob())
        testIgnoredDescription(new IvyJob())
    }

    @Test
    void testIgnoredRuleWithDefect() {
        testIgnoredDescriptionWithDefect(new FreeStyleJob())
        testIgnoredDescriptionWithDefect(new MavenJob(new FileJobManagement(new File("."))))
        testIgnoredDescriptionWithDefect(new MatrixJob())
        testIgnoredDescriptionWithDefect(new MultiJob(new FileJobManagement(new File("."))))
        testIgnoredDescriptionWithDefect(new WorkflowJob())
        testIgnoredDescriptionWithDefect(new BuildFlowJob())
        testIgnoredDescriptionWithDefect(new IvyJob())
    }

    private testIgnoredDescription (Item item) {
        assert !rule.isIgnored(item)
        item.description(rule.getName())
        assert rule.isIgnored(item)
    }

    private testIgnoredDescriptionWithDefect (Item item) {
        assert !rule.isIgnored(item)
        item.description(rule.getName())
        item.setName('something with spaces')
        assert rule.isIgnored(item)
        assert !rule.isDefect(item)
    }
}
