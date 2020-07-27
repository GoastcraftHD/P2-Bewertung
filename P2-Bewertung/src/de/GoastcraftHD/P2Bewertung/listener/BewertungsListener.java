package de.GoastcraftHD.P2Bewertung.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;

import de.GoastcraftHD.P2Bewertung.commands.BewertungsCommand;

public class BewertungsListener implements Listener {
	
	private int points1 = 1;
	private int points2 = 1;
	private int points3 = 1;
	private int points4 = 1;
	
	private Material[][] blocks = new Material[][] {new Material[] {Material.DIRT, Material.DIRT, Material.DIRT},
													new Material[] {Material.DIRT, Material.IRON_BLOCK, Material.DIRT},
													new Material[] {Material.IRON_BLOCK, Material.DIRT, Material.IRON_BLOCK},
													new Material[] {Material.IRON_BLOCK, Material.IRON_BLOCK, Material.IRON_BLOCK},
													new Material[] {Material.IRON_BLOCK, Material.DIAMOND_BLOCK, Material.IRON_BLOCK},
													new Material[] {Material.DIAMOND_BLOCK, Material.IRON_BLOCK, Material.DIAMOND_BLOCK},
													new Material[] {Material.DIAMOND_BLOCK, Material.DIAMOND_BLOCK, Material.DIAMOND_BLOCK},
													new Material[] {Material.DIAMOND_BLOCK, Material.BEACON, Material.DIAMOND_BLOCK},
													new Material[] {Material.BEACON, Material.DIAMOND_BLOCK, Material.BEACON},
													new Material[] {Material.BEACON, Material.BEACON, Material.BEACON}};
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		if (!(event.getInventory().getName().equals(BewertungsCommand.getInv().getName()))) return;
		points1 = 1;
		points2 = 1;
		points3 = 1;
		points4 = 1;
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		if (event.getClickedInventory() == null) return;
		if (!(event.getInventory().getName().equals(BewertungsCommand.getInv().getName()))) return;
		if (!(event.getRawSlot() < event.getInventory().getSize())) return;
			
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getCurrentItem().getType().equals(Material.INK_SACK)) {
			if (event.getCurrentItem().getDurability() == (short) 8) {
				int[][] pointOrderArray = BewertungsCommand.getPointOrderArray();
				
				for (int i = 0; i < pointOrderArray.length; i++) {
					int[] pointOrder = pointOrderArray[i];
					
					for (int j = 0; j < pointOrder.length; j++) {
						if (event.getRawSlot() == pointOrder[j]) {
							for (int j2 = 0; j2 < pointOrder.length; j2++) {
								event.getInventory().getItem(pointOrder[j2]).setDurability((short) 8);
								
								switch (i) {
								case 0:
									points1 = event.getCurrentItem().getAmount();
									break;
								case 1:
									points2 = event.getCurrentItem().getAmount();
									break;
								case 2:
									points3 = event.getCurrentItem().getAmount();
									break;
								case 3:
									points4 = event.getCurrentItem().getAmount();
									break;

								default:
									break;
								}
							}
						}
					}
				}
				event.getCurrentItem().setDurability((short) 10);
			}
		}
		
		if (event.getCurrentItem().getType().equals(Material.CONCRETE) && event.getCurrentItem().getDurability() == (short) 5) {
			
			int points = points1 + points2 + points3 + points4;
			PlotAPI plotAPI = new PlotAPI();
			Plot plot = plotAPI.getPlot(player.getLocation());
			com.intellectualcrafters.plot.object.Location plotLoc = plot.getDefaultHome();
			
			Location signLoc = new Location(player.getWorld(), plotLoc.getX()+1, plotLoc.getY(), plotLoc.getZ()-1);
			Location Block1 = new Location(player.getWorld(), plotLoc.getX()+1, plotLoc.getY(), plotLoc.getZ());
			Location Block2 = new Location(player.getWorld(), plotLoc.getX()+2, plotLoc.getY(), plotLoc.getZ());
			Location Block3 = new Location(player.getWorld(), plotLoc.getX()+3, plotLoc.getY(), plotLoc.getZ());

			
			int a = (int) Math.round((points/1.75));
			Material[] mat = blocks[a-2];
			
			Block1.getBlock().setType(mat[0]);
			Block2.getBlock().setType(mat[1]);
			Block3.getBlock().setType(mat[2]);
			
			signLoc.getBlock().setType(Material.WALL_SIGN);
			Sign sign = (Sign) signLoc.getBlock().getState();
			sign.setLine(0, "§0Bewertet von");
			sign.setLine(1, "§c" + player.getName());
			sign.setLine(2, "§0-*-");
			sign.setLine(3, "§c" + points + "/20");
			sign.update();
		}
		
		if (event.getCurrentItem().getType().equals(Material.CONCRETE) && event.getCurrentItem().getDurability() == (short) 14) {
			player.closeInventory();
		}
	}
}
