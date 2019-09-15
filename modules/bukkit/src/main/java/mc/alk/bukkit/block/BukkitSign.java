package mc.alk.bukkit.block;

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
	public void update(boolean b){
		sign.update(true);
	}
}
