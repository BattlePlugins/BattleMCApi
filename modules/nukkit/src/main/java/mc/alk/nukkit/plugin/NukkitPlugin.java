package mc.alk.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import mc.alk.mc.MCServer;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.nukkit.NukkitServer;

public abstract class NukkitPlugin extends PluginBase implements MCPlugin {

    private MCServer server;

    @Override
    public MCServer getMCServer() {
        if (server == null) {
            server = new NukkitServer();
            MCServer.setInstance(server);
        }

        return server;
    }
}
