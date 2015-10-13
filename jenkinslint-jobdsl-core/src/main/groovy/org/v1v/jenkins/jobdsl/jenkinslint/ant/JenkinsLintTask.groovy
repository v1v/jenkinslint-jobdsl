package org.v1v.jenkins.jobdsl.jenkinslint.ant

import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Task
import org.apache.tools.ant.types.FileSet
import org.v1v.jenkins.jobdsl.jenkinslint.JenkinsLintRunner

import java.util.logging.Logger

/**
 * Ant Task for running JenkinsLint.
 * At least one nested <code>fileset</code> element is required, and is used to specify the source files
 * to be analyzed. This is the standard Ant <i>FileSet</i>, and is quite powerful and flexible.
 * See the <i>Apache Ant Manual</i> for more information on <i>FileSets</i>.
 * <p/>
 *
 * @see "http://ant.apache.org/manual/index.html"
 *
 * @author Victor Martinez
 */
class JenkinsLintTask extends Task {

    /*
    private static final LOG = Logger.getLogger(JenkinsLintTask)

    String excludeBaseline

    protected List fileSets = []
    protected ruleSet

    private final resourceFactory = new DefaultResourceFactory()

    // Abstract creation of the CodeNarcRunner instance to allow substitution of test spy for unit tests
    protected createJenkinsLintRunner = {
        if (excludeBaseline) {
            LOG.info("Loading baseline violations from [$excludeBaseline]")
            def resource = resourceFactory.getResource(excludeBaseline)
            def resultsProcessor = new BaselineResultsProcessor(resource)
            return new JenkinsLintRunner(resultsProcessor:resultsProcessor)
        }
        return new JenkinsLintRunner()
    }

    void execute() throws BuildException {
        assert ruleSetFiles
        assert fileSets

        def sourceAnalyzer = createSourceAnalyzer()
        def codeNarcRunner = createJenkinsLintRunner()
        codeNarcRunner.ruleSetFiles = ruleSetFiles
        codeNarcRunner.reportWriters = reportWriters
        codeNarcRunner.sourceAnalyzer = sourceAnalyzer

        def results = codeNarcRunner.execute()

        checkMaxViolations(results)
    }

    void addFileset(FileSet fileSet) {
        assert fileSet
        this.fileSets << fileSet
    }

    protected SourceAnalyzer createSourceAnalyzer() {
        new AntFileSetSourceAnalyzer(getProject(), fileSets)

    }

    private void checkMaxViolations(Results results) {
        def p1 = results.getNumberOfViolationsWithPriority(1, true)
        def p2 = results.getNumberOfViolationsWithPriority(2, true)
        def p3 = results.getNumberOfViolationsWithPriority(3, true)
        def countsText = "(p1=$p1; p2=$p2; p3=$p3)"

        checkMaxViolationForPriority(1, p1, countsText)
        checkMaxViolationForPriority(2, p2, countsText)
        checkMaxViolationForPriority(3, p3, countsText)
    }

    private void checkMaxViolationForPriority(int priority, int count, String countsText) {
        if (count > this."maxPriority${priority}Violations") {
            throw new BuildException("Exceeded maximum number of priority ${priority} violations: " + countsText)
        }
    }
*/
}