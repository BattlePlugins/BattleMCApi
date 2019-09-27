package mc.alk.bukkit.block;

import mc.alk.bukkit.BukkitPlayer;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.block.MCSign;

import org.bukkit.block.Sign;

public class BukkitSign extends BukkitBlock implements MCSign {

	private Sign sign;

	public BukkitSign(Sign sign) {
		super(sign.getBlock());

		this.sign = sign;
	}

	@Override
	public void setLine(int index, String line) {
		sign.setLine(index, line);
	}

	@Override
	public void sendSignChange(MCPlayer player, String[] lines) {
		((BukkitPlayer) player).getHandle().sendSignChange(sign.getLocation(), lines);
	}

	@Override
	public String getLine(int index) {
		return sign.getLine(index);
	}

	@Override
	public String[] getLines() {
		return sign.getLines();
	}

	@Override
	public BukkitSign clone(){
		return new BukkitSign(sign);
	}

	@Override
	public void update() {
		sign.update();
	}

	@Override
	public void update(boolean force){
		sign.update(force);
	}
}
