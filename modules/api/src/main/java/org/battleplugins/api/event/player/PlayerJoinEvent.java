package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.message.Message;

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
    Message getJoinMessage();

    /**
     * Sets the {@link Player} message sent when the
     * player joins the server
     *
     * @param joinMessage the joinMessage
     */
    void setJoinMessage(Message joinMessage);
}
