package fr.gameurduxvi.meneliaapi.EventListeners;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.Databases.Accounts;
import fr.gameurduxvi.meneliaapi.Databases.Stats.TNTSmash;
import fr.gameurduxvi.meneliaapi.EventHandlers.AccountLoadedEvent;
import fr.gameurduxvi.meneliaapi.EventHandlers.JoinGameEvent;
import fr.gameurduxvi.meneliaapi.MySQL.MySQL;

public class EventListener implements Listener{
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		MeneliaAPI.getInstance().SendPluginMessage(e.getPlayer(), Bukkit.getServerName(), "MeneliaChannel", "GetProfile", false, null);
		BukkitRunnable task = new BukkitRunnable() {
			
			boolean isCanceled = false;
			int i = 0;
			int total = 0;
			@Override
			public void run() {
				total++;
				i++;
				if(i >= 50) {
					i = 0;
					Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(MeneliaAPI.getInstance().pluginPrefix + " &cMaking another request !"));
					MeneliaAPI.getInstance().SendPluginMessage(e.getPlayer(), Bukkit.getServerName(), "MeneliaChannel", "GetProfile", false, null);
				}
				for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
					if(account.getName().equals(e.getPlayer().getName())) {
						AccountLoadedEvent event = new AccountLoadedEvent(account);
						Bukkit.getPluginManager().callEvent(event);
						isCanceled = true;
					}
				}
				if(isCanceled) {
					Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(MeneliaAPI.getInstance().pluginPrefix + " &a" + e.getPlayer().getName() + " has been found ! (after " + total*2 + " ticks)"));
					cancel();
				}
			}
		};
		task.runTaskTimer(MeneliaAPI.getInstance(), 0, 2);
	}*/
	
	/*@EventHandler
	public void test(JoinGameEvent e) {
		Bukkit.broadcastMessage(e.getUuid());
	}*/
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {		
		e.setJoinMessage(null);
		
		if(MySQL.EXIST_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, e.getPlayer().getUniqueId().toString())) {
			MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, e.getPlayer().getName());
			MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LAST_CONNEXION_HOUR, MeneliaAPI.getFunctions().getHourFromDate(new Date(), ":", true));
			MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_IP, e.getPlayer().getAddress().getAddress().getHostAddress());
			MySQL.readAccount(e.getPlayer(), true);
			Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LAST_CONNEXION_DATE, "online");
				}
			}, 20);
		}
		else {
			Accounts account = new Accounts(e.getPlayer().getUniqueId(), e.getPlayer().getName(), "EN", 0, 0);
			MySQL.INSERT_ACCOUNT(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName(), 0, 0, 0, MeneliaAPI.getFunctions().getDateFromDate(new Date(), "-"), MeneliaAPI.getFunctions().getHourFromDate(new Date(), ":", true), e.getPlayer().getAddress().getAddress().getHostAddress());
			AccountLoadedEvent event = new AccountLoadedEvent(account);
			Bukkit.getPluginManager().callEvent(event);
		}
		
		if(MySQL.EXIST_DATA(MySQL.Table_Stats_TNTSmash, MySQL.Field_Stats_TNTSmash_UUID, e.getPlayer().getUniqueId().toString())) {
			MySQL.readStatTNTSmash(e.getPlayer().getUniqueId().toString());
		}
		else {
			MySQL.INSERT_TNTSMASH(e.getPlayer().getUniqueId().toString(), 0, 0, 0, 0, 0, 0, 0, 0);
		}
		
		if(MySQL.EXIST_DATA(MySQL.Table_Game_Joiner, MySQL.Field_Game_Joiner_UUID, e.getPlayer().getUniqueId().toString())) {
			if(MySQL.GET_DATA_String(MySQL.Table_Game_Joiner, MySQL.Field_Game_Joiner_UUID, e.getPlayer().getUniqueId().toString(), MySQL.Field_Game_Joiner_SERVER).equals(Bukkit.getServerName())) {
				String gameName = MySQL.GET_DATA_String(MySQL.Table_Game_Joiner, MySQL.Field_Game_Joiner_UUID, e.getPlayer().getUniqueId().toString(), MySQL.Field_Game_Joiner_GAME_NAME);
				int gameNumber = MySQL.GET_DATA_Int(MySQL.Table_Game_Joiner, MySQL.Field_Game_Joiner_UUID, e.getPlayer().getUniqueId().toString(), MySQL.Field_Game_Joiner_GAME_NUMBER);
				JoinGameEvent event = new JoinGameEvent(e.getPlayer(), gameName, gameNumber);
				Bukkit.getPluginManager().callEvent(event);
				MySQL.DELETE_DATA(MySQL.Table_Game_Joiner, MySQL.Field_Game_Joiner_UUID, e.getPlayer().getUniqueId().toString());
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LAST_CONNEXION_DATE, MeneliaAPI.getFunctions().getDateFromDate(new Date(), "-"));
		MySQL.UPDATE_DATA(e.getPlayer().getUniqueId().toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LAST_CONNEXION_HOUR, MeneliaAPI.getFunctions().getHourFromDate(new Date(), ":", true));
		
		if(!MeneliaAPI.getInstance().getAccounts().isEmpty())
		{
			int i = 0;
			for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
				if(account.getUUID().equals(e.getPlayer().getUniqueId())) {
					break;
				}
				i++;
			}
			MeneliaAPI.getInstance().getAccounts().remove(MeneliaAPI.getInstance().getAccounts().get(i));
		}
		
		if(!MeneliaAPI.getInstance().getStatTNTSmash().isEmpty())
		{
			int i = 0;
			for(TNTSmash data: MeneliaAPI.getInstance().getStatTNTSmash()) {
				if(data.getUuid().equals(e.getPlayer().getUniqueId().toString())) {
					break;
				}
				i++;
			}
			MeneliaAPI.getInstance().getStatTNTSmash().remove(MeneliaAPI.getInstance().getStatTNTSmash().get(i));
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().equalsIgnoreCase("/helpop")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(e.getPlayer()));
			//e.getPlayer().performCommand("/notexistcommand");
		}
	}
}
