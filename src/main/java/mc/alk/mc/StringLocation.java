package mc.alk.mc;

public class StringLocation implements MCLocation{

	private String world;
	private int x;
	private int y;
	private int z;

	public StringLocation(String world, Integer x, Integer y, Integer z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getWorldName() {
		return world;
	}

	@Override
	public MCWorld getWorld() {
		return null;
	}

	@Override
	public int getBlockX() {
		return x;
	}

	@Override
	public int getBlockY() {
		return y;
	}

	@Override
	public int getBlockZ() {
		return z;
	}
}
