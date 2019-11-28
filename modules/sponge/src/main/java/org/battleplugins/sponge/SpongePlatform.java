package org.battleplugins.sponge;

import org.battleplugins.APIType;
import org.battleplugins.Platform;
import org.battleplugins.message.Message;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.ServicePriority;
import org.battleplugins.sponge.entity.living.player.SpongeOfflinePlayer;
import org.battleplugins.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.sponge.message.SpongeMessage;
import org.battleplugins.sponge.inventory.SpongeInventory;
import org.battleplugins.sponge.inventory.item.SpongeItemStack;
import org.battleplugins.sponge.world.SpongeLocation;
import org.battleplugins.sponge.world.SpongeWorld;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpongePlatform extends Platform {

    @Override
    public APIType getAPIType() {
        return APIType.SPONGE;
    }

    @Override
    public SpongeLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
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
    public String getVersion() {
        return Sponge.getGame().getPlatform().getMinecraftVersion().getName();
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
    public SpongeInventory createInventory(Plugin plugin, int slots, String title) {
        Inventory inventory = Inventory.builder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(title)))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension( 9, slots / 9)).build(plugin.getPlatformPlugin());

        return new SpongeInventory<>(inventory);
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
