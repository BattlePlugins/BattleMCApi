package org.battleplugins.sponge.inventory.entity;

import org.battleplugins.entity.living.HumanEntity;
import org.battleplugins.sponge.entity.living.SpongeHumanEntity;
import org.battleplugins.sponge.inventory.SpongeInventory;
import org.battleplugins.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.Optional;

public class SpongePlayerInventory extends SpongeInventory<CarriedInventory<? extends Carrier>> implements org.battleplugins.inventory.entity.PlayerInventory {

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
    public HumanEntity getCarrier() {
        return new SpongeHumanEntity<>(inventory.getCarrier().get());
    }
}
