package mc.alk.sponge.inventory;

import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;

public class SpongePlayerInventory extends SpongeInventory {

    private PlayerInventory inventory;

    public SpongePlayerInventory(PlayerInventory inventory) {
        super(inventory);

        this.inventory = inventory;
    }

    @Override
    public Inventory getSpongeInventory() {
        return inventory;
    }
}
