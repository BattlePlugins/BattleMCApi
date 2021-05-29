package org.battleplugins.api.event.player;

import net.kyori.adventure.text.Component;
import org.battleplugins.api.entity.living.player.Player;

/**
 * An event called when a {@link Player} joins the server.
 */
public interface PlayerJoinEvent extends PlayerEvent {

    /**
     * The {@link Player} message sent when the player
     * joins the server
     *
     * @return the join message
     */
    Component getJoinMessage();

    /**
     * Sets the {@link Player} message sent when the
     * player joins the server
     *
     * @param joinMessage the joinMessage
     */
    void setJoinMessage(Component joinMessage);
}
