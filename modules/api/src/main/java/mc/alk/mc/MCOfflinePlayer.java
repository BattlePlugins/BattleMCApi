package mc.alk.mc;

import java.util.UUID;

public interface MCOfflinePlayer {

    boolean isOnline();

    String getName();

    UUID getUniqueId();

    MCPlayer getPlayer();

    long getFirstPlayed();

    long getLastPlayed();

    boolean hasPlayedBefore();

    MCLocation getBedSpawnLocation();
}
