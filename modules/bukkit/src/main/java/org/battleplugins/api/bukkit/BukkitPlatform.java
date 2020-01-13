package org.battleplugins.api.bukkit;

import io.papermc.lib.PaperLib;
import mc.euro.version.Version;

import org.battleplugins.api.Platform;
import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;
import org.battleplugins.api.bukkit.entity.living.player.BukkitOfflinePlayer;
import org.battleplugins.api.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.bukkit.message.BukkitMessage;
import org.battleplugins.api.bukkit.message.SpigotMessage;
import org.battleplugins.api.bukkit.world.BukkitWorld;
import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.plugin.service.ServicePriority;
import org.battleplugins.api.world.World;
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
    public Optional<World> getWorld(String world) {
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
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return Optional.ofNullable(Bukkit.getPlayer(uuid)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(String name) {
        return Optional.ofNullable(Bukkit.getOfflinePlayer(name)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        return Optional.ofNullable(Bukkit.getOfflinePlayer(uuid)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        List<Player> playerList = new ArrayList<>();
        for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
            playerList.add(new BukkitPlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<OfflinePlayer> getOfflinePlayers() {
        List<OfflinePlayer> playerList = new ArrayList<>();
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
    public Version<Platform> getVersion() {
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
