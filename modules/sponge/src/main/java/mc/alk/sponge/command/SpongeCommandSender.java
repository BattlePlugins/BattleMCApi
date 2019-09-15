package mc.alk.sponge.command;

import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.util.MCWrapper;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

public abstract class SpongeCommandSender extends MCWrapper<CommandSource> implements MCCommandSender {

    public SpongeCommandSender(CommandSource sender) {
        super(sender);
    }

    @Override
    public boolean hasPermission(String node) {
        return handle.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(Text.of(message));
    }

    @Override
    public String getName() {
        return handle.getName();
    }
}
