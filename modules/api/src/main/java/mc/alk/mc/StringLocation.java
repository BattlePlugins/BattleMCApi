package mc.alk.mc;

public class StringLocation implements MCLocation {

	private String world;
	private double x, y, z;
	private float pitch, yaw;

	public StringLocation(String world, double x, double y, double z, float pitch, float yaw) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public String getWorldName() {
		return world;
	}

	@Override
	public MCWorld getWorld() {
		return null;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public float getPitch() {
		return pitch;
	}

	@Override
	public float getYaw() {
		return yaw;
	}

	@Override
	public int getBlockX() {
		return (int) Math.floor(x);
	}

	@Override
	public int getBlockY() {
		return (int) Math.floor(y);
	}

	@Override
	public int getBlockZ() {
		return (int) Math.floor(z);
	}
}
