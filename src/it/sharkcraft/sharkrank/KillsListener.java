package it.sharkcraft.sharkrank;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static it.sharkcraft.sharkrank.SharkRank.*; // import global variable plugin

public class KillsListener implements Listener {
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    
    Player killer = event.getEntity().getKiller();
	
	if (!(killer instanceof Player)) {
		
		return;	// does nothing
	}
	
	if (plugin.getConfig().contains("Players." + killer.getName() + ".kills")) {
		
		int kills = plugin.getConfig().getInt("Players." + killer.getName() + ".kills");
		killboard.set(killer.getName(), kills + 1);
        plugin.getConfig().set("Players" + "." + killer.getName() + ".kills", kills + 1);
        Commands.rankUp(killer);
        
        
    } else {
    	
        plugin.getConfig().createSection("Players" + "." + killer.getName() + ".kills");
        plugin.getConfig().set("Players." + killer.getName() + ".kills", 1);
    }
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
	  
	  Player p = event.getPlayer();
	  
	  if (((p instanceof Player)) && (!plugin.getConfig().contains("Players." + p.getName() + ".kills"))) {
		  
		  plugin.getConfig().createSection("Players." + p.getName() + ".kills");
		  plugin.getConfig().set("Players." + p.getName() + ".kills", Integer.valueOf(0));
	  }
	  
	  p.setScoreboard(killboard.getKillBoard()); // Assegna al player la grafica delle kills.

  }
}