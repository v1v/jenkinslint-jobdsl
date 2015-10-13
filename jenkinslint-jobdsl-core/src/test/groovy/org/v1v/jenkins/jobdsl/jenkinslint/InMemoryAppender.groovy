package org.v1v.jenkins.jobdsl.jenkinslint


import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.spi.LoggingEvent

/**
 * Log4J Appender that saves all logged loggingEvents in a List.
 *
 * @author Chris Mair
 */
class InMemoryAppender extends AppenderSkeleton implements Closeable {

    private final List loggingEvents = []

    /**
     * Return the List of LoggingEvents logged to this Appender
     * @return the List of logged LoggingEvents
     */
    List<String> getLoggingEvents() {
        return loggingEvents
    }

    void clearLoggedMessages() {
        loggingEvents.clear()
    }

    protected void append(LoggingEvent loggingEvent) {
        loggingEvents.add(loggingEvent)
    }

    @Override
    void close() {
        // Do nothing
    }

    boolean requiresLayout() {
        return false
    }

}