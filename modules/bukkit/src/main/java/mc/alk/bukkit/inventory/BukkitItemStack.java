package mc.alk.bukkit.inventory;

import java.util.HashMap;
import java.util.Map;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.util.MCWrapper;
import mc.euro.bukkitadapter.enchant.BattleEnchant;
import mc.euro.bukkitadapter.material.BattleMaterial;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class BukkitItemStack extends MCWrapper<ItemStack> implements MCItemStack {

	public BukkitItemStack(ItemStack itemStack) {
		super(itemStack == null ? new ItemStack(Material.AIR): itemStack);
	}

	@Override
	public void setType(String type) {
		handle.setType(BattleMaterial.fromString(type).parseMaterial());
	}

	@Override
	public String getType() {
		return handle.getType().name();
	}

	@Override
	public void setDataValue(short value) {
		handle.setDurability(value);
	}

	@Override
	public short getDataValue() {
		return handle.getDurability();
	}

	@Override
	public void setQuantity(int quantity) {
		handle.setAmount(quantity);
	}

	@Override
	public int getQuantity() {
		return handle.getAmount();
	}

	@Override
	public Map<String, Integer> getEnchantments() {
		Map<String, Integer> encs = new HashMap<String,Integer>();
		for (Map.Entry<Enchantment, Integer> entry : handle.getEnchantments().entrySet()) {
			encs.put(entry.getKey().getName(), entry.getValue());
		}
		return encs;
	}

	@Override
	public String getCommonName() {
		return handle.getType().name().toLowerCase();
	}

	@Override
	public String getFormattedCommonName() {
		return BukkitInventoryUtil.getFormattedCommonName(handle);
	}

	@Override
	public BukkitItemStack clone(){
		return new BukkitItemStack(handle.clone());
	}

	@Override
	public String toString(){
		return handle != null ? "["+ handle.getType() +":"+handle.getDurability() + " q="+
				getQuantity()+"]" : "null";
	}

	@Override
	public void addEnchantment(String ench, int level) {
		handle.addEnchantment(BattleEnchant.fromString(ench).parseEnchant(), level);
	}

	@Override
	public int isSpecial() {
		int special = 0;
//		ItemMeta im = itemStack.getItemMeta();
//		Map<Enchantment,Integer> map = itemStack.getEnchantments();
//		if (map != null){
//			for (Entry<Enchantment,Integer> entry : map.entrySet()){
////				entry.getKey().getId()
//			}
//		}

		return special;
	}

	@Override
	public boolean hasItemMeta() {
		return handle.getItemMeta() != null;
	}

	@Override
	public BukkitItemMeta getItemMeta() {
		return new BukkitItemMeta(handle, handle.getItemMeta());
	}
}
