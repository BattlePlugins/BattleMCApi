package mc.alk.nukkit.inventory.virtual;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;

/**
 * Allows for creation of virtual "fake" inventories.
 *
 * Code adapted from: https://github.com/NukkitX/FakeInventories/
 */
public class VirtualSlotChangeEvent implements Cancellable {

    private final Player player;
    private final VirtualInventory inventory;
    private final SlotChangeAction action;
    private boolean cancelled = false;

    public VirtualSlotChangeEvent(Player player, VirtualInventory inventory, SlotChangeAction action) {
        this.player = player;
        this.inventory = inventory;
        this.action = action;
    }

    public Player getPlayer() {
        return player;
    }

    public VirtualInventory getInventory() {
        return inventory;
    }

    public SlotChangeAction getAction() {
        return action;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled() {
        this.cancelled = true;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}