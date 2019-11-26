package mc.alk.nukkit;

import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import cn.nukkit.plugin.service.ServicePriority;

import mc.alk.mc.APIType;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.chat.Message;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.plugin.MCServicePriority;
import mc.alk.nukkit.chat.NukkitMessage;
import mc.alk.nukkit.inventory.NukkitInventory;
import mc.alk.nukkit.inventory.NukkitItemStack;
import mc.alk.nukkit.inventory.virtual.VirtualChestInventory;
import mc.alk.nukkit.inventory.virtual.VirtualDoubleChestInventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class NukkitPlatform extends MCPlatform {

    @Override
    public APIType getAPIType() {
        return APIType.NUKKIT;
    }

    @Override
    public NukkitLocation getLocation(String world, double x, double y, double z) {
        return new NukkitLocation(world, x, y, z);
    }

    @Override
    public NukkitLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new NukkitLocation(world, x, y, z, pitch, yaw);
    }

    @Override
    public Optional<NukkitWorld> getWorld(String world) {
        return Optional.ofNullable(Server.getInstance().getLevelByName(world)).map(NukkitWorld::new);
    }

    @Override
    public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleDelayedTask((Plugin) plugin.getPlatformPlugin(), runnable, (int) millis/50).getTaskId();
    }

    @Override
    public long scheduleRepeatingTask(MCPlugin plugin, Runnable runnable, long millis) {
        return Server.getInstance().getScheduler().scheduleRepeatingTask((Plugin) plugin, runnable, (int) millis/50).getTaskId();
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
        return Server.getInstance().getOnlinePlayers().values()
                .stream().map(NukkitPlayer::new).collect(Collectors.toList());
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

    public NukkitInventory createInventory(MCPlugin plugin, int slots, String title) {
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

        return new NukkitInventory(inventory);
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority) {
        Server.getInstance().getServiceManager().register(clazz, service, (Plugin) plugin.getPlatformPlugin(), ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> Optional<T> getService(Class<T> clazz) {
        return Optional.ofNullable(Server.getInstance().getServiceManager().getProvider(clazz)).map(RegisteredServiceProvider::getProvider);
    }
}
