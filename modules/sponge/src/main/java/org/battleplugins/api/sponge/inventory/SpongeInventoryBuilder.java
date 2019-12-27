package org.battleplugins.api.sponge.inventory;

import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.plugin.Plugin;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

public class SpongeInventoryBuilder implements Inventory.Builder {

    private String name;
    private int size;
    private ItemStack[] contents = new ItemStack[54];

    @Override
    public SpongeInventoryBuilder fromInventory(Inventory inventory, String name) {
        this.name = name;
        this.size = ((SpongeInventory) inventory).getHandle().size();
        this.contents = inventory.getContents();
        return this;
    }

    @Override
    public SpongeInventoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public SpongeInventoryBuilder size(int size) {
        this.size = size;
        if (this.contents.length > size) {
            throw new IllegalArgumentException("Current inventory contents are larger than the set inventory size!");
        }

        ItemStack[] contents = this.contents.clone();
        System.arraycopy(contents, 0, this.contents, 0, contents.length);
        return this;
    }

    @Override
    public SpongeInventoryBuilder item(int slot, ItemStack item) {
        if (slot > size) {
            throw new IllegalArgumentException("Slot is greater than the inventory size!");
        }
        this.contents[slot] = item;
        return this;
    }

    @Override
    public SpongeInventoryBuilder contents(ItemStack[] contents) {
        if (contents.length > size) {
            throw new IllegalArgumentException("Given contents are larger than the inventory size!");
        }
        this.contents = contents;
        return null;
    }

    @Override
    public SpongeInventory build(Plugin plugin) {
        SpongeInventory inventory = new SpongeInventory<>(org.spongepowered.api.item.inventory.Inventory
                .builder()
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(name)))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension( 9, size / 9))
                .build(plugin));
        inventory.setContents(this.contents);
        return inventory;
    }
}
