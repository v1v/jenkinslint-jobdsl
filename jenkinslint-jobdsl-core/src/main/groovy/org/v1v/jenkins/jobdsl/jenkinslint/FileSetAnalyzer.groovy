package org.v1v.jenkins.jobdsl.jenkinslint

import groovy.io.FileType
import javaposse.jobdsl.dsl.DslScriptException
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.ScriptRequest
import org.v1v.jenkins.jobdsl.jenkinslint.util.WildcardPattern

/**
 * @author victor.martinez.
 */
class FileSetAnalyzer {

    static final DEFAULT_INCLUDES = '**/*.groovy'

    /**
     * The base (root) directory. Must not be null or empty.
     */
    String baseDirectory

    /**
     * The ant-style pattern of files to include in the analysis. Defaults to match all
     * files with names ending with '.groovy'. If null, match all
     * files/directories. This pattern can optionally contain wildcards: '**', '*' and '?'.
     * All file separators within paths are normalized to the standard '/' separator,
     * so use the '/' separator within this pattern where necessary. Example:
     * "&#42;&#42;/*.groovy". If both <code>includes</code> and <code>excludes</code>
     * are specified, then only files/directories that match at least one of the
     * <code>includes</code> and none of the <code>excludes</code> are analyzed.
     */
    String includes = DEFAULT_INCLUDES

    /**
     * The ant-style pattern of files to exclude from the analysis. If null, exclude no
     * files/directories. This pattern can optionally contain wildcards: '**', '*' and '?'.
     * All file separators within paths are normalized to the standard '/' separator,
     * so use the '/' separator within this pattern where necessary. Example:
     * "&#42;&#42;/*.groovy". If both <code>includes</code> and <code>excludes</code>
     * are specified, then only files/directories that match at least one of the
     * <code>includes</code> and none of the <code>excludes</code> are analyzed.
     */
    String excludes

    private WildcardPattern includesPattern
    private WildcardPattern excludesPattern

    private final items = []

    void analyze(List rules) {
        assert baseDirectory
        assert rules
        initializeWildcardPatterns()

        File cwd = new File(baseDirectory)
        URL cwdURL = cwd.toURI().toURL()

        FileJobManagement jm = new FileJobManagement(cwd)
        jm.parameters.putAll(System.getenv())
        System.properties.each { def key, def value ->
            jm.parameters.put(key.toString(), value.toString())
        }

        def dir = new File(baseDirectory)
        dir.eachFileRecurse(FileType.FILES) {
            if (this.matches(it)) {
                try {
                    ScriptRequest request = new ScriptRequest(it.getCanonicalPath(), null, cwdURL, false)
                    JobParent jp = DslScriptLoader.runDslEngineForParent(request, jm);
                    jp.getReferencedJobs().each { item ->
                        addItem(item)
                    }
                } catch (DslScriptException dsle) {
                    println "TODO: file '${it} is not a DSL supported file"
                } catch (Exception e) {
                    println "TODO: file '${it} is not a DSL supported file"
                }
            }
        }

        rules.each { rule ->
            items.each { item ->
                println "${rule} - ${rule.isDefect(item)}"
            }
        }
    }

    /**
     * Add a single File to this RuleSet
     * @param file - the File to add
     */
    void addItem(Item item) {
        assert item != null
        items << item
    }

    /**
     * @return a List of Rule objects. The returned List is immutable.
     */
    List getItems() {
        items.asImmutable()
    }

    protected boolean matches(File sourceFile) {
        includesPattern.matches(sourceFile.path) &&
                !excludesPattern.matches(sourceFile.path)
    }

    protected void initializeWildcardPatterns() {
        includesPattern = new WildcardPattern(includes)
        excludesPattern = new WildcardPattern(excludes, false)  // do not match by default
    }
}
