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
 * Tests for Description Rule
 *
 * @author Victor Martinez
 */
class DescriptionRuleTest {

    private rule

    @Before
    void setUp() {
        rule = new DescriptionRule()
    }

    @Test
    void testFreeStyleJobWithoutDescription() {
        Item job = new FreeStyleJob()
        assert rule.isDefect(job)
    }

    @Test
    void testMavenJobWithoutDescription() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        assert rule.isDefect(job)
    }

    @Test
    void testMatrixJobWithoutDescription() {
        Item job = new MatrixJob()
        assert rule.isDefect(job)
    }

    @Test
    void testMultiJobWithoutDescription() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        assert rule.isDefect(job)
    }

    @Test
    void testWorkflowJobWithoutDescription() {
        Item job = new WorkflowJob()
        assert rule.isDefect(job)
    }

    @Test
    void testBuildFlowJobWithoutDescription() {
        Item job = new BuildFlowJob()
        assert rule.isDefect(job)
    }

    @Test
    void testIvyJobWithoutDescription() {
        Item job = new IvyJob()
        assert rule.isDefect(job)
    }

    @Test
    void testFreeStyleJobWithDescription() {
        Item job = new FreeStyleJob()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testMavenJobWithDescription() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testMatrixJobWithDescription() {
        Item job = new MatrixJob()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testMultiJobWithDescription() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testWorkflowJobWithDescription() {
        Item job = new WorkflowJob()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testBuildFlowJobWithDescription() {
        Item job = new BuildFlowJob()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testIvyJobWithDescription() {
        Item job = new IvyJob()
        job.description('something')
        assert !rule.isDefect(job)
    }

}
