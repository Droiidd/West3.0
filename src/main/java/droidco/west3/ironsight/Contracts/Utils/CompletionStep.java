package droidco.west3.ironsight.Contracts.Utils;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class CompletionStep
{
    private List<String> taskDesc;
    private ItemStack requestedGoods;
    private String locationDesc;
    private int stepNumber;
    private HashMap<String, CompletionStep> steps = new HashMap<>();
    public CompletionStep(String stepKey,int stepNumber,List<String> taskDsc, ItemStack requestedGoods, String locationDesc){
        this.taskDesc=taskDsc;
        this.requestedGoods=requestedGoods;
        this.locationDesc=locationDesc;
        steps.put(stepKey,this);
    }
    public int getStepNumber(){
        return this.stepNumber;
    }

    public List<String> getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(List<String> taskDesc) {
        this.taskDesc = taskDesc;
    }

    public ItemStack getRequestedGoods() {
        return requestedGoods;
    }

    public void setRequestedGoods(ItemStack requestedGoods) {
        this.requestedGoods = requestedGoods;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }
}