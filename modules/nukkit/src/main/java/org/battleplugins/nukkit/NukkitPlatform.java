package org.battleplugins.nukkit;

import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.service.RegisteredServiceProvider;

import org.battleplugins.APIType;
import org.battleplugins.Platform;
import org.battleplugins.message.Message;
import org.battleplugins.nukkit.entity.living.player.NukkitOfflinePlayer;
import org.battleplugins.nukkit.entity.living.player.NukkitPlayer;
import org.battleplugins.nukkit.message.NukkitMessage;
import org.battleplugins.nukkit.inventory.NukkitInventory;
import org.battleplugins.nukkit.inventory.item.NukkitItemStack;
import org.battleplugins.nukkit.inventory.virtual.VirtualChestInventory;
import org.battleplugins.nukkit.inventory.virtual.VirtualDoubleChestInventory;
import org.battleplugins.nukkit.world.NukkitLocation;
import org.battleplugins.nukkit.world.NukkitWorld;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.ServicePriority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NukkitPlatform extends Platform {

    @Override
    public APIType getAPIType() {
        return APIType.NUKKIT;
    }

    @Override
    public NukkitLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new NukkitLocation(new Location(x, y, z, pitch, yaw, Server.getInstance().getLevelByName(world)));
    }

    @Override
    public Optional<NukkitWorld> getWorld(String world) {
        return Optional.ofNullable(Server.getInstance().getLevelByName(world)).map(NukkitWorld::new);
    }

    @Override
    public long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleDelayedTask((cn.nukkit.plugin.Plugin) plugin.getPlatformPlugin(), runnable, (int) millis/50).getTaskId();
    }

    @Override
    public long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleRepeatingTask((cn.nukkit.plugin.Plugin) plugin, runnable, (int) millis/50).getTaskId();
    }

    @Override
    public Optional<NukkitPlayer> getPlayer(String name) {
        return Optional.ofNullable(Server.getInstance().getPlayer(name)).map(NukkitPlayer::new);
    }

    @Override
    public Optional<NukkitPlayer> getPlayer(UUID uuid) {
        return Server.getInstance().getPlayer(uuid).map(NukkitPlayer::new);
    }

    @Override
    public Optional<NukkitOfflinePlayer> getOfflinePlayer(String name) {
        return Optional.ofNullable(Server.getInstance().getOfflinePlayer(name)).map(NukkitOfflinePlayer::new);
    }

    @Override
    public Optional<NukkitOfflinePlayer> getOfflinePlayer(UUID uuid) {
        return Optional.ofNullable(Server.getInstance().getOfflinePlayer(uuid)).map(NukkitOfflinePlayer::new);
    }

    @Override
    public Collection<NukkitPlayer> getOnlinePlayers() {
        List<NukkitPlayer> playerList = new ArrayList<>();
        for (cn.nukkit.Player player : Server.getInstance().getOnlinePlayers().values()) {
            playerList.add(new NukkitPlayer(player));
        }

        return playerList;
    }

    @Override
    public Collection<NukkitOfflinePlayer> getOfflinePlayers() {
        Collection<NukkitOfflinePlayer> players = new ArrayList<>();
        // TODO: Find a way to do this
        return players;
    }

    @Override
    public boolean isMainThread() {
        return Server.getInstance().isPrimaryThread();
    }

    @Override
    public boolean isOnlineMode() {
        return Server.getInstance().getProperties().getBoolean("xbox-auth");
    }

    @Override
    public String getVersion() {
        return Server.getInstance().getVersion();
    }

    @Override
    public Message getDefaultPlatformMessage() {
        return new NukkitMessage();
    }

    @Override
    public NukkitItemStack getDefaultPlatformItemStack() {
        return new NukkitItemStack(Item.get(0));
    }

    @Override
    public boolean cancelTask(long id) {
        Server.getInstance().getScheduler().cancelTask((int) id);
        return true;
    }

    public NukkitInventory createInventory(Plugin plugin, int slots, String title) {
        return createInventory(slots, title, true);
    }

    public NukkitInventory createInventory(int slots, String title, boolean cancelled) {
        // Nukkit on its own does not have support for virtual inventories
        // So instead, we have to use some hacky methods and packets to create this
        // However, they can only be 27 slots (3 rows) or 54 slots (6 rows) in size
        VirtualChestInventory inventory = new VirtualChestInventory(null, title);
        if (slots > 27) {
            inventory = new VirtualDoubleChestInventory(null, title);
        }

        // We just cancel this event for now :)
        inventory.addListener(event -> event.setCancelled(cancelled));

        return new NukkitInventory<>(inventory);
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority) {
        Server.getInstance().getServiceManager().register(clazz, service, (cn.nukkit.plugin.Plugin) plugin.getPlatformPlugin(), cn.nukkit.plugin.service.ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Optional.ofNullable(Server.getInstance().getServiceManager().getProvider(clazz)).map(RegisteredServiceProvider::getProvider);
    }
}
