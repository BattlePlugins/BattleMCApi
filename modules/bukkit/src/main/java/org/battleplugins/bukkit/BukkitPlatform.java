package org.battleplugins.bukkit;

import io.papermc.lib.PaperLib;
import mc.euro.version.Version;

import org.battleplugins.Platform;
import org.battleplugins.PlatformType;
import org.battleplugins.PlatformTypes;
import org.battleplugins.bukkit.entity.living.player.BukkitOfflinePlayer;
import org.battleplugins.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.bukkit.message.BukkitMessage;
import org.battleplugins.bukkit.message.SpigotMessage;
import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.bukkit.world.BukkitWorld;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.message.Message;
import org.battleplugins.plugin.service.ServicePriority;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BukkitPlatform extends Platform {

    private BukkitRegistry registry;

    public BukkitPlatform() {
        registry = new BukkitRegistry();
    }

    @Override
    public PlatformType getType() {
        return PlatformTypes.BUKKIT;
    }

    @Override
    public Optional<BukkitWorld> getWorld(String world) {
        return Optional.ofNullable(Bukkit.getWorld(world)).map(BukkitWorld::new);
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
    public Optional<BukkitPlayer> getPlayer(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<BukkitPlayer> getPlayer(UUID uuid) {
        return Optional.ofNullable(Bukkit.getPlayer(uuid)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<BukkitOfflinePlayer> getOfflinePlayer(String name) {
        return Optional.ofNullable(Bukkit.getOfflinePlayer(name)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Optional<BukkitOfflinePlayer> getOfflinePlayer(UUID uuid) {
        return Optional.ofNullable(Bukkit.getOfflinePlayer(uuid)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Collection<BukkitPlayer> getOnlinePlayers() {
        List<BukkitPlayer> playerList = new ArrayList<>();
        for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
            playerList.add(new BukkitPlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<BukkitOfflinePlayer> getOfflinePlayers() {
        List<BukkitOfflinePlayer> playerList = new ArrayList<>();
        for (org.bukkit.OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            playerList.add(new BukkitOfflinePlayer(player));
        }

        return playerList;
    }

    @Override
    public boolean isMainThread() {
        return Bukkit.isPrimaryThread();
    }

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getOnlineMode();
    }

    @Override
    public Version<BukkitPlatform> getVersion() {
        return new Version<>(Bukkit.getVersion());
    }

    @Override
    public Message getDefaultPlatformMessage() {
        if (PaperLib.isSpigot() || PaperLib.isPaper())
            return new SpigotMessage();

        return new BukkitMessage();
    }

    @Override
    public BukkitItemStack getDefaultPlatformItemStack() {
        return new BukkitItemStack(new ItemStack(Material.AIR));
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        Bukkit.getServicesManager().register(clazz, service, (JavaPlugin) plugin.getPlatformPlugin(), org.bukkit.plugin.ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Optional.ofNullable(Bukkit.getServicesManager().getRegistration(clazz)).map(RegisteredServiceProvider::getProvider);
    }

    @Override
    public BukkitRegistry getRegistry() {
        return registry;
    }

    @Override
    public boolean cancelTask(long id) {
        Bukkit.getScheduler().cancelTask((int)id);
        return true;
    }
}
