package org.v1v.jenkins.jobdsl.jenkinslint.rule

import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
import javaposse.jobdsl.dsl.jobs.MavenJob
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
}
