package mc.alk.sponge;

import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.sponge.command.SpongeCommandSender;
import mc.alk.sponge.inventory.SpongeInventory;

import org.spongepowered.api.entity.living.player.Player;

public class SpongePlayer extends SpongeCommandSender implements MCPlayer {

    private Player player;
    private String name;

    public SpongePlayer(Player player) {
        super(player);

        this.player = player;
        this.name = player.getName();
    }

    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(player.getWorld());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return player.getName();
    }

    @Override
    public MCInventory getInventory() {
        return new SpongeInventory(player.getInventory());
    }

    @Override
    public void updateInventory() {
        // TODO: Add API here
    }
}
