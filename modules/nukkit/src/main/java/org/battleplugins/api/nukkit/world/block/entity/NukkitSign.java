package org.battleplugins.api.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntitySign;

import org.battleplugins.api.world.block.entity.Sign;

public class NukkitSign extends NukkitBlockEntity<BlockEntitySign> implements Sign {

	public NukkitSign(BlockEntitySign sign) {
		super(sign);
	}

	@Override
	public void setLine(int index, String line) {
		String[] text = handle.getText();
		text[index] = line;

		handle.setText(text);
		handle.scheduleUpdate();
	}

	@Override
	public String getLine(int index) {
		return handle.getText()[index];
	}

	@Override
	public String[] getLines() {
		return handle.getText();
	}

	@Override
	public NukkitSign clone(){
		return new NukkitSign(handle);
	}
}
