package org.battleplugins.sponge;

import mc.euro.version.Version;
import org.battleplugins.PlatformType;
import org.battleplugins.Platform;
import org.battleplugins.message.Message;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.service.ServicePriority;
import org.battleplugins.sponge.entity.living.player.SpongeOfflinePlayer;
import org.battleplugins.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.sponge.message.SpongeMessage;
import org.battleplugins.sponge.inventory.item.SpongeItemStack;
import org.battleplugins.sponge.world.SpongeLocation;
import org.battleplugins.sponge.world.SpongeWorld;
import org.battleplugins.world.World;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpongePlatform extends Platform {

    private SpongeRegistry registry;

    public SpongePlatform() {
        this.registry = new SpongeRegistry();
    }

    @Override
    public PlatformType getType() {
        return PlatformType.SPONGE;
    }

    @Override
    public SpongeLocation getLocation(World world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(new Location<>(((SpongeWorld) world).getHandle(), x, y, z));
    }

    @Override
    public Optional<SpongeWorld> getWorld(String world) {
        return Sponge.getServer().getWorld(world).map(SpongeWorld::new);
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
    public Optional<SpongePlayer> getPlayer(String name) {
        return Sponge.getServer().getPlayer(name).map(SpongePlayer::new);
    }

    @Override
    public Optional<SpongePlayer> getPlayer(UUID uuid) {
        return Sponge.getServer().getPlayer(uuid).map(SpongePlayer::new);
    }

    @Override
    public Optional<SpongeOfflinePlayer> getOfflinePlayer(String name) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return Optional.empty();

        return userStorageService.get().get(name).map(SpongeOfflinePlayer::new);
    }

    @Override
    public Optional<SpongeOfflinePlayer> getOfflinePlayer(UUID uuid) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return Optional.empty();

        return userStorageService.get().get(uuid).map(SpongeOfflinePlayer::new);
    }

    @Override
    public Collection<SpongePlayer> getOnlinePlayers() {
        List<SpongePlayer> playerList = new ArrayList<>();
        for (org.spongepowered.api.entity.living.player.Player player : Sponge.getServer().getOnlinePlayers()) {
            playerList.add(new SpongePlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<SpongeOfflinePlayer> getOfflinePlayers() {
        Collection<SpongeOfflinePlayer> players = new ArrayList<>();
        // TODO: Find a way to do this
        return players;
    }

    @Override
    public boolean isMainThread() {
        return Sponge.getServer().isMainThread();
    }

    @Override
    public boolean isOnlineMode() {
        return Sponge.getServer().getOnlineMode();
    }

    @Override
    public Version<SpongePlatform> getVersion() {
        return new Version<>(Sponge.getGame().getPlatform().getMinecraftVersion().getName());
    }

    @Override
    public Message getDefaultPlatformMessage() {
        return new SpongeMessage();
    }

    @Override
    public SpongeItemStack getDefaultPlatformItemStack() {
        return new SpongeItemStack(ItemStack.of(ItemTypes.AIR));
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        Sponge.getServiceManager().setProvider(plugin.getPlatformPlugin(), clazz, service);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Sponge.getServiceManager().getRegistration(clazz).map(ProviderRegistration::getProvider);
    }

    @Override
    public SpongeRegistry getRegistry() {
        return registry;
    }
}
