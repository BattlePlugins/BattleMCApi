package org.battleplugins.api.bukkit.entity.living.player;

import org.battleplugins.api.bukkit.entity.living.BukkitHuman;
import org.battleplugins.api.bukkit.inventory.BukkitInventory;

import org.battleplugins.api.bukkit.util.BukkitUtil;
import org.battleplugins.api.entity.living.player.gamemode.GameMode;
import org.battleplugins.api.entity.living.player.gamemode.GameModes;
import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.world.Location;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.OptionalLong;

public class BukkitPlayer extends BukkitHuman<Player> implements org.battleplugins.api.entity.living.player.Player {

    public BukkitPlayer(Player player) {
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
        return Optional.ofNullable(handle.getBedSpawnLocation()).map(BukkitUtil::fromBukkitLocation);
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
