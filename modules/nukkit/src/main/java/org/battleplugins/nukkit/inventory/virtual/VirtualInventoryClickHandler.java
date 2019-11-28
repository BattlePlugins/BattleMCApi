package org.battleplugins.nukkit.inventory.virtual;

/**
 * Allows for creation of virtual "fake" inventories.
 *
 * Code adapted from: https://github.com/NukkitX/FakeInventories/
 */
public interface VirtualInventoryClickHandler {

    void onSlotChange(VirtualSlotChangeEvent event);
}
