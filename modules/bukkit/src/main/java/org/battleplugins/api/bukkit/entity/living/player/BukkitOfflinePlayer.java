package org.battleplugins.api.bukkit.entity.living.player;

import org.battleplugins.api.bukkit.util.BukkitUtil;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

public class BukkitOfflinePlayer extends MCWrapper<OfflinePlayer> implements org.battleplugins.api.entity.living.player.OfflinePlayer {

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
    public Optional<Player> getPlayer() {
        return Optional.ofNullable(handle.getPlayer()).map(BukkitPlayer::new);
    }

    @Override
    public OptionalLong getFirstPlayed() {
        return OptionalLong.of(handle.getFirstPlayed());
    }

    @Override
    public OptionalLong getLastPlayed() {
        return OptionalLong.of(handle.getLastPlayed());
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle.hasPlayedBefore();
    }

    @Override
    public Optional<Location> getBedSpawnLocation() {
        return Optional.ofNullable(handle.getBedSpawnLocation()).map(BukkitUtil::fromBukkitLocation);
    }
}
