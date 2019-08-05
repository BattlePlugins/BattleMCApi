package mc.alk.nukkit;

import cn.nukkit.OfflinePlayer;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;

import java.util.UUID;

public class NukkitOfflinePlayer implements MCOfflinePlayer {

    private OfflinePlayer offlinePlayer;

    public NukkitOfflinePlayer(OfflinePlayer offlinePlayer) {
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
        return new NukkitPlayer(offlinePlayer.getPlayer());
    }

    @Override
    public long getFirstPlayed() {
        return offlinePlayer.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return offlinePlayer.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return offlinePlayer.hasPlayedBefore();
    }

    @Override
    public MCLocation getBedSpawnLocation() {
        // TODO: Add API here
        return null;
    }
}
