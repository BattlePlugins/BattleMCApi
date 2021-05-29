package org.battleplugins.api.bukkit;

import org.battleplugins.api.Server;
import org.battleplugins.api.bukkit.entity.living.player.BukkitOfflinePlayer;
import org.battleplugins.api.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.api.bukkit.world.BukkitWorld;
import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.service.ServicePriority;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.World;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class BukkitServer extends MCWrapper<org.bukkit.Server> implements Server {

    public BukkitServer(org.bukkit.Server handle) {
        super(handle);
    }

    @Override
    public boolean isOnlineMode() {
        return handle.getOnlineMode();
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(handle.getPlayer(name)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return Optional.ofNullable(handle.getPlayer(uuid)).map(BukkitPlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(String name) {
        return Optional.ofNullable(handle.getOfflinePlayer(name)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        return Optional.ofNullable(handle.getOfflinePlayer(uuid)).map(BukkitOfflinePlayer::new);
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        List<Player> playerList = new ArrayList<>();
        for (org.bukkit.entity.Player player : handle.getOnlinePlayers()) {
            playerList.add(new BukkitPlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<OfflinePlayer> getOfflinePlayers() {
        List<OfflinePlayer> playerList = new ArrayList<>();
        for (org.bukkit.OfflinePlayer player : handle.getOfflinePlayers()) {
            playerList.add(new BukkitOfflinePlayer(player));
        }

        return playerList;
    }

    @Override
    public Optional<World> getWorld(String world) {
        return Optional.ofNullable(handle.getWorld(world)).map(BukkitWorld::new);
    }

    @Override
    public boolean isMainThread() {
        return handle.isPrimaryThread();
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        handle.getServicesManager().register(clazz, service, (JavaPlugin) plugin.getPlatformPlugin(), org.bukkit.plugin.ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Optional.ofNullable(handle.getServicesManager().getRegistration(clazz)).map(RegisteredServiceProvider::getProvider);
    }
}
