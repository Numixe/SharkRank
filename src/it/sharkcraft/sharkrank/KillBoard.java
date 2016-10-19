package it.sharkcraft.sharkrank;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import static it.sharkcraft.sharkrank.SharkRank.plugin;

public class KillBoard {
	
	public Scoreboard board;
	public Objective obj;
	
	public static final String TITLE = plugin.getConfig().getString("KillBoard.Title").replace("&", "§");
	public static final String PlayerColor = plugin.getConfig().getString("KillBoard.PlayerColor").replace("&", "§");
	
	public KillBoard() { // funzione da caricare all'avvio del server
		
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		obj = board.registerNewObjective("killboard", "dummy");
		obj.setDisplayName(TITLE);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	public Scoreboard getKillBoard() {
		return board;
	}
	
	public void refresh() { // funzione che (TEORICAMENTE) dovrebbe settare i players con le kill °-°
			
		// lista e itera i player presenti nel config
		
		for(String name : plugin.getConfig().getConfigurationSection("Players").getKeys(false)) {
			
			int kills = plugin.getConfig().getInt("Players." + name + ".kills");
			
			board.resetScores(PlayerColor + name);
			
			if (kills <= 0)
				continue;
			
			obj.getScore(PlayerColor + name).setScore(kills);
		}
	}
	
	public void set(String player, int value) {
		
		if (value == 0) {
			board.resetScores(PlayerColor + player);
			return;
		}
		
		obj.getScore(PlayerColor + player).setScore(value);
	}
}
