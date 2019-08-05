package mc.alk.mc.plugin;

public interface MCPlugin {

	void onEnable();
	void onDisable();

	boolean isEnabled();
}
