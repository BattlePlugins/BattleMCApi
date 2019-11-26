package mc.alk.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.platform.PlatformPlugin;
import mc.alk.nukkit.NukkitPlatform;
import mc.alk.nukkit.command.NukkitCommandExecutor;
import mc.alk.nukkit.inventory.virtual.VirtualInventoryListener;
import mc.alk.nukkit.logger.NukkitLogger;

public class NukkitPlugin extends PluginBase implements PlatformPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new VirtualInventoryListener(this), this);

        MCPlatform.setInstance(new NukkitPlatform());
        MCPlatform.getPluginManager().initializePlugin(this);
        MCPlatform.getPluginManager().enablePlugin();
    }

    @Override
    public void onDisable() {
        MCPlatform.getPluginManager().disablePlugin();
    }

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        NukkitCommandExecutor nukkitExecutor = new NukkitCommandExecutor(command.getLabel(), executor);
        nukkitExecutor.setDescription(command.getDescription());
        nukkitExecutor.setPermission(command.getPermission());
        nukkitExecutor.setAliases(command.getAliases().toArray(new String[command.getAliases().size()]));
        
        getServer().getCommandMap().register(command.getLabel(),nukkitExecutor);
    }

    @Override
    public MCLogger getMCLogger() {
        return new NukkitLogger(getLogger());
    }
}
