package mc.alk.bukkit.plugin;

import mc.alk.bukkit.BukkitServer;
import mc.alk.bukkit.command.BukkitCommandExecutor;
import mc.alk.bukkit.logger.BukkitLogger;
import mc.alk.bukkit.util.BukkitCommandUtil;
import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.MCPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
    public List<String> getAuthors() {
        return getDescription().getAuthors();
    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        BukkitCommandUtil.BattleBukkitCommand bukkitCommand = new BukkitCommandUtil.BattleBukkitCommand(command, this, new BukkitCommandExecutor(executor));
        BukkitCommandUtil.registerCommand(command.getLabel(), bukkitCommand);

        // getCommand(command.getLabel()).setExecutor(new BukkitCommandExecutor(executor));
    }

    @Override
    public MCLogger getMCLogger() {
        return new BukkitLogger(getLogger());
    }
}
