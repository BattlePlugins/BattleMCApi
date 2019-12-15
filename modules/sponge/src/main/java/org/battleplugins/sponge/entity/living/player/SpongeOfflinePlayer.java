package org.battleplugins.sponge.entity.living.player;

import org.battleplugins.entity.living.player.OfflinePlayer;
import org.battleplugins.sponge.util.SpongeUtil;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.Location;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.util.RespawnLocation;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

public class SpongeOfflinePlayer extends MCWrapper<User> implements OfflinePlayer {

    public SpongeOfflinePlayer(User offlinePlayer) {
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
    public Optional<SpongePlayer> getPlayer() {
        return handle.getPlayer().map(SpongePlayer::new);
    }

    @Override
    public OptionalLong getFirstPlayed() {
        return handle.get(Keys.FIRST_DATE_PLAYED).map(value -> OptionalLong.of(value.toEpochMilli()))
                .orElse(OptionalLong.empty());
    }

    @Override
    public OptionalLong getLastPlayed() {
        return handle.get(Keys.LAST_DATE_PLAYED).map(value -> OptionalLong.of(value.toEpochMilli()))
                .orElse(OptionalLong.empty());
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle != null;
    }

    @Override
    public Optional<Location> getBedSpawnLocation() {
        RespawnLocation respawnLoc = handle.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()).get(handle.getWorldUniqueId().orElse(null));
        if (respawnLoc != null && respawnLoc.isForced()) {
            return respawnLoc.asLocation().map(SpongeUtil::fromSpongeLocation);
        }
        return Optional.empty();
    }
}
