package mc.alk.nukkit.inventory;

import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCPlayerInventory;

public class NukkitPlayerInventory extends NukkitInventory implements MCPlayerInventory {

	private PlayerInventory inventory;

	public NukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public MCItemStack getItemInMainHand() {
		return new NukkitItemStack(inventory.getItemInHand());
	}

	@Override
	public MCItemStack getItemInOffHand() {
		return new NukkitItemStack(Item.get(Item.AIR));
	}

	@Override
	public MCItemStack getHelmet() {
		return new NukkitItemStack(inventory.getHelmet());
	}

	@Override
	public MCItemStack getChestplate() {
		return new NukkitItemStack(inventory.getChestplate());
	}

	@Override
	public MCItemStack getLeggings() {
		return new NukkitItemStack(inventory.getLeggings());
	}

	@Override
	public MCItemStack getBoots() {
		return new NukkitItemStack(inventory.getBoots());
	}

	@Override
	public PlayerInventory getNukkitInventory() {
		return inventory;
	}
}
