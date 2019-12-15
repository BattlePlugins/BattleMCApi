package org.battleplugins.sponge.world.block.entity;

import org.battleplugins.inventory.CarriedInventory;
import org.battleplugins.inventory.carrier.Carrier;
import org.battleplugins.sponge.inventory.SpongeCarriedInventory;
import org.battleplugins.sponge.inventory.SpongeInventory;
import org.battleplugins.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class SpongeChest extends SpongeBlockEntity<Chest> implements org.battleplugins.world.block.entity.Chest {

    public SpongeChest(Chest chest) {
        super(chest);
    }

    @Override
    public Optional<SpongeChest> getNeighborChest() {
        Location<World> loc = handle.getLocation();
        if (loc.getRelative(Direction.NORTH).getBlock().getType() == BlockTypes.CHEST)
            return loc.getRelative(Direction.NORTH).getTileEntity().map(chest -> new SpongeChest((Chest) chest));

        else if (loc.getRelative(Direction.SOUTH).getBlock().getType() == BlockTypes.CHEST)
            return loc.getRelative(Direction.SOUTH).getTileEntity().map(chest -> new SpongeChest((Chest) chest));

        else if (loc.getRelative(Direction.EAST).getBlock().getType() == BlockTypes.CHEST)
            return loc.getRelative(Direction.EAST).getTileEntity().map(chest -> new SpongeChest((Chest) chest));

        else if (loc.getRelative(Direction.WEST).getBlock().getType() == BlockTypes.CHEST)
            return loc.getRelative(Direction.WEST).getTileEntity().map(chest -> new SpongeChest((Chest) chest));

        return Optional.empty();
    }

    @Override
    public CarriedInventory<SpongeChest> getInventory() {
        return new SpongeCarriedInventory<>(handle.getInventory(), this);
    }
}
