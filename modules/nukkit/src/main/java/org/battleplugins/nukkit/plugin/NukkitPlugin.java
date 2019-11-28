package org.battleplugins.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import org.battleplugins.Platform;
import org.battleplugins.command.Command;
import org.battleplugins.command.CommandExecutor;
import org.battleplugins.logger.Logger;
import org.battleplugins.nukkit.NukkitPlatform;
import org.battleplugins.nukkit.command.NukkitCommandExecutor;
import org.battleplugins.nukkit.inventory.virtual.VirtualInventoryListener;
import org.battleplugins.nukkit.logger.NukkitLogger;
import org.battleplugins.plugin.platform.PlatformPlugin;

public class NukkitPlugin extends PluginBase implements PlatformPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new VirtualInventoryListener(this), this);

        Platform.setInstance(new NukkitPlatform());
        Platform.getPluginManager().initializePlugin(this);
        Platform.getPluginManager().enablePlugin();
    }

    @Override
    public void onDisable() {
        Platform.getPluginManager().disablePlugin();
    }

    @Override
    public void registerMCCommand(Command command, CommandExecutor executor) {
        NukkitCommandExecutor nukkitExecutor = new NukkitCommandExecutor(command.getLabel(), executor);
        nukkitExecutor.setDescription(command.getDescription());
        nukkitExecutor.setPermission(command.getPermission());
        nukkitExecutor.setAliases(command.getAliases().toArray(new String[command.getAliases().size()]));
        
        getServer().getCommandMap().register(command.getLabel(),nukkitExecutor);
    }

    @Override
    public Logger getMCLogger() {
        return new NukkitLogger(getLogger());
    }
}
