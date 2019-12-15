package org.battleplugins.bukkit.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author alkarin
 */
public class BukkitInventoryUtil {

    public static int getItemAmountFromInventory(Inventory inv, ItemStack is) {
        return getItemAmount(inv.getContents(), is);
    }

    public static boolean sameItem(ItemStack is1, ItemStack is2, boolean checkDura) {
        if (is1 == null || is2 == null)
            return false;
        if (is1.getType() != is2.getType())
            return false;
        if (checkDura && (is1.getDurability() != -1 && is1.getDurability() != is2.getDurability()) )
            return false;

        return is1.getEnchantments().equals(is2.getEnchantments());
    }

    public static int getItemAmount(ItemStack[] items, ItemStack is) {
        int count = 0;
        for (ItemStack item : items) {
            if (sameItem(item, is, true) && item.getAmount() > 0) {
                count += item.getAmount();
            }
        }
        return count;
    }

    public static int amountFreeSpace(Inventory inv, ItemStack is, int left){
        int maxStack = is.getType().getMaxStackSize();
        for (ItemStack curitem : inv.getContents()) {
            if (curitem == null) {
                left = left - maxStack;
                continue;
            }
            if (!sameItem(curitem, is, true))
                continue;
            int amount = curitem.getAmount();
            if (amount < maxStack) {
                left = left - (maxStack - amount);
            }
        }
        return -left;
    }
}
