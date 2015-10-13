package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.jobs.BuildFlowJob
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
import javaposse.jobdsl.dsl.jobs.MatrixJob
import javaposse.jobdsl.dsl.jobs.MavenJob
import javaposse.jobdsl.dsl.jobs.MultiJob
import javaposse.jobdsl.dsl.jobs.WorkflowJob
import org.junit.Test

/**
 * Tests for Description Rule
 *
 * @author Victor Martinez
 */
class DescriptionRuleTest {

    @Test
    void testFreeStyleJobWithoutDescription() {
        Item job = new FreeStyleJob()
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testMavenJobWithoutDescription() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testMatrixJobWithoutDescription() {
        Item job = new MatrixJob()
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testMultiJobWithoutDescription() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testWorkflowJobWithoutDescription() {
        Item job = new WorkflowJob()
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testBuildflowJobWithoutDescription() {
        Item job = new BuildFlowJob()
        DescriptionRule rule = new DescriptionRule()
        assert rule.isDefect(job)
    }

    @Test
    void testFreeStyleJobWithDescription() {
        Item job = new FreeStyleJob()
        job.description('something')
        DescriptionRule rule = new DescriptionRule()
        assert !rule.isDefect(job)
    }

    @Test
    void testMavenJobWithDescription() {
        Item job = new MavenJob(new FileJobManagement(new File(".")))
        job.description('something')
        DescriptionRule rule = new DescriptionRule()
        assert !rule.isDefect(job)
    }

    @Test
    void testMatrixJobWithDescription() {
        Item job = new MatrixJob()
        job.description('something')
        DescriptionRule rule = new DescriptionRule()
        assert !rule.isDefect(job)
    }

    @Test
    void testMultiJobWithDescription() {
        Item job = new MultiJob(new FileJobManagement(new File(".")))
        DescriptionRule rule = new DescriptionRule()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testWorkflowJobWithDescription() {
        Item job = new WorkflowJob()
        DescriptionRule rule = new DescriptionRule()
        job.description('something')
        assert !rule.isDefect(job)
    }

    @Test
    void testBuildflowJobWithDescription() {
        Item job = new BuildFlowJob()
        DescriptionRule rule = new DescriptionRule()
        job.description('something')
        assert !rule.isDefect(job)
    }
}
