import java.util.Random;
import java.util.Scanner;

public class SurvivalGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Family Members
        FamilyMember wife = new FamilyMember("Wife", 100, 80);
        FamilyMember son = new FamilyMember("Son", 90, 60);
        FamilyMember daughter = new FamilyMember("Daughter", 80, 70);
        FamilyMember noMembers = new FamilyMember("No member", 0, 0); // No member placeholder

        FamilyMember[] familyMembers = {wife, son, daughter, noMembers}; // Array of all family members
        FamilyMember[] selectedFamilyMembers = new FamilyMember[3];
        for (int i = 0; i < selectedFamilyMembers.length; i++) {
            selectedFamilyMembers[i] = noMembers; // Array to store selected family members
        }

        // Inventory Items
        InventoryItem[] inventoryItems = new InventoryItem[8];
        inventoryItems[0] = new InventoryItem("Food", "common", -1);
        inventoryItems[1] = new InventoryItem("Water", "common", -1);
        inventoryItems[2] = new InventoryItem("Axe", "tool", 5);
        inventoryItems[3] = new InventoryItem("Gas Mask", "tool", 3);
        inventoryItems[4] = new InventoryItem("Radio", "misc", 2);
        inventoryItems[5] = new InventoryItem("Map", "misc", 1);
        inventoryItems[6] = new InventoryItem("Medical Bag", "misc", 1);
        inventoryItems[7] = new InventoryItem("Empty Slot", "nothing", 0);

        // Introduction
        System.out.println("A nuclear fallout is coming, there is no hope.");
        System.out.println("Choose what you bring for your bunker. You only have a few items/people...");

        // Family Selection
        System.out.println("\n--Choose at least one family member in your survival.--");
        System.out.println("1. Wife");
        System.out.println("2. Son");
        System.out.println("3. Daughter");

        int familyCount = 0;
        while (familyCount < 3) {
            System.out.println("Enter the number of the family member you want to add (or 0 if you don't want to): ");
            while (!scanner.hasNextInt()) { // Validate input is a number
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
            int choice = scanner.nextInt();

            if (choice == 0) {
                break; // Stop adding family members
            } else if (choice >= 1 && choice <= 3) {
                FamilyMember selected = familyMembers[choice - 1]; // Get the selected family member
                boolean alreadySelected = false;
                for (FamilyMember member : selectedFamilyMembers) {
                    if (member.getName().equals(selected.getName())) {
                        alreadySelected = true; // Check if the family member is already selected
                        break;
                    }
                }
                if (!alreadySelected) {
                    selectedFamilyMembers[familyCount] = selected; // Add the family member to the selected array
                    familyCount++;
                } else {
                    System.out.println("This family member is already selected. Choose another one.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        // Selected Items
        InventoryItem[] selectedItems = new InventoryItem[4];
        for (int i = 0; i < selectedItems.length; i++) {
            selectedItems[i] = inventoryItems[7]; // Initialize selected items array with empty slots
        }

        // Item Selection
        System.out.println("\nChoose up to 4 items to bring with you.");
        System.out.println("1. Food");
        System.out.println("2. Water");
        System.out.println("3. Axe");
        System.out.println("4. Gas Mask");
        System.out.println("5. Radio");
        System.out.println("6. Medical Bag");
        System.out.println("7. Map");

        int itemCount = 0;
        while (itemCount < 4) {
            System.out.println("Enter the number of the item you want to add (or 0 if you don't want to): ");
            while (!scanner.hasNextInt()) { // Validate input is a number
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= 7) {
                InventoryItem selected = inventoryItems[choice - 1]; // Get the selected item
                boolean alreadySelected = false;
                for (InventoryItem item : selectedItems) {
                    if (item == selected) {
                        alreadySelected = true;
                        break;
                    }
                }
                if (!alreadySelected) {
                    selectedItems[itemCount] = selected; // Add the item to the selected array
                    itemCount++;
                } else {
                    System.out.println("This item is already selected. Choose another one.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        // Bunker Setup
        InventoryItem[][] bunker = new InventoryItem[3][4]; // 3 family members, 4 inventory slots each
        for (int i = 0; i < bunker.length; i++) {
            for (int j = 0; j < bunker[i].length; j++) {
                bunker[i][j] = inventoryItems[7]; // Initialize with "Empty Slot"
            }
        }

        // Assign Items to Family Members
        for (int i = 0; i < selectedFamilyMembers.length; i++) {
            if (!selectedFamilyMembers[i].getName().equals("No member")) {
                System.out.println("Assign items to " + selectedFamilyMembers[i].getName() + ":");
                for (int j = 0; j < bunker[i].length; j++) {
                    System.out.println("Enter the number of the item to assign to slot " + (j + 1) + " (or 0 to skip): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    }
                    int choice = scanner.nextInt();

                    if (choice == 0) {
                        break; // Stop assigning items
                    } else if (choice >= 1 && choice <= 7) {
                        bunker[i][j] = inventoryItems[choice - 1];
                    } else {
                        System.out.println("Invalid choice. Try again.");
                        j--; // Retry the same slot
                    }
                }
            }
        }

        // Display Bunker Setup
        System.out.println("\nBunker Setup:");
        for (int i = 0; i < selectedFamilyMembers.length; i++) {
            if (!selectedFamilyMembers[i].getName().equals("No member")) {
                System.out.println(selectedFamilyMembers[i].getName() + "'s Items:");
                for (int j = 0; j < bunker[i].length; j++) {
                    System.out.println("- " + bunker[i][j].getName());
                }
            }
        }

        // Game Loop
        int day = 1;
        int expeditionDaysRemaining = 0; // Tracks the remaining days for the expedition
        FamilyMember expeditionMember = null; // Tracks the family member on the expedition
        Expedition activeExpedition = null; // Tracks the active expedition

        while (day <= 15) {
            System.out.println("\nDay " + day + ":");
            System.out.println("1. Send an expedition");
            System.out.println("2. Feed a family member");
            System.out.println("3. Check family member status");
            System.out.println("4. End the day");
            System.out.print("Enter your choice: ");

            // Validate input for action
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            int action = scanner.nextInt();

            if (action == 1) {
                // Expedition Logic
                if (expeditionDaysRemaining > 0) {
                    System.out.println("An expedition is already in progress. Wait for it to complete.");
                    continue;
                }

                System.out.println("Choose a raid type:");
                System.out.println("1. House Raid");
                System.out.println("2. Town Raid");
                System.out.println("3. Factory Raid");
                System.out.println("4. Hospital Raid");

                // Validate input for raid choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int raidChoice = scanner.nextInt();

                Expedition expedition = null;
                if (raidChoice == 1) {
                    expedition = new HouseRaid();
                } else if (raidChoice == 2) {
                    expedition = new TownRaid();
                } else if (raidChoice == 3) {
                    expedition = new FactoryRaid();
                } else if (raidChoice == 4) {
                    expedition = new HospitalRaid();
                } else {
                    System.out.println("Invalid choice.");
                    continue;
                }

                System.out.println("Choose a family member for the expedition:");
                for (int i = 0; i < selectedFamilyMembers.length; i++) {
                    System.out.println((i + 1) + ". " + selectedFamilyMembers[i].getName());
                }

                // Validate input for family member choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int memberChoice = scanner.nextInt();

                FamilyMember member = null;
                if (memberChoice >= 1 && memberChoice <= selectedFamilyMembers.length) {
                    member = selectedFamilyMembers[memberChoice - 1];
                }

                if (member == null || member.getName().equals("No member") || member.getName().equals("On an expedition")) {
                    System.out.println("Invalid choice. No family member selected or already on an expedition.");
                    continue;
                }

                System.out.println("Choose up to 2 items to bring:");
                for (int i = 0; i < selectedItems.length; i++) {
                    System.out.println((i + 1) + ". " + selectedItems[i].getName());
                }

                // Validate input for item choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int item1Choice = scanner.nextInt();

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int item2Choice = scanner.nextInt();

                InventoryItem item1 = null, item2 = null;
                if (item1Choice >= 1 && item1Choice <= selectedItems.length) {
                    item1 = selectedItems[item1Choice - 1];
                }
                if (item2Choice >= 1 && item2Choice <= selectedItems.length) {
                    item2 = selectedItems[item2Choice - 1];
                }

                expedition.duration = Math.max(1, expedition.duration - (member.getEfficiency() / 20)); // Adjust duration based on efficiency

                // Start the expedition
                System.out.println(expedition.startExpedition(member, item1, item2));
                member.carryItem("On an expedition"); // Mark as "on an expedition"
                expeditionDaysRemaining = expedition.duration;
                expeditionMember = member;
                activeExpedition = expedition;

            } else if (action == 2) {
                // Feeding Logic
                System.out.println("Feeding a family member...");
                for (int i = 0; i < selectedFamilyMembers.length; i++) {
                    System.out.println((i + 1) + ". " + selectedFamilyMembers[i].getName());
                }
                System.out.print("Choose a family member to feed: ");

                // Validate input for family member choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
                int memberChoice = scanner.nextInt();

                FamilyMember memberToFeed = null;
                if (memberChoice >= 1 && memberChoice <= selectedFamilyMembers.length) {
                    memberToFeed = selectedFamilyMembers[memberChoice - 1];
                }

                if (memberToFeed != null && !memberToFeed.getName().equals("No member") && !memberToFeed.getName().equals("On an expedition")) {
                    boolean foodAvailable = false;
                    for (InventoryItem item : selectedItems) {
                        if (item.getName().equals("Food")) {
                            foodAvailable = true;
                            break;
                        }
                    }
                    if (!foodAvailable) {
                        System.out.println("No food available to feed " + memberToFeed.getName());
                    } else {
                        memberToFeed.restoreHealth(20); // Restore health
                        System.out.println(memberToFeed.getName() + " has been fed. Health restored.");
                    }
                } else {
                    System.out.println("Invalid choice or no member selected.");
                }
            } else if (action == 3) {
                // Family Member Status
                System.out.println("Family Member Status:");
                for (FamilyMember member : selectedFamilyMembers) {
                    System.out.println(member.getDetails());
                }
                if (expeditionMember != null) {
                    System.out.println(expeditionMember.getName() + " is currently on an expedition. " +
                            expeditionDaysRemaining + " days remaining.");
                }
            } else if (action == 4) {
                // End Day Logic
                System.out.println("Ending the day...");
                day++;

                // Handle Expedition Progress
                if (expeditionDaysRemaining > 0) {
                    expeditionDaysRemaining--;
                    System.out.println(expeditionMember.getName() + " is on an the expedition. " + expeditionDaysRemaining + " days remaining.");
                    if (expeditionDaysRemaining == 0) {
                        System.out.println("Expedition completed! " + activeExpedition.loot);
                        expeditionMember.reduceHealth(activeExpedition.healthImpact); // Apply health impact
                        System.out.println(expeditionMember.getName() + " returned with loot. Health impact: -" +
                                activeExpedition.healthImpact + ".");
                        expeditionMember.removeItem(); // Mark as no longer on an expedition
                        expeditionMember = null;
                        activeExpedition = null;
                    }
                }

                // Random Bandit Attack
                Random random = new Random();
                if (random.nextInt(100) < 30) { // 30% chance of attack
                    BanditAttack attack = new BanditAttack();
                    System.out.println(attack.startAttack(selectedFamilyMembers[0])); // Example attack on first member
                }
            } else {
                System.out.println("Invalid choice, try again.");
            }
        }

        // Summary
        System.out.println("\nGame Over. Survival Summary:");
        System.out.println("Total Expeditions: " + Expedition.getTotalExpeditions());
        System.out.println("Total Loot Collected: " + Expedition.getTotalLootCollected());
        scanner.close();
    }
}