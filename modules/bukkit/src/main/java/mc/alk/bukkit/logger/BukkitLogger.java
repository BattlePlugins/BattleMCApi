package mc.alk.bukkit.logger;

import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.util.MCWrapper;

import java.util.logging.Logger;

public class BukkitLogger extends MCWrapper<Logger> implements MCLogger {

    private boolean debug;

    public BukkitLogger(Logger logger) {
        super(logger);

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        handle.severe(message);
    }

    @Override
    public void error(String message) {
        handle.warning(message);
    }

    @Override
    public void warning(String message) {
        handle.warning(message);
    }

    @Override
    public void info(String message) {
        handle.info(message);
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
}
