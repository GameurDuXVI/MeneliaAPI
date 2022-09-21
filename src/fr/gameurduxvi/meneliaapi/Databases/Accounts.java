package fr.gameurduxvi.meneliaapi.Databases;

import java.util.UUID;

import fr.gameurduxvi.meneliaapi.MySQL.MySQL;

public class Accounts {	
	private UUID uuid;
	private String name;
	private String lang;
	private int moneyBase;
	private int moneySpecial;
	
	public Accounts(UUID uuid, String name, String lang,  int moneyBase, int moneySpecial){
		this.uuid = uuid;
		this.name = name;
		this.lang = lang;
		this.moneyBase = moneyBase;
		this.moneySpecial = moneySpecial;
	}

	public UUID getUUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLang() {
		return lang;
	}
	
	public int getMoneyBase() {
		return moneyBase;
	}
	
	public int getMoneySpecial() {
		return moneySpecial;
	}
	
// ==================================	
	public void setLang(String lang) {
		this.lang = lang;
		if(lang.equals("FR")) {
			MySQL.UPDATE_DATA(uuid.toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LANGUAGE, "1");
		}
		else {
			MySQL.UPDATE_DATA(uuid.toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_LANGUAGE, "0");
		}
		
	}
	
	public void setMoneyBase(int number) {
		this.moneyBase = number;
		MySQL.UPDATE_DATA(uuid.toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_MONEY_BASE, number + "");
	}
	
	public void setMoneySpecial(int number) {
		this.moneySpecial = number;
		MySQL.UPDATE_DATA(uuid.toString(), MySQL.Table_Accounts, MySQL.Field_Accounts_MONEY_SPECIAL, number + "");
	}
}
