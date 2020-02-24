package org.battleplugins.api.event.player;

import org.battleplugins.api.message.Message;

/**
 * An event called when a player joins the server.
 */
public interface PlayerJoinEvent extends PlayerEvent {

    /**
     * The join message sent when the player
     * joins the server
     *
     * @return the join message
     */
    Message getJoinMessage();

    /**
     * Sets the join message sent when the
     * player joins the server
     *
     * @param joinMessage the joinMessage
     */
    void setJoinMessage(Message joinMessage);
}
