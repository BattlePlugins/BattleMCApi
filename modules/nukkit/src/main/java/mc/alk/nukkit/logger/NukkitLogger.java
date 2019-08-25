package mc.alk.nukkit.logger;

import cn.nukkit.utils.Logger;
import mc.alk.mc.logger.MCLogger;

public class NukkitLogger implements MCLogger {

    private Logger logger;

    private boolean debug;

    public NukkitLogger(Logger logger) {
        this.logger = logger;

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        logger.emergency(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
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

    public Logger getNukkitLogger() {
        return logger;
    }
}
