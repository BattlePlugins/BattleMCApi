package org.battleplugins.nukkit.inventory;

import org.battleplugins.inventory.Inventory;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.nukkit.inventory.virtual.VirtualChestInventory;
import org.battleplugins.nukkit.inventory.virtual.VirtualDoubleChestInventory;

public class NukkitInventoryBuilder implements Inventory.Builder {

    private String name;
    private int size;
    private ItemStack[] contents = new ItemStack[54];

    @Override
    public NukkitInventoryBuilder fromInventory(Inventory inventory, String name) {
        this.name = name;
        this.size = ((NukkitInventory) inventory).getHandle().getSize();
        this.contents = inventory.getContents();
        return this;
    }

    @Override
    public NukkitInventoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public NukkitInventoryBuilder size(int size) {
        this.size = size;
        if (this.contents.length > size) {
            throw new IllegalArgumentException("Current inventory contents are larger than the set inventory size!");
        }

        ItemStack[] contents = this.contents.clone();
        System.arraycopy(contents, 0, this.contents, 0, contents.length);
        return this;
    }

    @Override
    public NukkitInventoryBuilder item(int slot, ItemStack item) {
        if (slot > size) {
            throw new IllegalArgumentException("Slot is greater than the inventory size!");
        }
        this.contents[slot] = item;
        return this;
    }

    @Override
    public NukkitInventoryBuilder contents(ItemStack[] contents) {
        if (contents.length > size) {
            throw new IllegalArgumentException("Given contents are larger than the inventory size!");
        }
        this.contents = contents;
        return null;
    }

    @Override
    public NukkitInventory build() {
        return build(true);
    }

    public NukkitInventory build(boolean cancelled) {
        // Nukkit on its own does not have support for virtual inventories
        // So instead, we have to use some hacky methods and packets to create this
        // However, they can only be 27 slots (3 rows) or 54 slots (6 rows) in size
        VirtualChestInventory inventory = new VirtualChestInventory(null, name);
        if (size > 27) {
            inventory = new VirtualDoubleChestInventory(null, name);
        }

        inventory.addListener(event -> event.setCancelled(true));

        NukkitInventory nukkitInventory = new NukkitInventory<>(inventory);
        nukkitInventory.setContents(this.contents);
        return nukkitInventory;
    }
}
