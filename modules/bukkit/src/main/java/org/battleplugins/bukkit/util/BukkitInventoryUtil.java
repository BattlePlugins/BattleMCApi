package org.battleplugins.bukkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mc.euro.bukkitadapter.material.BattleMaterial;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author alkarin
 *
 */
public class BukkitInventoryUtil {

	private static Map<String, ItemStack> commonToStack = new HashMap<String, ItemStack>();
	private static Map<String, String> typeToCommon = new HashMap<String, String>();

	private static final Map<String, ItemStack> itemNames = new HashMap<String,ItemStack>();

	static {
		itemNames.put("light_gray_wool", BattleMaterial.LIGHT_GRAY_WOOL.parseItem());
		itemNames.put("stone_brick", BattleMaterial.STONE_BRICKS.parseItem());
		itemNames.put("mossy_stone", BattleMaterial.MOSSY_COBBLESTONE.parseItem());
		itemNames.put("mossy_smooth", BattleMaterial.MOSSY_STONE_BRICKS.parseItem());
		itemNames.put("cracked_stone", BattleMaterial.CRACKED_STONE_BRICKS.parseItem());
		itemNames.put("piston", BattleMaterial.PISTON.parseItem());
		itemNames.put("sticky_piston", BattleMaterial.STICKY_PISTON.parseItem());
		itemNames.put("long_grass", BattleMaterial.GRASS.parseItem());
		itemNames.put("fern", BattleMaterial.FERN.parseItem());
		itemNames.put("mycelium", BattleMaterial.MYCELIUM.parseItem());
		itemNames.put("nether_wart", BattleMaterial.NETHER_WART.parseItem());
		itemNames.put("redstone_lamp", BattleMaterial.REDSTONE_LAMP.parseItem());
		itemNames.put("redstone_torch", BattleMaterial.REDSTONE_TORCH.parseItem());
		itemNames.put("carrot", BattleMaterial.CARROT.parseItem());
		itemNames.put("carrots", BattleMaterial.CARROTS.parseItem());
		itemNames.put("carrot_seed", BattleMaterial.CARROT.parseItem());
		itemNames.put("potato", BattleMaterial.POTATO.parseItem());
		itemNames.put("potatoes", BattleMaterial.POTATOES.parseItem());
		itemNames.put("potato_seed", BattleMaterial.POTATO.parseItem());
	}

	static {
		for (Material m : Material.values()){
			//			System.out.println(" data = " + m.getData());
			for (int i =0;i< 64;i++){
				try {
					String s = m.getNewData((byte)i).toString();
					s = s.replaceAll("null", ""); /// get rid of null
					s = s.replaceAll("generic", ""); /// get rid of generic
					s = s.replaceAll("\\(\\d+\\)$" , "");
					s = s.replaceAll("durability.*", "");
					s = s.replaceAll(" up ", "");
					s = s.replaceAll("^\\s+", "").replaceAll("\\s+$", ""); ///remove left and right whitespace
					s = s.replaceAll(" ", "_");
					s = s.toLowerCase();
					if ( s.split("_").length > 3 || s.contains("(") || s.contains(")")){ /// Some strange block
						break;
					}
					if (commonToStack.containsKey(s)){ /// already have gone through all of these
						break;
					}
					ItemStack is = m.getNewData((byte)i).toItemStack();
//					System.out.println("    data = <" + s +">  " + printItemStack(is));
					is.setAmount(1);
					commonToStack.put(s, is);
					s = s.replaceAll("_", " ");
					typeToCommon.put(is.getType() + ":" +is.getDurability(), s);
				} catch (Exception e){
					/// well whoops.. no data for that byte
					break;
				}
			}
		}
	}

	public static int getItemAmountFromInventory(Inventory inv, ItemStack is) {
		return getItemAmount(inv.getContents(), is);
	}

	public static boolean sameItem(final ItemStack is1, final ItemStack is2, boolean checkDura){
		if (is1 == null || is2 == null)
			return false;
		if (is1.getType() != is2.getType())
			return false;
		if (checkDura && (is1.getDurability() != -1 && is1.getDurability() != is2.getDurability()) )
			return false;
		final Map<Enchantment,Integer> e1 = is1.getEnchantments();
		final Map<Enchantment,Integer> e2 = is2.getEnchantments();
		return e1.size() == e2.size();
	}

	public static int getItemAmount(ItemStack[] items, ItemStack is){
		boolean checkDurability = true;
		int count = 0;
		for (ItemStack item : items) {
			if (sameItem(item,is, checkDurability) && item.getAmount() > 0){
				count += item.getAmount();}
		}
		return count;
	}

	/// Checks if there is enough free space in inventory
	public static boolean checkFreeSpace(Chest chest,ItemStack is, int left){
		Inventory inv = chest.getInventory();
		return checkFreeSpace(inv, is, left);
	}

	public static boolean checkFreeSpace(Inventory inv, ItemStack is, int left){
		return checkFreeSpace(inv.getContents(), is, left);
	}
	public static boolean checkFreeSpace(ItemStack[] contents, ItemStack is, int left){
		int maxStack = is.getType().getMaxStackSize();
		for(ItemStack curitem : contents){
			if(left <= 0){
				return true;
			}
			if(curitem == null || curitem.getType() == Material.AIR){
				left = left - maxStack;
				continue;
			}
			if (!sameItem(curitem, is, true))
				continue;

			int amount = curitem.getAmount();
			if(amount < maxStack){
				left = left - (maxStack - amount);
			}
		}
		return left <= 0;
	}

	public static int amountFreeSpace(ItemStack[] contents, ItemStack is, int left){
		int maxStack = is.getType().getMaxStackSize();
		for(ItemStack curitem : contents){
			if(curitem == null){
				left = left - maxStack;
				continue;
			}
			if (!sameItem(curitem, is, true))
				continue;
			int amount = curitem.getAmount();
			if(amount < maxStack){
				left = left - (maxStack - amount);
			}
		}
		return -left;
	}

	public static int amountFreeSpace(Chest chest, ItemStack is, int left) {
		Inventory inv = chest.getInventory();
		return amountFreeSpace(inv, is, left);
	}
	//Checks if there is enough free space in inventory
	public static int amountFreeSpace(Inventory inv, ItemStack is, int left){
		return amountFreeSpace(inv.getContents(), is, left);
	}


	@SuppressWarnings("deprecation")
	public static void addItemToInventory(Player player, ItemStack itemStack, int stockAmount) {
		addItemToInventory(player.getInventory(), itemStack,stockAmount);
		player.updateInventory();
	}

	public static void addItemToInventory(Chest chest, ItemStack is, int left){
		addItemToInventory(chest.getInventory(), is, left);
	}

	///Adds item to inventory
	public static void addItemToInventory(Inventory inv, ItemStack is, int left){
		int maxStackSize = is.getType().getMaxStackSize();
		if(left <= maxStackSize){
			is.setAmount(left);
			inv.addItem(is);
			return;
		}

		if(maxStackSize != 64){
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (int i = 0; i < Math.ceil(left / maxStackSize); i++) {
				if (left < maxStackSize) {
					is.setAmount(left);
					items.add(is);
					return;
				}else{
					is.setAmount(maxStackSize);
					items.add(is);
				}
			}
			Object[] iArray = items.toArray();
			for(Object o : iArray){
				inv.addItem((ItemStack) o);
			}
		}else{
			inv.addItem(is);
		}
	}


	public static int first(Inventory inv, ItemStack is1) {
		if (is1 == null) {
			return -1;
		}
		ItemStack[] inventory = inv.getContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is2 = inventory[i];
			if (is2 == null) continue;
			if (sameItem(is1,is2,true))
				return i;
		}
		return -1;
	}

	/**
	 * This is nearly a direct copy of the removeItem from CraftBukkit
	 * The difference is my ItemStack == ItemStack comparison (found in first())
	 * there I change it to go by itemid and datavalue
	 * as opposed to itemid and quantity
	 * @param inv
	 * @param items
	 * @return
	 */
	public static HashMap<Integer, ItemStack> removeItem(Inventory inv, ItemStack... items) {
		HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();

		for (int i = 0; i < items.length; i++) {
			ItemStack item = items[i];
			int toDelete = item.getAmount();

			while (true) {
				//            	System.out.println("inv= " + inv + "   " + items.length   + "    item=" + item);
				int first = first(inv, item);
				//            	System.out.println("first= " + first);

				// Drat! we don't have this type in the inventory
				if (first == -1) {
					item.setAmount(toDelete);
					leftover.put(i, item);
					break;
				} else {
					ItemStack itemStack = inv.getItem(first);
					int amount = itemStack.getAmount();

					if (amount <= toDelete) {
						toDelete -= amount;
						// clear the slot, all used up
						inv.setItem(first, null);
					} else {
						// split the stack and store
						itemStack.setAmount(amount - toDelete);
						inv.setItem(first, itemStack);
						toDelete = 0;
					}
				}

				// Bail when done
				if (toDelete <= 0) {
					break;
				}
			}
		}
		return leftover;
	}

	public static String printItemStack(ItemStack is){
		StringBuilder sb = new StringBuilder("[ItemStack] " +is.getType() + ":" + is.getAmount() + " dura="+is.getDurability());
		if (is.getData() != null){
			sb.append(" data=" + is.getData() + "  d.itemType=" + is.getType() +
					" d.data=" + is.getData().getData());
		} else {
			sb.append(" data=null");
		}
		return sb.toString();
	}

	/**
	 * Return a item stack from a given string
	 * @param name
	 * @return
	 */
	public static ItemStack getItemStack(String name) {
		if (name == null || name.isEmpty())
			return null;
		name = name.replace(" ", "_");
		name = name.replace(";", ":");
		name = decolorChat(name);
		name = name.toLowerCase();

		String split[] = name.split(":");
		short dataValue = 0;
		if (split.length > 1 && isInt(split[1])){
			int i = Integer.valueOf(split[1]);
			dataValue = (short) i;
			name = split[0];
		}

		/// Check our prenamed items first
		ItemStack is = itemNames.get(name);
		if (is != null){
			return is;}

		BattleMaterial mat;
		if (dataValue > 0) {
			mat = BattleMaterial.fromString(name + ":" + 0);
		} else {
			mat = BattleMaterial.fromString(name);
		}
		if (mat != null && mat != BattleMaterial.AIR) {
			return mat.parseItem();
		}
		/// Try to get from our generic list
		is = commonToStack.get(name);
		if (is == null){
			/// look for first matching item
			for (String itemName : commonToStack.keySet()){
				int index = itemName.indexOf(name,0);
				if (index != -1 && index == 0){
					is = commonToStack.get(itemName);
					return is;
				}
			}
		} else {
			is.setAmount(1);
		}
		return is;
	}

	public static boolean isInt(String i) {
		try {Integer.parseInt(i);return true;} catch (Exception e) {return false;}
	}
	public static String decolorChat(String string) {
		/// Remove all the color codes, first the user defined &[0-9a-fA-F]
		string = string.replaceAll("&[0-9a-fA-F]", "");
		/// Remove the implementation color codes
		string = ChatColor.stripColor(string);
		return string;
	}




//	public static String getCommonName(ItemStack is) {
//		int id = is.getTypeId();
//		int datavalue = is.getDurability();
//		if (datavalue > 0){
//			return Material.getMaterial(id).toString() + ":" + datavalue;
//		}
//		return Material.getMaterial(id).toString();
//	}

	public static String getFormattedCommonName(ItemStack is) {
		Material type = is.getType();
		short datavalue = is.getDurability();

		String iname = "";
		try {
			if (datavalue > 0){
				iname = type.toString() + ":" + datavalue;
			}
			String idkey = type + ":" + datavalue;

			String cname = typeToCommon.get(idkey);
			if (cname != null)
				return cname;

			int maxDurability = type.getMaxDurability();

			iname = formatCommonName(type.toString().toLowerCase()) + " (" + (maxDurability - datavalue) + "/" + maxDurability + ")";
		} catch (Exception e){
			System.err.println("Error getting commonName type=" + type + "   iname=" + iname + "   datavalue=" + datavalue);
			e.printStackTrace();
		}
		return iname;
	}

	public static String getCustomName(ItemStack item) {
		ItemMeta im = item.getItemMeta();
		if (im == null){
			return item.getType().name().toLowerCase();}
		String displayName = im.getDisplayName();
		return displayName == null || displayName.isEmpty() ? item.getType().name().toLowerCase() : displayName;
	}

	public static ItemStack getItemStack(Material type, short datavalue) {
		return BattleMaterial.fromString(type + ":" + datavalue).parseItem();
	}

	private static String formatCommonName(String name) {
		name = name.toLowerCase().replace("_", " ");

		String[] words = name.split(" ");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
		}

		return String.join(" ", words);
	}
}
