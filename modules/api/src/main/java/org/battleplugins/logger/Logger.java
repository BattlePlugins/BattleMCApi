package org.battleplugins.logger;

public interface Logger {

    void severe(String message);
    void error(String message);
    void warning(String message);
    void info(String message);

    void debug(String message);
    void setDebug(boolean debug);
}
