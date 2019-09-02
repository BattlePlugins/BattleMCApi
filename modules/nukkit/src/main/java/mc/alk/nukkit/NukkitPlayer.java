package mc.alk.nukkit;

import cn.nukkit.Player;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.nukkit.entity.NukkitHumanEntity;

public class NukkitPlayer extends NukkitHumanEntity implements MCPlayer {

	private Player player;
	private String name;

	public NukkitPlayer(Player player){
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
	public void sendMessage(String message) {
		player.sendMessage(message);
	}

	@Override
	public boolean isOp() {
		return player.isOp();
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
