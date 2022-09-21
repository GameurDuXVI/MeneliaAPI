package fr.gameurduxvi.meneliaapi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MySQL.MySQL;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Functions {

	@SuppressWarnings("deprecation")
	public String getGroup(Player player) {
		String highGroup = null;
		int highPriority = 20;
		for(String group: PermissionsEx.getUser(player).getGroupNames()) {
			int priority;
			priority = Integer.parseInt(PermissionsEx.getPermissionManager().getGroup(group).getOption("priority"));
			if(highPriority>priority){
				highGroup = group;
				highPriority = priority;
			}
		}
		return highGroup;
	}
	
	public void logInfo(String fileName, String text) {
		try(FileWriter fw = new FileWriter("plugins/Menelia/logs/" + fileName + ".log", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw))
		{
			out.println("[" + getDateFromDate(new Date(), "/") + " " + getHourFromDate(new Date(), true) + " INFO] " + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void logWarning(String fileName, String text) {
		try(FileWriter fw = new FileWriter("plugins/Menelia/logs/" + fileName + ".log", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw))
		{
			out.println("[" + getDateFromDate(new Date(), "/") + " " + getHourFromDate(new Date(), true) + " WARNING] " + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getNoPermissionMessage(Player player) {
		return MeneliaAPI.getInstance().getLang(player, "§cVous n'avez pas la permission !", "§cYou don't have the permission !");
	}
	
	
	
	
		
	
	
	
	
	public boolean isEmpty(Object obj) {
		try {
			if(!obj.equals(null)) {
				return false;
			}
		} catch (NullPointerException e) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	public void refreshAccounts() {
		MeneliaAPI.getInstance().getAccounts().clear();
		Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p: Bukkit.getOnlinePlayers()) {
					MySQL.readAccount(p, false);
				}
			}
		}, 20);
	}
	
	
	
	
	public void refreshStatsTNTSmash() {
		MeneliaAPI.getInstance().getAccounts().clear();
		Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p: Bukkit.getOnlinePlayers()) {
					MySQL.readStatTNTSmash(p.getUniqueId().toString());
				}
			}
		}, 20);
	}
	
	
	
	
	public String getDateFromDate(Date date) {
		String stringBetweenDate = "/";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd" + stringBetweenDate + "MM" + stringBetweenDate + "yyyy");
		String stringDate = dateFormatter.format(c.getTime());
		return stringDate;		
	}
	
	
	
	
	public String getDateFromDate(Date date, String betweenDate) {
		String stringBetweenDate = "/";
		if(!betweenDate.equals("")) {
			stringBetweenDate = betweenDate;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd" + stringBetweenDate + "MM" + stringBetweenDate + "yyyy");
		String stringDate = dateFormatter.format(c.getTime());
		return stringDate;		
	}
	
	
	
	
	public String getHourFromDate(Date date, String betweenHour, boolean includeSeconds) {
		String stringBetweenHour = ":";
		if(!betweenHour.equals("")) {
			stringBetweenHour = betweenHour;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(includeSeconds) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm" + stringBetweenHour + "ss");
			return dateFormatter.format(c.getTime());
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm");
		return dateFormatter.format(c.getTime());
	}
	
	
	
	
	public String getHourFromDate(Date date, boolean includeSeconds) {
		String stringBetweenHour = ":";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(includeSeconds) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm" + stringBetweenHour + "ss");
			return dateFormatter.format(c.getTime());
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH" + stringBetweenHour + "mm");
		return dateFormatter.format(c.getTime());
	}
	
	
	
	
	public String argument(String[] args, int i) {
		if(args.length >= i) {
			return args[i-1];
		}
		return "";
	}
}
