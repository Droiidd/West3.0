package droidco.west3.ironsight.Contracts.UI;

import droidco.west3.ironsight.Contracts.Contract;
import droidco.west3.ironsight.Contracts.Utils.CompletionStep;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.ItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ActiveContractUI
{
    public static Inventory openActiveContractUi(Player p, Contract contract){
        Inventory contractUi = Bukkit.createInventory(p, 54, ChatColor.DARK_GRAY+"Active Contract info:");
        //Bandit b = Bandit.getPlayer(p);
        int reqColumn = 4;
        int locColumn = 2;
        int descColumn = 6;

        contractUi.setItem(locColumn, ItemIcon.getIcon("contract_location").getItem());
        contractUi.setItem(reqColumn,ItemIcon.getIcon("contract_req").getItem());
        contractUi.setItem(descColumn,ItemIcon.getIcon("contract_desc").getItem());
        //the rest of the slots are auto generated by the steps list
        List<CompletionStep> steps = contract.getSteps();
        for(CompletionStep step : steps){
            locColumn += 9;
            reqColumn +=9;
            descColumn +=9;
            int stepNum = step.getStepNumber();
            ItemStack locSlot = getStepLocationIcon(stepNum,step.getLocationDesc());
            ItemStack descSlot = getStepDescIcon(stepNum,step.getTaskDesc());
            ItemStack reqSlot = getStepRequestedItem(stepNum,step.getRequestedGoods(),contract.getRequestedAmount());
            contractUi.setItem(locColumn, locSlot);
            if(reqSlot != null){
                contractUi.setItem(reqColumn,reqSlot);
            }
            contractUi.setItem(descColumn,descSlot);
        }
        contractUi.setItem(0,ContractUI.getResignContractIcon());
        //contractUi.setItem(13,getActiveContractItem(iPlayer));
        return contractUi;
    }
    public static ItemStack getStepDescIcon(int stepNumber, List<String> stepDesc){
        ItemStack item = new ItemStack(Material.GRAY_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Step "+stepNumber);
        List<String> lore = new ArrayList<>();
        for (String step : stepDesc){
            lore.add(String.valueOf(ChatColor.GRAY)+step);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getStepLocationIcon(int stepNumber, String stepLoc){
        ItemStack item = new ItemStack(Material.WHITE_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE+"Step "+stepNumber);
        List<String> lore = new ArrayList<>();
        lore.add(String.valueOf(ChatColor.GRAY)+stepLoc);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getStepRequestedItem(int stepNumber, ItemStack requestedItem,int requestAmount){
        ItemStack item = null;
        if(requestedItem != null){
            item = requestedItem;
            ItemMeta meta = item.getItemMeta();
            String requestMessage = String.valueOf(ChatColor.WHITE)+"Aqcuire "+ChatColor.GREEN +requestAmount+" "+requestedItem.getItemMeta().getDisplayName();
            meta.setDisplayName(requestMessage);
            item.setItemMeta(meta);
        }

        return item;
    }
}
