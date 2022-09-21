package fr.gameurduxvi.meneliaapi.MySQL.GameManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.gameurduxvi.meneliaapi.MySQL.MySQL;

public class Game {
	private String gameType;
	private int gameNumber;
	private int gameStatus;
	
	public Game(String gameType, int gameNumber, int gameStatus) {
		this.gameType = gameType;
		this.gameNumber = gameNumber;
		this.gameStatus = gameStatus;
	}
	
	public int getGameNumber() {
		return gameNumber;
	}
	
	public int getGameStatus() {
		return gameStatus;
	}
	
	public String getGameType() {
		return gameType;
	}
	
	public ArrayList<String> getPlayers() {
		ArrayList<String> players = new ArrayList<>();
		
		try {
			String gameNum = gameNumber + "";
			String[] whereField = {MySQL.Field_Games_Content_GAME_TYPE, MySQL.Field_Games_Content_GAME_ID};
			String[] field = {gameType, gameNum};
			ResultSet result = MySQL.GET_RESULT(MySQL.Table_Games, whereField, field);
		
			while(result.next()) {
				players.add(result.getString(MySQL.Field_Games_Content_UUID));
			}
			
			return players;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setPlayers(ArrayList<String> players) {		
		deletePlayers();
		for(String str: players) {
			MySQL.INSERT_GAMES_CONTENT(gameType, gameNumber, str);
		}
	}
	
	public void deletePlayers() {
		try {
			String gameNum = gameNumber + "";
			String[] whereField = {MySQL.Field_Games_Content_GAME_TYPE, MySQL.Field_Games_Content_GAME_ID};
			String[] field = {gameType, gameNum};
			ResultSet result = MySQL.GET_RESULT(MySQL.Table_Games_Content, whereField, field);
		
			while(result.next()) {
				MySQL.DELETE_DATA(MySQL.Table_Games_Content, MySQL.Field_Games_Content_ID, result.getString(MySQL.Field_Games_Content_ID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}
	
	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
		String gameNum = gameNumber + "";
		String[] whereField = {MySQL.Field_Games_GAME_TYPE, MySQL.Field_Games_GAME_ID};
		String[] field = {gameType, gameNum};
		MySQL.UPDATE_DATA(MySQL.Table_Games, whereField, field, MySQL.Field_Games_GAME_STATUS, gameStatus + "");
	
	}
	
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
}
