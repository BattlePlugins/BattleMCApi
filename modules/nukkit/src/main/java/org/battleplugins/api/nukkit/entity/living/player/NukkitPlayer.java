package org.battleplugins.api.nukkit.entity.living.player;

import cn.nukkit.Player;

import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.hand.Hands;
import org.battleplugins.api.entity.living.player.gamemode.GameMode;
import org.battleplugins.api.entity.living.player.gamemode.GameModes;
import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.nukkit.entity.living.NukkitHuman;
import org.battleplugins.api.nukkit.inventory.NukkitInventory;
import org.battleplugins.api.world.Location;

import java.util.Optional;
import java.util.OptionalLong;

public class NukkitPlayer extends NukkitHuman<Player> implements org.battleplugins.api.entity.living.player.Player {

    public NukkitPlayer(Player player){
        super(player);
    }

    @Override
    public Optional<org.battleplugins.api.entity.living.player.Player> getPlayer() {
        if (!isOnline())
            return Optional.empty();

        return Optional.of(this);
    }

    @Override
    public OptionalLong getFirstPlayed() {
        return OptionalLong.of(handle.getFirstPlayed());
    }

    @Override
    public OptionalLong getLastPlayed() {
        return OptionalLong.of(handle.getLastPlayed());
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle.hasPlayedBefore();
    }

    @Override
    public Optional<Location> getBedSpawnLocation() {
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
        return GameModes.values()[handle.getGamemode()];
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        handle.setGamemode(gameMode.getId());
    }

    @Override
    public Hand getHand() {
        return Hands.MAIN_HAND; // no support for this in Nukkit :(
    }

    @Override
    public Player getHandle() {
        return handle;
    }
}
