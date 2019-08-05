package mc.alk.mc.plugin;

import mc.alk.mc.MCServer;

public interface MCPlugin {

	MCServer getMCServer();

	void onEnable();
	void onDisable();

	boolean isEnabled();
}
