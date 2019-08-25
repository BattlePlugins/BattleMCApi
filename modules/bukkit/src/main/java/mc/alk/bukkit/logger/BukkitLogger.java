package mc.alk.bukkit.logger;

import mc.alk.mc.logger.MCLogger;

import java.util.logging.Logger;

public class BukkitLogger implements MCLogger {

    private Logger logger;

    private boolean debug;

    public BukkitLogger(Logger logger) {
        this.logger = logger;

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        logger.severe(message);
    }

    @Override
    public void error(String message) {
        logger.warning(message);
    }

    @Override
    public void warning(String message) {
        logger.warning(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void debug(String message) {
        if (debug)
            info(message);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Logger getBukkitLogger() {
        return logger;
    }
}
