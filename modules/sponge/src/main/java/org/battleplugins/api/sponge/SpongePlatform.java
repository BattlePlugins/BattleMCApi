package org.battleplugins.api.sponge;

import mc.euro.version.Version;

import org.battleplugins.api.Platform;
import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;
import org.battleplugins.api.Server;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class SpongePlatform extends Platform {

    private SpongeRegistry registry = new SpongeRegistry();
    private SpongeServer server;

    public SpongePlatform(org.spongepowered.api.Server server) {
        this.server = new SpongeServer(server);
        this.registry.setup();
    }

    @Override
    public PlatformType getType() {
        return PlatformTypes.SPONGE;
    }

    @Override
    public long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis) {
        Sponge.getScheduler().createTaskBuilder().delay(millis, TimeUnit.MILLISECONDS).execute(runnable).submit(plugin.getPlatformPlugin());
        return Sponge.getScheduler().getScheduledTasks().size(); // Should work for now....
    }

    @Override
    public long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis) {
        Sponge.getScheduler().createTaskBuilder().interval(millis, TimeUnit.MILLISECONDS).execute(runnable).submit(plugin.getPlatformPlugin());
        return Sponge.getScheduler().getScheduledTasks().size(); // Should work for now....
    }

    @Override
    public boolean cancelTask(long id) {
        return false; // No support for sponge task ids
    }

    @Override
    public Version<Platform> getVersion() {
        return new Version<>(Sponge.getGame().getPlatform().getMinecraftVersion().getName());
    }

    @Override
    public SpongeItemStack getDefaultPlatformItemStack() {
        return new SpongeItemStack(ItemStack.of(ItemTypes.AIR));
    }

    @Override
    public SpongeRegistry getPlatformRegistry() {
        return registry;
    }

    @Override
    public Server getPlatformServer() {
        return server;
    }
}
