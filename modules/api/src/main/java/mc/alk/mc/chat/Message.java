package mc.alk.mc.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCPlayer;

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

    public abstract void sendMessage(MCPlayer player);

    public static Message getDefaultPlatformMessage() {
        return MCPlatform.getPlatform().getDefaultPlatformMessage();
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
