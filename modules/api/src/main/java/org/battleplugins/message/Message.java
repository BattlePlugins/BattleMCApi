package org.battleplugins.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.battleplugins.Platform;
import org.battleplugins.entity.living.player.Player;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Message {

    @NonNull
    protected String message;

    protected ClickAction clickAction;
    protected HoverAction hoverAction;

    protected String hoverMessage;
    protected String clickValue;

    public abstract void sendMessage(Player player);

    private static Message getDefaultPlatformMessage() {
        return Platform.getPlatform().getDefaultPlatformMessage();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Message message;

        Builder() {
            this.message = Message.getDefaultPlatformMessage();
        }

        public Builder fromMessage(Message message) {
            this.message = message;
            return this;
        }

        public Builder message(String message) {
            this.message.setMessage(message);
            return this;
        }

        public Builder clickAction(ClickAction clickAction) {
            this.message.setClickAction(clickAction);
            return this;
        }

        public Builder clickValue(String clickValue) {
            this.message.setClickValue(clickValue);
            return this;
        }

        public Builder hoverAction(HoverAction hoverAction) {
            this.message.setHoverAction(hoverAction);
            return this;
        }

        public Builder hoverMessage(String hoverMessage) {
            this.message.setHoverMessage(hoverMessage);
            return this;
        }

        public Message build() {
            return message;
        }
    }
}
