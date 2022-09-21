package fr.gameurduxvi.meneliaapi.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.Databases.Accounts;
import fr.gameurduxvi.meneliaapi.Databases.Stats.TNTSmash;
import fr.gameurduxvi.meneliaapi.EventHandlers.AccountLoadedEvent;

public class MySQL {
	public static String Table_Accounts = "accounts";
		public static String Field_Accounts_ID = "id";
		public static String Field_Accounts_UUID = "uuid";
		public static String Field_Accounts_USERNAME = "username";
		public static String Field_Accounts_LANGUAGE = "lang";
		public static String Field_Accounts_MONEY_BASE = "money_base";
		public static String Field_Accounts_MONEY_SPECIAL = "money_special";
		public static String Field_Accounts_LAST_CONNEXION_DATE = "last_connexion_date";
		public static String Field_Accounts_LAST_CONNEXION_HOUR = "last_connexion_hour";
		public static String Field_Accounts_IP = "last_ip";
	
		
	public static String Table_Stats_Ascension = "stats_ascension";
		public static String Field_Stats_Ascension_ID = "id";
		public static String Field_Stats_Ascension_UUID = "uuid";
		public static String Field_Stats_Ascension_KILLS = "kills";
		public static String Field_Stats_Ascension_WINS = "wins";
	
	public static String Table_Stats_TNTSmash = "stats_tntsmash";
		public static String Field_Stats_TNTSmash_ID = "id";
		public static String Field_Stats_TNTSmash_UUID = "uuid";
		public static String Field_Stats_TNTSmash_KILLS = "kills";
		public static String Field_Stats_TNTSmash_WINS = "wins";
		public static String Field_Stats_TNTSmash_METHOD_EXPLOSION = "method_explosion";
		public static String Field_Stats_TNTSmash_METHOD_STICK = "method_stick";
		public static String Field_Stats_TNTSmash_METHOD_BLAZE_ROD = "method_blaze_rod";
		public static String Field_Stats_TNTSmash_METHOD_FISHING = "method_fishing";
		public static String Field_Stats_TNTSmash_METHOD_SNOWBALL = "method_snowball";
		public static String Field_Stats_TNTSmash_METHOD_ARROW = "method_arrow";
		
	public static String Table_Game_Joiner = "games_joiner";
		public static String Field_Game_Joiner_ID = "id";
		public static String Field_Game_Joiner_SERVER = "server";
		public static String Field_Game_Joiner_UUID = "uuid";
		public static String Field_Game_Joiner_GAME_NAME = "game_name";
		public static String Field_Game_Joiner_GAME_NUMBER = "game_number";
		
	public static String Table_Games = "games";
		public static String Field_Games_ID = "id";
		public static String Field_Games_GAME_TYPE = "game_type";
		public static String Field_Games_GAME_ID = "game";
		public static String Field_Games_GAME_MAX = "max";
		public static String Field_Games_GAME_STATUS = "status";
	
	public static String Table_Games_Content = "games_content";
		public static String Field_Games_Content_ID = "id";
		public static String Field_Games_Content_GAME_TYPE = "game_type";
		public static String Field_Games_Content_GAME_ID = "game";
		public static String Field_Games_Content_UUID = "uuid";
		
	public static String Table_Updates = "updates";
		public static String Field_Updates_ID = "id";
		public static String Field_Updates_DATE = "date";
		public static String Field_Updates_MESSAGE = "message";
	
	public static String Table_Report = "reports";
		public static String Field_Report_ID = "id";
		public static String Field_Report_UUID = "uuid";
		public static String Field_Report_REPORTER = "reporter";
		public static String Field_Report_DATE = "date";
		public static String Field_Report_REASON = "reason";
		public static String Field_Report_TYPE = "type";
		public static String Field_Report_STATUS = "status";
		public static String Field_Report_TRAITE = "traite";
	
	public static String Table_Informer = "informer";
		public static String Field_Informer_ID = "id";
		public static String Field_Informer_UUID = "uuid";
		public static String Field_Informer_MESSAGE = "message";
		public static String Field_Informer_STATUS = "status";
		
	public static String Table_Online = "online";
		public static String Field_Online_ID = "id";
		public static String Field_Online_UUID = "uuid";
		public static String Field_Online_USERNAME = "username";
		public static String Field_Online_SERVER = "server";

	public static String Table_Friends = "friends";
		public static String Field_Friends_ID = "id";
		public static String Field_Friends_UUID1 = "uuid1";
		public static String Field_Friends_UUID2 = "uuid2";
		public static String Field_Friends_STATUS = "status";
	
	
	
	
	//=======================================================================================================
	// 
	// Insert
	// 
	//=======================================================================================================
	
	
	
	
	public static void INSERT_ACCOUNT(String uuid, String username, int lang, int moneyBase, int moneySpecial, String lastConnexionDate, String lastConnexionHour, String ip) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Accounts + "` (`" + Field_Accounts_ID + "`, `" + Field_Accounts_UUID + "`, `" + Field_Accounts_USERNAME + "`, `" + Field_Accounts_LANGUAGE + "`, `" + Field_Accounts_MONEY_BASE + "`, `" + Field_Accounts_MONEY_SPECIAL + "`, `" + Field_Accounts_LAST_CONNEXION_DATE + "`, `" + Field_Accounts_LAST_CONNEXION_HOUR + "`, `" + Field_Accounts_IP + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, uuid + "");
				statement.setString(2, username + "");
				statement.setString(3, lang + "");
				statement.setString(4, moneyBase + "");
				statement.setString(5, moneySpecial + "");
				statement.setString(6, lastConnexionDate + "");
				statement.setString(7, lastConnexionHour + "");
				statement.setString(8, ip + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_ASCENSION(String uuid, int kills, int wins) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Stats_Ascension + "` (`" + Field_Stats_Ascension_ID + "`, `" + Field_Stats_Ascension_UUID + "`, `" + Field_Stats_Ascension_KILLS + "`, `" + Field_Stats_Ascension_WINS + "`) VALUES (NULL, ?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, kills + "");
				statement.setString(3, wins + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_GAME_JOINER(String uuid, String server, String gameName, String gameNumber) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Game_Joiner + "` (`" + Field_Game_Joiner_ID + "`, `" + Field_Game_Joiner_UUID + "`, `" + Field_Game_Joiner_SERVER + "`, `" + Field_Game_Joiner_GAME_NAME + "`, `" + Field_Game_Joiner_GAME_NUMBER + "`) VALUES (NULL, ?,?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, server + "");
				statement.setString(3, gameName + "");
				statement.setString(4, gameNumber + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_TNTSMASH(String uuid, int kills, int wins, int method_explosion, int method_stick, int method_blaze_rod, int method_fishing, int method_snowball, int method_arrow) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Stats_TNTSmash + "` (`" + Field_Stats_TNTSmash_ID + "`, `" + Field_Stats_TNTSmash_UUID + "`, `" + Field_Stats_TNTSmash_KILLS + "`, `" + Field_Stats_TNTSmash_WINS + "`, `" + Field_Stats_TNTSmash_METHOD_EXPLOSION + "`, `" + Field_Stats_TNTSmash_METHOD_STICK + "`, `" + Field_Stats_TNTSmash_METHOD_BLAZE_ROD + "`, `" + Field_Stats_TNTSmash_METHOD_FISHING + "`, `" + Field_Stats_TNTSmash_METHOD_SNOWBALL + "`, `" + Field_Stats_TNTSmash_METHOD_ARROW + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, uuid + "");
				statement.setString(2, kills + "");
				statement.setString(3, wins + "");
				statement.setString(4, method_explosion + "");
				statement.setString(5, method_stick + "");
				statement.setString(6, method_blaze_rod + "");
				statement.setString(7, method_fishing + "");
				statement.setString(8, method_snowball + "");
				statement.setString(9, method_arrow + "");
				System.out.println("INSERT INTO `" + Table_Stats_TNTSmash + "` (`" + Field_Stats_TNTSmash_ID + "`, `" + Field_Stats_TNTSmash_UUID + "`, `" + Field_Stats_TNTSmash_KILLS + "`, `" + Field_Stats_TNTSmash_WINS + "`, `" + Field_Stats_TNTSmash_METHOD_EXPLOSION + "`, `" + Field_Stats_TNTSmash_METHOD_STICK + "`, `" + Field_Stats_TNTSmash_METHOD_BLAZE_ROD + "`, `" + Field_Stats_TNTSmash_METHOD_FISHING + "`, `" + Field_Stats_TNTSmash_METHOD_SNOWBALL + "`, `" + Field_Stats_TNTSmash_METHOD_ARROW + "`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_GAMES(String game_type, int gameNum, int status, int max) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Games + "` (`" + Field_Games_ID + "`, `" + Field_Games_GAME_TYPE + "`, `" + Field_Games_GAME_ID + "`, `" + Field_Games_GAME_MAX + "`, `" + Field_Games_GAME_STATUS + "`) VALUES (NULL, ?,?,?,?);");
				statement.setString(1, game_type + "");
				statement.setString(2, gameNum + "");
				statement.setString(3, max + "");
				statement.setString(4, status + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_GAMES_CONTENT(String game_type, int gameNum, String uuid) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Games_Content + "` (`" + Field_Games_Content_ID + "`, `" + Field_Games_Content_GAME_TYPE + "`, `" + Field_Games_Content_GAME_ID + "`, `" + Field_Games_Content_UUID + "`) VALUES (NULL, ?,?,?);");
				statement.setString(1, game_type + "");
				statement.setString(2, gameNum + "");
				statement.setString(3, uuid + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_FRIEND(String uuid1, String uuid2) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Friends + "` (`" + Field_Friends_ID + "`, `" + Field_Friends_UUID1 + "`, `" + Field_Friends_UUID2 + "`, `" + Field_Friends_STATUS + "`) VALUES (NULL, ?,?,0);");
				statement.setString(1, uuid1 + "");
				statement.setString(2, uuid2 + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_REPORT(String uuid, String reporter, String date, String reason, int type, int status, String traite) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Report + "` (`" + Field_Report_ID + "`, `" + Field_Report_UUID + "`, `" + Field_Report_REPORTER + "`, `" + Field_Report_DATE + "`, `" +  Field_Report_REASON + "`, `" +  Field_Report_TYPE + "`,`" +  Field_Report_STATUS + "`, `" +  Field_Report_TRAITE + "`) VALUES (NULL,?,?,?,?,?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, reporter + "");
				statement.setString(3, date + "");
				statement.setString(4, reason + "");
				statement.setString(5, type + "");
				statement.setString(6, status + "");
				statement.setString(7, traite + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void INSERT_INFORMER(String uuid, String message, int status) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO `" + Table_Informer + "` (`" + Field_Informer_ID + "`, `" + Field_Informer_UUID + "`, `" + Field_Informer_MESSAGE + "`, `" + Field_Informer_STATUS + "`) VALUES (NULL,?,?,?);");
				statement.setString(1, uuid + "");
				statement.setString(2, message + "");
				statement.setString(3, status + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

	//=======================================================================================================
	// 
	// Add - Remove
	// 
	//=======================================================================================================
	
	
	
	
	public static void ADD_INT(String table, String fieldWhere, String valueWhere, String field, int value) {
		int totalValue = GET_DATA_Int(table, fieldWhere, valueWhere, field);
		totalValue += value;
		UPDATE_DATA(table, fieldWhere, valueWhere, field, "" + totalValue);
	}
	
	public static void REMOVE_INT(String table, String fieldWhere, String valueWhere, String field, int value) {
		int totalValue = GET_DATA_Int(table, fieldWhere, valueWhere, field);
		totalValue -= value;
		UPDATE_DATA(table, fieldWhere, valueWhere, field, "" + totalValue);
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Truncate
	// 
	//=======================================================================================================
	
	
	
	
	public static void Truncate(String table) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("TRUNCATE `" + table + "`;");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Update
	// 
	//=======================================================================================================
	
	
	
	
	public static void UPDATE_DATA(String uuid, String table, String field, String value) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("UPDATE `" + table + "` SET `" + field + "`=? WHERE " + Field_Accounts_UUID + "=?;");
				statement.setString(1, value + "");
				statement.setString(2, uuid + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public static void UPDATE_DATA(String table, String fieldWhere, String valueWhere, String field, String value) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("UPDATE `" + table + "` SET `" + field + "`=? WHERE " + fieldWhere + "=?;");
				statement.setString(1, value + "");
				statement.setString(2, valueWhere + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void UPDATE_DATA(String table, String[] fieldWhere, String[] valueWhere, String field, String value) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				String fieldString = "";
				int i = 0;
				for(String str: fieldWhere) {
					if(fieldString.equals("")) {
						fieldString = "`" + str + "` = '" + valueWhere[i] + "'";
					}
					else {
						fieldString = fieldString + " AND `" + str + "` = '" + valueWhere[i] + "'";
					}
					i++;
				}
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("UPDATE `" + table + "` SET `" + field + "`=? WHERE " + fieldString + ";");
				statement.setString(1, value + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Exist
	// 
	//=======================================================================================================
	
	
	
	
	public static boolean EXIST_DATA(String table, String field, String value) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + field + "=?;");
				statement.setString(1, value + "");
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	public static boolean EXIST_DATA(String table, String[] field, String[] value) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				String fieldString = "";
				int i = 0;
				for(String str: field) {
					if(fieldString.equals("")) {
						fieldString = "`" + str + "` = '" + value[i] + "'";
					}
					else {
						fieldString = fieldString + " AND `" + str + "` = '" + value[i] + "'";
					}
					i++;
				}
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldString + ";");
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return true;
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Delete
	// 
	//=======================================================================================================
	
	
	
	
	public static void DELETE_DATA(String table, String fieldWhere, String valueWhere) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("DELETE FROM `" + table + "` WHERE `" + fieldWhere + "`=?;");
				statement.setString(1, valueWhere + "");
				statement.executeUpdate();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//=======================================================================================================
	// 
	// Get
	// 
	//=======================================================================================================
	
	
	
	
	public static String GET_DATA_String(String table, String fieldWhere, String valueWhere, String field) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return result.getString(field);
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	
	
	
	public static ResultSet GET_RESULT(String table, String[] fieldWhere, String[] valueWhere) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				String fieldString = "";
				int i = 0;
				for(String str: fieldWhere) {
					if(fieldString.equals("")) {
						fieldString = "`" + str + "` = '" + valueWhere[i] + "'";
					}
					else {
						fieldString = fieldString + " AND `" + str + "` = '" + valueWhere[i] + "'";
					}
					i++;
				}
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldString + ";");
				ResultSet result = statement.executeQuery();
				con.close();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static ResultSet GET_RESULT(String table, String fieldWhere, String valueWhere) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				con.close();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	public static ResultSet GET_RESULT(String table) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "`;");
				ResultSet result = statement.executeQuery();
				con.close();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static ResultSet GET_RESULT(String table, String option) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` " + option + ";");
				ResultSet result = statement.executeQuery();
				con.close();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	public static int GET_DATA_Int(String table, String fieldWhere, String valueWhere, String field) {
		synchronized (MeneliaAPI.getInstance()) {
			try {
				Connection con = MeneliaAPI.getInstance().MySQLConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + table + "` WHERE " + fieldWhere + "=?;");
				statement.setString(1, valueWhere);
				ResultSet result = statement.executeQuery();
				con.close();
				if(result.next()) {
					return result.getInt(field);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	
	
	/*public int GET_ACCOUNT_LANG(String uuid) {
		try {
			PreparedStatement statement = MeneliaAPI.getInstance().getConnection().prepareStatement("SELECT * FROM `" + Table_Accounts + "` WHERE " + Field_Accounts_UUID + "=?;");
			statement.setString(1, uuid + "");
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(Field_Accounts_LANGUAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int GET_ACCOUNT_MONEY_BASE(String uuid) {
		try {
			PreparedStatement statement = MeneliaAPI.getInstance().getConnection().prepareStatement("SELECT * FROM `" + Table_Accounts + "` WHERE " + Field_Accounts_UUID + "=?;");
			statement.setString(1, uuid + "");
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(Field_Accounts_MONEY_BASE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int GET_ACCOUNT_MONEY_SPECIAL(String uuid) {
		try {
			PreparedStatement statement = MeneliaAPI.getInstance().getConnection().prepareStatement("SELECT * FROM `" + Table_Accounts + "` WHERE " + Field_Accounts_UUID + "=?;");
			statement.setString(1, uuid + "");
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return result.getInt(Field_Accounts_MONEY_SPECIAL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}*/
	
	public static void readAccount(Player player, boolean join) {
		String lang = "EN";
		if(GET_DATA_Int(Table_Accounts, Field_Accounts_UUID, player.getUniqueId().toString(), Field_Accounts_LANGUAGE) == 1) {
			lang = "FR";
		}
		int moneyBase = GET_DATA_Int(Table_Accounts, Field_Accounts_UUID, player.getUniqueId().toString(), Field_Accounts_MONEY_BASE);
		int moneySpecial = GET_DATA_Int(Table_Accounts, Field_Accounts_UUID, player.getUniqueId().toString(), Field_Accounts_MONEY_SPECIAL);
		Accounts account = new Accounts(player.getUniqueId(), player.getName(), lang,  moneyBase, moneySpecial);
		MeneliaAPI.getInstance().getAccounts().add(account);
		/*for(Accounts a: MeneliaAPI.getInstance().getAccounts()) {
			Bukkit.broadcastMessage(a.getName());
		}*/
		if(join) {
			AccountLoadedEvent event = new AccountLoadedEvent(account);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
	
	public static void readStatTNTSmash(String uuid) {
		int kills = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_KILLS);
		int wins = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_WINS);
		
		int method_explosion = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_EXPLOSION);
		int method_stick = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_STICK);
		int method_blaze_rod = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_BLAZE_ROD);
		int method_fishing = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_FISHING);
		int method_snowball = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_SNOWBALL);
		int method_arrow = GET_DATA_Int(Table_Stats_TNTSmash, Field_Accounts_UUID, uuid, Field_Stats_TNTSmash_METHOD_ARROW);
		
		TNTSmash tntsmash = new TNTSmash(uuid, kills, wins, method_explosion, method_stick, method_blaze_rod, method_fishing, method_snowball, method_arrow);
		MeneliaAPI.getInstance().getStatTNTSmash().add(tntsmash);
	}
}
