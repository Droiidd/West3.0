package droidco.west3.ironsight.Contracts;

import droidco.west3.ironsight.Contracts.UI.ActiveContractUI;
import droidco.west3.ironsight.Contracts.UI.ContractUI;
import droidco.west3.ironsight.Bandit.Bandit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ContractMenuCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit b = Bandit.getPlayer(p);
            if(strings.length == 0){
                Inventory test = ActiveContractUI.openActiveContractUi(p,b.getActiveContract());
                if(test != null){
                    p.openInventory(ActiveContractUI.openActiveContractUi(p,b.getActiveContract()));
                }else{
                    p.sendMessage(ChatColor.RED+"No Contract selected! "+ChatColor.GRAY+"Go to a contractor first and start one to view "+ChatColor.AQUA+" active contracts");
                }
            }else if(strings[0].equalsIgnoreCase("reset")){
                Contract.assignPlayerContracts(p,b);
            }else if(strings[0].equalsIgnoreCase("menu")){
                    p.openInventory(ContractUI.openContractUi(p));
            }
        }
        return true;
    }




}
