package it.sharkcraft.sharkrank;

import org.bukkit.entity.Player;

public class Commands
{
  public static SharkRank plugin;
  
  @SuppressWarnings("unused")
public static void rankUp(Player player)
  {
    int kills = Integer.parseInt(plugin.getConfig().getString("Players." + player.getName() + ".kills"));
    
    if (kills >= plugin.RANK_A_PTK && kills < plugin.RANK_B_PTK)
    {
      String category = "A";
      plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
        player.getName() + " group set " + plugin.RANK_A_GROUP);
    }
    
    if (kills >= plugin.RANK_B_PTK && kills < plugin.RANK_C_PTK)
    {
      String category = "B";
      plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
        player.getName() + " group set " + plugin.RANK_B_GROUP);
    }
    
    if (kills >= plugin.RANK_C_PTK && kills < plugin.RANK_D_PTK)
    {
      String category = "C";
      plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
        player.getName() + " group set " + plugin.RANK_C_GROUP);
    }
    
    if (kills >= plugin.RANK_D_PTK && kills < plugin.RANK_E_PTK)
    {
      String category = "D";
      plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
        player.getName() + " group set " + plugin.RANK_D_GROUP);
    }
    
    if (kills >= plugin.RANK_E_PTK)
    {
      String category = "E";
      plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
        player.getName() + " group set " + plugin.RANK_E_GROUP);
    }
  }
  
  public static void shrank(Player sender, String[] args)
  {
    Player p = sender;
    if (args.length == 0)
    {
      if (plugin.getConfig().contains("Players." + p.getName() + ".kills"))
      {
        p.sendMessage("§8[§c§l!§8] §9SharkRank> §a" + p.getName() + 
          " §7ha §a" + plugin.getConfig().getString(new StringBuilder("Players.").append(p.getName()).append(".kills").toString()) + 
          " §7kills.");
      }
      else
      {
        plugin.getConfig().createSection("Players." + p.getName());
        plugin.getConfig().set("Players." + p.getName() + ".kills", Integer.valueOf(0));
      }
    }
    else if (args.length == 1) {
      if (plugin.getConfig().contains("Players." + args[0] + ".kills")) {
        p.sendMessage("§8[§c§l!§8] §9SharkRank> §a" + args[0] + 
          " §7ha §a" + plugin.getConfig().getString(new StringBuilder("Players.").append(args[0]).append(".kills").toString()) + 
          " §7kills.");
       } else {
        p.sendMessage("§8[§c§l!§8] §9SharkRank> §a" + args[0] + 
          " §7non risulta sui nostri registri!");
      }
    }
  }
}