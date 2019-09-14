package mc.alk.bukkit.plugin;

import mc.alk.bukkit.BukkitPlatform;
import mc.alk.bukkit.command.BukkitCommandExecutor;
import mc.alk.bukkit.logger.BukkitLogger;
import mc.alk.bukkit.util.BukkitCommandUtil;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.PlatformPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class BukkitPlugin extends JavaPlugin implements PlatformPlugin {

    private MCPlatform platform;

    @Override
    public MCPlatform getPlatform() {
        if (platform == null) {
            platform = new BukkitPlatform();
            MCPlatform.setInstance(platform);
        }

        return platform;
    }

    @Override
    public void onEnable() {
        MCPlatform.getPluginManager().initializePlugin(this);
        MCPlatform.getPluginManager().enablePlugin();
    }

    @Override
    public void onDisable() {
        MCPlatform.getPluginManager().disablePlugin();
    }

    public List<String> getAuthors() {
        return getDescription().getAuthors();
    }

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
