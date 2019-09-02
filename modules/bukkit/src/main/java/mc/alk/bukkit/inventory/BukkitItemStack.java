package mc.alk.bukkit.inventory;

import java.util.HashMap;
import java.util.Map;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.inventory.MCItemStack;
import mc.euro.bukkitadapter.enchant.BattleEnchant;
import mc.euro.bukkitadapter.material.BattleMaterial;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class BukkitItemStack implements MCItemStack {

	private ItemStack itemStack;

	public BukkitItemStack(ItemStack itemStack) {
		this.itemStack = itemStack == null ? new ItemStack(Material.AIR): itemStack;
	}

	@Override
	public void setType(String type) {
		itemStack.setType(BattleMaterial.fromString(type).parseMaterial());
	}

	@Override
	public String getType() {
		return itemStack.getType().name();
	}

	@Override
	public void setDataValue(short value) {
		itemStack.setDurability(value);
	}

	@Override
	public short getDataValue() {
		return itemStack.getDurability();
	}

	@Override
	public void setQuantity(int quantity) {
		itemStack.setAmount(quantity);
	}

	@Override
	public int getQuantity() {
		return itemStack.getAmount();
	}

	@Override
	public Map<String, Integer> getEnchantments() {
		Map<String, Integer> encs = new HashMap<String,Integer>();
		for (Map.Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			encs.put(entry.getKey().getName(), entry.getValue());
		}
		return encs;
	}

	@Override
	public boolean hasMetaData() {
		return itemStack.hasItemMeta();
	}

	@Override
	public String getCommonName() {
		return itemStack.getType().name().toLowerCase();
	}

	@Override
	public String getFormattedCommonName() {
		return BukkitInventoryUtil.getFormattedCommonName(itemStack);
	}

	@Override
	public MCItemStack clone(){
		return new BukkitItemStack(itemStack.clone());
	}

	@Override
	public String toString(){
		return itemStack != null ? "["+ itemStack.getType() +":"+itemStack.getDurability() + " q="+
				getQuantity()+"]" : "null";
	}

	@Override
	public void addEnchantment(String ench, int level) {
		itemStack.addEnchantment(BattleEnchant.fromString(ench).parseEnchant(), level);
	}

	public void addEnchantment(Enchantment enc, int level) {
		itemStack.addEnchantment(enc, level);
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

	public ItemStack getBukkitItemStack() {
		return itemStack;
	}
}
