package mc.alk.nukkit.inventory.fakeinventory;

/**
 * Allows for creation of virtual "fake" inventories.
 *
 * Code adapted from: https://github.com/NukkitX/FakeInventories/
 */
public interface VirtualInventoryClickHandler {

    void onSlotChange(VirtualSlotChangeEvent event);
}
