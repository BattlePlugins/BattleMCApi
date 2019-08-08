package mc.alk.nukkit;

import cn.nukkit.Player;
import mc.alk.mc.MCLocation;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.nukkit.command.NukkitCommandSender;
import mc.alk.nukkit.inventory.NukkitPlayerInventory;

import java.util.UUID;

public class NukkitPlayer extends NukkitCommandSender implements MCPlayer {

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
	public UUID getUniqueId() {
		return player.getUniqueId();
	}

	@Override
	public MCPlayer getPlayer() {
		return this;
	}

	@Override
	public long getFirstPlayed() {
		return player.getFirstPlayed();
	}

	@Override
	public long getLastPlayed() {
		return player.getLastPlayed();
	}

	@Override
	public boolean hasPlayedBefore() {
		return player.hasPlayedBefore();
	}

	@Override
	public MCLocation getBedSpawnLocation() {
		// TODO: Add API here
		return null;
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
	public boolean isOp() {
		return player.isOp();
	}

	@Override
	public MCInventory getInventory() {
		return new NukkitPlayerInventory(player.getInventory());
	}

	@Override
	public void updateInventory() {
		// TODO: Add API here
	}

	@Override
	public boolean isOnline() {
		return player.isOnline();
	}

	public Player getNukkitPlayer() {
		return player;
	}
}
