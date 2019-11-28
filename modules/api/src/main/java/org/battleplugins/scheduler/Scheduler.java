package org.battleplugins.scheduler;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {

	private static int count = 0; // count of current async timers

	/** Our current async timers */
	private static final Map<Integer, Timer> timers = new ConcurrentHashMap<>();

	@AllArgsConstructor
	static class CompletedTask extends TimerTask{

		private Runnable runnable;
		private int id;

		@Override
		public void run() {
			timers.remove(id);
			runnable.run();
		}
	}

	public static int scheduleAsynchrounousTask(Runnable task) {
		return scheduleAsynchrounousTask(task, 0);
	}

	public static int scheduleAsynchrounousTask(Runnable task, long millis) {
		int tid = count++;
		synchronized(timers){
			Timer timer = new Timer();
			timer.schedule(new CompletedTask(task, tid), millis);
			timers.put(tid, timer);
		}
		return tid;
	}
}
