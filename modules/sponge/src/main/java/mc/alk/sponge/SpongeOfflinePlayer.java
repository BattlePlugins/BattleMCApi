package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.util.RespawnLocation;

import java.util.UUID;

public class SpongeOfflinePlayer implements MCOfflinePlayer {

    private User offlinePlayer;

    public SpongeOfflinePlayer(User offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
    }

    @Override
    public boolean isOnline() {
        return offlinePlayer.isOnline();
    }

    @Override
    public String getName() {
        return offlinePlayer.getName();
    }

    @Override
    public UUID getUniqueId() {
        return offlinePlayer.getUniqueId();
    }

    @Override
    public MCPlayer getPlayer() {
        return new SpongePlayer(offlinePlayer.getPlayer().get());
    }

    @Override
    public long getFirstPlayed() {
        return offlinePlayer.get(Keys.FIRST_DATE_PLAYED).get().toEpochMilli();
    }

    @Override
    public long getLastPlayed() {
        return offlinePlayer.get(Keys.LAST_DATE_PLAYED).get().toEpochMilli();
    }

    @Override
    public boolean hasPlayedBefore() {
        return offlinePlayer != null;
    }

    @Override
    public MCLocation getBedSpawnLocation() {
        RespawnLocation respawnLoc = offlinePlayer.get(Keys.RESPAWN_LOCATIONS).get().get(offlinePlayer.getWorldUniqueId().get());
        if (respawnLoc != null && respawnLoc.isForced()) {
            return new SpongeLocation(respawnLoc.asLocation().get());
        }
        return null;
    }
}
