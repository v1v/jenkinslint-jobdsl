package org.v1v.jenkins.jobdsl.jenkinslint

import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.v1v.jenkins.jobdsl.jenkinslint.TestUtil.captureSystemOut
import static org.v1v.jenkins.jobdsl.jenkinslint.TestUtil.shouldFailWithMessageContaining

/**
 * @author victor.martinez.
 */
class LintTest {

    private static final BASE_DIR = 'src/test/resources'
    private static final INCLUDES = 'sourcewithdirs/**/*.groovy'
    private static final EXCLUDES = '**/*File2.groovy'

    private lint
    private int exitCode

    protected final LOG = Logger.getLogger(getClass())

    /**
     * Write out the specified log message, prefixing with the current class name.
     * @param message - the message to log; toString() is applied first
     */
    protected void log(message) {
        LOG.info message
    }

    @Test
    void testParseArgs_InvalidOptionName() {
        shouldFailWithMessageContaining('unknown') { parseArgs('-unknown=abc') }
    }

    @Test
    void testParseArgs_InvalidOption_NoHyphen() {
        shouldFailWithMessageContaining('bad') { parseArgs('bad=abc') }
    }

    @Test
    void testParseArgs_InvalidOption_NoEquals() {
        shouldFailWithMessageContaining('badstuff') { parseArgs('badstuff') }
    }

    @Test
    void testParseArgs_BaseDir() {
        parseArgs("-basedir=$BASE_DIR")
        assert lint.baseDir == BASE_DIR
    }

    @Test
    void testParseArgs_Includes() {
        parseArgs("-includes=$INCLUDES")
        assert lint.includes == INCLUDES
    }

    @Test
    void testParseArgs_Excludes() {
        parseArgs("-excludes=$EXCLUDES")
        assert lint.excludes == EXCLUDES
    }

    @Test
    void testSetDefaultsIfNecessary_ValuesAlreadySet() {
        lint.includes = 'aaa'
        lint.baseDir = 'ddd'
        lint.setDefaultsIfNecessary()
        assert lint.includes == 'aaa'
        assert lint.baseDir == 'ddd'
    }

    @Test
    void testExecute_NoArgs() {
        assert exitCode == 0
    }

    @Test
    void testMain() {
        final ARGS = [
                "-basedir=$BASE_DIR", "-includes=$INCLUDES",
                "-excludes=$EXCLUDES"] as String[]
        Lint.main(ARGS)
        assert exitCode == 0
    }

    @Test
    void testMain_Help() {
        final ARGS = ['-help'] as String[]
        def stdout = captureSystemOut {
            Lint.main(ARGS)
        }
        log("stdout=[$stdout]")
        assert !stdout.contains('ERROR')
        assert stdout.contains(Lint.HELP)
        assert exitCode == 0
    }

    @Test
    void testMain_BadOptionFormat() {
        final ARGS = ["-excludes=$EXCLUDES", '&^%#BAD%$#'] as String[]
        def stdout = captureSystemOut {
            Lint.main(ARGS)
        }
        log("stdout=[$stdout]")
        assert stdout.contains(ARGS[1])
        assert stdout.contains(Lint.HELP)
        assert exitCode == 1
    }

    @Test
    void testMain_UnknownOption() {
        final ARGS = ['-unknown=23', "-excludes=$EXCLUDES"] as String[]
        def stdout = captureSystemOut {
            Lint.main(ARGS)
        }
        log("stdout=[$stdout]")
        assert stdout.contains(ARGS[0])
        assert stdout.contains(Lint.HELP)
        assert exitCode == 1
    }

    //--------------------------------------------------------------------------
    // Test setUp/tearDown and helper methods
    //--------------------------------------------------------------------------

    @Before
    void setUp() {
        lint = new Lint()
        lint.systemExit = { code -> exitCode = code }
    }

    @After
    void tearDown() {
    }

    private void parseArgs(Object[] args) {
        def argsAsArray = args as String[]
        lint.parseArgs(argsAsArray)
    }
}