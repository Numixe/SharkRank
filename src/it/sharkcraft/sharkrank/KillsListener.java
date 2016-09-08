package it.sharkcraft.sharkrank;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class KillsListener
  implements Listener
{
  public SharkRank plugin;
  
  public KillsListener(SharkRank plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event)
  {
    Player killer = event.getEntity().getKiller();
    if (!(killer instanceof Player)) {
      return;
    }
    
    int kills = this.plugin.getConfig().getInt("Players." + killer.getName() + ".kills");
    if (this.plugin.getConfig().contains("Players." + killer.getName() + ".kills")) {
      this.plugin.getConfig().set("Players." + killer.getName() + ".kills", Integer.valueOf(kills + 1));
      
    } else {
    	
      this.plugin.getConfig().createSection("Players." + killer.getName() + ".kills");
      plugin.getConfig().set("Players." + killer.getName() + ".kills", 1);
      
    }
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event)
  {
    Player p = event.getPlayer();
    if (((p instanceof Player)) && 
      (!this.plugin.getConfig().contains("Players." + p.getName() + ".kills"))) {
      this.plugin.getConfig().createSection("Players." + p.getName() + ".kills");
      this.plugin.getConfig().set("Players." + p.getName() + ".kills", Integer.valueOf(0));
    }
  }
}
