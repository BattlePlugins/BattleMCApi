package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.util.MCWrapper;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.util.Optional;

public class SpongeInventory extends MCWrapper<Inventory> implements MCInventory {

    public SpongeInventory(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void addItem(MCItemStack... itemStacks) {
        for (MCItemStack itemStack : itemStacks) {
            handle.set(((SpongeItemStack) itemStack).getHandle());
        }
    }

    @Override
    public void removeItem(MCItemStack itemStack) {
        handle.query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(((SpongeItemStack) itemStack).getHandle())).poll();
    }

    @Override
    public void setItem(int slot, MCItemStack item) {
        handle.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotIndex.of(slot))).offer(((SpongeItemStack) item).getHandle());
    }

    @Override
    public Optional<SpongeItemStack> getItem(int slot) {
        return handle.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(slot))).peek().map(SpongeItemStack::new);
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
        return handle.canFit(((SpongeItemStack) itemStack).getHandle());
    }

    @Override
    public SpongeItemStack[] getContents() {
        SpongeItemStack[] itemStacks = new SpongeItemStack[handle.capacity()];
        // Inventory for slots... what..? okay sure, whatever works ig
        int i = 0;
        for (Inventory inventory : handle.slots()) {
            itemStacks[i] = new SpongeItemStack(inventory.peek().orElse(ItemStack.builder().itemType(ItemTypes.AIR).build()));
            i++;
        }
        return itemStacks;
    }

    @Override
    public void clear() {
        handle.clear();
    }
}
