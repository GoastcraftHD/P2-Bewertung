package de.GoastcraftHD.P2Bewertung.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.GoastcraftHD.P2Bewertung.commands.BewertungsCommand;

public class Main extends JavaPlugin{

	public static String PREFIX = "[ServerName] >§r";
	
	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		getCommand("PlotBewertung").setExecutor(new BewertungsCommand());
	}

	public static Main getPlugin() {
		return plugin;
	}
}
