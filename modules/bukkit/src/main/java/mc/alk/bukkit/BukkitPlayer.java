package mc.alk.bukkit;

import mc.alk.bukkit.command.BukkitCommandSender;
import mc.alk.bukkit.inventory.BukkitPlayerInventory;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;

import org.bukkit.entity.Player;

public class BukkitPlayer extends BukkitCommandSender implements MCPlayer {

	private Player player;
	private String name;

	public BukkitPlayer(Player player){
		super(player);

		this.player = player;
		this.name = player.getName();
	}

	@Override
	public MCWorld getWorld() {
		return new BukkitWorld(player.getWorld());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDisplayName() {
		return player.getDisplayName();
	}

	@Override
	public boolean isOp() {
		return player.isOp();
	}

	@Override
	public boolean hasPermission(String node) {
		return player.hasPermission(node);
	}

	@Override
	public MCInventory getInventory() {
		return new BukkitPlayerInventory(player.getInventory());
	}

	@Override
	public void updateInventory() {
		player.updateInventory();
	}

	@Override
	public boolean isOnline() {
		return player.isOnline();
	}

	public Player getBukkitPlayer() {
		return player;
	}
}
