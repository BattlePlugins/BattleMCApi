package org.battleplugins.api.sponge.entity.living.player;

import org.battleplugins.api.entity.living.player.gamemode.GameMode;
import org.battleplugins.api.entity.living.player.gamemode.GameModes;
import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.sponge.entity.living.SpongeHuman;
import org.battleplugins.api.sponge.inventory.SpongeInventory;
import org.battleplugins.api.sponge.util.SpongeUtil;
import org.battleplugins.api.world.Location;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.RespawnLocation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalLong;

public class SpongePlayer extends SpongeHuman<Player> implements org.battleplugins.api.entity.living.player.Player {

    public SpongePlayer(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<org.battleplugins.api.entity.living.player.Player> getPlayer() {
        if (!handle.isOnline())
            return Optional.empty();

        return Optional.of(this);
    }

    @Override
    public OptionalLong getFirstPlayed() {
        return handle.get(Keys.FIRST_DATE_PLAYED).map(value -> OptionalLong.of(value.toEpochMilli()))
                .orElse(OptionalLong.empty());
    }

    @Override
    public OptionalLong getLastPlayed() {
        return handle.get(Keys.LAST_DATE_PLAYED).map(value -> OptionalLong.of(value.toEpochMilli()))
                .orElse(OptionalLong.empty());
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle.hasPlayedBefore();
    }

    @Override
    public Optional<Location> getBedSpawnLocation() {
        RespawnLocation respawnLoc = handle.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()).get(handle.getWorldUniqueId().orElse(null));
        if (respawnLoc != null && respawnLoc.isForced()) {
            return respawnLoc.asLocation().map(SpongeUtil::fromSpongeLocation);
        }
        return Optional.empty();
    }

    @Override
    public String getDisplayName() {
        return handle.getDisplayNameData().displayName().get().toPlain();
    }

    @Override
    public void openInventory(Inventory inventory) {
        handle.openInventory(((SpongeInventory) inventory).getHandle());
    }

    @Override
    public boolean hasPermission(String node) {
        return handle.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(Text.of(message));
    }

    @Override
    public boolean isOp() {
        return false;
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
        org.spongepowered.api.entity.living.player.gamemode.GameMode spongeGamemode = handle.gameMode().get();
        return Arrays.stream(GameModes.values())
                .filter(mode -> mode.getKey().getKey().equals(spongeGamemode.getId()))
                .findFirst().orElse(GameModes.SURVIVAL);
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        handle.offer(Keys.GAME_MODE, Sponge.getRegistry().getType(org.spongepowered.api.entity.living.player.gamemode.GameMode.class,
                gameMode.getKey().getKey().toUpperCase()).orElse(org.spongepowered.api.entity.living.player.gamemode.GameModes.SURVIVAL));
    }
}
