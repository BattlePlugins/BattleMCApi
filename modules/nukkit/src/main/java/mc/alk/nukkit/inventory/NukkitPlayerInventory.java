package mc.alk.nukkit.inventory;

import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCPlayerInventory;

import java.util.Optional;

public class NukkitPlayerInventory extends NukkitInventory implements MCPlayerInventory {

	private PlayerInventory inventory;

	public NukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public Optional<NukkitItemStack> getItemInMainHand() {
		return Optional.ofNullable(inventory.getItemInHand()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<NukkitItemStack> getItemInOffHand() {
		return Optional.empty(); // no support... yet (open PR atm)
	}

	@Override
	public Optional<NukkitItemStack> getHelmet() {
		return Optional.ofNullable(inventory.getHelmet()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<NukkitItemStack> getChestplate() {
		return Optional.ofNullable(inventory.getChestplate()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<NukkitItemStack> getLeggings() {
		return Optional.ofNullable(inventory.getLeggings()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<NukkitItemStack> getBoots() {
		return Optional.ofNullable(inventory.getBoots()).map(NukkitItemStack::new);
	}
}
