import java.util.Random;

public class BanditAttack {
    private boolean defendedSuccessfully;
    private int healthImpact;

    public BanditAttack() {
        this.defendedSuccessfully = false;
        this.healthImpact = 0;
    }
    public String startAttack(FamilyMember familyMember) {
        if (familyMember == null) {
            return "No family member selected to defend against the attack.";
        }

        System.out.println("Bandits are attacking your bunker!");

        if (familyMember.hasItem("Axe")) {
            System.out.println(familyMember.getName() + " used the Axe to defend the bunker successfully!");
            this.defendedSuccessfully = true;

            Random random = new Random();
            if (random.nextBoolean()) {
                System.out.println("The Axe was damaged during the defense.");
                familyMember.removeItem();
            }
        } else {
            System.out.println("You have no Axe to defend yourself!");
            System.out.println(familyMember.getName() + " was injured during the attack. Health decreased by 30%.");
            this.healthImpact = 30;
            familyMember.reduceHealth(this.healthImpact);
        }

        if (this.defendedSuccessfully) {
            return "Attack successfully defended!";
        } else {
            return "Attack failed. Family injured.";
        }
    }
}
