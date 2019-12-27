package org.battleplugins.api.nukkit.entity.living.player;

import cn.nukkit.IPlayer;

import org.battleplugins.api.entity.living.player.OfflinePlayer;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

public class NukkitOfflinePlayer extends MCWrapper<IPlayer> implements OfflinePlayer {

    public NukkitOfflinePlayer(IPlayer offlinePlayer) {
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
    public Optional<NukkitPlayer> getPlayer() {
        if (!handle.isOnline())
            return Optional.empty();

        return Optional.ofNullable(handle.getPlayer()).map(NukkitPlayer::new);
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
        // TODO: Add API here
        return Optional.empty();
    }
}
