package mc.alk.bukkit;

import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BukkitOfflinePlayer extends MCWrapper<OfflinePlayer> implements MCOfflinePlayer {

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
    public BukkitPlayer getPlayer() {
        return new BukkitPlayer(handle.getPlayer());
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
    public BukkitLocation getBedSpawnLocation() {
        return new BukkitLocation(handle.getBedSpawnLocation());
    }
}
