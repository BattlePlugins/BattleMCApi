package mc.alk.mc.inventory;

import java.util.Optional;

public interface MCPlayerInventory extends MCInventory {

    Optional<? extends MCItemStack> getItemInMainHand();
    Optional<? extends MCItemStack> getItemInOffHand();

    Optional<? extends MCItemStack> getHelmet();
    Optional<? extends MCItemStack> getChestplate();
    Optional<? extends MCItemStack> getLeggings();
    Optional<? extends MCItemStack> getBoots();
}
