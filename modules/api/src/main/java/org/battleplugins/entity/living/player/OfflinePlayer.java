package org.battleplugins.entity.living.player;

import org.battleplugins.world.Location;

import java.util.Optional;
import java.util.UUID;

public interface OfflinePlayer {

    boolean isOnline();

    String getName();
    UUID getUniqueId();

    Optional<? extends Player> getPlayer();

    long getFirstPlayed();
    long getLastPlayed();

    boolean hasPlayedBefore();

    Optional<? extends Location> getBedSpawnLocation();
}
