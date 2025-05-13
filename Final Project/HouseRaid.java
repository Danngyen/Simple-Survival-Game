import java.util.Random;
public class HouseRaid extends Expedition {

    public HouseRaid() {
        super();
        this.duration = 1 + new Random().nextInt(3); // 1-3 days
    }

    @Override
    public String startExpedition(FamilyMember member, InventoryItem item1, InventoryItem item2) {
        super.startExpedition(member, item1, item2);

        int efficiency = member.getEfficiency();
        int maxLoot = Math.min(5, efficiency / 20); // Max 5 items based on efficiency
        loot = "Food and Water (" + maxLoot + " items)";
        healthImpact = 10 + (100 - efficiency) / 10; // Health impact based on efficiency
        member.reduceHealth(healthImpact);

        return "House Raid completed! Loot: " + loot + ". Health impact: -" + healthImpact + ".";
    }
}