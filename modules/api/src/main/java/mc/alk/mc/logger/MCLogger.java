package mc.alk.mc.logger;

public interface MCLogger {

    void severe(String message);

    void error(String message);

    void warning(String message);

    void info(String message);

    void debug(String message);

    void setDebug(boolean debug);
}
