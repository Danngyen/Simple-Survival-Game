import java.util.Random;

public class Expedition {
    protected static int totalExpeditions = 0;
    protected static int totalLootCollected = 0;

    protected String loot;
    protected int healthImpact;
    protected int duration;

    public Expedition() {
        this.loot = "";
        this.healthImpact = 0;
        this.duration =0;
    }

    public String startExpedition(FamilyMember member, InventoryItem item1, InventoryItem item2) {
        if (member.getName().equals("No member")) {
            return "Expedition failed. No family member selected.";
        }

        totalExpeditions++;
        return "Expedition started with " + member.getName() + ". Duration: " + duration + " days.";
    }

    public static int getTotalExpeditions() {
        return totalExpeditions;
    }

    public static int getTotalLootCollected() {
        return totalLootCollected;
    }
}