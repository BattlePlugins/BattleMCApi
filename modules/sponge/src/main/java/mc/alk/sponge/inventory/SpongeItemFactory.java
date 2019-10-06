package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCItemFactory;
import mc.alk.mc.inventory.MCItemStack;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

public class SpongeItemFactory extends MCItemFactory {

    @Override
    public MCItemStack createMCItem(String text) {
        return createMCItem(text, (short) 0, 1);
    }

    @Override
    public MCItemStack createMCItem(String type, short value, int quantity) {
        SpongeItemStack spongeItemStack = new SpongeItemStack(ItemStack.builder().itemType(ItemTypes.AIR).build());
        spongeItemStack.setType(type);
        spongeItemStack.setDataValue(value);
        spongeItemStack.setQuantity(quantity);
        return spongeItemStack;
    }
}
