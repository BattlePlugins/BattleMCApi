package org.battleplugins.api;

import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.service.ServicePriority;
import org.battleplugins.api.world.World;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a server.
 */
public interface Server {

    /**
     * If the server is in online mode
     *
     * @return if the server is in online mode
     */
    boolean isOnlineMode();

    /**
     * Gets the {@link Player} from the given name. Will not
     * be present if the player with the given name
     * is not currently online
     *
     * @param name the name of the player
     * @return the player from the given name
     */
    Optional<Player> getPlayer(String name);

    /**
     * Gets the {@link Player} from the given {@link UUID}.
     * Will not be present if the player with the given UUID
     * is not currently online
     *
     * @param uuid the UUID of the player
     * @return the player from the given name
     */
    Optional<Player> getPlayer(UUID uuid);

    /**
     * Gets the {@link OfflinePlayer} from the given name.
     * Will not be present if a player does not exist with
     * the given name
     *
     * @param name the name of the offline player
     * @return the offline player from the given name
     */
    Optional<OfflinePlayer> getOfflinePlayer(String name);

    /**
     * Gets the {@link OfflinePlayer} from the given {@link UUID}.
     * Will not be present if a player does not exist with
     * the given UUID
     *
     * @param uuid the UUID of the offline player
     * @return the offline player from the given name
     */
    Optional<OfflinePlayer> getOfflinePlayer(UUID uuid);

    /**
     * A collection of all the online {@link Player}s
     *
     * @return a collection of all the online players
     */
    Collection<Player> getOnlinePlayers();

    /**
     * A collection of all the {@link OfflinePlayer}s
     * that have joined this server before
     *
     * @return a collection of all the offline players
     */
    Collection<OfflinePlayer> getOfflinePlayers();

    /**
     * Gets the {@link World} from the given string. Will not be
     * present if the world does not exist
     *
     * @param world the name of the world
     * @return the world from the given string
     */
    Optional<World> getWorld(String world);

    /**
     * If the main thread is being used
     *
     * @return if the main thread is being used
     */
    boolean isMainThread();

    /**
     * Registers a service with the server's builtin
     * service provider
     *
     * @param clazz the class of the server
     * @param service the service
     * @param plugin the plugin registering the service
     * @param <T> the value
     */
    default <T> void registerService(Class<T> clazz, T service, Plugin plugin) {
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
    <T> void registerService(Class<T> clazz, T service, Plugin plugin, ServicePriority priority);

    /**
     * Gets the service from the given class. Will return
     * empty if a service is not registered with the class
     * given
     *
     * @param clazz the class to get the service from
     * @param <T> the value
     * @return the service from the given class
     */
    <T> Optional<T> getService(Class<T> clazz);
}
