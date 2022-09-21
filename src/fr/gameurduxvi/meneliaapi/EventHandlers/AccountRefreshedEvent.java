package fr.gameurduxvi.meneliaapi.EventHandlers;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.gameurduxvi.meneliaapi.Databases.Accounts;

public class AccountRefreshedEvent extends Event{

	 private static final HandlerList HANDLERS = new HandlerList();
	 private final Accounts account;
	 
	 public AccountRefreshedEvent(Accounts account) {
		 this.account = account;
	 }
	 
	 public HandlerList getHandlers() {
		 return HANDLERS;
	 }

	 public static HandlerList getHandlerList() {
		 return HANDLERS;
	 }
	 
	 public Accounts getAccount() {
		 return account;
	 }
	
}
