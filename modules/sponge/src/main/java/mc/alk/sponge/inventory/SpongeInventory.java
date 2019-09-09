package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

public class SpongeInventory implements MCInventory {

    private Inventory inventory;

    public SpongeInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void addItem(MCItemStack... itemStacks) {
        for (MCItemStack itemStack : itemStacks) {
            inventory.set(((SpongeItemStack) itemStack).getSpongeItemStack());
        }
    }

    @Override
    public void removeItem(MCItemStack itemStack) {
        inventory.query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(((SpongeItemStack) itemStack).getSpongeItemStack())).poll();
    }

    @Override
    public void setItem(int slot, MCItemStack item) {
        inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(slot))).offer(((SpongeItemStack) item).getSpongeItemStack());
    }

    @Override
    public MCItemStack getItem(int slot) {
        return new SpongeItemStack(inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(slot))).peek().get());
    }

    @Override
    public int getItemAmount(MCItemStack itemStack) {
        return itemStack.getQuantity();
    }

    @Override
    public int freeSpaceAfter(MCItemStack itemStack) {
        // TODO: Add API here
        return 0;
    }

    @Override
    public boolean canFit(MCItemStack itemStack) {
        return inventory.canFit(((SpongeItemStack) itemStack).getSpongeItemStack());
    }

    @Override
    public MCItemStack[] getContents() {
        MCItemStack[] itemStacks = new MCItemStack[inventory.capacity()];
        // Inventory for slots... what..? okay sure, whatever works ig
        int i = 0;
        for (Inventory inventory : inventory.slots()) {
            itemStacks[i] = new SpongeItemStack(inventory.peek().get());
            i++;
        }
        return itemStacks;
    }

    public Inventory getSpongeInventory() {
        return inventory;
    }
}
