package org.battleplugins.api.entity.living.player.gamemode;

import org.battleplugins.api.util.NamespacedKey;

/**
 * Holds all the valid GameModes.
 */
public class GameModes {

    public static final GameMode SURVIVAL = new GameMode(0, NamespacedKey.minecraft("survival"));
    public static final GameMode CREATIVE = new GameMode(1, NamespacedKey.minecraft("creative"));
    public static final GameMode ADVENTURE = new GameMode(2, NamespacedKey.minecraft("adventure"));
    public static final GameMode SPECTATOR = new GameMode(3, NamespacedKey.minecraft("spectator"));

    /**
     * An array of all the {@link GameMode}s
     *
     * @return an array of all the gamemodes
     */
    public static GameMode[] values() {
        return new GameMode[] {SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR};
    }
}
