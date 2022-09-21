package fr.gameurduxvi.meneliaapi.Databases.Stats;

public class TNTSmash {
	private String uuid;
	private int kills;
	private int wins;
	private int methodExplosion;
	private int methodStick;
	private int methodBlazeRod;
	private int methodFishing;
	private int methodSnowball;
	private int methodArrow;
	
	public TNTSmash(String uuid, int kills, int wins, int method_explosion, int method_stick, int method_blaze_rod, int method_fishing, int method_snowball, int method_arrow) {
		this.uuid = uuid;
		this.kills = kills;
		this.wins = wins;
		this.methodExplosion = method_explosion;
		this.methodStick = method_stick;
		this.methodBlazeRod = method_blaze_rod;
		this.methodFishing = method_fishing;
		this.methodSnowball = method_snowball;
		this.methodArrow = method_arrow;
	}
	
	public int getKills() {
		return kills;
	}
	
	public int getMethodArrow() {
		return methodArrow;
	}
	
	public int getMethodBlazeRod() {
		return methodBlazeRod;
	}
	
	public int getMethodExplosion() {
		return methodExplosion;
	}
	
	public int getMethodFishing() {
		return methodFishing;
	}
	
	public int getMethodSnowball() {
		return methodSnowball;
	}
	
	public int getMethodStick() {
		return methodStick;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public int getWins() {
		return wins;
	}
	
	//==========================================================================
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public void setMethodArrow(int methodArrow) {
		this.methodArrow = methodArrow;
	}
	
	public void setMethodBlazeRod(int methodBlazeRod) {
		this.methodBlazeRod = methodBlazeRod;
	}
	
	public void setMethodExplosion(int methodExplosion) {
		this.methodExplosion = methodExplosion;
	}
	
	public void setMethodFishing(int methodFishing) {
		this.methodFishing = methodFishing;
	}
	
	public void setMethodSnowball(int methodSnowball) {
		this.methodSnowball = methodSnowball;
	}
	
	public void setMethodStick(int methodStick) {
		this.methodStick = methodStick;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
}
