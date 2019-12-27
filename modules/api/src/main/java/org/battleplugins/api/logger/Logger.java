package org.battleplugins.api.logger;

/**
 * Logs messages to the console.
 */
public interface Logger {

    /**
     * Logs a message prepended with a
     * severe tag. Usually ran when something
     * terribly wrong has happened
     *
     * @param message the message to log
     */
    void severe(String message);

    /**
     * Logs a messaged prepended with an
     * error tag. Usually ran when an
     * error has occured
     *
     * @param message the message to log
     */
    void error(String message);

    /**
     * Logs a messaged prepended with a
     * warning tag. Usually ran when there
     * is a warning
     *
     * @param message the message to log
     */
    void warning(String message);

    /**
     * Logs an info message
     *
     * @param message the message to log
     */
    void info(String message);

    /**
     * Logs a message only visible if
     * {@link #setDebug(boolean)} is true
     *
     * @param message the message to log
     */
    void debug(String message);

    /**
     * Sets if debug messages should be
     * logged
     *
     * @param debug if debug messages should be logged
     */
    void setDebug(boolean debug);
}
