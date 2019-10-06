package mc.alk.mc.chat;

import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCPlayer;

public abstract class Message {

    protected String message;

    protected ClickAction clickAction;
    protected HoverAction hoverAction;

    protected String hoverMessage;
    protected String clickValue;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ClickAction getClickAction() {
        return clickAction;
    }

    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    public HoverAction getHoverAction() {
        return hoverAction;
    }

    public void setHoverAction(HoverAction hoverAction) {
        this.hoverAction = hoverAction;
    }

    public String getHoverMessage() {
        return hoverMessage;
    }

    public void setHoverMessage(String hoverMessage) {
        this.hoverMessage = hoverMessage;
    }

    public String getClickValue() {
        return clickValue;
    }

    public void setClickValue(String clickValue) {
        this.clickValue = clickValue;
    }

    public abstract void sendMessage(MCPlayer player);

    public static Message getDefaultPlatformMessage() {
        return MCPlatform.getDefaultPlatformMessage();
    }
}
