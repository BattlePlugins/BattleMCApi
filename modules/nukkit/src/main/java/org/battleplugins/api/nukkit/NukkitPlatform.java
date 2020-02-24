package org.battleplugins.api.nukkit;

import cn.nukkit.Server;
import cn.nukkit.item.Item;

import mc.euro.version.Version;

import org.battleplugins.api.Platform;
import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.nukkit.message.NukkitMessage;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;
import org.battleplugins.api.plugin.Plugin;

public class NukkitPlatform extends Platform {

    private NukkitRegistry registry = new NukkitRegistry();;
    private NukkitServer server;

    public NukkitPlatform(Server server) {
        this.server = new NukkitServer(server);
    }

    @Override
    public PlatformType getType() {
        return PlatformTypes.NUKKIT;
    }

    @Override
    public long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleDelayedTask((cn.nukkit.plugin.Plugin) plugin.getPlatformPlugin(), runnable, (int) millis/50).getTaskId();
    }

    @Override
    public long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleRepeatingTask((cn.nukkit.plugin.Plugin) plugin, runnable, (int) millis/50).getTaskId();
    }

    @Override
    public Version<Platform> getVersion() {
        return new Version<>(Server.getInstance().getVersion());
    }

    @Override
    public Message getDefaultPlatformMessage() {
        return new NukkitMessage();
    }

    @Override
    public NukkitItemStack getDefaultPlatformItemStack() {
        return new NukkitItemStack(Item.get(0));
    }

    @Override
    public boolean cancelTask(long id) {
        Server.getInstance().getScheduler().cancelTask((int) id);
        return true;
    }

    @Override
    public NukkitRegistry getPlatformRegistry() {
        return registry;
    }

    @Override
    public org.battleplugins.api.Server getPlatformServer() {
        return server;
    }
}
