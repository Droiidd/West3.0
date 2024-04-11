package droidco.west3.ironsight.Processors;

import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.IronSight;
import droidco.west3.ironsight.Items.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class ProcessorEvents implements Listener {
    private static final String smokeLeafProcName1 = ChatColor.RED    + "Smokeleaf Processor 1";
    private static final String smokeLeafProcName2 = ChatColor.RED   + "Smokeleaf Processor 2";
    private static final String smokeLeafProcName3 = ChatColor.RED  + "Smokeleaf Processor 3";
    private IronSight plugin;
    public ProcessorEvents(IronSight plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void illegalSalesmenHandler(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        // >>>===--- SMOKE LEAF PROCESSOR 1 ---===<<<
        for(var processor : Processor.getProcessors().entrySet()) {
            if(e.getView().getTitle().equalsIgnoreCase(ChatColor.stripColor(processor.getValue().getProcessorCode()))) {
                e.setCancelled(true);
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CustomItem.getCustomItem("Unprocessed Smokelef").getItemStack().getItemMeta().getDisplayName())) {
                    if (!p.getInventory().containsAtLeast(CustomItem.getCustomItem("Unprocessed Smokeleaf").getItemStack(), 8)) {
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "You have no " + ChatColor.RED + "smokeleaf!");

                    } else {
                        //PLAYER HAS DRUGS
                        if (processor.getValue().isProcessing()) {
                            //THERE IS A PLAYER PROCESSING ALREADY
                            p.sendMessage(ChatColor.RED + "This processor is already in use!");
                            p.closeInventory();
                            return;
                        } else {
                            //IT'S UNUSED!
                            //

                            int chance = GlobalUtils.getRandomNumber(101);
                            if (chance < 5) {
                                Bukkit.broadcastMessage(ChatColor.RED + processor.getValue().getProcessorCode()+ChatColor.GRAY+" has " + ChatColor.AQUA + " changed locations!");
                                //SPAWN LOC 1
                                List<Entity> entities = p.getNearbyEntities(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                                for (int i = 0; i < entities.size(); i++) {
                                    if (entities.get(i) instanceof Villager) {
                                        if (entities.get(i).getCustomName().equalsIgnoreCase(ChatColor.RED + processor.getValue().getProcessorCode())) {
                                            entities.get(i).remove();
                                        }
                                    }
                                }
                                p.closeInventory();
                                processor.getValue().randomizeLocAndSpawn(p);
                            } else {
                                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1, 1);
                                new ProcessorTask(processor.getValue(), plugin, p, 60, processor.getValue().getUnprocDrug(),processor.getValue().getProcDrug(),
                                        90.0, 8, processor.getValue().getDefaultLocation());
                                p.closeInventory();
                            }

                        }
                    }
                }else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    p.closeInventory();
                }
            }
        }
    }
}