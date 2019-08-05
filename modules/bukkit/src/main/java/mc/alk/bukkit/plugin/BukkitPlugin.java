package mc.alk.bukkit.plugin;

import mc.alk.bukkit.BukkitServer;
import mc.alk.mc.MCServer;
import mc.alk.mc.plugin.MCPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitPlugin extends JavaPlugin implements MCPlugin {

    private MCServer server;

    @Override
    public MCServer getMCServer() {
        if (server == null) {
            server = new BukkitServer();
            MCServer.setInstance(server);
        }

        return server;
    }
}
