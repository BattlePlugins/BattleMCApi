package mc.alk.bukkit.plugin;

import mc.alk.bukkit.BukkitPlatform;
import mc.alk.bukkit.command.BukkitCommandExecutor;
import mc.alk.bukkit.logger.BukkitLogger;
import mc.alk.bukkit.util.BukkitCommandUtil;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.platform.PlatformPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin implements PlatformPlugin {

    @Override
    public void onEnable() {
        MCPlatform.setInstance(new BukkitPlatform());
        MCPlatform.getPluginManager().initializePlugin(this);
        MCPlatform.getPluginManager().enablePlugin();
    }

    @Override
    public void onDisable() {
        MCPlatform.getPluginManager().disablePlugin();
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
