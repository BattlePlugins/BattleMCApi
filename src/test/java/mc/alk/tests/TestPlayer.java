package mc.alk.tests;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCWorld;

public class TestPlayer implements MCPlayer {
	final String name;
	String displayName;
	MCLocation loc;
	MCInventory inventory = new TestInventory();

	public TestPlayer(String name){
		MCLocation loc = new TestLocation("world",0,0,0);
		this.name = name;
		this.loc = loc;
		TestServer.addPlayer(this);
	}

	public TestPlayer(String name, MCLocation loc){
		this.name = name;
		this.loc = loc;
		TestServer.addPlayer(this);
	}

	@Override
	public MCWorld getWorld() {
		return loc.getWorld();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDisplayName() {
		return displayName == null ? name : displayName;
	}


	@Override
	public void sendMessage(String message) {
		System.out.println("["+name+" : " + message +"]");
	}

	@Override
	public boolean hasPermission(String node) {
		return true;
	}

	@Override
	public MCInventory getInventory() {
		return inventory;
	}

	@Override
	public void updateInventory() {

	}

}
