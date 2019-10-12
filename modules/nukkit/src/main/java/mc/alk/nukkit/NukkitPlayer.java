package mc.alk.nukkit;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.nukkit.entity.NukkitHumanEntity;
import mc.alk.nukkit.inventory.NukkitInventory;

public class NukkitPlayer extends NukkitHumanEntity implements MCPlayer {

    private Player player;

    public NukkitPlayer(Player player){
        super(player);

        this.player = player;
    }

    @Override
    public NukkitPlayer getPlayer() {
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
    public NukkitLocation getBedSpawnLocation() {
        // TODO: Add API here
        return null;
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public void openInventory(MCInventory inventory) {
        player.addWindow(((NukkitInventory) inventory).getHandle());
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
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public void updateInventory() {
        // TODO: Add API here
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
