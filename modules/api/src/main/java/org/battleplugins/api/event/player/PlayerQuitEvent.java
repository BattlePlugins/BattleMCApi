package org.battleplugins.api.event.player;

import net.kyori.adventure.text.Component;
import org.battleplugins.api.entity.living.player.Player;

/**
 * An event called when a {@link Player} quits the server.
 */
public interface PlayerQuitEvent extends PlayerEvent {

    /**
     * The quit message sent when the {@link Player}
     * quits the server
     *
     * @return the quit message
     */
    Component getQuitMessage();

    /**
     * Sets the quit message sent when the
     * {@link Player} quits the server
     *
     * @param quitMessage the quitMessage
     */
    void setQuitMessage(Component quitMessage);
}