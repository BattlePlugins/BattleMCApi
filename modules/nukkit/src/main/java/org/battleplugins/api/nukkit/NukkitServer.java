package org.battleplugins.api.nukkit;

import cn.nukkit.plugin.service.RegisteredServiceProvider;

import org.battleplugins.api.Server;
import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.nukkit.entity.living.player.NukkitOfflinePlayer;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;
import org.battleplugins.api.nukkit.world.NukkitWorld;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.service.ServicePriority;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.World;

import java.util.*;

public class NukkitServer extends MCWrapper<cn.nukkit.Server> implements Server {

    public NukkitServer(cn.nukkit.Server handle) {
        super(handle);
    }

    @Override
    public boolean isOnlineMode() {
        return handle.getPropertyBoolean("xbox-auth");
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(handle.getPlayer(name)).map(NukkitPlayer::new);
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return handle.getPlayer(uuid).map(NukkitPlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(String name) {
        return Optional.ofNullable(handle.getOfflinePlayer(name)).map(NukkitOfflinePlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        return Optional.ofNullable(handle.getOfflinePlayer(uuid)).map(NukkitOfflinePlayer::new);
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        List<Player> playerList = new ArrayList<>();
        for (cn.nukkit.Player player : handle.getOnlinePlayers().values()) {
            playerList.add(new NukkitPlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<OfflinePlayer> getOfflinePlayers() {
        Collection<OfflinePlayer> players = new ArrayList<>();
        // TODO: Find a way to do this
        return players;
    }

    @Override
    public Optional<World> getWorld(String world) {
        return Optional.ofNullable(handle.getLevelByName(world)).map(NukkitWorld::new);
    }

    @Override
    public boolean isMainThread() {
        return handle.isPrimaryThread();
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        handle.getServiceManager().register(clazz, service, (cn.nukkit.plugin.Plugin) plugin.getPlatformPlugin(), cn.nukkit.plugin.service.ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Optional.ofNullable(handle.getServiceManager().getProvider(clazz)).map(RegisteredServiceProvider::getProvider);
    }
}
