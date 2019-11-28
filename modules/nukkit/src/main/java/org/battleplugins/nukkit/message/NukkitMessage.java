package org.battleplugins.nukkit.message;

import org.battleplugins.entity.living.player.Player;
import org.battleplugins.message.Message;

public class NukkitMessage extends Message {

    public NukkitMessage() {
        super();
    }

    public NukkitMessage(String message) {
        super(message);
    }

    @Override
    public void sendMessage(Player player) {
        // Nukkit (bedrock edition) has no support for clickable/hoverable messages
        player.sendMessage(message);
    }
}
