package org.battleplugins.bukkit.entity.living.player;

import org.battleplugins.bukkit.world.BukkitLocation;
import org.battleplugins.util.MCWrapper;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.UUID;

public class BukkitOfflinePlayer extends MCWrapper<OfflinePlayer> implements org.battleplugins.entity.living.player.OfflinePlayer {

    public BukkitOfflinePlayer(OfflinePlayer offlinePlayer) {
        super(offlinePlayer);
    }

    @Override
    public boolean isOnline() {
        return handle.isOnline();
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public Optional<BukkitPlayer> getPlayer() {
        return Optional.ofNullable(handle.getPlayer()).map(BukkitPlayer::new);
    }

    @Override
    public long getFirstPlayed() {
        return handle.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return handle.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle.hasPlayedBefore();
    }

    @Override
    public Optional<BukkitLocation> getBedSpawnLocation() {
        return Optional.ofNullable(handle.getBedSpawnLocation()).map(BukkitLocation::new);
    }
}
