package fr.gameurduxvi.meneliaapi.EventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JoinGameEvent extends Event{

	 private static final HandlerList HANDLERS = new HandlerList();
	 private final Player player;
	 private final String gameName;
	 private final int gameNumber;
	 
	 public JoinGameEvent(Player player, String gameName, int gameNumber) {
		 this.player = player;
		 this.gameName = gameName;
		 this.gameNumber = gameNumber;
	 }
	 
	 public HandlerList getHandlers() {
		 return HANDLERS;
	 }

	 public static HandlerList getHandlerList() {
		 return HANDLERS;
	 }
	 
	 public String getGameName() {
		 return gameName;
	 }
	 
	 public int getGameNumber() {
		 return gameNumber;
	 }
	
	 public Player getPlayer() {
		 return player;
	 }
	
}
