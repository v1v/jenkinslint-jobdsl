# JenkinsLint

**JenkinsLint** is a static analysis tool for Job DSL source code, enabling monitoring and enforcement of many coding standards and best practices. JenkinsLint applies a set of predefined Rules that are applied to each Job DSL groovy file, and generates an HTML or XML report of the results, including a list of rules violated for each source file, and a count of the number of violations per package and for the whole project.

JenkinsLint is similar to CodeNarc.

DEPENDENCIES

JenkinsLint requires
 - Groovy version 2.1 or later
 - The Log4J jar, version 1.2.13 or later, accessible on the CLASSPATH
   (http://logging.apache.org/log4j/index.html).

AVAILABLE FROM MAVEN CENTRAL REPOSITORY

TODO