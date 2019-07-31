package mc.alk.tests.blocks;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCChest;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;

import mc.alk.tests.TestBlock;
import mc.alk.tests.TestInventory;

public class TestChest extends TestBlock implements MCChest{
	TestInventory inventory = new TestInventory();

	public TestChest(MCLocation loc, MCItemStack... items) {
		super(loc, Material.CHEST.getId());
		if (items.length > 0)
			inventory.addItem(items);
	}

	@Override
	public MCItemStack[] getItems() {
		MCChest chest = getNeighborChest();
		if (chest != null){
			return (MCItemStack[]) ArrayUtils.addAll(inventory.getContents(),
					((TestChest)chest).getItemsFromNeighborChest());}
		return inventory.getContents();
	}

	private MCItemStack[] getItemsFromNeighborChest(){
		return inventory.getContents();
	}

	@Override
	public boolean isDoubleChest() {
		MCWorld w = this.getWorld();
		return (w.isType(w.getBlockAt(getX()-1, getY(), getZ()),MCChest.class) ||
				w.isType(w.getBlockAt(getX()+1, getY(), getZ()),MCChest.class) ||
				w.isType(w.getBlockAt(getX(), getY(), getZ()-1),MCChest.class) ||
				w.isType(w.getBlockAt(getX(), getY(), getZ()+1),MCChest.class)
				);
	}

	@Override
	public MCChest getNeighborChest() {
		return getNeighborChest(this);
	}

	public static MCChest getNeighborChest(MCBlock block) {
		MCWorld w = block.getWorld();
		MCBlock b = w.getBlockAt(block.getX()-1, block.getY(), block.getZ());
		if (w.isType(b,MCChest.class)){
			return (MCChest)b;}
		b = w.getBlockAt(block.getX()+1, block.getY(), block.getZ());
		if (w.isType(b,MCChest.class)){
			return (MCChest)b;}
		b = w.getBlockAt(block.getX(), block.getY(), block.getZ()-1);
		if (w.isType(b,MCChest.class)){
			return (MCChest)b;}
		b = w.getBlockAt(block.getX(), block.getY(), block.getZ()+1);
		if (w.isType(b,MCChest.class)){
			return (MCChest)b;}
		return null;
	}

	@Override
	public MCInventory getInventory() {
		return inventory;
	}
}
