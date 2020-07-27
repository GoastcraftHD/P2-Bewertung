package de.GoastcraftHD.P2Bewertung.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.GoastcraftHD.P2Bewertung.commands.BewertungsCommand;
import de.GoastcraftHD.P2Bewertung.listener.BewertungsListener;

public class Main extends JavaPlugin{

	public static String PREFIX = "[ServerName] >§r";
	
	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		getCommand("PlotBewertung").setExecutor(new BewertungsCommand());
		
		Bukkit.getPluginManager().registerEvents(new BewertungsListener(), this);
	}

	public static Main getPlugin() {
		return plugin;
	}
}
