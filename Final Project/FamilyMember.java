public class FamilyMember {
    private String name;
    private int health;
    private int efficent;
    private String carriedItem;
    
    public FamilyMember(String name, int health, int efficent){
        this.name = name;
        this.health = health;
        this.efficent = efficent;
        this.carriedItem = "";
    }
    
    public void carryItem(String item) {
        this.carriedItem = item;
    }
    public void removeItem() {
        this.carriedItem = "";
    }
    public boolean hasItem(String item){
        return this.carriedItem.equalsIgnoreCase(item);
    }
    
    public void reduceHealth(int amount){
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }
    public String getName() {
        return name;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getEfficiency() {
        return efficent;
    }
    
    public void restoreHealth(int amount) {
        this.health += amount;
        if (this.health > 100) { // Cap health at 100
            this.health = 100;
        }
    }
    
    public String getDetails() {
        return name + " - Health: " + health + " - Efficiency: " + efficent;
    }
}
