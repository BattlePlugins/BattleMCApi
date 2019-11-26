package mc.alk.sponge.block;

import mc.alk.mc.block.MCChest;
import mc.alk.sponge.inventory.SpongeInventory;
import mc.alk.sponge.inventory.SpongeItemStack;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class SpongeChest extends SpongeBlock implements MCChest {

    private Chest chest;

    public SpongeChest(Chest chest) {
        super(BlockSnapshot.builder().blockState(chest.getBlock()).build());

        this.chest = chest;
    }

    @Override
    public boolean isDoubleChest() {
        return chest.getConnectedChests().size() > 0;
    }

    @Override
    public SpongeItemStack[] getItems() {
        SpongeItemStack[] items = new SpongeItemStack[chest.getInventory(Direction.NONE).totalItems()];

        int i = 0;
        for (Inventory slot : chest.getInventory(Direction.NONE)) {
            Optional<ItemStack> opStack = slot.peek();
            if (!opStack.isPresent())
                continue;

            ItemStack stack = opStack.get();
            items[i] = new SpongeItemStack(stack);
            i++;
        }

        return items;
    }
    @Override
    public Optional<SpongeChest> getNeighborChest() {
        Location<World> loc = handle.getLocation().get();
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
    public SpongeInventory getInventory() {
        return new SpongeInventory(chest.getInventory());
    }
}
