package org.battleplugins.api.entity.living.player;

import org.battleplugins.api.world.Location;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

/**
 * Represents an offline player.
 */
public interface OfflinePlayer {

    /**
     * If the offline player is logged on
     *
     * @return if the offline player is logged on
     */
    boolean isOnline();

    /**
     * The name of the offline player
     *
     * @return the name of the player
     */
    String getName();

    /**
     * The {@link UUID} of the offline player
     *
     * @return the unique identifier of the player
     */
    UUID getUniqueId();

    /**
     * The {@link Player} object of the offline player.
     * Will not be present if {@link #isOnline()} is false
     *
     * @return the player object of this offline player
     */
    Optional<Player> getPlayer();

    /**
     * When the offline player first played. Will not be
     * present if {@link #hasPlayedBefore()} is false
     *
     * @return when the offline player first played
     */
    OptionalLong getFirstPlayed();

    /**
     * When the offline player last played. Will not be
     * present if {@link #hasPlayedBefore()} is false
     *
     * @return when the offline player last played
     */
    OptionalLong getLastPlayed();

    /**
     * If the offline player has played before
     *
     * @return if the offline player has played before
     */
    boolean hasPlayedBefore();

    /**
     * The bed spawn location of the player. Will
     * not be present if the player's bed has been
     * obstructed
     *
     * @return the bed spawn location of the player
     */
    Optional<Location> getBedSpawnLocation();
}
