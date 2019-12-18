package org.battleplugins.bukkit.plugin;

import org.battleplugins.Platform;
import org.battleplugins.bukkit.BukkitPlatform;
import org.battleplugins.bukkit.command.BukkitCommandExecutor;
import org.battleplugins.bukkit.logger.BukkitLogger;
import org.battleplugins.bukkit.util.BukkitCommandUtil;
import org.battleplugins.command.Command;
import org.battleplugins.command.CommandExecutor;
import org.battleplugins.logger.Logger;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.platform.PlatformPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin implements PlatformPlugin {

    private Plugin plugin;

    @Override
    public void onEnable() {
        Platform.setInstance(new BukkitPlatform());
        this.plugin = Platform.getPluginManager().initializePlugin(this);
        Platform.getPluginManager().enablePlugin(this.plugin);
    }

    @Override
    public void onDisable() {
        Platform.getPluginManager().disablePlugin(this.plugin);
    }

    @Override
    public void registerMCCommand(Command command, CommandExecutor executor) {
        BukkitCommandUtil.BattleBukkitCommand bukkitCommand = new BukkitCommandUtil.BattleBukkitCommand(command, this, new BukkitCommandExecutor(executor));
        BukkitCommandUtil.registerCommand(command.getLabel(), bukkitCommand);

        // getCommand(command.getLabel()).setExecutor(new BukkitCommandExecutor(executor));
    }

    @Override
    public Logger getMCLogger() {
        return new BukkitLogger(getLogger());
    }
}
