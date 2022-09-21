package fr.gameurduxvi.meneliaapi.EventListeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;

public class BungeeCord implements PluginMessageListener {
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
		if(channel.equals("MeneliaChannel")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
			
			String server = in.readUTF();
			String sub = in.readUTF();
			
			if(server.equals(Bukkit.getServerName())) {
				if(sub.equals("AdminPanel_Players.Action")) {
					/*Inventory inv = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§3§rJoueurs", "§A§3§rPlayers"));					
					
					MeneliaAPI.getInstance().AdminPanelEventListenerInstance.inventoryDefault(inv);			    	
					
					String linePlayer = in.readUTF();
					String[] arrOfStrPlayer = linePlayer.split("#");
					List<String> listPlayer = Arrays.asList(arrOfStrPlayer);
					
					int i = 0;
					for(String loopPlayerInfo: listPlayer) {
						i++;
						if(i == 8 || i == 17 || i == 26 || i == 35) {
							i++;
							i++;
						}
						String[] loopPlayerInfos = loopPlayerInfo.split("<");
						ItemStack PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
						SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
						//String username = MeneliaAPI.getInstance().MySQLLibrary.GET_DATA_String(MeneliaAPI.getInstance().MySQLLibrary.Table_Accounts, MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_UUID, loopPlayerInfos[0], MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_USERNAME);
						customMPlayerHead.setDisplayName(loopPlayerInfos[0]);
						customMPlayerHead.setLore(Arrays.asList("§9" + loopPlayerInfos[1]));
						customMPlayerHead.setOwner(loopPlayerInfos[0]);
						PlayerHead.setItemMeta(customMPlayerHead);
						
						inv.setItem(9 + i, PlayerHead);
					}
			    	ItemStack back = new ItemStack(Material.ARROW);
			    	ItemMeta metaBack = back.getItemMeta();
			    	
			    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
			    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    	
			    	back.setItemMeta(metaBack);
			    	
			    	inv.setItem(49, back);	
			    	
			    	player.openInventory(inv);*/
				}
				else if(sub.equals("AdminPanel_Staff.Action")) {
					/*Inventory inv = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§6§rStaff", "§A§6§rStaff"));
					
					MeneliaAPI.getInstance().AdminPanelEventListenerInstance.inventoryDefault(inv);
					
					String linePlayer = in.readUTF();
					String[] arrOfStrPlayer = linePlayer.split("#");
					List<String> listPlayer = Arrays.asList(arrOfStrPlayer);
					
					int i = 0;
					for(String loopPlayerInfo: listPlayer) {
						if(PermissionsEx.getUser(loopPlayerInfo).has("admin.staff")) {
							i++;
							if(i == 8 || i == 17 || i == 26 || i == 35) {
								i++;
								i++;
							}
							ItemStack PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
							SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
							//String username = MeneliaAPI.getInstance().MySQLLibrary.GET_DATA_String(MeneliaAPI.getInstance().MySQLLibrary.Table_Accounts, MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_UUID, loopPlayerInfos[0], MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_USERNAME);
							customMPlayerHead.setDisplayName(loopPlayerInfo);
							customMPlayerHead.setOwner(loopPlayerInfo);
							PlayerHead.setItemMeta(customMPlayerHead);
							
							inv.setItem(9 + i, PlayerHead);							
						}
					}
					
					ItemStack back = new ItemStack(Material.ARROW);
			    	ItemMeta metaBack = back.getItemMeta();
			    	
			    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
			    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    	
			    	back.setItemMeta(metaBack);
			    	
			    	inv.setItem(49, back);	
					
					player.openInventory(inv);*/
				}
				else if(sub.equals("AdminPanel_Server_Restart.Action")) {
					for(Player loopPlayer: Bukkit.getOnlinePlayers()) {
						loopPlayer.sendMessage(MeneliaAPI.getInstance().getLang(loopPlayer, "§cLe serveur a été fermé par un administrateur !", "§cThe server has been closed by the administrator !"));
						loopPlayer.sendMessage(MeneliaAPI.getInstance().getLang(loopPlayer, "§cRedirrection vers le serveur: hub", "§cRedirection to the server: hub"));
						Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								MeneliaAPI.getBungeecordInstance().sendToServer(loopPlayer, "hub");
							}
						}, 10);
					}
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize("&c Shutting down the server by receiving the order to shut down."));
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getConsoleSender().sendMessage("");
					Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							Bukkit.shutdown();
						}
					}, 20);
				}
			}
		}
	}
}
