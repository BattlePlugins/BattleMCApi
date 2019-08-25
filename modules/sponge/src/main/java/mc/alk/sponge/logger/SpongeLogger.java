package mc.alk.sponge.logger;

import mc.alk.mc.logger.MCLogger;
import org.slf4j.Logger;

public class SpongeLogger implements MCLogger {

    private Logger logger;

    private boolean debug;

    public SpongeLogger(Logger logger) {
        this.logger = logger;

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void warning(String message) {
        logger.warn(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void debug(String message) {
        if (debug)
            logger.info(message);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Logger getSpongeLogger() {
        return logger;
    }
}
