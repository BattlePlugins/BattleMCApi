package org.battleplugins.nukkit.entity.living.player;

import cn.nukkit.IPlayer;

import org.battleplugins.entity.living.player.OfflinePlayer;
import org.battleplugins.nukkit.world.NukkitLocation;
import org.battleplugins.util.MCWrapper;

import java.util.Optional;
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
    public Optional<NukkitLocation> getBedSpawnLocation() {
        // TODO: Add API here
        return Optional.empty();
    }
}
