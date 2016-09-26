package it.sharkcraft.sharkrank;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class SharkRank extends JavaPlugin  {
	
	public static SharkRank plugin;
	
	public final int RANK_A_PTK = Integer.parseInt(getConfig().getString("Rank.A.Player_To_Kill"));
	public final String RANK_A_GROUP = getConfig().getString("Rank.A.Pex_Group");
	
	public final int RANK_B_PTK = Integer.parseInt(getConfig().getString("Rank.B.Player_To_Kill"));
	public final String RANK_B_GROUP = getConfig().getString("Rank.B.Pex_Group");
	
	public final int RANK_C_PTK = Integer.parseInt(getConfig().getString("Rank.C.Player_To_Kill"));
	public final String RANK_C_GROUP = getConfig().getString("Rank.C.Pex_Group");
	
	public final int RANK_D_PTK = Integer.parseInt(getConfig().getString("Rank.D.Player_To_Kill"));
	public final String RANK_D_GROUP = getConfig().getString("Rank.D.Pex_Group");
	
	public final int RANK_E_PTK = Integer.parseInt(getConfig().getString("Rank.E.Player_To_Kill"));
	public final String RANK_E_GROUP = getConfig().getString("Rank.E.Pex_Group");
	
 
    public void onEnable(){
    	
    	SharkRank.plugin = this;	// set this as global variable
    	
    	@SuppressWarnings(value = {"unused" })
    	Update up = new Update();

    	Bukkit.getServer().getConsoleSender()
    	.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "!" 
    	+ ChatColor.GRAY + "] " + ChatColor.GRAY + "SharkRank Enabled");
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new KillsListener(), this);
    }
    
	public void onDisable(){
    	Bukkit.getServer().getConsoleSender()
  	    .sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "!" 
  	    + ChatColor.GRAY + "] " + "SharkRank Disabled");
        saveConfig();
    }
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        
        if (args.length == 0) {
        if ((cmd.getName().equalsIgnoreCase("shrank") || (cmd.getName().equalsIgnoreCase("kill")))){
        	
        	Commands.shrank(p, args);
        	
        } else if (cmd.getName().equalsIgnoreCase("shreload")){
        	if (p.hasPermission("SharkRank.Reload")) {
        	Commands.shreload(p);
        	}
        	
        } else if (args.length == 1) {
        	
        	if (args[0].equalsIgnoreCase("setvip")) {
        		
        		if (!getConfig().getBoolean("Players." + args[1] + ".vip")) { // se false, (ti ricordo che (!false) == true)
        			
        			getConfig().set("Players." + args[1] + ".vip", true);
        			p.sendMessage("�9SharkRank> �a" + args[1] + " �7� ora un VIP!");
        			
        		} else {	// se true, con i boolean non c'e bisogno di verificare due volte
        		
        			getConfig().set("Players." + args[1] + ".vip", false);
        			p.sendMessage("�9SharkRank> �a" + args[1] + " �7non � piu un VIP!");
        		}
        	}
        }
        }
        return false; 
    }

 
}

 