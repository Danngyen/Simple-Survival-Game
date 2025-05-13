import java.util.Random;
public class HospitalRaid extends Expedition {

    public HospitalRaid() {
        super();
        this.duration = 2 + new Random().nextInt(4); // 2-5 days
    }

    @Override
    public String startExpedition(FamilyMember member, InventoryItem item1, InventoryItem item2) {
        super.startExpedition(member, item1, item2);

        int efficiency = member.getEfficiency();
        int maxLoot = Math.min(4, efficiency / 25); // Max 4 items based on efficiency
        loot = "Medical Supplies and Food/Water (" + maxLoot + " items)";
        healthImpact = 15 + (100 - efficiency) / 7; // Health impact based on efficiency
        member.reduceHealth(healthImpact);

        return "Hospital Raid completed! Loot: " + loot + ". Health impact: -" + healthImpact + ".";
    }
}