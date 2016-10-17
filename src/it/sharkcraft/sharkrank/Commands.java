package it.sharkcraft.sharkrank;

// import global variable plugin
import static it.sharkcraft.sharkrank.SharkRank.plugin;

import org.bukkit.entity.Player;

public class Commands {
	
	//public static SharkRank plugin;
	
	public static void rankUp(Player player) {
		
		int kills = Integer.parseInt(plugin.getConfig().getString("Players." + player.getName() + ".kills"));
    	
    	String group = null;
    	
    	if (plugin.getConfig().getBoolean("Players." + player.getName() + ".vip"))	// se vip, non fare nulla
    		return;
    	
    	if (kills >= plugin.RANK_A_PTK && kills < plugin.RANK_B_PTK) {
    		
    		group = plugin.RANK_A_GROUP;
    	
    	} else if (kills >= plugin.RANK_B_PTK && kills < plugin.RANK_C_PTK) {
    		
    		group = plugin.RANK_B_GROUP;
    		
    	} else if (kills >= plugin.RANK_C_PTK && kills < plugin.RANK_D_PTK) {
    		
    		group = plugin.RANK_C_GROUP;
    		
    	} else if (kills >= plugin.RANK_D_PTK && kills < plugin.RANK_E_PTK) {
    		
    		group = plugin.RANK_D_GROUP;
    		
    	} else if (kills >= plugin.RANK_E_PTK) {
    		
    		group = plugin.RANK_E_GROUP;
    	}
    	
    	if (group == null)
    		return;			// don't dispatch any command
    	
    	plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
	    	    player.getName() + " group set " + group);
	}
	
	public static void shrank(Player sender, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
            if(plugin.getConfig().contains("Players" + "." + p.getName() + ".kills")){  
            	p.sendMessage("§8[§c§l!§8] §9SharkRank> " + plugin.getConfig().getString("Message." + "MSG_YOUR_KILLS")
            			.replace("&", "§").replace("%playername%", p.getName())
            			.replace("%mykills%", plugin.getConfig().getString("Players" + "." + p.getName() + ".kills")));
 
        } else {
        	plugin.getConfig().createSection("Players" + "." + p.getName());
        	plugin.getConfig().set("Players" + "." + p.getName() + ".kills", 0);
            }
            
        	} else if (args.length == 1) {
        		if(plugin.getConfig().contains("Players" + "." + args[0] + ".kills")) {
        			p.sendMessage("§8[§c§l!§8] §9SharkRank> " + plugin.getConfig().getString("Message." +  "MSG_OTHER_KILLS")
        			.replace("&", "§").replace("%othername%", args[0])
        	        .replace("%otherkills%", plugin.getConfig().getString("Players" + "." + args[0] + ".kills")));
        		} else {
        			p.sendMessage("§8[§c§l!§8] §9SharkRank> "  + plugin.getConfig().getString("Message." +  "MSG_NO_KILLS")
        			.replace("&", "§").replace("%othername%", args[0]));
        		}
        	}
	}
	
	public static void shreload(Player sender) {
		plugin.saveConfig();
    	plugin.reloadConfig();
    	sender.sendMessage("§8[§c§l!§8] §9SharkRank> " + plugin.getConfig().getString("Message." +  "MSG_RELOAD")			
    	.replace("&", "§"));
	}
  
	public static void killset(Player sender, String[] args) {
    Player p = sender;
    if (args.length == 0) {
      p.sendMessage("§8[§c§l!§8] §9SharkRank> §7Usa: §a/killset <nome> <kill>");
    }
    else if (args.length == 1) {
      p.sendMessage("§8[§c§l!§8] §9SharkRank> §7Usa: §a/killset <nome> <kill>");
    }
    else if (args.length == 2)
    {
      SharkRank.plugin.getConfig().set("Players." + args[0] + ".kills", Integer.valueOf(args[1]));
      p.sendMessage("§8[§c§l!§8] §9SharkRank> §7Kill di §c" + args[0] + " §7impostate a §a" + args[1]);
    }
  }
}
