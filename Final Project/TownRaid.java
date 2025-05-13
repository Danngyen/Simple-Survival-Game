import java.util.Random;
public class TownRaid extends Expedition {

    public TownRaid() {
        super();
        this.duration = 3 + new Random().nextInt(4); // 3-6 days
    }

    @Override
    public String startExpedition(FamilyMember member, InventoryItem item1, InventoryItem item2) {
        super.startExpedition(member, item1, item2);

        int efficiency = member.getEfficiency();
        int maxLoot = Math.min(5, efficiency / 20); // Max 5 items based on efficiency
        loot = "Random Items (" + maxLoot + " items)";
        healthImpact = 15 + (100 - efficiency) / 8; // Health impact based on efficiency
        member.reduceHealth(healthImpact);

        return "Town Raid completed! Loot: " + loot + ". Health impact: -" + healthImpact + ".";
    }
}