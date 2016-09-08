package it.sharkcraft.sharkrank;

import org.bukkit.Bukkit;

public class Update implements Runnable
{
  public static final long SEC = 20;
  public SharkRank plugin;
  
  public Update(SharkRank plugin)
  {
    this.plugin = plugin;
    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, SEC, 30 * SEC);
  }
  
  public void run()
  {
    /**Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
    for (Player player : players) {

    }**/
    this.plugin.saveConfig();
    this.plugin.reloadConfig();
  }
}
