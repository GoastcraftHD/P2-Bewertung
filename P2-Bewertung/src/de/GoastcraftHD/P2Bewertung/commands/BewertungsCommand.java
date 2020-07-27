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

	private static Inventory inv = Bukkit.createInventory(null, 9*6, "Test");
	
	private ItemStack onePoint = new ItemBuilder(Material.INK_SACK, (short) 10).setName("ß61 ßaPunkt").build();
	private ItemStack twoPoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("ß62 ßaPunkte").setAmount(2).build();
	private ItemStack threePoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("ß63 ßaPunkte").setAmount(3).build();
	private ItemStack fourPoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("ß64 ßaPunkte").setAmount(4).build();
	private ItemStack fivePoints = new ItemBuilder(Material.INK_SACK, (short) 8).setName("ß65 ßaPunkte").setAmount(5).build();
	
	private ItemStack firstCategory = new ItemBuilder(Material.WOOL, (short) 1).setName("").build();
	private ItemStack secondCategory = new ItemBuilder(Material.WOOL, (short) 2).setName("").build();
	private ItemStack thirdCategory = new ItemBuilder(Material.WOOL, (short) 3).setName("").build();
	private ItemStack fourthCategory = new ItemBuilder(Material.WOOL, (short) 4).setName("").build();
	
	private ItemStack confirm = new ItemBuilder(Material.CONCRETE, (short) 5).setName("ß2Best‰tigen").build();
	private ItemStack close = new ItemBuilder(Material.CONCRETE, (short) 14).setName("ß4Schlieﬂen").build();
	
	private ItemStack[] pointItemOrder = new ItemStack[] {onePoint, twoPoints, threePoints, fourPoints, fivePoints};
	
	private static int[] pointOrder1 = new int[] {12, 13, 14, 15, 16};
	private static int[] pointOrder2 = new int[] {21, 22, 23, 24, 25};
	private static int[] pointOrder3 = new int[] {30, 31, 32, 33, 34};
	private static int[] pointOrder4 = new int[] {39, 40, 41, 42, 43};
	
	private static int[][] pointOrderArray = new int[][] {pointOrder1, pointOrder2, pointOrder3, pointOrder4};
	
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
							inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 7).setName(" ").build());
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
								for (ItemStack itemStack : pointItemOrder) {
									inv.setItem(i+p, itemStack);
									p++;
								}
								break;

							default:
								break;
							}
						}
						
						player.openInventory(inv);
						
					} else
						player.sendMessage("ßcDieser ß4Plot ßchat ß4keinen Besitzerßc!");
				} else
					player.sendMessage("ßcDu musst dich ß4auf ßceinem ß4Plot befindenßc!");
			} else
				player.sendMessage("ßcDazu hast du ß4keine ßcRechte!");
		}
		return false;
	}
	
	public static Inventory getInv() {
		return inv;
	}
	
	public static int[][] getPointOrderArray() {
		return pointOrderArray;
	}
}
