package fr.gameurduxvi.meneliaapi.EventListeners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.MySQL.MySQL;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AdminPanel implements CommandExecutor, Listener {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("admin")) {
				if(PermissionsEx.getUser(player).has("admin.open")) {
					AdminPanelInv(player);
				}
			}
		}
		return false;
	}
	
	// Prefix of menus in AdminPanel
	//
	// Main               -->     AdminPanel
	// Servers            -->     §A§1§r
	// Servers Config     -->     §A§2§r
	// Players            -->     §A§3§r
	// Player Option      -->     §A§4§r
	// Player Option Move -->     §A§5§r
	// Staff              -->     §A§6§r
	// Updates            -->     §A§7§r
	// Reports            -->     §A§8§r
	// Reports List       -->     §A§9§r
	// Report Option      -->     §A§1§0§r
	
	private void Servers(Player player, Inventory inv, boolean rightClick) {		
		int i = 9;
		Iterator<Entry<String, Integer>> itr = MeneliaAPI.getInstance().getServerList().entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, Integer> pair = itr.next();
			i++;
			ItemStack item = new ItemStack(Material.GRASS);
	    	ItemMeta metaItem = item.getItemMeta();
	    	
	    	metaItem.setDisplayName(pair.getKey());
	    	metaItem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    	
	    	ArrayList<String> lore = new ArrayList<>();
	    	if(rightClick) {
	    		lore.add(MeneliaAPI.getInstance().getLang(player, "§8>> §aClic gauche pour changer de serveur", "§8>> §aLeft click to change server"));
		    	lore.add(MeneliaAPI.getInstance().getLang(player, "§8>> §aClic droit pour intéragir avec les options du serveur", "§8>> §aRight click to interact with server options"));
	    	}
	    	metaItem.setLore(lore);
	    	
	    	item.setItemMeta(metaItem);
	    	
	    	inv.setItem(i, item);
		}
	}
	
	public void inventoryDefault(Inventory inv) {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§a");
		item.setItemMeta(meta);
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		
		inv.setItem(9, item);
		inv.setItem(18, item);
		inv.setItem(27, item);
		inv.setItem(36, item);
		
		
		inv.setItem(17, item);
		inv.setItem(26, item);
		inv.setItem(35, item);
		inv.setItem(44, item);
		
		inv.setItem(45, item);
		inv.setItem(46, item);
		inv.setItem(47, item);
		inv.setItem(48, item);
		inv.setItem(49, item);
		inv.setItem(50, item);
		inv.setItem(51, item);
		inv.setItem(52, item);
		inv.setItem(53, item);
	}
	
	public void AdminPanelInv(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, "AdminPanel");
    	

		inventoryDefault(inv);
    	
		ItemStack item1 = new ItemStack(Material.REDSTONE_TORCH_ON);
    	ItemMeta metaItem1 = item1.getItemMeta();
    	
    	metaItem1.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Mise a jours", "§9Updates"));
    	metaItem1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item1.setItemMeta(metaItem1);
    	
    	inv.setItem(13, item1);
		
    	item1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    	metaItem1 = item1.getItemMeta();
    	
    	metaItem1.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Joueurs", "§9Players"));
    	metaItem1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item1.setItemMeta(metaItem1);
    	
    	inv.setItem(21, item1);
    	
    	ItemStack item2 = new ItemStack(Material.TOTEM);
    	ItemMeta metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Staff", "§9Staff"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(22, item2);
    	
    	ItemStack item3 = new ItemStack(Material.GRASS);
    	ItemMeta metaItem3 = item3.getItemMeta();
    	
    	metaItem3.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Serveurs", "§9Servers"));
    	metaItem3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item3.setItemMeta(metaItem3);
    	
    	inv.setItem(23, item3);
    	
    	item2 = new ItemStack(Material.SIGN);
    	metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Avertissements", "§9Warns"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(30, item2);
    	
    	item2 = new ItemStack(Material.BOOK_AND_QUILL);
    	metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Signalements", "§9Reports"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(31, item2);
    	
    	item2 = new ItemStack(Material.ITEM_FRAME);
    	metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Calendrier", "§9Calendar"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(32, item2);

    	
    	item2 = new ItemStack(Material.EMPTY_MAP);
    	metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9StaffChat", "§9StaffChat"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(53, item2);
    	
    	player.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	public void AdminPanelPlayers(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§3§rJoueurs", "§A§3§rPlayers"));					
		
		inventoryDefault(inv);			    	
		
		ResultSet result = MySQL.GET_RESULT(MySQL.Table_Online);
		int i = 0;
		try {
			while(result.next()) {
				i++;
				if(i == 8 || i == 17 || i == 26 || i == 35) {
					i++;
					i++;
				}
				ItemStack PlayerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
				//String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, loopPlayerInfos[0], MySQL.Field_Accounts_USERNAME);
				customMPlayerHead.setDisplayName(result.getString(MySQL.Field_Online_USERNAME));
				
				ArrayList<String> lore = new ArrayList<>();
				lore.add("§9" + result.getString(MySQL.Field_Online_SERVER));
				if(MySQL.EXIST_DATA(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID))) {
					String game = MySQL.GET_DATA_String(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID), MySQL.Field_Games_Content_GAME_TYPE);
					String gameId = MySQL.GET_DATA_String(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID), MySQL.Field_Games_Content_GAME_ID);
					lore.add(MeneliaAPI.getInstance().getLang(player, "§7Ce joueur est dans le §6" + game, "§7This player is in §6" + game));
					lore.add(MeneliaAPI.getInstance().getLang(player, "§7dans la partie §6" + gameId, "§7in the game §6" + gameId));
				}
				customMPlayerHead.setLore(lore);
				customMPlayerHead.setOwner(result.getString(MySQL.Field_Online_USERNAME));
				PlayerHead.setItemMeta(customMPlayerHead);
				
				inv.setItem(9 + i, PlayerHead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv.setItem(49, back);	
    	
    	player.openInventory(inv);
    	
    	Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if(player.getOpenInventory().getTitle().equals(inv.getName())) {
					AdminPanelPlayers(player);
				}
			}
		}, 100);
	}
	
	@SuppressWarnings("deprecation")
	public void AdminPanelStaff(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§6§rStaff", "§A§6§rStaff"));					
		
		inventoryDefault(inv);			    	
		
		ResultSet result = MySQL.GET_RESULT(MySQL.Table_Online);
		int i = 0;
		try {
			while(result.next()) {
				if(PermissionsEx.getUser(result.getString(MySQL.Field_Online_USERNAME)).has("admin.staff")) {
					i++;
					if(i == 8 || i == 17 || i == 26 || i == 35) {
						i++;
						i++;
					}
					
					ItemStack PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
					//String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, loopPlayerInfos[0], MySQL.Field_Accounts_USERNAME);
					customMPlayerHead.setDisplayName(result.getString(MySQL.Field_Online_USERNAME));
					
					ArrayList<String> lore = new ArrayList<>();
					lore.add("§9" + result.getString(MySQL.Field_Online_SERVER));
					if(MySQL.EXIST_DATA(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID))) {
						String game = MySQL.GET_DATA_String(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID), MySQL.Field_Games_Content_GAME_TYPE);
						String gameId = MySQL.GET_DATA_String(MySQL.Table_Games_Content, MySQL.Field_Games_Content_UUID, result.getString(MySQL.Field_Online_UUID), MySQL.Field_Games_Content_GAME_ID);
						lore.add(MeneliaAPI.getInstance().getLang(player, "§7Ce joueur est dans le §6" + game, "§7This player is in §6" + game));
						lore.add(MeneliaAPI.getInstance().getLang(player, "§7dans la partie §6" + gameId, "§7in the game §6" + gameId));
					}
					customMPlayerHead.setLore(lore);
					customMPlayerHead.setOwner(result.getString(MySQL.Field_Online_USERNAME));
					PlayerHead.setItemMeta(customMPlayerHead);
					
					inv.setItem(9 + i, PlayerHead);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv.setItem(49, back);	
    	
    	player.openInventory(inv);
    	
    	Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if(player.getOpenInventory().getTitle().equals(inv.getName())) {
					AdminPanelStaff(player);
				}
			}
		}, 100);
	}
	
	public void AdminPanelChat(Player player, String playerName) {
		Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "AP >> Déplacer >> " + playerName, "AP >> Move >> " + playerName));
		
		String[] listName = {"§9Usebug", "SpeedHack", "NoFall", "Nuker", "AutoChat/AutoSign", "AutoArmor", "ClickAura/Reach", "Dolphin", "KillAura", "FreeCam", "Criticals", "Miley Circus"};
		String[] listAction = {"tempban", "tempban", "tempban", "tempban", "mute",            "tempban", "tempban",           "tempban", "ban", "tempban", "tempban", "kick"};
		
		int slot = 9;
		for(int i = 0; i <= listName.length; i++) {
			slot++;
			if(slot == 17) {
				slot++;
				slot++;
			}
			ItemStack item1 = new ItemStack(Material.PAPER);
	    	ItemMeta metaItem1 = item1.getItemMeta();
	    	
	    	metaItem1.setDisplayName(listName[i]);
	    	metaItem1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    	metaItem1.setLore(Arrays.asList(listAction[i]));
	    	
	    	item1.setItemMeta(metaItem1);
	    	
	    	inv2.setItem(slot, item1);
		}    		
    	

    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv2.setItem(49, back);	
		
		player.openInventory(inv2);
	}
	
	public void AdminPanelServers(Player player) {
		Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§1§rServeurs", "§A§1§rServers"));
		

		inventoryDefault(inv2);
    	
    	Servers(player, inv2, true);

    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv2.setItem(49, back);	
		
		player.openInventory(inv2);
	}
	
	public void AdminPanelUpdates(Player player) {
		Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§7§rMise a jours", "§A§7§rUpdates"));

		inventoryDefault(inv2);
    	
    	ResultSet result = MySQL.GET_RESULT(MySQL.Table_Updates, "ORDER BY `" + MySQL.Field_Updates_ID + "` DESC");
    	
    	if(!result.equals(null)) {
    		try {
    			int i = 0;
				while(result.next()) {
					i++;
					if(i == 8 || i == 17 || i == 26 || i == 35) {
						i++;
						i++;
					}
					if(i>=35) {
						break;
					}
					ItemStack item = new ItemStack(Material.PAPER);
					ItemMeta meta = item.getItemMeta();
					
					meta.setDisplayName("§3" + result.getString(MySQL.Field_Updates_DATE));
					String message = result.getString(MySQL.Field_Updates_MESSAGE);
					String[] split = message.split(" ");
					ArrayList<String> lore = new ArrayList<>();
					String line = "";
					for(String str: split) {
						if(line.length()<=30) {
							if(line.equals("")) {
								line = str;
							}
							else {
								line = line + " " + str;
							}
						}
						else {
							lore.add("§7" +line);
							line = str;
						}						
					}
					lore.add("§7" + line);
					meta.setLore(lore);
					
					item.setItemMeta(meta);
					
					inv2.setItem(9 + i, item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}

    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv2.setItem(49, back);	
		
		player.openInventory(inv2);
	}
	
	
	@SuppressWarnings("deprecation")
	public void AdminPanelReports(Player player) {
		synchronized (MeneliaAPI.getInstance()) {
			Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§8§rSignalements", "§A§8§rReports"));
	
			inventoryDefault(inv2);
	    	
	    	ResultSet result = MySQL.GET_RESULT(MySQL.Table_Report, "ORDER BY `" + MySQL.Field_Report_STATUS + "`,`" + MySQL.Field_Report_ID + "` DESC");
	    	
	    	if(!result.equals(null)) {
	    		Map<String, Integer> list = new HashMap<String, Integer>();
	    		try {
					while(result.next()) {
						boolean exist = false;
						if(!result.getString(MySQL.Field_Report_STATUS).equals("-1")) {
							for(Entry<String, Integer> data: list.entrySet()) {
								if(data.getKey().equals(result.getString(MySQL.Field_Report_UUID))) {
									exist = true;
									if(result.getString(MySQL.Field_Report_STATUS).equals("0")) {
										int val = list.get(result.getString(MySQL.Field_Report_UUID));
										data.setValue(val + 1);
									}
								}
							}
							if(!exist) {
								if(result.getString(MySQL.Field_Report_STATUS).equals("0")) {
									list.put(result.getString(MySQL.Field_Report_UUID), 1);
								}
								else {
									list.put(result.getString(MySQL.Field_Report_UUID), 0);
								}
							}
						}
					}
					
					MySQL.Truncate("admin_report");
					
					for(Entry<String, Integer> data: list.entrySet()) {
						try {
							PreparedStatement statement = MeneliaAPI.getInstance().MySQLConnection().prepareStatement("INSERT INTO `admin_report` (`id`, `uuid`, `num`) VALUES (NULL, ?,?);");
							statement.setString(1, data.getKey() + "");
							statement.setString(2, data.getValue() + "");
							statement.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					ResultSet result2 = MySQL.GET_RESULT("admin_report", "ORDER BY `num` DESC");
					/*Map<String, Integer> sortedMap = sortByValue(list);
					NavigableMap<String,Integer> map = new TreeMap<String,Integer>();
					
					for(Entry<String, Integer> data: sortedMap.entrySet()) {
						map.put(data.getKey(), data.getValue());
					}
					
				    LinkedHashMap<String, Integer> reverseMap = new LinkedHashMap<String,Integer>();
				    NavigableSet<String> keySet = map.navigableKeySet();
				    Iterator<String> iterator = keySet.descendingIterator();
				    String in;
				    while(iterator.hasNext())
				    {
				        in = iterator.next();
				        reverseMap.put(in,sortedMap.get(in));
				    }*/
				    
				    
					int i = 0;
					while(result2.next()) {
						i++;
						if(i == 8 || i == 17 || i == 26 || i == 35) {
							i++;
							i++;
						}
						if(i>=35) {
							break;
						}
						ItemStack PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
						SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
						String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, result2.getString("uuid"), MySQL.Field_Accounts_USERNAME);
						customMPlayerHead.setDisplayName(username);
						customMPlayerHead.setLore(Arrays.asList(MeneliaAPI.getInstance().getLang(player, "§b" + result2.getString("num") + " §7sont ouvert", "§b" + result2.getString("num") + " §7are open")));
						customMPlayerHead.setOwner(username);
						PlayerHead.setItemMeta(customMPlayerHead);
						
						inv2.setItem(9 + i, PlayerHead);
					}
					MySQL.Truncate("admin_report");
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	
	    	ItemStack back = new ItemStack(Material.ARROW);
	    	ItemMeta metaBack = back.getItemMeta();
	    	
	    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
	    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    	
	    	back.setItemMeta(metaBack);
	    	
	    	inv2.setItem(49, back);	
			
			player.openInventory(inv2);
    	}
	}
	
	/*private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }*/
	
	public void AdminPanelReportsList(Player player, String playerName) {
		Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§9§r" + playerName, "§A§9§r" + playerName));

		inventoryDefault(inv2);
		player.openInventory(inv2);
		
		String uuid = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, playerName, MySQL.Field_Accounts_UUID);
		
    	ResultSet result = MySQL.GET_RESULT(MySQL.Table_Report, "WHERE `" + MySQL.Field_Report_UUID + "` = '" + uuid + "' ORDER BY `" + MySQL.Field_Report_STATUS + "`,`" + MySQL.Field_Report_ID + "` ASC");
    	
    	if(!result.equals(null)) {
    		try {
				int i = 0;
				while(result.next()) {
					i++;
					if(i == 8 || i == 17 || i == 26 || i == 35) {
						i++;
						i++;
					}
					if(i>=35) {
						break;
					}
					ItemStack item = null;
					if(result.getString(MySQL.Field_Report_STATUS).equals("0")) {
						item = new ItemStack(org.bukkit.Material.PAPER);
					}
					else {
						item = new ItemStack(org.bukkit.Material.STAINED_GLASS_PANE, 1, (short) 14);
					}
					ItemMeta meta = item.getItemMeta();
					String uuidTraite = result.getString(MySQL.Field_Report_TRAITE);
					String traiteName = "";
					if(uuidTraite.equals("none")) {
						traiteName = MeneliaAPI.getInstance().getLang(player, "personne", "nobody");
					}
					else {
						traiteName = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, uuidTraite, MySQL.Field_Accounts_USERNAME);
					}
					meta.setDisplayName(result.getString(MySQL.Field_Report_ID));
					
					ArrayList<String> lore = new ArrayList<>();
					lore.add(MeneliaAPI.getInstance().getLang(player, "§7Traité par §b" + traiteName, "§7Processed by §b" + traiteName));
					if(!result.getString(MySQL.Field_Report_STATUS).equals("0")) {
						lore.add(MeneliaAPI.getInstance().getLang(player, "§cFermé", "§cClosed"));
					}
					meta.setLore(lore);
					item.setItemMeta(meta);
					
					inv2.setItem(9 + i, item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}

    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv2.setItem(49, back);	
		
		player.openInventory(inv2);
	}
	
	@SuppressWarnings("deprecation")
	public void AdminPanelReportOption(Player player, String id) {
		try {
			Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§1§0§rReport #" + id, "§A§1§0§rReport #" + id));
	
			inventoryDefault(inv2);
			
			ResultSet result = MySQL.GET_RESULT(MySQL.Table_Report, MySQL.Field_Report_ID, id);		
			
			while(result.next()) {
				ItemStack PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				SkullMeta customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
				//String reporterUuid = MySQL.GET_DATA_String(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_REPORTER);
				String reporterUuid = result.getString(MySQL.Field_Report_REPORTER);
				String reporterUsername = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, reporterUuid, MySQL.Field_Accounts_USERNAME);
				customMPlayerHead.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Signalement par", "§7Reported by"));
				customMPlayerHead.setLore(Arrays.asList("§b" + reporterUsername));
				customMPlayerHead.setOwner(reporterUsername);
				PlayerHead.setItemMeta(customMPlayerHead);
				
				inv2.setItem(21, PlayerHead);
				
				
				
				PlayerHead = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				customMPlayerHead = (SkullMeta)PlayerHead.getItemMeta();
				//String uuid = MySQL.GET_DATA_String(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_UUID);
				String uuid = result.getString(MySQL.Field_Report_UUID);
				String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, uuid, MySQL.Field_Accounts_USERNAME);
				customMPlayerHead.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Le signalement parle de", "§7The report is about"));
				customMPlayerHead.setLore(Arrays.asList("§b" + username));
				customMPlayerHead.setOwner(username);
				PlayerHead.setItemMeta(customMPlayerHead);
				
				inv2.setItem(23, PlayerHead);
				
				
				
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta meta = item.getItemMeta();
				
				//String date = MySQL.GET_DATA_String(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_DATE);
				String date = result.getString(MySQL.Field_Report_DATE);
				SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
				Date theDate = null;
				try {
					theDate = dateFormatter1.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				SimpleDateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
				String stringDate = dateFormatter2.format(theDate);
				
				meta.setDisplayName("§3" + stringDate);
				//String message = MySQL.GET_DATA_String(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_REASON);
				String message = result.getString(MySQL.Field_Report_REASON);
				String[] split = message.split(" ");
				ArrayList<String> lore = new ArrayList<>();
				String line = "";
				for(String str: split) {
					if(line.length()<=30) {
						if(line.equals("")) {
							line = str;
						}
						else {
							line = line + " " + str;
						}
					}
					else {
						lore.add("§7" +line);
						line = str;
					}						
				}
				lore.add("§7" + line);
				meta.setLore(lore);
				item.setItemMeta(meta);
				
				inv2.setItem(22, item);
				
				int status = result.getInt(MySQL.Field_Report_STATUS);
				
				item = null;
				if(status == 0) {
					item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
				}
				else {
					item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
				}
				meta = item.getItemMeta();
				if(status == 0) {
					meta.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§cFermer ce signalement", "§cClose this report"));
				}
				else {
					meta.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§cRéouvrir ce signalement", "§cReopen this report"));
				}
				item.setItemMeta(meta);
				
				inv2.setItem(40, item);
				
				
				
		    	ItemStack back = new ItemStack(Material.ARROW);
		    	ItemMeta metaBack = back.getItemMeta();
		    	
		    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
		    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		    	
		    	back.setItemMeta(metaBack);
		    	
		    	inv2.setItem(49, back);	
				
				player.openInventory(inv2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void AdminPanelServersConfig(Player player, String serverName) {
		Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§2§r" + serverName, "§A§2§r" + serverName));
		

		inventoryDefault(inv2);
    	
    	ItemStack item1 = new ItemStack(Material.EMERALD_BLOCK);
    	ItemMeta metaItem1 = item1.getItemMeta();
    	
    	metaItem1.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§aRedémarer le serveur", "§aRestart the server"));
    	metaItem1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item1.setItemMeta(metaItem1);
    	
    	inv2.setItem(10, item1);

    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv2.setItem(49, back);	
		
		player.openInventory(inv2);
	}
	
	public void AdminPanelPlayerOption(Player player, String playerName) {
		Inventory inv = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§4§r" + playerName, "§A§4§r" + playerName));					
		
		
		ItemStack panel = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
    	ItemMeta metaPanel = panel.getItemMeta();
    	
    	metaPanel.setDisplayName("§a");
    	
    	panel.setItemMeta(metaPanel);
    	
    	for(int i = 0; i<54; i++) {
    		inv.setItem(i, panel);
    	}
		
		
		ItemStack item1 = new ItemStack(Material.PAPER);
    	ItemMeta metaItem1 = item1.getItemMeta();
    	
    	
    	metaItem1.setDisplayName("§9" + playerName);
    	metaItem1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	String uuid = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, playerName, MySQL.Field_Accounts_UUID);
    	int lang = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, playerName, MySQL.Field_Accounts_LANGUAGE);
    	int moneyBase = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, playerName, MySQL.Field_Accounts_MONEY_BASE);
    	int moneSpecial = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, playerName, MySQL.Field_Accounts_MONEY_SPECIAL);
    	String stringLang = "English";
    	if(lang == 1) {
    		stringLang = "Français";
    	}
    	ArrayList<String> lore = new ArrayList<>();
    	lore.add("§7Uuid : " + uuid);
    	lore.add("§7Lang : " + stringLang);
    	lore.add("§7Astolys : " + moneyBase);
    	lore.add("§7Staralys : " + moneSpecial);
    	metaItem1.setLore(lore);
    	
    	item1.setItemMeta(metaItem1);
    	
    	inv.setItem(13, item1);
		
		ItemStack item2 = new ItemStack(Material.GRASS);
    	ItemMeta metaItem2 = item2.getItemMeta();
    	
    	metaItem2.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Déplacer le joueur", "§9Move the player"));
    	metaItem2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item2.setItemMeta(metaItem2);
    	
    	inv.setItem(40, item2);
		
		ItemStack item3 = new ItemStack(Material.GRASS);
    	ItemMeta metaItem3 = item3.getItemMeta();
    	
    	metaItem3.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§9Expulser le joueur", "§9Kick the player"));
    	metaItem3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item3.setItemMeta(metaItem3);
    	
    	inv.setItem(19, item3);
		
		ItemStack item4 = new ItemStack(Material.SIGN);
    	ItemMeta metaItem4 = item4.getItemMeta();
    	
    	metaItem4.setDisplayName("§9Chat");
    	metaItem4.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	item4.setItemMeta(metaItem4);
    	
    	inv.setItem(28, item4);
		
    	ItemStack back = new ItemStack(Material.ARROW);
    	ItemMeta metaBack = back.getItemMeta();
    	
    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	
    	back.setItemMeta(metaBack);
    	
    	inv.setItem(49, back);
    	
    	player.openInventory(inv);
	}
	
	@EventHandler
	public void OnClickAdminPanel(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase("AdminPanel")) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Serveurs", "§9Servers"))) {				
				AdminPanelServers(player);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Joueurs", "§9Players"))) {
				AdminPanelPlayers(player);
				//MeneliaAPI.getInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Players.Request", false, null);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Staff", "§9Staff"))) {
				AdminPanelStaff(player);
				//MeneliaAPI.getInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Staff.Request", false, null);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Mise a jours", "§9Updates"))) {
				AdminPanelUpdates(player);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Signalements", "§9Reports"))) {
				AdminPanelReports(player);
			}			
		}
	}
	
	@EventHandler
	public void OnClickAdminPanelReportsOption(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().contains(MeneliaAPI.getInstance().getLang(player, "§A§1§0§r", "§A§1§0§r"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			String id = inv.getName().replace("§A§1§0§rReport #", "");
			String playerUuid = MySQL.GET_DATA_String(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_UUID);
			String playerName = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, playerUuid, MySQL.Field_Accounts_USERNAME);
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelReportsList(player, playerName);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§cFermer ce signalement", "§cClose this report"))) {
				MySQL.UPDATE_DATA(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_STATUS, "1");
				MySQL.UPDATE_DATA(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_TRAITE, player.getUniqueId().toString());
				AdminPanelReportOption(player, id);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§cRéouvrir ce signalement", "§cReopen this report"))) {
				MySQL.UPDATE_DATA(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_STATUS, "0");
				MySQL.UPDATE_DATA(MySQL.Table_Report, MySQL.Field_Report_ID, id, MySQL.Field_Report_TRAITE, player.getUniqueId().toString());
				AdminPanelReportOption(player, id);
			}
		}
	}
	
	@EventHandler
	public void OnClickAdminPanelReportsList(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().contains(MeneliaAPI.getInstance().getLang(player, "§A§9§r", "§A§9§r"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelReports(player);
			}
			else if(current.getType().equals(Material.PAPER) || current.getType().equals(Material.STAINED_GLASS_PANE)) {
				AdminPanelReportOption(player, current.getItemMeta().getDisplayName());
			}
		}
	}
	
	@EventHandler
	public void OnClickAdminPanelReports(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase(MeneliaAPI.getInstance().getLang(player, "§A§8§rSignalements", "§A§8§rReports"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelInv(player);
			}
			else if(current.getType().equals(Material.SKULL_ITEM)) {
				AdminPanelReportsList(player, current.getItemMeta().getDisplayName());
			}
		}
	}
		
	@EventHandler
	public void OnClickAdminPanelUpdates(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase(MeneliaAPI.getInstance().getLang(player, "§A§7§rMise a jours", "§A§7§rUpdates"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelInv(player);
			}
		}
	}
	
	@EventHandler
	public void OnClickAdminPanelStaff(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase(MeneliaAPI.getInstance().getLang(player, "§A§6§rStaff", "§A§6§rStaff"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelInv(player);
			}
		}
	}
	
	@EventHandler
	public void OnClickAdminPanelServers(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase(MeneliaAPI.getInstance().getLang(player, "§A§1§rServeurs", "§A§1§rServers"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelInv(player);			
			}
			else if(current.getType().equals(Material.GRASS)) {
				if(event.isLeftClick()) {
					MeneliaAPI.getBungeecordInstance().sendToServer(player, current.getItemMeta().getDisplayName());
				}
				else {
					AdminPanelServersConfig(player, current.getItemMeta().getDisplayName());
				}
			}

		}
	}
		
	@EventHandler
	public void OnClickAdminPanelServersConfig(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
					
		if(inv.getName().contains(MeneliaAPI.getInstance().getLang(player, "§A§2§r", "§A§2§r"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			String serverName = inv.getName().replace(MeneliaAPI.getInstance().getLang(player, "§A§2§r", "§A§2§r"), "");
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelServers(player);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§aRedémarer le serveur", "§aRestart the server"))) {
				String[] playerSelected = {serverName};
				MeneliaAPI.getBungeecordInstance().SendPluginMessage(player, serverName, "MeneliaChannel", "AdminPanel_Server_Restart.Request", true, playerSelected);
			}
		}
		

	}
		
	@EventHandler
	public void OnClickAdminPanelPlayers(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().equalsIgnoreCase(MeneliaAPI.getInstance().getLang(player, "§A§3§rJoueurs", "§A§3§rPlayers"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelInv(player);
			}
			else if(current.getType().equals(Material.SKULL_ITEM)){
				//String[] playerSelected = {current.getItemMeta().getDisplayName()};
				//MeneliaAPI.getInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Player.Request", true, playerSelected);
				String playerName = current.getItemMeta().getDisplayName();
				AdminPanelPlayerOption(player, playerName);
			}
		}

	}
		
	@EventHandler
	public void OnClickAdminPanelPlayersOption(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
			
		if(inv.getName().contains(MeneliaAPI.getInstance().getLang(player, "§A§4§r", "§A§4§r"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			String playerName = inv.getName().replace(MeneliaAPI.getInstance().getLang(player, "§A§4§r", "§A§4§r"), "");
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelPlayers(player);
				//MeneliaAPI.getInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Players.Request", false, null);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Expulser le joueur", "§9Kick the player"))) {
				String[] playerSelected = {playerName, "none"};
				MeneliaAPI.getBungeecordInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Player_Kick.Request", true, playerSelected);
			}
			else if(current.getItemMeta().getDisplayName().equals("§9Hub")) {
				AdminPanelChat(player, playerName);
			}
			else if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§9Déplacer le joueur", "§9Move the player"))) {
				Inventory inv2 = Bukkit.createInventory(null, 54, MeneliaAPI.getInstance().getLang(player, "§A§5§r" + playerName, "§A§5§r" + playerName));

				inventoryDefault(inv2);
					    	
		    	Servers(player, inv2, false);
				

		    	ItemStack back = new ItemStack(Material.ARROW);
		    	ItemMeta metaBack = back.getItemMeta();
		    	
		    	metaBack.setDisplayName(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"));
		    	metaBack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		    	
		    	back.setItemMeta(metaBack);
		    	
		    	inv2.setItem(49, back);	
				
				player.openInventory(inv2);
			}
		}
	}
		
	@EventHandler
	public void OnClickAdminPanelPlayersOptionMove(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(inv.getName().contains(MeneliaAPI.getInstance().getLang(player, "§A§5§r", "§A§5§r"))) {
			
			if(MeneliaAPI.getFunctions().isEmpty(current)) return;
			if(MeneliaAPI.getFunctions().isEmpty(current.getItemMeta())) return;			
			
			event.setCancelled(true);
			String playerName = inv.getName().replace(MeneliaAPI.getInstance().getLang(player, "§A§5§r", "§A§5§r"), "");
			if(current.getItemMeta().getDisplayName().equals(MeneliaAPI.getInstance().getLang(player, "§7Retour", "§7Back"))) {
				AdminPanelPlayerOption(player, playerName);
			}
			else if(current.getType().equals(Material.GRASS)) {
				String[] playerSelected = {playerName, current.getItemMeta().getDisplayName()};
				MeneliaAPI.getBungeecordInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Player_Move.Request", true, playerSelected);
			}
		}
		
	}

}
