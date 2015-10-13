package org.v1v.jenkins.jobdsl.jenkinslint

import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.v1v.jenkins.jobdsl.jenkinslint.TestUtil.shouldFailWithMessageContaining

/**
 * Tests for JenkinsLint runner
 *
 * @author victor.martinez.
 */
class JenkinsLintRunnerTest {

    private static final SOURCE_DIRS = ['abc']

    private LintRunner lintRunner

    protected final LOG = Logger.getLogger(getClass())

    /**
     * Write out the specified log message, prefixing with the current class name.
     * @param message - the message to log; toString() is applied first
     */
    protected void log(message) {
        LOG.info message
    }

    @Test
    void testExecute_NoSourceAnalyzer() {
        shouldFailWithMessageContaining('fileSetAnalyzer') { lintRunner.execute() }
    }

    @Test
    void testExecute() {
        def ruleSet
        def fileSetAnalyzer = [analyze: { rs -> ruleSet = rs }, getSourceDirectories: { SOURCE_DIRS }] as FileSetAnalyzer

        lintRunner.fileSetAnalyzer = fileSetAnalyzer
    }

    //--------------------------------------------------------------------------
    // Test setUp/tearDown and helper methods
    //--------------------------------------------------------------------------

    @Before
    void setUpLintRunnerTest() {
        lintRunner = new LintRunner()
    }
}