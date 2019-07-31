package mc.alk.tests;

import java.util.HashMap;
import java.util.Map;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.MCServer;
import mc.alk.mc.MCWorld;

public class TestServer extends MCServer{
	public static Map<String,MCWorld> worlds = new HashMap<String,MCWorld>();
	public static boolean disableThreads = false;
	public static Map<String,MCPlayer> players = new HashMap<String,MCPlayer>();

	@Override
	public APIType getAPIType() {
		return APIType.TEST;
	}
	@Override
	public MCLocation getMCLocation(String world, int x, int y, int z) {
		return new TestLocation(world,x,y,z);
	}

	@Override
	public MCWorld getMCWorld(String world) {
		if (worlds.containsKey(world))
			return worlds.get(world);
		worlds.put(world, new TestWorld(world));
		return worlds.get(world);
	}

	@Override
	public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
		if (disableThreads){
			runnable.run();
			return -1;
		}
		Thread t = new Thread(runnable);
		t.start();
		return t.getId();
	}

	@Override
	public boolean cancelMCTask(long id) {
		return true;
	}

	public static void setWorld(String name, MCWorld world) {
		worlds.put(name,world);
	}
	public static void enableThreading(boolean enable){
		disableThreads = !enable;
	}

	@Override
	public MCPlayer getMCPlayer(String name) {
		return players.get(name);
	}

	public static void addPlayer(MCPlayer player) {
		players.put(player.getName(), player);
	}



}
