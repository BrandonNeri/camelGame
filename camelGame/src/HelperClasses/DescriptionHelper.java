package HelperClasses;

import GameEntities.ComputerRobot;
import GameEntities.Hackerman;

/**
 *
 * @author Brandon Neri
 */
public class DescriptionHelper {

    private DescriptionHelper() {
    }

    // Describes computer robot thirst based on 4 ranged conditions
    public static String getThirstDescription(ComputerRobot computerRobot) { 
        if (computerRobot.getThirst() >= 12) {
            return "Not Thirsty";
        } else if (computerRobot.getThirst() < 12 && computerRobot.getThirst() >= 6) {
            return "Slightly Thirsty";
        } else if (computerRobot.getThirst() < 6 && computerRobot.getThirst() >= 2) {
            return "Thirsty";
        } else {
            return "Very Thirsty";
        }
    }
    
    // Describes hacker thirst based on 4 ranged conditions
    public static String getThirstDescription(Hackerman hacker) {
        if (hacker.getThirst() >= 12) {
            return "Not Thirsty";
        } else if (hacker.getThirst() < 12 && hacker.getThirst() >= 6) {
            return "Slightly Thirsty";
        } else if (hacker.getThirst() < 6 && hacker.getThirst() >= 2) {
            return "Thirsty";
        } else {
            return "Very Thirsty";
        }
    }
    
    // Describes computer robot hunger based on 4 ranged conditions
    public static String getHungerDescription(ComputerRobot computerRobot) {
        if (computerRobot.getHunger() >= 12) {
            return "Not Hungry";
        } else if (computerRobot.getHunger() < 12 && computerRobot.getHunger() >= 6) {
            return "Slightly Hungry";
        } else if (computerRobot.getHunger() < 6 && computerRobot.getHunger() >= 2) {
            return "Hungry";
        } else {
            return "Very Hungry";
        }
    }
    
    // Describes hacker hunger based on 4 ranged conditions
    public static String getHungerDescription(Hackerman hacker) {
        if (hacker.getHunger() >= 12) {
            return "Not Hungry";
        } else if (hacker.getHunger() < 12 && hacker.getHunger() >= 6) {
            return "Slightly Hungry";
        } else if (hacker.getHunger() < 6 && hacker.getHunger() >= 2) {
            return "Hungry";
        } else {
            return "Very Hungry";
        }
    }
    
    // Describes computer robot energy based on 4 ranged conditions
    public static String getEnergyDescription(ComputerRobot computerRobot) {
        if (computerRobot.getEnergy() >= 12) {
            return "Not Tired";
        } else if (computerRobot.getEnergy() < 12 && computerRobot.getEnergy() >= 6) {
            return "Slightly Tired";
        } else if (computerRobot.getEnergy() < 6 && computerRobot.getEnergy() >= 2) {
            return "Tired";
        } else {
            return "Very Tired";
        }
    }
    
    // Describes hacker energy based on 4 ranged conditions
    public static String getEnergyDescription(Hackerman hacker) {
        if (hacker.getEnergy() >= 12) {
            return "Not Tired";
        } else if (hacker.getEnergy() < 12 && hacker.getEnergy() >= 6) {
            return "Slightly Tired";
        } else if (hacker.getEnergy() < 6 && hacker.getEnergy() >= 2) {
            return "Tired";
        } else {
            return "Very Tired";
        }
    }
    
    // Returns hunger, energy, & thirst in a string for computer robot
    public static String getStatus(ComputerRobot computerRobot) {
        return DescriptionHelper.getHungerDescription(computerRobot) + ", "
                + DescriptionHelper.getEnergyDescription(computerRobot) + ", "
                + DescriptionHelper.getThirstDescription(computerRobot) + ".";
    }
    
    // Returns hunger, energy, & thirst in a string for hacker
    public static String getStatus(Hackerman hacker) {
        return DescriptionHelper.getHungerDescription(hacker) + ", "
                + DescriptionHelper.getEnergyDescription(hacker) + ", "
                + DescriptionHelper.getThirstDescription(hacker) + ".";
    }
}
