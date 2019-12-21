package org.battleplugins.message;

import lombok.*;

import org.battleplugins.Platform;
import org.battleplugins.entity.living.player.Player;

import java.util.Optional;

/**
 * Represents a customizable message able to be sent to players.
 */
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Message {

    /**
     * The message to send
     *
     */
    @NonNull
    protected String message;

    /**
     * The {@link ClickAction} of the message
     */
    protected ClickAction clickAction;

    /**
     * The {@link HoverAction} of the message
     *
     */
    protected HoverAction hoverAction;

    /**
     * The hover message

     */
    protected String hoverMessage;

    /**
     * The click value

     */
    protected String clickValue;

    /**
     * Sends the message to the {@link Player}
     *
     * @param player the player to send the message to
     */
    public abstract void sendMessage(Player player);

    /**
     * The plain text of the message
     *
     * @return the plain text of the message
     */
    public String getPlainText() {
        return message;
    }

    /**
     * The {@link ClickAction} of the message
     *
     * @return the click action of the message
     */
    public Optional<ClickAction> getClickAction() {
        return Optional.ofNullable(clickAction);
    }

    /**
     * The {@link HoverAction} of the message
     *
     * @return the hover action of the message
     */
    public Optional<HoverAction> getHoverAction() {
        return Optional.ofNullable(hoverAction);
    }

    /**
     * The hover message
     *
     * @return the hover message
     */
    public Optional<String> getHoverMessage() {
        return Optional.ofNullable(hoverMessage);
    }

    /**
     * The click value
     *
     * @return the click value
     */
    public Optional<String> getClickValue() {
        return Optional.ofNullable(clickValue);
    }
}
