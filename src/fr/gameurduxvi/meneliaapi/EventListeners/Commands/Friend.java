package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.MySQL.MySQL;

public class Friend implements CommandExecutor {
	private String prefix = "§e[Friend] ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("friend")) {
				if(MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("add") || MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("a")) {
					if(args.length >=2) {
						String username = MeneliaAPI.getFunctions().argument(args, 2);
						if(username.equals(player.getName())) {
							player.sendMessage("");
							player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous ne pouvez pas être ami avec vous même", "§7You can't be your own friend"));
							return false;
						}
						if(MySQL.EXIST_DATA(MySQL.Table_Online, MySQL.Field_Online_USERNAME, username)) {
							String uuid1 = player.getUniqueId().toString();
							String uuid2 = MySQL.GET_DATA_String(MySQL.Table_Online, MySQL.Field_Online_USERNAME, username, MySQL.Field_Online_UUID);
							if(existAsFriend(uuid1, uuid2)) {
								player.sendMessage("");
								player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous êtes déja ami avec ce joueur", "§7You are already friend with this player"));
							}
							else {
								//player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7Vous n'êtes pas ami avec ce joueur", "§7You are not friends with this player"));
								if(existAsRequestFriend(uuid1, uuid2)) {
									player.sendMessage("");
									player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous avez déja envoyé un invitation récemment", "§7You have already sent an invitation recently"));
								}
								else {
									player.sendMessage("");
									player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Requête envoyée", "§7Request sent"));
									String[] playerSelected = {uuid2, "test"};
									MeneliaAPI.getBungeecordInstance().SendPluginMessage(Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Player_Message.Action", true, playerSelected);
									MySQL.INSERT_FRIEND(uuid1, uuid2);
									Bukkit.getScheduler().runTaskLater(MeneliaAPI.getInstance(), new Runnable() {
										
										@Override
										public void run() {
											String[] fieldWhere = {MySQL.Field_Friends_UUID1, MySQL.Field_Friends_UUID2};
											String[] valueWhere = {uuid1, uuid2};
											ResultSet result = MySQL.GET_RESULT(MySQL.Table_Friends, fieldWhere, valueWhere);
											try {
												while(result.next()) {
													if(result.getString(MySQL.Field_Friends_STATUS).equals("0")) {
														player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Aucune réponse de §6" + username, "§7No response from §6" + username));
														MySQL.DELETE_DATA(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid1);
													}
												}
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
									}, 10*20);
								}
							}
						}
						else {
							player.sendMessage("");
							player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7Ce joueur n'est pas en ligne", "§7This player isn't online"));
						}
					}
					else {
						player.sendMessage("");
						player.sendMessage("§7/friend add §b<username>");
					}
				}
				else if(MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("list")) {
					if(hasFriends(player.getUniqueId().toString())) {
						int amount = getAmountFriends(player.getUniqueId().toString());
						if(args.length >=2) {
							String onlyNumber = MeneliaAPI.getFunctions().argument(args, 2).replaceAll("[^0-9]", "");
							if(onlyNumber.length()>0) {
								double number = amount / 5.0;
								int numberArg = Integer.parseInt(onlyNumber);
								if(numberArg <= Math.ceil(number)) {
									int i = 0;
									player.sendMessage("");
									player.sendMessage("§e===================================================");
									player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§6Page " + numberArg + " sur " + (int) Math.ceil(number), "§6Page " + numberArg + " on " + (int) Math.ceil(number)));
									player.sendMessage("");
									for(String uuid: getFriends(player.getUniqueId().toString())) {
										i++;
										if(i > ((numberArg-1) * 5) && i <= (numberArg * 5)) {
											String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, uuid, MySQL.Field_Accounts_USERNAME);
											String color = "§7";
											if(MySQL.EXIST_DATA(MySQL.Table_Online, MySQL.Field_Online_UUID, uuid)) {
												color = "§b";
											}
											player.sendMessage(color + username);
										}
									}
									player.sendMessage("§e===================================================");
								}
								else {
									player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Il n'y a que " + (int) Math.ceil(number) + " pages", "§7There are only " + (int) Math.ceil(number) + " pages"));
								}
							}
							else {
								player.sendMessage("§7/friend list <number>");
							}
						}
						else {
							double number = amount / 5.0;
							int i = 0;
							player.sendMessage("");
							player.sendMessage("§e===================================================");
							player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§6Page 1 sur " + (int) Math.ceil(number), "§6Page 1 on " + (int) Math.ceil(number)));
							player.sendMessage("");
							for(String uuid: getFriends(player.getUniqueId().toString())) {
								i++;
								if(i<=5) {
									String username = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_UUID, uuid, MySQL.Field_Accounts_USERNAME);
									String color = "§7";
									if(MySQL.EXIST_DATA(MySQL.Table_Online, MySQL.Field_Online_UUID, uuid)) {
										color = "§b";
									}
									player.sendMessage(color + username);
								}
							}
							player.sendMessage("§e===================================================");
						}
					}
					else {
						player.sendMessage("");
						player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous n'avez pas encore d'ami", "§7You do not have any friends yet"));
					}
				}
				else if(MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("accept")) {
					if(args.length >=2) {
						String username = MeneliaAPI.getFunctions().argument(args, 2);
						if(username.equals(player.getName())) {
							player.sendMessage("");
							player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous ne pouvez pas être ami avec vous même", "§7You can't be your own friend"));
							return false;
						}
						if(MySQL.EXIST_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 2))) {
							String uuid2 = MySQL.GET_DATA_String(MySQL.Table_Online, MySQL.Field_Online_USERNAME, username, MySQL.Field_Online_UUID);
							String[] fieldWhere = {MySQL.Field_Friends_UUID1, MySQL.Field_Friends_UUID2};
							String[] value = {uuid2, player.getUniqueId().toString()};
							if(MySQL.EXIST_DATA(MySQL.Table_Friends, fieldWhere, value)) {
								ResultSet result = MySQL.GET_RESULT(MySQL.Table_Friends, fieldWhere, value);
								try {
									if(result.next()) {
										if(result.getString(MySQL.Field_Friends_STATUS).equals("0")) {
											MySQL.UPDATE_DATA(MySQL.Table_Friends, MySQL.Field_Friends_ID, result.getString(MySQL.Field_Friends_ID), MySQL.Field_Friends_STATUS, "1");
											player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous êtes desormais ami avec " + username, "§7You are now friends with" + username));
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								player.sendMessage("");
								player.sendMessage(prefix +MeneliaAPI.getInstance().getLang(player, "§7Ce joueur ne vous a pas envoyé de demande", "§7This player did not send you a request"));
							}
						}
						else {
							player.sendMessage("");
							player.sendMessage(prefix +MeneliaAPI.getInstance().getLang(player, "§7Ce joueur n'exite pas", "§7This player don't exist"));
						}
					}
					else{
						player.sendMessage("");
						player.sendMessage("§7/friend accept §b<username>");
					}
				}
				else if(MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("remove") || MeneliaAPI.getFunctions().argument(args, 1).equalsIgnoreCase("r")) {
					if(args.length >=2) {
						String username = MeneliaAPI.getFunctions().argument(args, 2);
						if(username.equals(player.getName())) {
							player.sendMessage("");
							player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous ne pouvez pas être ami avec vous même", "§7You can't be your own friend"));
							return false;
						}
						if(MySQL.EXIST_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 2))) {
							String uuid2 = MySQL.GET_DATA_String(MySQL.Table_Online, MySQL.Field_Online_USERNAME, username, MySQL.Field_Online_UUID);
							if(existAsFriend(player.getUniqueId().toString(), uuid2)) {
								ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, player.getUniqueId().toString());
								try {
									while(result1.next()) {
										if(result1.getString("status").equals("1")) {
											if(result1.getString(MySQL.Field_Friends_UUID2).equals(uuid2)) {
												MySQL.DELETE_DATA(MySQL.Table_Friends, MySQL.Field_Friends_ID, result1.getString(MySQL.Field_Friends_ID));
												player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous êtes désormais plus amis avec " + username, "§7You are now more friends with " + username));
												return false;
											}
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid2);
								try {
									while(result2.next()) {
										if(result2.getString("status").equals("1")) {
											if(result2.getString(MySQL.Field_Friends_UUID2).equals(player.getUniqueId().toString())) {
												MySQL.DELETE_DATA(MySQL.Table_Friends, MySQL.Field_Friends_ID, result1.getString(MySQL.Field_Friends_ID));
												player.sendMessage(prefix + MeneliaAPI.getInstance().getLang(player, "§7Vous êtes désormais plus amis avec " + username, "§7You are now more friends with " + username));
												return false;
											}
										}
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								player.sendMessage("");
								player.sendMessage(prefix +MeneliaAPI.getInstance().getLang(player, "§7Vous n'êtes pas ami avec ce joueur", "§7You are not friends with this player"));
							}
						}
						else {
							player.sendMessage("");
							player.sendMessage(prefix +MeneliaAPI.getInstance().getLang(player, "§7Ce joueur n'exite pas", "§7This player don't"));
						}
					}
					else {
						player.sendMessage("");
						player.sendMessage("§7/friend remove §b<username>");
					}
				}
				else {
					player.sendMessage("");
					player.sendMessage("§7/friend §badd <username>");
					player.sendMessage("§7/friend §bremove <username>");
					player.sendMessage("§7/friend §blist");
					player.sendMessage("§7/friend §baccept <username>");
				}
			}
		}
		return false;
	}
	
	private boolean existAsFriend(String uuid1, String uuid2) {
		ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid1);
		try {
			while(result1.next()) {
				if(result1.getString("status").equals("1")) {
					if(result1.getString(MySQL.Field_Friends_UUID2).equals(uuid2)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid2);
		try {
			while(result2.next()) {
				if(result2.getString("status").equals("1")) {
					if(result2.getString(MySQL.Field_Friends_UUID2).equals(uuid1)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private boolean existAsRequestFriend(String uuid1, String uuid2) {
		ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid1);
		try {
			while(result1.next()) {
				if(result1.getString("status").equals("0")) {
					if(result1.getString(MySQL.Field_Friends_UUID2).equals(uuid2)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid2);
		try {
			while(result2.next()) {
				if(result2.getString("status").equals("0")) {
					if(result2.getString(MySQL.Field_Friends_UUID2).equals(uuid1)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean hasFriends(String uuid) {
		ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid);
		try {
			while(result1.next()) {
				if(result1.getString("status").equals("1")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID2, uuid);
		try {
			while(result2.next()) {
				if(result2.getString("status").equals("1")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private int getAmountFriends(String uuid) {
		int i = 0;
		ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid);
		try {
			while(result1.next()) {
				if(result1.getString("status").equals("1")) {
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID2, uuid);
		try {
			while(result2.next()) {
				if(result2.getString("status").equals("1")) {
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	private ArrayList<String> getFriends(String uuid) {
		ArrayList<String> list = new ArrayList<>();
		ResultSet result1 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID1, uuid);
		try {
			while(result1.next()) {
				if(result1.getString("status").equals("1")) {
					list.add(result1.getString(MySQL.Field_Friends_UUID2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = MySQL.GET_RESULT(MySQL.Table_Friends, MySQL.Field_Friends_UUID2, uuid);
		try {
			while(result2.next()) {
				if(result2.getString("status").equals("1")) {
					list.add(result2.getString(MySQL.Field_Friends_UUID1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
