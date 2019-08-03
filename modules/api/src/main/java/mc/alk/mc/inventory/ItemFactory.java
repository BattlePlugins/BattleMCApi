package mc.alk.mc.inventory;

public abstract class ItemFactory {

	public abstract MCItemStack createMCItem(String type, short value, int quantity);
	public abstract MCItemStack createMCItem(String text);
}
