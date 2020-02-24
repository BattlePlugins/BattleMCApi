package org.battleplugins.api.nukkit.plugin;

import cn.nukkit.plugin.Plugin;

import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.nukkit.command.NukkitCommandExecutor;
import org.battleplugins.api.nukkit.logger.NukkitLogger;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.battleplugins.api.util.MCWrapper;

import java.io.File;

public class NukkitPlatformPlugin extends MCWrapper<Plugin> implements PlatformPlugin {

    public NukkitPlatformPlugin(Plugin handle) {
        super(handle);
    }

    @Override
    public NukkitPluginDescription getPluginDescription() {
        return new NukkitPluginDescription(handle.getDescription());
    }

    @Override
    public void registerPluginCommand(Command command, CommandExecutor executor) {
        NukkitCommandExecutor nukkitExecutor = new NukkitCommandExecutor(command.getLabel(), executor);
        nukkitExecutor.setDescription(command.getDescription());
        nukkitExecutor.setPermission(command.getPermission());
        nukkitExecutor.setAliases(command.getAliases().toArray(new String[command.getAliases().size()]));

        handle.getServer().getCommandMap().register(command.getLabel(),nukkitExecutor);
    }

    @Override
    public File getPluginDataFolder() {
        return handle.getDataFolder();
    }

    @Override
    public NukkitLogger getPluginLogger() {
        return new NukkitLogger(handle.getLogger());
    }

    @Override
    public boolean isPluginEnabled() {
        return handle.isEnabled();
    }
}
