package mc.alk.nukkit.logger;

import cn.nukkit.utils.Logger;

import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.util.MCWrapper;

public class NukkitLogger extends MCWrapper<Logger> implements MCLogger {

    private boolean debug;

    public NukkitLogger(Logger logger) {
        super(logger);

        this.debug = false;
    }

    @Override
    public void severe(String message) {
        handle.emergency(message);
    }

    @Override
    public void error(String message) {
        handle.error(message);
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
