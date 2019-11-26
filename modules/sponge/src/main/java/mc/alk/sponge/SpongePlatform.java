package mc.alk.sponge;

import mc.alk.mc.APIType;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.chat.Message;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.plugin.MCServicePriority;
import mc.alk.sponge.chat.SpongeMessage;
import mc.alk.sponge.inventory.SpongeInventory;
import mc.alk.sponge.inventory.SpongeItemStack;

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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SpongePlatform extends MCPlatform {

    @Override
    public APIType getAPIType() {
        return APIType.SPONGE;
    }

    @Override
    public SpongeLocation getLocation(String world, double x, double y, double z) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }

    @Override
    public SpongeLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(world, x, y, z, pitch, yaw);
    }

    @Override
    public Optional<SpongeWorld> getWorld(String world) {
        return Sponge.getServer().getWorld(world).map(SpongeWorld::new);
    }

    @Override
    public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
        Sponge.getScheduler().createTaskBuilder().delay(millis, TimeUnit.MILLISECONDS).execute(runnable).submit(plugin.getPlatformPlugin());
        return Sponge.getScheduler().getScheduledTasks().size(); // Should work for now....
    }

    @Override
    public long scheduleRepeatingTask(MCPlugin plugin, Runnable runnable, long millis) {
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
        return Sponge.getServer().getOnlinePlayers().stream()
                .map(SpongePlayer::new).collect(Collectors.toList());
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
    public SpongeInventory createInventory(MCPlugin plugin, int slots, String title) {
        Inventory inventory = Inventory.builder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(title)))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension( 9, slots / 9)).build(plugin.getPlatformPlugin());

        return new SpongeInventory(inventory);
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority) {
        Sponge.getServiceManager().setProvider(plugin.getPlatformPlugin(), clazz, service);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Sponge.getServiceManager().getRegistration(clazz).map(ProviderRegistration::getProvider);
    }
}
