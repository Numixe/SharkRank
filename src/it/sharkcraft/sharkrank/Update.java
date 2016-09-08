package it.sharkcraft.sharkrank;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Update implements Runnable
{
  public static final long SEC = 20;
  public SharkRank plugin;
  
  public Update(SharkRank plugin)
  {
    this.plugin = plugin;
    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, SEC, 10 * SEC);
  }
  
  public void run()
  {
    Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
    for (Player player : players) {
      Commands.rankUp(player);
    }
    this.plugin.saveConfig();
    this.plugin.reloadConfig();
  }
}
