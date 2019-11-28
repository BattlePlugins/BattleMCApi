package org.battleplugins.nukkit.entity.living.player;

import cn.nukkit.Player;

import org.battleplugins.entity.living.player.GameMode;
import org.battleplugins.inventory.Inventory;
import org.battleplugins.nukkit.world.NukkitLocation;
import org.battleplugins.nukkit.entity.living.NukkitHumanEntity;
import org.battleplugins.nukkit.inventory.NukkitInventory;

import java.util.Optional;

public class NukkitPlayer extends NukkitHumanEntity<Player> implements org.battleplugins.entity.living.player.Player {

    public NukkitPlayer(Player player){
        super(player);
    }

    @Override
    public Optional<NukkitPlayer> getPlayer() {
        if (!isOnline())
            return Optional.empty();

        return Optional.of(this);
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
    public Optional<NukkitLocation> getBedSpawnLocation() {
        // TODO: Add API here
        return Optional.empty();
    }

    @Override
    public String getDisplayName() {
        return handle.getDisplayName();
    }

    @Override
    public void openInventory(Inventory inventory) {
        handle.addWindow(((NukkitInventory) inventory).getHandle());
    }

    @Override
    public boolean hasPermission(String node) {
        return handle.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(message);
    }

    @Override
    public boolean isOp() {
        return handle.isOp();
    }

    @Override
    public void updateInventory() {
        // TODO: Add API here
    }

    @Override
    public boolean isOnline() {
        return handle.isOnline();
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.values()[handle.getGamemode()];
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        handle.setGamemode(gameMode.getId());
    }

    @Override
    public Player getHandle() {
        return handle;
    }
}
