package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

import org.spongepowered.api.item.inventory.Inventory;
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
        // TODO: Add API here
        return false;
    }

    @Override
    public MCItemStack[] getContents() {
        // TODO: Add API here
        return new MCItemStack[0];
    }

    public Inventory getSpongeInventory() {
        return inventory;
    }
}
