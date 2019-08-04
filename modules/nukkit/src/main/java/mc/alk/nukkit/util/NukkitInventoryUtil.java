package mc.alk.nukkit.util;

import cn.nukkit.Player;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

import mc.alk.mc.util.MCInventoryUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alkarin
 *
 */
public class NukkitInventoryUtil implements MCInventoryUtil {

    public static int getItemAmountFromInventory(Inventory inv, Item is) {
        return getItemAmount(inv.getContents().values(), is);
    }

    public static boolean sameItem(final Item is1, final Item is2, boolean checkDura) {
        if (is1 == null || is2 == null)
            return false;

        if (is1.getId() != is2.getId())
            return false;

        if (checkDura && (is1.getDamage() != -1 && is1.getDamage() != is2.getDamage()))
            return false;

        final Enchantment[] e1 = is1.getEnchantments();
        final Enchantment[] e2 = is2.getEnchantments();
        return e1.length == e2.length;
    }

    public static int getItemAmount(Collection<Item> items, Item is) {
        boolean checkDurability = true;
        int count = 0;
        for (Item item : items) {
            if (sameItem(item, is, checkDurability) && item.getCount() > 0) {
                count += item.getCount();
            }
        }
        return count;
    }

    /// Checks if there is enough free space in inventory
    public static boolean checkFreeSpace(Inventory inv, Item is, int left) {
        return checkFreeSpace(inv.getContents().values(), is, left);
    }

    public static boolean checkFreeSpace(Collection<Item> contents, Item is, int left) {
        int maxStack = is.getMaxStackSize();
        for (Item curitem : contents) {
            if (left <= 0) {
                return true;
            }
            if (curitem == null || curitem.getId() == 0) {
                left = left - maxStack;
                continue;
            }

            if (!sameItem(curitem, is, true))
                continue;

            int amount = curitem.getCount();
            if (amount < maxStack) {
                left = left - (maxStack - amount);
            }
        }
        return left <= 0;
    }

    public static int amountFreeSpace(Collection<Item> contents, Item is, int left) {
        int maxStack = is.getMaxStackSize();
        for (Item curitem : contents) {
            if (curitem == null) {
                left = left - maxStack;
                continue;
            }
            if (!sameItem(curitem, is, true))
                continue;
            int amount = curitem.getCount();
            if (amount < maxStack) {
                left = left - (maxStack - amount);
            }
        }
        return -left;
    }

    //Checks if there is enough free space in inventory
    public static int amountFreeSpace(Inventory inv, Item is, int left) {
        return amountFreeSpace(inv.getContents().values(), is, left);
    }

    public static void addItemToInventory(Player player, Item itemStack, int stockAmount) {
        addItemToInventory(player.getInventory(), itemStack, stockAmount);
    }

    ///Adds item to inventory
    public static void addItemToInventory(Inventory inv, Item is, int left) {
        int maxStackSize = is.getMaxStackSize();
        if (left <= maxStackSize) {
            is.setCount(left);
            inv.addItem(is);
            return;
        }

        if (maxStackSize != 64) {
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < Math.ceil(left / maxStackSize); i++) {
                if (left < maxStackSize) {
                    is.setCount(left);
                    items.add(is);
                    return;
                } else {
                    is.setCount(maxStackSize);
                    items.add(is);
                }
            }
            Object[] iArray = items.toArray();
            for (Object o : iArray) {
                inv.addItem((Item) o);
            }
        } else {
            inv.addItem(is);
        }
    }

    public static String getTypeFromId(int id) {
        // This is a hacky method, but Nukkit materials are terribly coded..
        // so what else can you do?
        try {
            for (Field field : Item.class.getFields()) {
                Item item = Item.fromString(field.getName());
                if (item == null)
                    continue;

                if (id == item.getId())
                    return field.getName();
            }
        } catch (Exception ex) {
            /* do nothing */
        }

        return String.valueOf(id);
    }
}
