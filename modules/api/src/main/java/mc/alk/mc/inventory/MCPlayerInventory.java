package mc.alk.mc.inventory;

public interface MCPlayerInventory extends MCInventory {

    MCItemStack getItemInMainHand();
    MCItemStack getItemInOffHand();

    MCItemStack getHelmet();
    MCItemStack getChestplate();
    MCItemStack getLeggings();
    MCItemStack getBoots();
}
