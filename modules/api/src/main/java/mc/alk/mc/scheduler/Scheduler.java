package mc.alk.mc.scheduler;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {

	static int count = 0; /// count of current async timers

	/** Our current async timers */
	static Map<Integer,Timer> timers = new ConcurrentHashMap<Integer,Timer>();

	static class CompletedTask extends TimerTask{
		final Runnable r;
		final int id;
		public CompletedTask(Runnable r, int id) {
			this.r = r;
			this.id = id;
		}

		@Override
		public void run() {
			timers.remove(id);
			r.run();
		}
	}

	public static int scheduleAsynchrounousTask(Runnable task) {
		return scheduleAsynchrounousTask(task, 0);
	}

	public static int scheduleAsynchrounousTask(Runnable task, long millis) {
		int tid = count++;
		synchronized(timers){
			Timer t = new Timer();
			t.schedule(new CompletedTask(task,tid), millis);
			timers.put(tid, t);
		}
		return tid;
	}
}
