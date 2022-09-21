package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Server implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("server")) {
				if(PermissionsEx.getUser(player).has("admin.server")) {
					Iterator<Entry<String, Integer>> itr = MeneliaAPI.getInstance().getServerList().entrySet().iterator();
					String serverMessage = "";
					while (itr.hasNext()) {
						Entry<String, Integer> pair = itr.next();
						if(itr.hasNext()) {
							if(serverMessage.equals("")) {
								serverMessage = pair.getKey();
							}
							else {
								serverMessage = serverMessage + ", " + pair.getKey();
							}
						}
						else {
							if(serverMessage.equals("")) {
								serverMessage = pair.getKey();								
							}
							else {
								serverMessage = serverMessage + MeneliaAPI.getInstance().getLang(player, " et ", " and ") + pair.getKey();
							}
							
						}
					}
					if(args.length >= 1) {
						
						if(args[0].equalsIgnoreCase("beta")) {
							MeneliaAPI.getBungeecordInstance().sendToServer(player, "beta");
						}
						
						itr = MeneliaAPI.getInstance().getServerList().entrySet().iterator();
						
						boolean correct = false;
						while (itr.hasNext()) {
							Entry<String, Integer> pair = itr.next();
							if(args[0].equalsIgnoreCase(pair.getKey())) {
								correct = true;
								MeneliaAPI.getBungeecordInstance().sendToServer(player, args[0]);
							}
						}
						if(!correct) {
							player.sendMessage("§c" + MeneliaAPI.getInstance().getLang(player, "Choissisez entre", "Select between") + ": §6" + serverMessage);
						}
					}
					else {
						player.sendMessage("§c" + MeneliaAPI.getInstance().getLang(player, "Choissisez entre", "Select between") + ": §6" + serverMessage);
					}
				}
				else {
					
				}
			}
		}
		return false;
	}
}
