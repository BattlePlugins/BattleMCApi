package mc.alk.bukkit.chat;

import mc.alk.mc.MCPlayer;
import mc.alk.mc.chat.Message;

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
    public void sendMessage(MCPlayer player) {
        player.sendMessage(message);
    }
}
