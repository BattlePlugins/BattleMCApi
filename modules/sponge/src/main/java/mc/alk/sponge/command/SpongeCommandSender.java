package mc.alk.sponge.command;

import mc.alk.mc.command.MCCommandSender;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

public class SpongeCommandSender implements MCCommandSender {

    private CommandSource sender;

    public SpongeCommandSender(CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasPermission(String node) {
        return sender.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Text.of(message));
    }

    @Override
    public String getName() {
        return sender.getName();
    }
}
