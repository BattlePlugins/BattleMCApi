package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCPlayerInventory;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

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
    public MCItemStack getItemInMainHand() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getItemInHand(HandTypes.MAIN_HAND).get());
    }

    @Override
    public MCItemStack getItemInOffHand() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getItemInHand(HandTypes.OFF_HAND).get());
    }

    @Override
    public MCItemStack getHelmet() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getHelmet().get());
    }

    @Override
    public MCItemStack getChestplate() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getChestplate().get());
    }

    @Override
    public MCItemStack getLeggings() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getLeggings().get());
    }

    @Override
    public MCItemStack getBoots() {
        Player player = inventory.getCarrier().get();
        return new SpongeItemStack(player.getBoots().get());
    }


    @Override
    public Inventory getSpongeInventory() {
        return inventory;
    }
}
