package org.battleplugins.api.sponge.logger;

import org.battleplugins.api.util.MCWrapper;
import org.slf4j.Logger;

public class SpongeLogger extends MCWrapper<Logger> implements org.battleplugins.api.logger.Logger {

    private boolean debug;

    public SpongeLogger(Logger logger) {
        super(logger);

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        handle.warn(message);
    }

    @Override
    public void error(String message) {
        handle.error(message);
    }

    @Override
    public void warning(String message) {
        handle.warn(message);
    }

    @Override
    public void info(String message) {
        handle.info(message);
    }

    @Override
    public void debug(String message) {
        if (debug)
            handle.info(message);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
