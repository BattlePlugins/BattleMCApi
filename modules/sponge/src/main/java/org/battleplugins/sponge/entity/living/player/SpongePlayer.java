package org.battleplugins.sponge.entity.living.player;

import org.battleplugins.entity.living.player.GameMode;
import org.battleplugins.inventory.Inventory;
import org.battleplugins.sponge.world.SpongeLocation;
import org.battleplugins.sponge.entity.living.SpongeHumanEntity;
import org.battleplugins.sponge.inventory.SpongeInventory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.RespawnLocation;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;

public class SpongePlayer extends SpongeHumanEntity<Player> implements org.battleplugins.entity.living.player.Player {

    public SpongePlayer(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<SpongePlayer> getPlayer() {
        if (!handle.isOnline())
            return Optional.empty();

        return Optional.of(this);
    }

    @Override
    public long getFirstPlayed() {
        return handle.get(Keys.FIRST_DATE_PLAYED).orElse(Instant.now()).toEpochMilli();
    }

    @Override
    public long getLastPlayed() {
        return handle.get(Keys.LAST_DATE_PLAYED).orElse(Instant.now()).toEpochMilli();
    }

    @Override
    public boolean hasPlayedBefore() {
        return handle.hasPlayedBefore();
    }

    @Override
    public Optional<SpongeLocation> getBedSpawnLocation() {
        RespawnLocation respawnLoc = handle.get(Keys.RESPAWN_LOCATIONS).orElse(new HashMap<>()).get(handle.getWorldUniqueId().orElse(null));
        if (respawnLoc != null && respawnLoc.isForced()) {
            return respawnLoc.asLocation().map(SpongeLocation::new);
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
        return GameMode.valueOf(handle.getGameModeData().type().get().getId().toUpperCase());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        handle.getGameModeData().type().set(Sponge.getRegistry().getType(org.spongepowered.api.entity.living.player.gamemode.GameMode.class, gameMode.name().toUpperCase()).orElse(GameModes.SURVIVAL));
    }
}
