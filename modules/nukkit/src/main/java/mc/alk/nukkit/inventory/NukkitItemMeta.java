package mc.alk.nukkit.inventory;

import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Due to how Nukkit handles item metadata, we instead
 * pass a Nukkit 'Item' in the constructor.
 */
public class NukkitItemMeta implements MCItemMeta {

    private Item item;

    protected NukkitItemMeta(Item item) {
        this.item = item;
    }

    @Override
    public Optional<String> getDisplayName() {
        if (!item.hasCustomName())
            return Optional.empty();

        return Optional.of(item.getCustomName());
    }

    @Override
    public void setDisplayName(String displayName) {
        item.setCustomName(displayName);
    }

    @Override
    public Optional<List<String>> getLore() {
        if (item.getLore().length == 0)
            return Optional.empty();

        return Optional.of(Arrays.asList(item.getLore()));
    }

    @Override
    public void setLore(List<String> lore) {
        item.setLore(lore.toArray(new String[0]));
    }

    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    public void setCustomModelData(int modelData) {
        // No support for this in Nukkit
    }

    @Override
    public boolean isUnbreakable() {
        return item.isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // No support for this in Nukkit
    }
}
