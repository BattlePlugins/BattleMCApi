package mc.alk.mc.inventory;

public abstract class MCItemFactory {

	public abstract MCItemStack createMCItem(String type, short value, int quantity);
	public abstract MCItemStack createMCItem(String text);

}
