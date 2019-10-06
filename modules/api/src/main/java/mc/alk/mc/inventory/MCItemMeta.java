package mc.alk.mc.inventory;

import java.util.List;

public interface MCItemMeta {

    default boolean hasDisplayName() {
        return getDisplayName() != null ;
    }

    String getDisplayName();
    void setDisplayName(String displayName);

    List<String> getLore();
    void setLore(List<String> lore);

    int getCustomModelData();
    void setCustomModelData(int modelData);

    boolean isUnbreakable();
    void setUnbreakable(boolean unbreakable);
}
