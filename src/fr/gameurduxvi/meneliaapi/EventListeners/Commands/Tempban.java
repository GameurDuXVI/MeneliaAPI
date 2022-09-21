package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Tempban implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("tempban")) {
				if(PermissionsEx.getUser(player).has("admin.tempban")) {
					if(args.length >= 1) {
						String reason = "";
						for(int i = 1; i < args.length; i++) {
							reason = reason + " " + args[i];
						}
						String[] playerSelected = {player.getName(), args[0], reason};
						MeneliaAPI.getBungeecordInstance().SendPluginMessage(player, Bukkit.getServerName(), "MeneliaChannel", "AdminPanel_Player_BanTemp.Request", true, playerSelected);
					}
					else {
						player.sendMessage("§6/tempban <player> <time> <reason>");
					}
				}
			}
		}
		return false;
	}
}
