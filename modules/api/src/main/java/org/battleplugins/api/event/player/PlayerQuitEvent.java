package org.battleplugins.api.event.player;

import org.battleplugins.api.message.Message;

/**
 * An event called when a player quits the server.
 */
public interface PlayerQuitEvent extends PlayerEvent {

    /**
     * The quit message sent when the player
     * quits the server
     *
     * @return the quit message
     */
    Message getQuitMessage();

    /**
     * Sets the quit message sent when the
     * player quits the server
     *
     * @param quitMessage the quitMessage
     */
    void setQuitMessage(Message quitMessage);
}