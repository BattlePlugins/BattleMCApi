package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCPlayerInventory;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.Optional;

public class SpongePlayerInventory extends SpongeInventory implements MCPlayerInventory {

    private CarriedInventory<? extends Player> inventory;

    public SpongePlayerInventory(CarriedInventory<? extends Carrier> inventory) {
        super(inventory);

        if (inventory.getCarrier().isPresent() && inventory.getCarrier().get() instanceof Player)
            this.inventory = (CarriedInventory<Player>) inventory;
        else
            throw new IllegalArgumentException("Inventory carrier is not a player!");
    }

    @Override
    public Optional<SpongeItemStack> getItemInMainHand() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getItemInHand(HandTypes.MAIN_HAND).orElse(null)));
    }

    @Override
    public Optional<SpongeItemStack> getItemInOffHand() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getItemInHand(HandTypes.OFF_HAND).orElse(null)));
    }

    @Override
    public Optional<SpongeItemStack> getHelmet() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getHelmet().orElse(null)));
    }

    @Override
    public Optional<SpongeItemStack> getChestplate() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getChestplate().orElse(null)));
    }

    @Override
    public Optional<SpongeItemStack> getLeggings() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getLeggings().orElse(null)));
    }

    @Override
    public Optional<SpongeItemStack> getBoots() {
        return inventory.getCarrier().map(carrier -> new SpongeItemStack(carrier.getBoots().orElse(null)));
    }
}
