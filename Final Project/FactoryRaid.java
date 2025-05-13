import java.util.Random;
public class FactoryRaid extends Expedition {

    public FactoryRaid() {
        super();
        this.duration = 3 + new Random().nextInt(4); // 3-6 days
    }

    @Override
    public String startExpedition(FamilyMember member, InventoryItem item1, InventoryItem item2) {
        super.startExpedition(member, item1, item2);

        int efficiency = member.getEfficiency();
        int maxLoot = Math.min(4, efficiency / 25); // Max 4 tools based on efficiency
        loot = "Tools (" + maxLoot + " items)";
        healthImpact = 20 + (100 - efficiency) / 6; // Health impact based on efficiency
        member.reduceHealth(healthImpact);

        return "Factory Raid completed! Loot: " + loot + ". Health impact: -" + healthImpact + ".";
    }
}