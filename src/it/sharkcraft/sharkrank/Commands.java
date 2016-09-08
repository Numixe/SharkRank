package it.sharkcraft.sharkrank;

import org.bukkit.entity.Player;
import static it.sharkcraft.sharkrank.SharkRank.*; // import global variable plugin

public class Commands {
	
	//public static SharkRank plugin;
	
	public static void rankUp(Player player) {
		
    	int kills = Integer.parseInt(plugin.getConfig().getString("Players." + player.getName() + ".kills"));
    	
    	String group = null;
    	
    	if (kills == plugin.RANK_A_PTK) {
    		
    		group = plugin.RANK_A_GROUP;
    	
    	} else if (kills == plugin.RANK_B_PTK) {
    		
    		group = plugin.RANK_B_GROUP;
    		
    	} else if (kills == plugin.RANK_C_PTK) {
    		
    		group = plugin.RANK_C_GROUP;
    		
    	} else if (kills >= plugin.RANK_D_PTK && kills < plugin.RANK_E_PTK) {
    		
    		group = plugin.RANK_D_GROUP;
    		
    	} else if (kills == plugin.RANK_E_PTK) {
    		
    		group = plugin.RANK_E_GROUP;
    	}
    	
    	if (group == null)
    		return;			// don't dispatch any command
    	
    	plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + 
	    	    player.getName() + " group set " + group);
    }
	
	public static void rankUpdate(Player player) {
		
		int kills = Integer.parseInt(plugin.getConfig().getString("Players." + player.getName() + ".kills"));
    	
    	String group = null;
    	
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
            	p.sendMessage("�8[�c�l!�8] �9SharkRank> " + "�a" + p.getName() 
            	+ " �7ha �a" + plugin.getConfig().getString("Players" + "." + p.getName() + ".kills") 
            	+ " �7kills.");
 
        } else {
        	plugin.getConfig().createSection("Players" + "." + p.getName());
        	plugin.getConfig().set("Players" + "." + p.getName() + ".kills", 0);
            }
        	} else if (args.length == 1) {
        		if(plugin.getConfig().contains("Players" + "." + args[0] + ".kills")) {
        			p.sendMessage("�8[�c�l!�8] �9SharkRank> " + "�a" + args[0] 
                	+ " �7ha �a" + plugin.getConfig().getString("Players" + "." + args[0] + ".kills") 
                	+ " �7kills.");
        		} else {
        			p.sendMessage("�8[�c�l!�8] �9SharkRank> " + "�a" + args[0] 
                	+ " �7non risulta sui nostri registri!");
        		}
        	}
	}
	
	public static void shreload(Player sender) {
		
		plugin.saveConfig();
    	plugin.reloadConfig();
    	sender.sendMessage("�8[�c�l!�8] �9SharkRank> " + " �7Reload Complete.");
	}
	
}
