package org.battleplugins.api.sponge.inventory;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.util.Optional;

public class SpongeInventory<T extends Inventory> extends MCWrapper<T> implements org.battleplugins.api.inventory.Inventory {

    public SpongeInventory(T inventory) {
        super(inventory);
    }

    @Override
    public void addItem(ItemStack itemStack) {
        handle.set(((SpongeItemStack) itemStack).getHandle());
    }

    @Override
    public void removeItem(ItemStack itemStack) {
        handle.query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(((SpongeItemStack) itemStack).getHandle())).poll();
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        handle.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotIndex.of(slot))).offer(((SpongeItemStack) item).getHandle());
    }

    @Override
    public Optional<SpongeItemStack> getItem(int slot) {
        return handle.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(slot))).peek().map(SpongeItemStack::new);
    }

    @Override
    public int getItemAmount(ItemStack itemStack) {
        return itemStack.getQuantity();
    }

    @Override
    public int freeSpaceAfter(ItemStack itemStack) {
        // TODO: Add API here
        return 0;
    }

    @Override
    public boolean canFit(ItemStack itemStack) {
        return handle.canFit(((SpongeItemStack) itemStack).getHandle());
    }

    @Override
    public SpongeItemStack[] getContents() {
        SpongeItemStack[] itemStacks = new SpongeItemStack[handle.capacity()];
        // Inventory for slots... what..? okay sure, whatever works ig
        int i = 0;
        for (Inventory inventory : handle.slots()) {
            itemStacks[i] = new SpongeItemStack(inventory.peek().orElse(org.spongepowered.api.item.inventory.ItemStack.builder().itemType(ItemTypes.AIR).build()));
            i++;
        }
        return itemStacks;
    }

    @Override
    public void clear() {
        handle.clear();
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
