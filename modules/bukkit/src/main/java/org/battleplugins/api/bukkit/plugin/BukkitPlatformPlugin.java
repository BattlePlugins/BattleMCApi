package org.battleplugins.api.bukkit.plugin;

import org.battleplugins.api.bukkit.command.BukkitCommandExecutor;
import org.battleplugins.api.bukkit.logger.BukkitLogger;
import org.battleplugins.api.bukkit.util.BukkitCommandUtil;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.battleplugins.api.util.MCWrapper;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class BukkitPlatformPlugin extends MCWrapper<Plugin> implements PlatformPlugin {

    public BukkitPlatformPlugin(Plugin handle) {
        super(handle);
    }

    @Override
    public BukkitPluginDescription getPluginDescription() {
        return new BukkitPluginDescription(handle.getDescription());
    }

    @Override
    public void registerPluginCommand(Command command, CommandExecutor executor) {
        BukkitCommandUtil.BattleBukkitCommand bukkitCommand = new BukkitCommandUtil.BattleBukkitCommand(command, handle, new BukkitCommandExecutor(executor));
        BukkitCommandUtil.registerCommand(command.getLabel(), bukkitCommand);

        // getCommand(command.getLabel()).setExecutor(new BukkitCommandExecutor(executor));
    }

    @Override
    public File getPluginDataFolder() {
        return handle.getDataFolder();
    }

    @Override
    public BukkitLogger getPluginLogger() {
        return new BukkitLogger(handle.getLogger());
    }

    @Override
    public boolean isPluginEnabled() {
        return handle.isEnabled();
    }
}
