package org.battleplugins.api.sponge.message;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.sponge.entity.living.player.SpongePlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class SpongeMessage extends Message {

    public SpongeMessage() {
        super();
    }

    public SpongeMessage(String message) {
        super(message);
    }

    @Override
    public void sendMessage(Player player) {
        Text.Builder builder = Text.builder(message);
        if (hoverAction != null && hoverMessage != null) {
            switch (hoverAction) {
                case SHOW_ITEM:
                    builder.onHover(TextActions.showItem(Sponge.getRegistry().getType(ItemType.class, hoverMessage).get().getTemplate()));
                    break;
                case SHOW_ACHIEVEMENT: // no support in sponge for this
                case SHOW_TEXT:
                    builder.onHover(TextActions.showText(Text.of(hoverMessage)));
                    break;
                case SHOW_ENTITY:
                    builder.onHover(TextActions.showEntity(UUID.randomUUID(), hoverMessage));
                    break;
            }
        }

        if (clickAction != null && clickValue != null) {
            switch (clickAction) {
                case OPEN_FILE:
                case OPEN_URL:
                    try {
                        builder.onClick(TextActions.openUrl(new URL(clickValue)));
                    } catch (MalformedURLException ex) {
                        /* do nothing */
                    }
                    break;
                case CHANGE_PAGE:
                    builder.onClick(TextActions.changePage(Integer.parseInt(clickValue)));
                    break;
                case RUN_COMMAND:
                    builder.onClick(TextActions.runCommand(clickValue));
                    break;
                case SUGGEST_COMMAND:
                    builder.onClick(TextActions.suggestCommand(clickValue));
                    break;
            }
        }

        SpongePlayer spongePlayer = (SpongePlayer) player;
        spongePlayer.getHandle().sendMessage(builder.build());
    }
}
