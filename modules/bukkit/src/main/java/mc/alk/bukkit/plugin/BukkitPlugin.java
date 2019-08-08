package mc.alk.bukkit.plugin;

import mc.alk.bukkit.BukkitServer;
import mc.alk.bukkit.command.BukkitCommandExecutor;
import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
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

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        getCommand(command.getLabel()).setExecutor(new BukkitCommandExecutor(executor));
    }
}
