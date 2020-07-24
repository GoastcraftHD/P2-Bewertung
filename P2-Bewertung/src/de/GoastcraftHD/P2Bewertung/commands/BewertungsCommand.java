package de.GoastcraftHD.P2Bewertung.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;

import de.GoastcraftHD.P2Bewertung.util.ItemBuilder;

public class BewertungsCommand implements CommandExecutor {

	private Inventory inv = Bukkit.createInventory(null, 9*6, "Test");
	
	private ItemStack onePoint = new ItemBuilder(Material.INK_SACK, (short) 10).setName("1 Punkt").build();
	private ItemStack twoPoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("2 Punkte").setAmount(2).build();
	private ItemStack threePoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("3 Punkte").setAmount(3).build();
	private ItemStack fourPoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("4 Punkte").setAmount(4).build();
	private ItemStack fivePoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("5 Punkte").setAmount(5).build();
	
	private ItemStack firstCategory = new ItemBuilder(Material.WOOL, (short) 1).build();
	private ItemStack secondCategory = new ItemBuilder(Material.WOOL, (short) 2).build();
	private ItemStack thirdCategory = new ItemBuilder(Material.WOOL, (short) 3).build();
	private ItemStack fourthCategory = new ItemBuilder(Material.WOOL, (short) 4).build();
	
	private ItemStack confirm = new ItemBuilder(Material.CONCRETE, (short) 5).setName("Best‰tigen").build();
	private ItemStack close = new ItemBuilder(Material.CONCRETE, (short) 14).setName("Schlieﬂen").build();
	
	private ItemStack[] pointOrder = new ItemStack[] {onePoint, twoPoints, threePoints, fourPoints, fivePoints};
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("ServerName.Plot.Bewertung")) {
				PlotAPI plotAPI = new PlotAPI();
				if (plotAPI.isInPlot(player)) {
					Plot plot = plotAPI.getPlot(player.getLocation());
					if (plot.hasOwner()) {
						
						for (int i = 0; i < inv.getSize(); i++) {
							inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 7).setName("").build());
						}
						
						for (int i = 0; i < inv.getSize(); i++) {
							switch (i) {
							
							case 10:
								inv.setItem(i, firstCategory);
								break;
							
							case 19:
								inv.setItem(i, secondCategory);
								break;
								
							case 28:
								inv.setItem(i, thirdCategory);
								break;
							
							case 37:
								inv.setItem(i, fourthCategory);
								break;
								
							case 48:
								inv.setItem(i, close);
								break;
								
							case 50:
								inv.setItem(i, confirm);
								break;
							
							case 12: case 21: case 30: case 39:
								int p = 0;
								for (ItemStack itemStack : pointOrder) {
									inv.setItem(i+p, itemStack);
									p++;
								}
								break;

							default:
								break;
							}
						}
					} else
						player.sendMessage("Dieser Plot hat keinen besitzer!");
				} else
					player.sendMessage("Du musst dich in einem plot befinden!");
			} else
				player.sendMessage("Du hast dazu keine Rechte!");
		}
		return false;
	}

}
