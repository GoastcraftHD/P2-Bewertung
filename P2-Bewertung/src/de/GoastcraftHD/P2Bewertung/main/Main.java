package de.GoastcraftHD.P2Bewertung.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static String PREFIX = "[ServerName] >§r";
	
	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
	}

	public static Main getPlugin() {
		return plugin;
	}
}
