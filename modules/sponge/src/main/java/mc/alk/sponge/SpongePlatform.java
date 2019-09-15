package mc.alk.sponge;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.plugin.MCPlugin;

import mc.alk.mc.plugin.platform.PlatformPlugin;
import mc.alk.sponge.chat.SpongeMessage;
import mc.alk.sponge.inventory.SpongeInventory;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
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
    public MCLocation getMCLocation(String world, double x, double y, double z) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }

    @Override
    public MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(world, x, y, z, pitch, yaw);
    }

    @Override
    public MCWorld getMCWorld(String world) {
        return new SpongeWorld(Sponge.getServer().getWorld(world).get());
    }

    @Override
    public long scheduleSyncTask(PlatformPlugin plugin, Runnable runnable, long millis) {
        Sponge.getScheduler().createTaskBuilder().interval(millis, TimeUnit.MILLISECONDS).execute(runnable).submit(plugin);
        return 0; // No support for sponge task ids
    }

    @Override
    public boolean cancelMCTask(long id) {
        return false; // No support for sponge task ids
    }

    @Override
    public MCPlayer getMCPlayer(String name) {
        Optional<Player> player = Sponge.getServer().getPlayer(name);
        return player.map(SpongePlayer::new).orElse(null);
    }

    @Override
    public MCOfflinePlayer getMCOfflinePlayer(String name) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return null;

        if (!userStorageService.get().get(name).isPresent())
            return null;

        return new SpongeOfflinePlayer(userStorageService.get().get(name).get());
    }

    @Override
    public MCOfflinePlayer getMCOfflinePlayer(UUID uuid) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        if (!userStorageService.isPresent())
            return null;

        if (!userStorageService.get().get(uuid).isPresent())
            return null;

        return new SpongeOfflinePlayer(userStorageService.get().get(uuid).get());
    }

    @Override
    public Collection<MCPlayer> getMCOnlinePlayers() {
        return Sponge.getServer().getOnlinePlayers().stream()
                .map(SpongePlayer::new).collect(Collectors.toList());
    }

    @Override
    public Collection<MCOfflinePlayer> getMCOfflinePlayers() {
        Collection<MCOfflinePlayer> players = new ArrayList<>();
        // TODO: Find a way to do this
        return players;
    }

    @Override
    public boolean isMCMainThread() {
        return Sponge.getServer().isMainThread();
    }

    @Override
    public boolean isMCOnlineMode() {
        return Sponge.getServer().getOnlineMode();
    }

    @Override
    public String getMCVersion() {
        return "Java-" + Sponge.getGame().getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public Message getMCMessage() {
        return new SpongeMessage();
    }

    @Override
    public MCInventory createMCInventory(MCPlugin plugin, int slots, String title) {
        Inventory inventory = Inventory.builder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(title)))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension( 9, 6)).build(plugin);

        return new SpongeInventory(inventory);
    }
}
