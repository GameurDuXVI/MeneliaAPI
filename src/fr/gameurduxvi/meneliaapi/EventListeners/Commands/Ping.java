package fr.gameurduxvi.meneliaapi.EventListeners.Commands;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.MinecraftServer;

public class Ping implements CommandExecutor {
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equals("ping")) {
				try {
					  Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
					  int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
					  player.sendMessage("§6Ping: " + ping);
					  StringBuilder sb = new StringBuilder("§6TPS from last 1m, 5m, 15m: " );
				        for ( double tps : MinecraftServer.getServer().recentTps )
				        {
				        	double d = (double) Math.round(tps * 100) / 100;
				        	sb.append("§a" + d);
				            sb.append("§6, ");
				        }
				        sender.sendMessage( sb.substring( 0, sb.length() - 2 ) );
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
