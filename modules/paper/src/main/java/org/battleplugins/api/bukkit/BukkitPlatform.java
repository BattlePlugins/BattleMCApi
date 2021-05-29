package org.battleplugins.api.bukkit;

import mc.euro.version.Version;

import org.battleplugins.api.Platform;
import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;
import org.battleplugins.api.Server;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlatform extends Platform {

    private BukkitRegistry registry;
    private BukkitServer server;

    public BukkitPlatform(org.bukkit.Server server) {
        this.registry = new BukkitRegistry();
        this.server = new BukkitServer(server);
    }

    @Override
    public PlatformType getType() {
        return PlatformTypes.PAPER;
    }

    @Override
    public long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis) {
        return Bukkit.getScheduler().scheduleSyncDelayedTask((JavaPlugin) plugin.getPlatformPlugin(), runnable,millis/50);
    }

    @Override
    public long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis) {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask((JavaPlugin) plugin.getPlatformPlugin(), runnable, millis/50, millis/50);
    }

    @Override
    public Version<Platform> getVersion() {
        return new Version<>(Bukkit.getVersion());
    }

    @Override
    public BukkitItemStack getDefaultPlatformItemStack() {
        return new BukkitItemStack(new ItemStack(Material.AIR));
    }

    @Override
    public BukkitRegistry getPlatformRegistry() {
        return registry;
    }

    @Override
    public Server getPlatformServer() {
        return server;
    }

    @Override
    public boolean cancelTask(long id) {
        Bukkit.getScheduler().cancelTask((int)id);
        return true;
    }
}
