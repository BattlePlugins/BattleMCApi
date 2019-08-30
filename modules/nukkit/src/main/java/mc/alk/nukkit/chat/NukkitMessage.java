package mc.alk.nukkit.chat;

import mc.alk.mc.MCPlayer;
import mc.alk.mc.chat.Message;

public class NukkitMessage extends Message {

    public NukkitMessage() {
        super();
    }

    public NukkitMessage(String message) {
        super(message);
    }

    @Override
    public void sendMessage(MCPlayer player) {
        // Nukkit has no support for clickable/hoverable messages
        player.sendMessage(message);
    }
}
