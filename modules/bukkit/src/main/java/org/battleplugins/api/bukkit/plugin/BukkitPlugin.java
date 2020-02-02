package org.battleplugins.api.bukkit.plugin;

import org.battleplugins.api.Platform;
import org.battleplugins.api.bukkit.BukkitPlatform;
import org.battleplugins.api.bukkit.command.BukkitCommandExecutor;
import org.battleplugins.api.bukkit.logger.BukkitLogger;
import org.battleplugins.api.bukkit.util.BukkitCommandUtil;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin implements PlatformPlugin {

    private Plugin plugin;

    @Override
    public void onEnable() {
        Platform.setInstance(new BukkitPlatform(this.getServer()));
        Platform.getPluginManager().enablePlugin(this.plugin = Platform.getPluginManager().initializePlugin(this));
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
