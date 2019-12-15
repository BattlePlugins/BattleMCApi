package org.battleplugins;

import mc.euro.version.Version;

import org.battleplugins.message.Message;
import org.battleplugins.entity.living.player.OfflinePlayer;
import org.battleplugins.entity.living.player.Player;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.plugin.Plugin;
import org.battleplugins.plugin.PluginManager;
import org.battleplugins.plugin.service.ServicePriority;
import org.battleplugins.world.Location;
import org.battleplugins.world.World;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a platform. Can be extended by any of the
 * platforms in {@link PlatformType}.
 */
public abstract class Platform {

    private static Platform INSTANCE;

    private static PluginManager pluginManager = new PluginManager();

    /**
     * Sets the instance of this platform
     *
     * @param platform the platform instance
     */
    public static void setInstance(Platform platform) {
        if (INSTANCE == null) {
            INSTANCE = platform;
        }
    }

    /**
     * Gets the location with the given {@link World}, x, y, and z
     * parameters
     *
     * @param world the world of the location
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the location with the given parameters
     */
    public Location getLocation(World world, double x, double y, double z) {
        return getLocation(world, x, y, z, 0, 0);
    }

    /**
     * Gets the location with the given {@link World}, x, y, z,
     * pitch and yaw parameters
     *
     * @param world the world of the location
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param pitch the pitch of the location
     * @param yaw the yaw of the location
     * @return the location with the given parameters
     */
    public abstract Location getLocation(World world, double x, double y, double z, float pitch, float yaw);

    /**
     * Gets the {@link World} from the given string. Will not be
     * present if the world does not exist
     *
     * @param world the name of the world
     * @return the world from the given string
     */
    public abstract Optional<? extends World> getWorld(String world);

    /**
     * The {@link PlatformType}
     *
     * @return the platform type
     */
    public abstract PlatformType getType();

    // TODO: Rewrite scheduler/task API
    public abstract long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis);

    // TODO: Rewrite scheduler/task API
    public abstract long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis);

    // TODO: Rewrite scheduler/task API
    public abstract boolean cancelTask(long id);

    /**
     * Gets the {@link Player} from the given name. Will not
     * be present if the player with the given name
     * is not currently online
     *
     * @param name the name of the player
     * @return the player from the given name
     */
    public abstract Optional<? extends Player> getPlayer(String name);

    /**
     * Gets the {@link Player} from the given {@link UUID}.
     * Will not be present if the player with the given UUID
     * is not currently online
     *
     * @param uuid the UUID of the player
     * @return the player from the given name
     */
    public abstract Optional<? extends Player> getPlayer(UUID uuid);

    /**
     * Gets the {@link OfflinePlayer} from the given name.
     * Will not be present if a player does not exist with
     * the given name
     *
     * @param name the name of the offline player
     * @return the offline player from the given name
     */
    public abstract Optional<? extends OfflinePlayer> getOfflinePlayer(String name);

    /**
     * Gets the {@link OfflinePlayer} from the given {@link UUID}.
     * Will not be present if a player does not exist with
     * the given UUID
     *
     * @param uuid the UUID of the offline player
     * @return the offline player from the given name
     */
    public abstract Optional<? extends OfflinePlayer> getOfflinePlayer(UUID uuid);

    /**
     * A collection of all the online {@link Player}s
     *
     * @return a collection of all the online players
     */
    public abstract Collection<? extends Player> getOnlinePlayers();

    /**
     * A collection of all the {@link OfflinePlayer}s
     * that have joined this server before
     *
     * @return a collection of all the offline players
     */
    public abstract Collection<? extends OfflinePlayer> getOfflinePlayers();

    /**
     * If the main thread is being used
     *
     * @return if the main thread is being used
     */
    public abstract boolean isMainThread();

    /**
     * If the server is in online mode
     *
     * @return if the server is in online mode
     */
    public abstract boolean isOnlineMode();

    /**
     * The version of the platform
     *
     * @return the version of the platform
     */
    public abstract Version<? extends Platform> getVersion();

    /**
     * The default {@link Message} used for this platform
     *
     * @return the default Message for this platform
     */
    public abstract Message getDefaultPlatformMessage();

    /**
     * The default {@link ItemStack} used for this platform
     *
     * @return the default ItemStack for this platform
     */
    public abstract ItemStack getDefaultPlatformItemStack();

    /**
     * Broadcasts a message to all the online {@link Player}s
     *
     * @param message the message to send
     */
    public static void broadcastMessage(Message message) {
        INSTANCE.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    /**
     * Registers a service with the server's builtin
     * service provider
     *
     * @param clazz the class of the server
     * @param service the service
     * @param plugin the plugin registering the service
     * @param <T> the value
     */
    public <T> void registerService(Class<T> clazz, T service, Plugin plugin) {
        registerService(clazz, service, plugin, ServicePriority.NORMAL);
    }

    /**
     * Registers a service with the server's builtin
     * service provider
     *
     * @param clazz the class of the server
     * @param service the service
     * @param plugin the plugin registering the service
     * @param priority the priority of the service
     * @param <T> the value
     */
    public abstract <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority);

    /**
     * Gets the service from the given class. Will return
     * empty if a service is not registered with the class
     * given
     *
     * @param clazz the class to get the service from
     * @param <T> the value
     * @return the service from the given class
     */
    public abstract <T> Optional<T> getService(Class<T> clazz);

    /**
     * The {@link Registry}
     *
     * @return the registry
     */
    public abstract Registry getRegistry();

    /**
     * The {@link PluginManager}
     *
     * @return the plugin manager
     */
    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    /**
     * The {@link PlatformType}
     *
     * @return the platform type
     */
    public static PlatformType getPlatformType() {
        return INSTANCE.getType();
    }

    /**
     * The platform in use
     *
     * @return the platform in use
     */
    public static Platform getPlatform() {
        return INSTANCE;
    }
}
