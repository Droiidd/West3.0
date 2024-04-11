package droidco.west3.ironsight.Horse.Commands;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Horse.FrontierHorse;
import droidco.west3.ironsight.Horse.FrontierHorseType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminGetHorseCmd implements CommandExecutor

{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit iP = Bandit.getPlayer(p);
            if(strings.length != 2){
                //Player entered commands with no args
                //show options
                p.sendMessage("Iron Sight Command options:");
                p.sendMessage("/gethorse *horseType* *horseName*");

            }
            else{
                switch(strings[0]){
                    case "standard":
                        checkNewPlayerHorse(p, strings[1] != null ? strings[1] : "Default name", FrontierHorseType.STANDARD);
                        break;
                    case "thoroughbred":
                        checkNewPlayerHorse(p,  strings[1] != null ? strings[1] : "Default name", FrontierHorseType.THOROUGHBRED);
                        break;
                    case "donkey":
                        checkNewPlayerHorse(p,  strings[1] != null ? strings[1] : "Default name", FrontierHorseType.DONKEY);
                        break;
                }
            }
        }

        return true;
    }

    public void checkNewPlayerHorse(Player p, String horseName, FrontierHorseType horseType) {
        Bandit b = Bandit.getPlayer(p);
        List<FrontierHorse> horses = b.getHorses();
        boolean sameNameFlag = false;
        if(horses.size() < b.getMaxHorseLimit()){
            for(FrontierHorse horse : horses){
                if(horse.getHorseName().equalsIgnoreCase(horseName)){
                    sameNameFlag = true;
                }
            }
            if(sameNameFlag){
                p.sendMessage("Horse exists with that name already!!");
            }else{
                b.getHorses().add(new FrontierHorse(p.getUniqueId().toString(),horseName,horseType));
                p.sendMessage("New horse!");
            }
        }
    }
}
