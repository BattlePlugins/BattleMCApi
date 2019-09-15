package mc.alk.sponge;

import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.util.MCWrapper;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.util.RespawnLocation;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class SpongeOfflinePlayer extends MCWrapper<User> implements MCOfflinePlayer {

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
    public SpongePlayer getPlayer() {
        return new SpongePlayer(handle.getPlayer().get());
    }

    @Override
    public long getFirstPlayed() {
        return handle.get(Keys.FIRST_DATE_PLAYED).orElse(Instant.now()).toEpochMilli();
    }

    @Override
    public long getLastPlayed() {
        return handle.get(Keys.LAST_DATE_PLAYED).orElse(Instant.now()).toEpochMilli();
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle != null;
    }

    @Override
    public SpongeLocation getBedSpawnLocation() {
        RespawnLocation respawnLoc = handle.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()).get(handle.getWorldUniqueId().get());
        if (respawnLoc != null && respawnLoc.isForced()) {
            return new SpongeLocation(respawnLoc.asLocation().get());
        }
        return null;
    }
}
