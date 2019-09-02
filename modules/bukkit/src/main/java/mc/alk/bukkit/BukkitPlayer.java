package mc.alk.bukkit;

import mc.alk.bukkit.inventory.BukkitPlayerInventory;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.inventory.MCPlayerInventory;

import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlayer extends MCPlayer {

	private Player player;
	private String name;

	public BukkitPlayer(Player player){
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
	public MCPlayerInventory getInventory() {
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
