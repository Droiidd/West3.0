package droidco.west3.ironsight.Objects.Player.Commands;

import droidco.west3.ironsight.Objects.Player.IronPlayer;
import droidco.west3.ironsight.Utils.GlobalUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            IronPlayer iP = IronPlayer.getPlayer(p);
            if(strings.length == 0){
                //Player entered commands with no args
                //show options
                p.sendMessage("Iron Sight Command options:");
                p.sendMessage("/is gold add *amount* -> adds funds to players bank");

            }
            else{
                switch(strings[0]){
                    case "gold":
                        if(strings.length == 3){
                            switch(strings[1]){
                                case "add":
                                    IronPlayer iPlayer =  IronPlayer.getPlayer(p);
                                    double amount = GlobalUtils.checkStrToDErrMsg(strings[2], p);
                                    iPlayer.updateBank(amount);
                                    p.sendMessage("Gold added to account.");
                                    break;
                                case "remove":

                                    break;
                            }
                        }
                        break;
                    case "save":
                        if(strings[1].equalsIgnoreCase("player")){
                            //SAVE PLAYER HERE
                            System.out.println("Player data successfully saved");
                        }
                        else{
                            p.sendMessage("Incorrect usage, try /is save player");
                        }
                        break;
                    case "bounty":
                        iP.updateBounty(1000);
                        break;
                    case "bounty2":
                        iP.setBounty(1);
                }
            }



        }



        return false;
    }


}
