package org.battleplugins.api.sponge.inventory.entity;

import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.inventory.entity.PlayerInventory;
import org.battleplugins.api.sponge.entity.living.SpongeHuman;
import org.battleplugins.api.sponge.inventory.SpongeInventory;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.Optional;

public class SpongePlayerInventory extends SpongeInventory<CarriedInventory<? extends Carrier>> implements PlayerInventory {

    private CarriedInventory<Player> inventory;

    public SpongePlayerInventory(CarriedInventory<? extends Carrier> inventory) {
        super(inventory);

        if (inventory.getCarrier().isPresent() && inventory.getCarrier().get() instanceof Player)
            this.inventory = (CarriedInventory<Player>) inventory;
        else
            throw new IllegalArgumentException("Inventory carrier is not a player!");
    }

    @Override
    public Optional<SpongeItemStack> getItemInMainHand() {
        return inventory.getCarrier().get().getItemInHand(HandTypes.MAIN_HAND).map(SpongeItemStack::new);
    }

    @Override
    public Optional<SpongeItemStack> getItemInOffHand() {
        return inventory.getCarrier().get().getItemInHand(HandTypes.OFF_HAND).map(SpongeItemStack::new);
    }

    @Override
    public Optional<SpongeItemStack> getHelmet() {
        return inventory.getCarrier().get().getHelmet().map(SpongeItemStack::new);
    }

    @Override
    public Optional<SpongeItemStack> getChestplate() {
        return inventory.getCarrier().get().getChestplate().map(SpongeItemStack::new);
    }

    @Override
    public Optional<SpongeItemStack> getLeggings() {
        return inventory.getCarrier().get().getLeggings().map(SpongeItemStack::new);
    }

    @Override
    public Optional<SpongeItemStack> getBoots() {
        return inventory.getCarrier().get().getBoots().map(SpongeItemStack::new);
    }

    @Override
    public Human getCarrier() {
        return new SpongeHuman<>(inventory.getCarrier().get());
    }
}
