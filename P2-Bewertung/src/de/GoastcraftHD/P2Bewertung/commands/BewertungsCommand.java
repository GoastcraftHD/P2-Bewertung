package de.GoastcraftHD.P2Bewertung.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BewertungsCommand implements CommandExecutor {

	private Inventory inv = Bukkit.createInventory(null, 9*6, "Test");
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("ServerName.Plot.Bewertung")) {
				
			} else
				player.sendMessage("Du hast dazu keine Rechte!");
		}
		return false;
	}

}
