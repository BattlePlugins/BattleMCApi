package mc.alk.nukkit;

import cn.nukkit.OfflinePlayer;

import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.util.MCWrapper;

import java.util.UUID;

public class NukkitOfflinePlayer extends MCWrapper<OfflinePlayer> implements MCOfflinePlayer {

    public NukkitOfflinePlayer(OfflinePlayer offlinePlayer) {
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
    public NukkitPlayer getPlayer() {
        return new NukkitPlayer(handle.getPlayer());
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
    public NukkitLocation getBedSpawnLocation() {
        // TODO: Add API here
        return null;
    }
}
