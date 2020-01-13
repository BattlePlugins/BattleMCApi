package org.battleplugins.api.nukkit.inventory.entity;

import cn.nukkit.inventory.PlayerInventory;

import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.nukkit.inventory.NukkitInventory;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.util.Optional;

public class NukkitPlayerInventory extends NukkitInventory<PlayerInventory> implements org.battleplugins.api.inventory.entity.PlayerInventory {

	private Human carrier;

	public NukkitPlayerInventory(Human carrier, PlayerInventory inventory) {
		super(inventory);

		this.carrier = carrier;
	}

	@Override
	public Optional<ItemStack> getItemInMainHand() {
		return Optional.ofNullable(handle.getItemInHand()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getItemInOffHand() {
		return Optional.empty(); // no support... yet (open PR atm)
	}

	@Override
	public Optional<ItemStack> getHelmet() {
		return Optional.ofNullable(handle.getHelmet()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getChestplate() {
		return Optional.ofNullable(handle.getChestplate()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getLeggings() {
		return Optional.ofNullable(handle.getLeggings()).map(NukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getBoots() {
		return Optional.ofNullable(handle.getBoots()).map(NukkitItemStack::new);
	}

	@Override
	public Human getCarrier() {
		return carrier;
	}
}
