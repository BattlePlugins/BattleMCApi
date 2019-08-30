package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.sponge.command.SpongeCommandSender;
import mc.alk.sponge.inventory.SpongeInventory;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.RespawnLocation;

import java.util.UUID;

public class SpongePlayer extends MCPlayer {

    private Player player;
    private String name;

    public SpongePlayer(Player player) {
        this.player = player;
        this.name = player.getName();
    }

    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(player.getWorld());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public MCPlayer getPlayer() {
        return this;
    }

    @Override
    public long getFirstPlayed() {
        return player.get(Keys.FIRST_DATE_PLAYED).get().toEpochMilli();
    }

    @Override
    public long getLastPlayed() {
        return player.get(Keys.LAST_DATE_PLAYED).get().toEpochMilli();
    }

    @Override
    public boolean hasPlayedBefore() {
        return player != null;
    }

    @Override
    public MCLocation getBedSpawnLocation() {
        RespawnLocation respawnLoc = player.get(Keys.RESPAWN_LOCATIONS).get().get(player.getWorldUniqueId().get());
        if (respawnLoc != null && respawnLoc.isForced()) {
            return new SpongeLocation(respawnLoc.asLocation().get());
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return player.getName();
    }

    @Override
    public boolean hasPermission(String node) {
        return player.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Text.of(message));
    }

    @Override
    public boolean isOp() {
        return false; // Why sponge.. why?
    }

    @Override
    public MCInventory getInventory() {
        return new SpongeInventory(player.getInventory());
    }

    @Override
    public void updateInventory() {
        // TODO: Add API here
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }

    public Player getSpongePlayer() {
        return player;
    }
}
