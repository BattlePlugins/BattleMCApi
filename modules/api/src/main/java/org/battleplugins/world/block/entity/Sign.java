package org.battleplugins.world.block.entity;

import org.battleplugins.entity.living.player.Player;

public interface Sign extends BlockEntity {

	String getLine(int index);
	String[] getLines();

	void setLine(int index, String msg);

	void sendSignChange(Player player, String[] lines);
}
