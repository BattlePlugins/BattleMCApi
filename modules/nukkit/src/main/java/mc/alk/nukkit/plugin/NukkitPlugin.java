package mc.alk.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.PlatformPlugin;
import mc.alk.nukkit.NukkitPlatform;
import mc.alk.nukkit.command.NukkitCommandExecutor;
import mc.alk.nukkit.logger.NukkitLogger;

import java.util.List;

public abstract class NukkitPlugin extends PluginBase implements PlatformPlugin {

    private MCPlatform platform;

    @Override
    public MCPlatform getPlatform() {
        if (platform == null) {
            platform = new NukkitPlatform();
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
        NukkitCommandExecutor nukkitExecutor = new NukkitCommandExecutor(command.getLabel(), executor);
        nukkitExecutor.setDescription(command.getDescription());
        nukkitExecutor.setPermission(command.getPermission());
        nukkitExecutor.setAliases(command.getAliases().toArray(new String[command.getAliases().size()]));
        
        getServer().getCommandMap().register(command.getLabel(),nukkitExecutor);
    }

    public void setNothing() {

    }

    @Override
    public MCLogger getMCLogger() {
        return new NukkitLogger(getLogger());
    }
}
