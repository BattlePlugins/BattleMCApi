package mc.alk.mc.command;

public interface MCCommandExecutor {

    boolean onCommand(MCCommandSender sender, MCCommand command, String label, String[] args);
}
