package mc.alk.testbukkit;

import mc.alk.mc.MCPlugin;
import mc.alk.TestServer;

public class TestMCBukkitServer extends TestServer{

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

}
