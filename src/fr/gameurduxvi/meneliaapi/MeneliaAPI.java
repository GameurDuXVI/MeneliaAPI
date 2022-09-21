package fr.gameurduxvi.meneliaapi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.gameurduxvi.meneliaapi.Databases.Accounts;
import fr.gameurduxvi.meneliaapi.Databases.Stats.TNTSmash;
import fr.gameurduxvi.meneliaapi.EventListeners.AdminPanel;
import fr.gameurduxvi.meneliaapi.EventListeners.BungeeCord;
import fr.gameurduxvi.meneliaapi.EventListeners.EventListener;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Account;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Friend;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Ping;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Report;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Server;
import fr.gameurduxvi.meneliaapi.EventListeners.Commands.Tempban;
import fr.gameurduxvi.meneliaapi.MySQL.GamesManager;

public class MeneliaAPI extends JavaPlugin {
	
	// Main instance plugin
	private static MeneliaAPI instance;
	
	public static MeneliaAPI getInstance() {
		return instance;
	}
	
	
	// Other instances
	
	private GamesManager GamesManagerInstance;	
	public GamesManager getGamesManager() {
		return GamesManagerInstance;
	}
	
	private static fr.gameurduxvi.meneliaapi.BungeeCord bungeecordInstance;
	public static fr.gameurduxvi.meneliaapi.BungeeCord getBungeecordInstance() {
		return bungeecordInstance;
	}
	
	private static Functions functionsInstance;
	public static Functions getFunctions() {
		return functionsInstance;
	}
	
	private static JSON jsonInstance;
	public static JSON getJsonInstance() {
		return jsonInstance;
	}
	
	private static Sockets socketsInstance;
	public static Sockets getSocketsInstance() {
		return socketsInstance;
	}
	
	
	// Plugin properties
	public String pluginPrefix = "§6[MeneliaAPI]";
	
	
	// Current server properties
	public String currentServerName;
	
	
	// Databases
	private ArrayList<Accounts> accounts = new ArrayList<>();
	public ArrayList<Accounts> getAccounts(){
		return this.accounts;
	}
	
	
	private ArrayList<TNTSmash> StatTNTSmash = new ArrayList<>();
	public ArrayList<TNTSmash> getStatTNTSmash() {
		return StatTNTSmash;
	}
	
	
	private Map<String, Integer> serverList = new HashMap<>();
	public Map<String, Integer> getServerList() {
		return serverList;
	}
	
	
	//SQL Stuff
	private String SQLHost;
	private String SQLDatabase; 
	private String SQLUsername; 
	private String SQLPasseword;
	private int SQLPort = 3306;
	
	private Connection con = null;
	public Connection MySQLConnection() {
		return con;
		/*try {
			synchronized (this) {
				Class.forName("com.mysql.jdbc.Driver");
				
				return DriverManager.getConnection("jdbc:mysql://" + SQLHost + ":" + SQLPort + "/" + SQLDatabase, SQLUsername, SQLPasseword);
			}			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;*/
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
		
		// 
		// Plugin loading Message
		// 
		
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "");
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "");
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + " Enabling MeneliaAPI");
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "=========================================================");		

		// 
		// Main plugin instance
		// 
		
		instance = this;
		
		// 
		// Creating if plugin directory doesn't exist
		// 
		
		File dir = new File(getDataFolder() + "/../Menelia/");
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		// 
		// Loading database
		// 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Object obj = new JSONParser().parse(new FileReader("../../data/config.json"));
					
					JSONObject jo = (JSONObject) obj;
					
					Map<String, String> mysql = ((Map<String, String>)jo.get("mysql"));
					
					Iterator<Entry<String, String>> itr1 = mysql.entrySet().iterator(); 
					while (itr1.hasNext()) {
						Entry<String, String> pair = itr1.next();
						switch (pair.getKey()) {
							case "Host":
								SQLHost = pair.getValue();
								break;
							case "DatabaseName":
								SQLDatabase = pair.getValue();
								break;
							case "Username":
								SQLUsername = pair.getValue();
								break;
							case "Password":
								SQLPasseword = pair.getValue();
								break;
							case "Port":
								SQLPort = Integer.parseInt(pair.getValue());
								break;
							default:
								break;
						} 
					}
					
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				
				try {
					synchronized (this) {
						Class.forName("com.mysql.jdbc.Driver");
						
						con =  DriverManager.getConnection("jdbc:mysql://" + SQLHost + ":" + SQLPort + "/" + SQLDatabase, SQLUsername, SQLPasseword);
					}			
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).run();
		

		// 
		// 
		// 
		try {
			Object obj = new JSONParser().parse(new FileReader("../../data/config.json"));
			
			JSONObject jo = (JSONObject) obj;
			//System.out.println( ((JSONArray)jo.get("servers")).toJSONString() );
						
			JSONArray ar = (JSONArray) jo.get("servers");
			Iterator<JSONObject> iterator = ar.iterator();
			while(iterator.hasNext()) {
				JSONObject jo2 = iterator.next();
				//System.out.println(jo2.toJSONString());
				String serverName = (String) jo2.get("serverName");
				int port = ((Long)jo2.get("port")).intValue();
				serverList.put(serverName, port);
			}
			
			//ArrayList<JSONObject> ja = getJsonInstance().JSONArrayReaderToJSONOject(jo.get("servers"));
			
			/*for(JSONObject jo2: ja) {
				
				
			
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 
		// Saving other instances
		// 
		GamesManagerInstance = new GamesManager();
		
		bungeecordInstance = new fr.gameurduxvi.meneliaapi.BungeeCord();
		functionsInstance = new Functions();
		jsonInstance = new JSON();
		socketsInstance = new Sockets();

		// 
		// Refreshing java databases
		// 
		getFunctions().refreshAccounts();
		getFunctions().refreshStatsTNTSmash();
		

		// 
		// Introducing plugin commands
		// 
		
		getCommand("admin").setExecutor(new AdminPanel());	
		getCommand("server").setExecutor(new Server());
		getCommand("ping").setExecutor(new Ping());
		getCommand("tempban").setExecutor(new Tempban());
		getCommand("account").setExecutor(new Account());
		getCommand("report").setExecutor(new Report());
		getCommand("friend").setExecutor(new Friend());

		// 
		// Loading EventListeners
		// 
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new AdminPanel(), this);

		// 
		// Setting BungeeCord Channeling
		// 
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "MeneliaChannel");
		getServer().getMessenger().registerIncomingPluginChannel(this, "MeneliaChannel", new BungeeCord());
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		

		// 
		// Plugin loading Message
		// 
		
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "=========================================================");
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "");
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + "");
		
		//getOtherServerOnlinePlayers("51.38.240.221", 25565);
	}		
	
	
	
	
	@Override
	public void onDisable() {
		// Plugin unloading Message
		try {
			con.close();
		} catch (SQLException e) {
		}
		
		for(Player lp: Bukkit.getOnlinePlayers()) {
			if(lp.isOnline()) {
				lp.closeInventory();
			}
		}
		Bukkit.getConsoleSender().sendMessage(pluginPrefix + " Disabling MeneliaAPI");
	}
	
	
	public String getLang(Player player, String fr, String en) {
		for(Accounts account: MeneliaAPI.getInstance().getAccounts()) {
			if(account.getName().equals(player.getName())) {
				if(account.getLang().equals("FR")) {
					return fr;
				}
				else {
					return en;
				}
			}
		}
		//System.out.println("[MeneliaAPI] Data not found for " + player.getName());
		//refreshAccounts();
		return en;
	}
	public String colorize(String string) {
		return ChatColor.translateAlternateColorCodes('&', "" + string);
	}
}
