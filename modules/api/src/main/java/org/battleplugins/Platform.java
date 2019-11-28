package org.battleplugins;

import org.battleplugins.message.Message;
import org.battleplugins.entity.living.player.OfflinePlayer;
import org.battleplugins.entity.living.player.Player;
import org.battleplugins.inventory.Inventory;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.PluginManager;
import org.battleplugins.plugin.ServicePriority;
import org.battleplugins.world.Location;
import org.battleplugins.world.World;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public abstract class Platform {

    private static Platform INSTANCE;

    private static PluginManager pluginManager = new PluginManager();

    public static void setInstance(Platform api) {
        if (INSTANCE == null){
            INSTANCE = api;
        }
    }

    public Location getLocation(String world, double x, double y, double z) {
        return getLocation(world, x, y, z, 0, 0);
    }

    public abstract Location getLocation(String world, double x, double y, double z, float pitch, float yaw);

    public abstract Optional<? extends World> getWorld(String world);

    public abstract APIType getAPIType();

    public abstract long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis);
    public abstract long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis);

    public abstract boolean cancelTask(long id);

    public abstract Optional<? extends Player> getPlayer(String name);
    public abstract Optional<? extends Player> getPlayer(UUID uuid);

    public abstract Optional<? extends OfflinePlayer> getOfflinePlayer(String name);

    public abstract Optional<? extends OfflinePlayer> getOfflinePlayer(UUID uuid);

    public abstract Collection<? extends Player> getOnlinePlayers();

    public abstract Collection<? extends OfflinePlayer> getOfflinePlayers();

    public abstract boolean isMainThread();

    public abstract boolean isOnlineMode();

    public abstract String getVersion();

    public abstract Message getDefaultPlatformMessage();

    public abstract ItemStack getDefaultPlatformItemStack();

    public static void broadcastMessage(Message message) {
        INSTANCE.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    public abstract Inventory createInventory(Plugin plugin, int slots, String title);

    public abstract <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority);

    public abstract <T> Optional<T> getService(Class<T> clazz);

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static APIType getAPI() {
        return INSTANCE.getAPIType();
    }

    public static Platform getPlatform() {
        return INSTANCE;
    }
}
