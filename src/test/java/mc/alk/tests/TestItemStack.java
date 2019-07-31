package mc.alk.tests;

import java.util.HashMap;
import java.util.Map;

import mc.alk.bukkit.inventory.BukkitItemStack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class TestItemStack extends BukkitItemStack{
	HashMap<Integer,Integer> enchantments = new HashMap<Integer,Integer>();

	public TestItemStack(int id) {
		super(new ItemStack(id));
	}

	public TestItemStack(int id, int quantity) {
		super(new ItemStack(id, quantity));
	}

	public TestItemStack(int id, int quantity, short datavalue) {
		super(new ItemStack(id, quantity,datavalue));
	}

	@Override
	public String toString(){
		String encs = (enchantments.isEmpty() ? "" : " enchs="+enchantments.keySet());
		return "["+getType() + ":"+getDataValue()+" q="+this.getQuantity()+ encs+"]";
	}

	@Override
	public Map<Integer, Integer> getEnchantments() {
        return enchantments;
	}

	@Override
	public void addEnchantment(Enchantment enc, int level) {
		enchantments.put(enc.getId(), level);
	}

}
