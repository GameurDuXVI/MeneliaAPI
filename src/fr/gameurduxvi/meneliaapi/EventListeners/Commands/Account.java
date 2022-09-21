package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.Databases.Accounts;
import fr.gameurduxvi.meneliaapi.EventHandlers.AccountRefreshedEvent;
import fr.gameurduxvi.meneliaapi.MySQL.MySQL;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Account implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("account")) {
				if(!PermissionsEx.getUser(player).has("admin.account")) {
					player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
					return false;
				}
				
				String entireCommand = "";
				for(String t: args) {
					entireCommand = entireCommand + " " + t;
				}
				
				if(args.length >=1) { 
					if(MySQL.EXIST_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 1))) {
						
						MeneliaAPI.getFunctions().logInfo("command_account", player.getName() + " executed the command: /account" + entireCommand);
						
						String username = MeneliaAPI.getFunctions().argument(args, 1);
						String arg2 = MeneliaAPI.getFunctions().argument(args, 2);
						String arg3 = MeneliaAPI.getFunctions().argument(args, 3);
						String arg4 = MeneliaAPI.getFunctions().argument(args, 4);
						
						if(arg2.equals("lang")) {
							
							if(arg3.equals("set")) {
								if(!PermissionsEx.getUser(player).has("admin.account.lang.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								
								if(arg4.equalsIgnoreCase("FR")) {
									
									MySQL.UPDATE_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_LANGUAGE, "1");
									
									player.sendMessage("§7The language of §6" + username + "§7 has been changed to §bFR");
									
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setLang("FR");
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
									
								}
								else if(arg4.equalsIgnoreCase("EN")) {
									
									MySQL.UPDATE_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_LANGUAGE, "0");
									
									player.sendMessage("§7The language of §6" + MeneliaAPI.getFunctions().argument(args, 1) + "§7 has been changed to §bEN");
									
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setLang("EN");
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
									
								}
								else {
									
									player.sendMessage("§7Choose between §bEN §7and §bFR");
								
								}
							}
							else {
								int lang = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 1), MySQL.Field_Accounts_LANGUAGE);
								String stringLang = "EN";
								if(lang == 1) stringLang = "FR";
								player.sendMessage("§e===================================================");
								player.sendMessage("§7The language of §6" + username + "§7 is §b" + stringLang);
								player.sendMessage("§1");
								player.sendMessage("§7To change the language of this player:");
								player.sendMessage("§7/account " + username + " lang §bset <EN/FR>");
								player.sendMessage("§e===================================================");
							}
						}
						else if(arg2.equals("astrolys")) {
							
							if(arg3.equals("set")) {
								if(!PermissionsEx.getUser(player).has("admin.account.astrolys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.UPDATE_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_BASE, "" + arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneyBase(arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else if(arg3.equals("add")) {
								if(!PermissionsEx.getUser(player).has("admin.account.astrolys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.ADD_INT(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_BASE, arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneyBase(account.getMoneyBase() + arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else if(arg3.equals("remove")) {
								if(!PermissionsEx.getUser(player).has("admin.account.astrolys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.REMOVE_INT(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_BASE, arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneyBase(account.getMoneyBase() - arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else {
								int money = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_BASE);
								player.sendMessage("§e===================================================");
								player.sendMessage("§6" + username + "§7 has §b" + money + " astrolys");
								player.sendMessage("§1");
								player.sendMessage("§7To change the amount of this player:");
								player.sendMessage("§7/account " + username + " astrolys §badd <amount>");
								player.sendMessage("§7/account " + username + " astrolys §bset <amount>");
								player.sendMessage("§7/account " + username + " astrolys §bremove <amount>");
								player.sendMessage("§e===================================================");
							}
						}
						else if(arg2.equals("staralys")) {
							
							if(arg3.equals("set")) {
								if(!PermissionsEx.getUser(player).has("admin.account.staralys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.UPDATE_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_SPECIAL, "" + arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneySpecial(arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else if(arg3.equals("add")) {
								if(!PermissionsEx.getUser(player).has("admin.account.staralys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.ADD_INT(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_SPECIAL, arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneySpecial(account.getMoneySpecial() + arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else if(arg3.equals("remove")) {
								if(!PermissionsEx.getUser(player).has("admin.account.staralys.change")) {
									player.sendMessage(MeneliaAPI.getFunctions().getNoPermissionMessage(player));
									return false;
								}
								String onlyNumber = arg4.replaceAll("[^0-9]", "");
								if(onlyNumber.length() != 0) {
									int arg4Int = Integer.valueOf(onlyNumber);
									MySQL.REMOVE_INT(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_SPECIAL, arg4Int);
									if(MeneliaAPI.getInstance().getAccounts().size() > 0) {
										for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
											if(account.getName().equals(username)) {
												account.setMoneySpecial(account.getMoneySpecial() - arg4Int);
												AccountRefreshedEvent event = new AccountRefreshedEvent(account);
												Bukkit.getPluginManager().callEvent(event);
											}
										}
									}
								}
							}
							else {
								int money = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_SPECIAL);
								player.sendMessage("§e===================================================");
								player.sendMessage("§6" + username + "§7 has §b" + money + " staralys");
								player.sendMessage("§1");
								player.sendMessage("§7To change the amount of this player:");
								player.sendMessage("§7/account " + username + " staralys §badd <amount>");
								player.sendMessage("§7/account " + username + " staralys §bset <amount>");
								player.sendMessage("§7/account " + username + " staralys §bremove <amount>");
								player.sendMessage("§e===================================================");
							}
						}
						else if(arg2.equals("info")) {
							//String ip = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_IP);
							String last_connected_hour = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_LAST_CONNEXION_HOUR);
							String last_connected_date = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_LAST_CONNEXION_DATE);
							int lang = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_LANGUAGE);
							int moneyBase = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_BASE);
							int moneySpecial = MySQL.GET_DATA_Int(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_MONEY_SPECIAL);
							String uuid = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, username, MySQL.Field_Accounts_UUID);
							String langString = "";
							if(lang == 1) {
								langString = "Français";
							}
							else {
								langString = "English";
							}
							
							player.sendMessage("§e===================================================");
							player.sendMessage("§8Username: §6" + username + " §8(§b" + langString + "§8)");
							player.sendMessage("§8UUID: §6" + uuid);
							player.sendMessage("§8Astrolys: §b" + moneyBase);
							player.sendMessage("§8Staralys: §b" + moneySpecial);
							//player.sendMessage("§8IP: §b" + ip);
							if(last_connected_date.contains("online")) {
								player.sendMessage("§8Is currently §bonline");
							}
							else {
								player.sendMessage("§8Last connected on §b" + last_connected_date + "§7 at §b" + last_connected_hour);
							}
							player.sendMessage("§e===================================================");
						}
						else {
							player.sendMessage("");
							player.sendMessage("§7Available commands:");
							player.sendMessage("§7/account " + username + " §binfo");
							player.sendMessage("§7/account " + username + " §blang");
							player.sendMessage("§7/account " + username + " §bastrolys");
							player.sendMessage("§7/account " + username + " §bstaralys");
						}
					}
					else {
						player.sendMessage("");
						player.sendMessage("§7Account for §6" + MeneliaAPI.getFunctions().argument(args, 1) + "§7 don't exist");
					}
				}
				else {
					player.sendMessage("");
					player.sendMessage("§7/account §b<username> ...");
				}
			}
		}
		return false;
	}
}
