public class InventoryItem {
    private String name;
    private String type;
    private int durability;
    
    public InventoryItem(String name, String type, int durability) {
        this.name = name;
        this.type = type;
        this.durability = durability;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public int getDurability() {
        return durability;
    }
    
    public void useItem() {
        if (durability > 0) durability --;
    }
    
    public boolean isBroken() {
        return durability == 0;
    }
    
    public String getDetails() {
        String durabilityText;
        if (durability == -1) {
            durabilityText = "Infinite";
        }
        else {
            durabilityText = "" + durability;
        }
        return name + " (" + type + "), Durability " + durabilityText;
    }
}
