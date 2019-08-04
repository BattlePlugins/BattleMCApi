package mc.alk.nukkit;

import cn.nukkit.Player;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.nukkit.command.NukkitCommandSender;
import mc.alk.nukkit.inventory.NukkitPlayerInventory;

public class NukkitPlayer extends NukkitCommandSender implements MCPlayer{

	private Player player;
	private String name;

	public NukkitPlayer(Player player){
		super(player);

		this.player = player;
		this.name = player.getName();
	}

	@Override
	public MCWorld getWorld() {
		return new NukkitWorld(player.getLevel());
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
	public boolean hasPermission(String node) {
		return player.hasPermission(node);
	}

	@Override
	public MCInventory getInventory() {
		return new NukkitPlayerInventory(player.getInventory());
	}

	@Override
	public void updateInventory() {
		// TODO: Add API here
	}

	public Player getNukkitPlayer() {
		return player;
	}
}
