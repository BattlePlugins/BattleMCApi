package org.battleplugins.api.bukkit.world.block.entity;

import org.bukkit.block.Sign;

public class BukkitSign extends BukkitBlockEntity<Sign> implements org.battleplugins.api.world.block.entity.Sign {

	private Sign sign;

	public BukkitSign(Sign sign) {
		super(sign);

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
}
