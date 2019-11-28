package org.battleplugins.sponge.util;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.property.item.UseLimitProperty;
import org.spongepowered.api.item.inventory.ItemStack;

public class SpongeInventoryUtil {

    public static String getFormattedCommonName(ItemStack is) {
        short datavalue = is.get(Keys.ITEM_DURABILITY).orElse(0).shortValue();

        String iname = "";
        try {
            int maxDurability = 0;
            if (is.getType().getDefaultProperty(UseLimitProperty.class).isPresent())
                maxDurability = is.getType().getDefaultProperty(UseLimitProperty.class).get().getValue();

            iname = formatCommonName(is.getType().getName().toLowerCase()) + " (" + (maxDurability - datavalue) + "/" + maxDurability + ")";
        } catch (Exception e){
            System.err.println("Error getting commonName type=" + is + "   iname=" + iname + "   datavalue=" + datavalue);
            e.printStackTrace();
        }
        return iname;
    }

    private static String formatCommonName(String name) {
        name = name.toLowerCase().replace("_", " ");
        name = name.replace("minecraft:", "");

        String[] words = name.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }
}
