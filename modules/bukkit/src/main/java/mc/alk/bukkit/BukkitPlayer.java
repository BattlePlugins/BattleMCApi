package mc.alk.bukkit;

import mc.alk.bukkit.entity.BukkitHumanEntity;
import mc.alk.bukkit.inventory.BukkitInventory;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;

import mc.alk.mc.inventory.MCInventory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BukkitPlayer extends BukkitHumanEntity implements MCPlayer {

    private Player player;

    public BukkitPlayer(Player player){
        super(player);

        this.player = player;
    }

    @Override
    public BukkitPlayer getPlayer() {
        return this;
    }

    @Override
    public long getFirstPlayed() {
        return player.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return player.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return player.hasPlayedBefore();
    }

    @Override
    public BukkitLocation getBedSpawnLocation() {
        return new BukkitLocation(player.getBedSpawnLocation());
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public void openInventory(MCInventory inventory) {
        player.openInventory(((BukkitInventory) inventory).getHandle());
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public boolean hasPermission(String node) {
        return player.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void updateInventory() {
        player.updateInventory();
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }

    @Override
    public Player getHandle() {
        return player;
    }
}
