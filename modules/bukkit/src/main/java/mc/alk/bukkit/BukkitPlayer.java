package mc.alk.bukkit;

import mc.alk.bukkit.entity.BukkitHumanEntity;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;

import org.bukkit.entity.Player;

public class BukkitPlayer extends BukkitHumanEntity implements MCPlayer {

	private Player player;
	private String name;

	public BukkitPlayer(Player player){
	    super(player);

		this.player = player;
		this.name = player.getName();
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
		return new BukkitLocation(player.getBedSpawnLocation());
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
	public void sendMessage(String message) {
		player.sendMessage(message);
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
