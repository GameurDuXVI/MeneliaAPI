package fr.gameurduxvi.meneliaapi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeCord {
	public void sendToServer(Player player, String targetServer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetServer);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		player.sendPluginMessage(MeneliaAPI.getInstance(), "BungeeCord", b.toByteArray());
	}
	
	
	
	
	public void SendPluginMessage(Player player, String server, String channel, String subChannel, boolean useArgs, String[] args) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF(server);
		out.writeUTF(subChannel);
		if(useArgs) {
			for(String argument: args) {
				out.writeUTF(argument);
			}
		}
		//Bukkit.getConsoleSender().sendMessage(INSTANCE.colorize(INSTANCE.pluginPrefix + " &6Sending request for account to bungeecord..."));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(MeneliaAPI.getInstance(), ()->{
			player.sendPluginMessage(MeneliaAPI.getInstance(), channel, out.toByteArray());
		}, 3L);
	}
	
	
	
	
	public void SendPluginMessage(String server, String channel, String subChannel, boolean useArgs, String[] args) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF(server);
		out.writeUTF(subChannel);
		if(useArgs) {
			for(String argument: args) {
				out.writeUTF(argument);
			}
		}
		//Bukkit.getConsoleSender().sendMessage(INSTANCE.colorize(INSTANCE.pluginPrefix + " &6Sending request for account to bungeecord..."));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(MeneliaAPI.getInstance(), ()->{
			Bukkit.getServer().sendPluginMessage(MeneliaAPI.getInstance(), channel, out.toByteArray());
		}, 3L);
	}
}
