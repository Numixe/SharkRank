package it.sharkcraft.sharkrank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class SharkRank extends JavaPlugin  {
	
	public static SharkRank plugin;
	public static KillBoard killboard;
	
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
    	.sendMessage("SharkRank Enabled");
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new KillsListener(), this);
        // Carica KillBoard
        killboard = new KillBoard();
    }
    
	public void onDisable(){
    	Bukkit.getServer().getConsoleSender()
  	    .sendMessage("SharkRank Disabled");
        saveConfig();
    }
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        
        if ((cmd.getName().equalsIgnoreCase("shrank") || (cmd.getName().equalsIgnoreCase("kill")))) {
        	
        	Commands.shrank(p, args);
        	
        } else if (cmd.getName().equalsIgnoreCase("shreload")) {
        	if (p.hasPermission("SharkRank.Reload")) {
        		
        	Commands.shreload(p);
        	killboard.refresh();
        	}
        	
      } else if ((cmd.getName().equalsIgnoreCase("shset")) || (cmd.getName().equalsIgnoreCase("killset"))) {
      if (p.hasPermission("SharkRank.set")) {
        Commands.killset(p, args);
      } else {
        p.sendMessage("�8[�c�l!�8] �9SharkRank> �cNon hai il permesso di usare questo comando!");
      }
    }
    return false;
  }
}
