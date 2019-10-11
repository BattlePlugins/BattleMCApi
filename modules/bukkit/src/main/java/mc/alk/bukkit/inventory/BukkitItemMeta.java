package mc.alk.bukkit.inventory;

import mc.alk.mc.inventory.MCItemMeta;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BukkitItemMeta extends MCWrapper<ItemMeta> implements MCItemMeta {

    private ItemStack itemStack;

    protected BukkitItemMeta(ItemStack itemStack, ItemMeta handle) {
        super(handle);

        this.itemStack = itemStack;
    }

    @Override
    public String getDisplayName() {
        return handle.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        handle.setDisplayName(displayName);
        itemStack.setItemMeta(handle);
    }

    @Override
    public List<String> getLore() {
        return handle.getLore();
    }

    @Override
    public void setLore(List<String> lore) {
        handle.setLore(lore);
        itemStack.setItemMeta(handle);
    }

    @Override
    public int getCustomModelData() {
        return handle.getCustomModelData();
    }

    @Override
    public void setCustomModelData(int modelData) {
        handle.setCustomModelData(modelData);
        itemStack.setItemMeta(handle);
    }

    @Override
    public boolean isUnbreakable() {
        return handle.isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        handle.setUnbreakable(unbreakable);
        itemStack.setItemMeta(handle);
    }
}
