package mc.alk.mc.inventory;

import mc.alk.mc.MCPlatform;

import java.util.Map;

public interface MCItemStack {

    void setType(String type);
    String getType();

    void setDataValue(short value);
    short getDataValue();

    void setQuantity(int quantity);
    int getQuantity();

    Map<String, Integer> getEnchantments();
    void addEnchantment(String ench, int level);

    String getCommonName();
    String getFormattedCommonName();

    MCItemStack clone();
    int isSpecial();

    boolean hasItemMeta();
    MCItemMeta getItemMeta();

    static MCItemStack getDefaultPlatformItemStack() {
        return MCPlatform.getPlatform().getDefaultPlatformItemStack();
    }
}
