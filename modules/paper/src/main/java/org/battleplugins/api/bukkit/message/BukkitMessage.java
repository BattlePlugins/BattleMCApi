package org.battleplugins.api.bukkit.message;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.message.Message;

/**
 * Bukkit does not have support for hoverable/clickable
 * messages unless it's through packets directly.
 */
public class BukkitMessage extends Message {

    public BukkitMessage() {
        super();
    }

    public BukkitMessage(String message) {
        super(message);
    }

    @Override
    public void sendMessage(Player player) {
        player.sendMessage(message);
    }
}
