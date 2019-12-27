package org.battleplugins.api.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import org.battleplugins.api.Platform;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.nukkit.NukkitPlatform;
import org.battleplugins.api.nukkit.command.NukkitCommandExecutor;
import org.battleplugins.api.nukkit.inventory.virtual.VirtualInventoryListener;
import org.battleplugins.api.nukkit.logger.NukkitLogger;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.platform.PlatformPlugin;

public class NukkitPlugin extends PluginBase implements PlatformPlugin {

    private Plugin plugin;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new VirtualInventoryListener(this), this);

        Platform.setInstance(new NukkitPlatform());
        this.plugin = Platform.getPluginManager().initializePlugin(this);
        Platform.getPluginManager().enablePlugin(this.plugin);
    }

    @Override
    public void onDisable() {
        Platform.getPluginManager().disablePlugin(this.plugin);
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
