package mc.alk.bukkit.inventory;

import mc.alk.mc.inventory.MCItemMeta;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BukkitItemMeta extends MCWrapper<ItemMeta> implements MCItemMeta {

    protected BukkitItemMeta(ItemMeta handle) {
        super(handle);
    }

    @Override
    public String getDisplayName() {
        return handle.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        handle.setDisplayName(displayName);
    }

    @Override
    public List<String> getLore() {
        return handle.getLore();
    }

    @Override
    public void setLore(List<String> lore) {
        handle.setLore(lore);
    }

    @Override
    public int getCustomModelData() {
        return handle.getCustomModelData();
    }

    @Override
    public void setCustomModelData(int modelData) {
        handle.setCustomModelData(modelData);
    }

    @Override
    public boolean isUnbreakable() {
        return handle.isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        handle.setUnbreakable(unbreakable);
    }
}
