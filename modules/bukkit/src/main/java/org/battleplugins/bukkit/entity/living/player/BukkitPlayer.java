package org.battleplugins.bukkit.entity.living.player;

import org.battleplugins.bukkit.world.BukkitLocation;
import org.battleplugins.bukkit.entity.living.BukkitHumanEntity;
import org.battleplugins.bukkit.inventory.BukkitInventory;

import org.battleplugins.entity.living.player.gamemode.GameMode;
import org.battleplugins.entity.living.player.gamemode.GameModes;
import org.battleplugins.inventory.Inventory;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.OptionalLong;

public class BukkitPlayer extends BukkitHumanEntity<Player> implements org.battleplugins.entity.living.player.Player {

    public BukkitPlayer(Player player) {
        super(player);
    }

    @Override
    public Optional<BukkitPlayer> getPlayer() {
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
    public Optional<BukkitLocation> getBedSpawnLocation() {
        return Optional.ofNullable(handle.getBedSpawnLocation()).map(BukkitLocation::new);
    }

    @Override
    public String getDisplayName() {
        return handle.getDisplayName();
    }

    @Override
    public void openInventory(Inventory inventory) {
        handle.openInventory(((BukkitInventory) inventory).getHandle());
    }

    @Override
    public boolean isOp() {
        return handle.isOp();
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
    public void updateInventory() {
        handle.updateInventory();
    }

    @Override
    public boolean isOnline() {
        return handle.isOnline();
    }

    @Override
    public GameMode getGameMode() {
        return GameModes.values()[handle.getGameMode().ordinal()];
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        handle.setGameMode(org.bukkit.GameMode.values()[gameMode.getId()]);
    }
}
