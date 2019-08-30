package mc.alk.mc.chat;

import mc.alk.mc.MCPlatform;

public class MessageBuilder {

    private Message message;

    public MessageBuilder() {
        this.message = MCPlatform.getPlatformMessage();
    }

    public MessageBuilder(String message) {
        this.message = MCPlatform.getPlatformMessage();
        this.message.message = message;
    }

    public MessageBuilder(Message message) {
        this.message = message;
    }

    public MessageBuilder setMessage(Message message) {
        this.message = message;
        return this;
    }

    public MessageBuilder setMessage(String message) {
        this.message.message = message;
        return this;
    }

    public MessageBuilder setClickAction(ClickAction clickAction) {
        this.message.clickAction = clickAction;
        return this;
    }

    public MessageBuilder setClickValue(String clickValue) {
        this.message.clickValue = clickValue;
        return this;
    }

    public MessageBuilder setHoverAction(HoverAction hoverAction) {
        this.message.hoverAction = hoverAction;
        return this;
    }

    public MessageBuilder setHoverMessage(String hoverMessage) {
        this.message.hoverMessage = hoverMessage;
        return this;
    }

    public Message build() {
        return message;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }
}
