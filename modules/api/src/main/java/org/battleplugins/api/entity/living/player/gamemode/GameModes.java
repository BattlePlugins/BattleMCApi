package org.battleplugins.api.entity.living.player.gamemode;

import org.battleplugins.api.util.Identifier;

/**
 * Holds all the valid GameModes.
 */
public class GameModes {

    public static final GameMode SURVIVAL = new GameMode(0, Identifier.minecraft("survival"));
    public static final GameMode CREATIVE = new GameMode(1, Identifier.minecraft("creative"));
    public static final GameMode ADVENTURE = new GameMode(2, Identifier.minecraft("adventure"));
    public static final GameMode SPECTATOR = new GameMode(3, Identifier.minecraft("spectator"));

    /**
     * An array of all the {@link GameMode}s
     *
     * @return an array of all the gamemodes
     */
    public static GameMode[] values() {
        return new GameMode[] {SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR};
    }
}
