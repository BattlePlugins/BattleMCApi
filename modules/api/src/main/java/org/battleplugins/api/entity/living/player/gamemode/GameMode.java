package org.battleplugins.api.entity.living.player.gamemode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.battleplugins.api.util.Identifier;

/**
 * Represents a game mode.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GameMode {

    /**
     * The id of the gamemode
     *
     * @return the id of the gamemode
     */
    private int id;

    /**
     * The {@link Identifier} of the gamemode
     *
     * @return the identifier of the gamemode
     */
    private Identifier identifier;
}
