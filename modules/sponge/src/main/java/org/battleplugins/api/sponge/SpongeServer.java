package org.battleplugins.api.sponge;

import org.battleplugins.api.Server;
import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.service.ServicePriority;
import org.battleplugins.api.sponge.entity.living.player.SpongeOfflinePlayer;
import org.battleplugins.api.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.api.sponge.world.SpongeWorld;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.World;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.*;

public class SpongeServer extends MCWrapper<org.spongepowered.api.Server> implements Server {

    public SpongeServer(org.spongepowered.api.Server handle) {
        super(handle);
    }

    @Override
    public boolean isOnlineMode() {
        return handle.getOnlineMode();
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return handle.getPlayer(name).map(SpongePlayer::new);
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return handle.getPlayer(uuid).map(SpongePlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(String name) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return Optional.empty();

        return userStorageService.get().get(name).map(SpongeOfflinePlayer::new);
    }

    @Override
    public Optional<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return Optional.empty();

        return userStorageService.get().get(uuid).map(SpongeOfflinePlayer::new);
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        List<Player> playerList = new ArrayList<>();
        for (org.spongepowered.api.entity.living.player.Player player : handle.getOnlinePlayers()) {
            playerList.add(new SpongePlayer(player));
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
        return handle.getWorld(world).map(SpongeWorld::new);
    }

    @Override
    public boolean isMainThread() {
        return handle.isMainThread();
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        Sponge.getServiceManager().setProvider(plugin.getPlatformPlugin(), clazz, service);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Sponge.getServiceManager().getRegistration(clazz).map(ProviderRegistration::getProvider);
    }
}
