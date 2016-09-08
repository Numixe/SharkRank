package it.sharkcraft.sharkrank;

import org.bukkit.Bukkit;
import static it.sharkcraft.sharkrank.SharkRank.*;	// import global variable plugin

public class Update implements Runnable {
	
	public static final long SEC = 20;
	
	public Update() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, SEC, 10 * SEC);
	}

	public void run() {
		
		/*Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		
		for (Player player : players) {	// itera player per ogni elemento della lista
			
			Commands.rankUpdate(player);
		}*/
		
		// rankUp() va richiamata solo in caso di uccisione, e non ricorsivamente
		// in quanto si basa su un numero di kill preciso
		// rankUpdate() si basa invece sull'intervallo di kills
		// dunque puo' essere usata per aggiornare lo stato del giocatore occasionalmente
		
		plugin.saveConfig();
		plugin.reloadConfig();
	}

}
