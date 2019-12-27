package org.battleplugins.api.entity.living.player.gamemode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.battleplugins.api.util.NamespacedKey;

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
     * The {@link NamespacedKey} of the gamemode
     *
     * @return the namespaced key of the gamemode
     */
    private NamespacedKey key;
}
