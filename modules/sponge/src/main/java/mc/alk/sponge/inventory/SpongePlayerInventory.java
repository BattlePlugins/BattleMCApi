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
    public SpongeItemStack getItemInMainHand() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getItemInHand(HandTypes.MAIN_HAND).get())).orElse(null);
    }

    @Override
    public SpongeItemStack getItemInOffHand() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getItemInHand(HandTypes.OFF_HAND).get())).orElse(null);
    }

    @Override
    public SpongeItemStack getHelmet() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getHelmet().get())).orElse(null);
    }

    @Override
    public SpongeItemStack getChestplate() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getChestplate().get())).orElse(null);
    }

    @Override
    public SpongeItemStack getLeggings() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getLeggings().get())).orElse(null);
    }

    @Override
    public SpongeItemStack getBoots() {
        Optional<Player> player = (Optional<Player>) inventory.getCarrier();
        return player.map(value -> new SpongeItemStack(value.getBoots().get())).orElse(null);
    }
}
