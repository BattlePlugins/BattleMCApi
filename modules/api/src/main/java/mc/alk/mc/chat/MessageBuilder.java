package mc.alk.mc.chat;

public class MessageBuilder {

    private Message message;

    public MessageBuilder() {
        this.message = Message.getDefaultPlatformMessage();
    }

    public MessageBuilder(String message) {
        this();
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
