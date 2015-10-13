package org.v1v.jenkins.jobdsl.jenkinslint

import java.util.logging.Logger

class JenkinsLint {

    private static final Logger LOG = Logger.getLogger(JenkinsLint.class.getName());

    protected static final HELP = """JenkinsLint - static analysis for JobDSL',
Usage: java Jenkins [OPTIONS]
  where OPTIONS are zero or more command-line options of the form "-NAME[=VALUE]":
    -basedir=<DIR>
        The base (root) directory for the source code to be analyzed.
        Defaults to the current directory (".").
    -includes=<PATTERNS>
        The comma-separated list of Ant-style file patterns specifying files that must
        be included. Defaults to "**/*.groovy".
    -excludes=<PATTERNS>
        The comma-separated list of Ant-style file patterns specifying files that must
        be excluded. No files are excluded when omitted.
    -help
        Display the command-line help. If present, this must be the only command-line parameter.
  Example command-line invocations:
    java org.v1v.jenkins.jobdsl.jenkinslint.JenkinsLint
    java org.v1v.jenkins.jobdsl.jenkinslint.JenkinsLint -includes="jobs/*.groovy"
    java org.v1v.jenkins.jobdsl.jenkinslint.JenkinsLint -help'"""

    protected String baseDir
    protected String includes
    protected String excludes


    // Abstract creation of the LintRunner instance to allow substitution of test spy for unit tests
    protected createLintRunner = { new LintRunner() }

    // Abstract calling System.exit() to allow substitution of test spy for unit tests
    protected static systemExit = { exitCode -> System.exit(exitCode) }


    /**
     * Main command-line entry-point. Run the JenkinsLint application.
     * @param args - the String[] of command-line arguments
     */
    static void main(String[] args) {
        def lint = new JenkinsLint()

        if (args == ['-help'] as String[]) {
            println HELP
            return
        }

        try {
            lint.execute(args)
        }
        catch(Throwable t) {
            println "ERROR: ${t.message}"
            t.printStackTrace()
            println HELP
            systemExit(1)
        }
    }

    protected void execute(String[] args) {
        parseArgs(args)
        setDefaultsIfNecessary()
        def lintRunner = createLintRunner()
        def fileSet = createFileSet()
        lintRunner.fileSetAnalyzer = fileSet
        lintRunner.execute()
    }

    protected void setDefaultsIfNecessary() {
        baseDir = baseDir ?: '.'
        includes = includes ?: '**/*.groovy'
    }

    /**
     * Create and return the FileSet
     * @return a configured FileSet instance
     */
    protected FileSetAnalyzer createFileSet() {
        new FileSetAnalyzer(baseDirectory: baseDir, includes: includes, excludes: excludes)
    }

    protected void parseArgs(String[] args) {
        args.each { arg ->
            final PATTERN = /\-(.*)\=(.*)/      // -name=value
            def matcher = arg =~ PATTERN
            assert matcher, "Invalid argument format: [$arg]"
            def name = matcher[0][1]
            def value = matcher[0][2]
            switch(name) {
                case 'basedir': baseDir = value; break
                case 'includes': includes = value; break
                case 'excludes': excludes = value; break
                default: throw new IllegalArgumentException("Invalid option: [$arg]")
            }
        }
    }

}
