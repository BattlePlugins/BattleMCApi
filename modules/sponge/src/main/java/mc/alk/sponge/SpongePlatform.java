package mc.alk.sponge;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.plugin.MCServicePriority;
import mc.alk.sponge.chat.SpongeMessage;
import mc.alk.sponge.inventory.SpongeInventory;
import mc.alk.sponge.inventory.SpongeItemStack;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
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
    public MCLocation getLocation(String world, double x, double y, double z) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }

    @Override
    public MCLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(world, x, y, z, pitch, yaw);
    }

    @Override
    public MCWorld getWorld(String world) {
        return new SpongeWorld(Sponge.getServer().getWorld(world).get());
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
    public MCPlayer getPlayer(String name) {
        Optional<Player> player = Sponge.getServer().getPlayer(name);
        return player.map(SpongePlayer::new).orElse(null);
    }

    @Override
    public MCPlayer getPlayer(UUID uuid) {
        Optional<Player> player = Sponge.getServer().getPlayer(uuid);
        return player.map(SpongePlayer::new).orElse(null);
    }

    @Override
    public MCOfflinePlayer getOfflinePlayer(String name) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return null;

        if (!userStorageService.get().get(name).isPresent())
            return null;

        return new SpongeOfflinePlayer(userStorageService.get().get(name).get());
    }

    @Override
    public MCOfflinePlayer getOfflinePlayer(UUID uuid) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return null;

        if (!userStorageService.get().get(uuid).isPresent())
            return null;

        return new SpongeOfflinePlayer(userStorageService.get().get(uuid).get());
    }

    @Override
    public Collection<MCPlayer> getOnlinePlayers() {
        return Sponge.getServer().getOnlinePlayers().stream()
                .map(SpongePlayer::new).collect(Collectors.toList());
    }

    @Override
    public Collection<MCOfflinePlayer> getOfflinePlayers() {
        Collection<MCOfflinePlayer> players = new ArrayList<>();
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
    public MCItemStack getDefaultPlatformItemStack() {
        return new SpongeItemStack(ItemStack.of(ItemTypes.AIR));
    }

    @Override
    public MCInventory createInventory(MCPlugin plugin, int slots, String title) {
        Inventory inventory = Inventory.builder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(title)))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension( 9, slots / 9)).build(plugin);

        return new SpongeInventory(inventory);
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority) {
        Sponge.getServiceManager().setProvider(plugin.getPlatformPlugin(), clazz, service);
    }

    @Override
    public <T> T getService(Class<T> clazz) {
        Optional<ProviderRegistration<T>> service = Sponge.getServiceManager().getRegistration(clazz);
        return service.map(ProviderRegistration::getProvider).orElse(null);
    }
}
