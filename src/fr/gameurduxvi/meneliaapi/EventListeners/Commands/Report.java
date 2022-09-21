package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import fr.gameurduxvi.meneliaapi.MySQL.MySQL;

public class Report implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("report")) {
				if(args.length >= 1) {
					if(MySQL.EXIST_DATA(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 1))) {
						String uuid = MySQL.GET_DATA_String(MySQL.Table_Accounts, MySQL.Field_Accounts_USERNAME, MeneliaAPI.getFunctions().argument(args, 1), MySQL.Field_Accounts_UUID);
						
						String[] fieldWhere = {MySQL.Field_Report_REPORTER, MySQL.Field_Report_UUID};
						String[] valueWhere = {player.getUniqueId().toString(), uuid};
						
						if(MySQL.EXIST_DATA(MySQL.Table_Report, fieldWhere, valueWhere)) {
							player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7Vous avez déjà signalé ce joueur !", "§7You already have reported this player !"));
							return false;
						}
						if(args.length>=2) {
							SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
							String stringDate = dateFormatter.format(new Date());
							int i = 0;
							String reason = "";
							for(String str: args) {
								i++;
								if(i != 1) {
									if(reason.equals("")) {
										reason = str;
									}
									else {
										reason = reason + " " + str;
									}
								}
							}
							MySQL.INSERT_REPORT(uuid, player.getUniqueId().toString(), stringDate, reason, 0, 0, "none");
							player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7Vous venez de signaler §6" + MeneliaAPI.getFunctions().argument(args, 1) + " §7pour: " + reason, "§7You just reported §6" + MeneliaAPI.getFunctions().argument(args, 1) + " §7for: " + reason));
						}
						else {
							player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7/report " + MeneliaAPI.getFunctions().argument(args, 1) + " §b<raison>", "§7/report " + MeneliaAPI.getFunctions().argument(args, 1) + " §b<reason>"));
						}
					}
					else {
						player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7Le joueur §6" + MeneliaAPI.getFunctions().argument(args, 1) + " §7n'existe pas !", "§7The player §6" + MeneliaAPI.getFunctions().argument(args, 1) + " §7don't exist !"));
					}
				}
				else {
					player.sendMessage(MeneliaAPI.getInstance().getLang(player, "§7/report §b<joueur> <raison>", "§7/report §b<player> <reason>"));
				}
			}
		}
		return false;
	}

}
