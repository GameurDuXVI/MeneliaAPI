package fr.gameurduxvi.meneliaapi.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

import fr.gameurduxvi.meneliaapi.MySQL.GameManager.Game;

public class GamesManager {
	
	public GamesManager() {
		if(Bukkit.getServerName().equals("games") || Bukkit.getServerName().equals("beta")) {
			MySQL.Truncate(MySQL.Table_Games);
			MySQL.Truncate(MySQL.Table_Games_Content);
		}
	}
	
	public void setGames(String gameType, int totalGames, int max) {
		if(!MySQL.EXIST_DATA(MySQL.Table_Games, MySQL.Field_Games_GAME_TYPE, gameType)) {
			for(int i = 1; i < totalGames + 1; i++) {
				MySQL.INSERT_GAMES(gameType, i, 0, max);		
			}
		}
		else {
			System.out.println("GamesManager.java - Already exist !");
		}
	}
	
	public ArrayList<Game> getGames(String gameType) {
		ArrayList<Game> games = new ArrayList<>();
		
		try {
			ResultSet result = MySQL.GET_RESULT(MySQL.Table_Games, MySQL.Field_Games_GAME_TYPE, gameType);
			while(result.next()) {
				games.add(new Game(gameType, result.getInt(MySQL.Field_Games_GAME_ID), result.getInt(MySQL.Field_Games_GAME_STATUS)));
			}
			return games;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Game getGame(String gameType, int gameNum) {
		Game game = null;
		try {
			String gameNumber = gameNum + "";
			String[] whereField = {MySQL.Field_Games_GAME_TYPE, MySQL.Field_Games_ID};
			String[] field = {gameType, gameNumber};
			ResultSet result = MySQL.GET_RESULT(MySQL.Table_Games, whereField, field);
			while (result.next()) {
				game = new Game(gameType, gameNum, result.getInt(MySQL.Field_Games_GAME_STATUS));
			}
			return game;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
