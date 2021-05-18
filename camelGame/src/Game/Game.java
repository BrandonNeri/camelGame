package Game;

import GameEntities.ComputerRobot;
import GameEntities.Hackerman;
import HelperClasses.DescriptionHelper;
import HelperClasses.MenuHelper;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Brandon Neri
 */
public class Game {

    ComputerRobot computerRobot;
    Hackerman hackerman;

    Random r = new Random();
    Scanner s = new Scanner(System.in);

    // Starting values
    int difficulty = 1;
    int distanceTraveled = 0;
    int pursuerDistance = -100;
    int currentDay = 1;
    int difficultyModifier;
    int distanceBetweenPursuersAndRider = 100;

    String timeOfDay = "3:00 AM";
    String location = "best buy";
    String weather = "clear";

    public Game(int difficulty, ComputerRobot computer, Hackerman hackerman) {
        this.computerRobot = computer;
        this.hackerman = hackerman;
        this.difficulty = difficulty;

        switch (this.difficulty) { // Difficulty selection
            case 1:
                difficultyModifier = 1;
                break;
            case 2:
                difficultyModifier = 2;
                break;
            case 3:
                difficultyModifier = 3;
                break;
            case 4:
                difficultyModifier = 4;
                break;
            default:
                break;
        }
    }

    public void start() {
        while (true) { // Continous loop for game play
            while (this.checkComputerCanTravel() == true && this.checkRiderIsDead() == false) { // Check if game is lost
                this.outputTurnInformation();
                if (distanceTraveled >= 100) { // Check if game is won
                    System.out.println("Congrats! You escaped the government! You Win!");
                    System.exit(0);
                }
                if (distanceBetweenPursuersAndRider <= 0) { // Check if caught by government
                    System.out.println("Game Over...You were caught by the government.");
                    System.exit(0);
                }
                this.processTurn();
            }
            System.out.println("Game Over...You died trying to escape the government.");
            System.exit(0);
        }

    }

    private boolean checkComputerCanTravel() { // Returns true if computer can travel
        return !(computerRobot.getEnergy() <= 0 || computerRobot.getHunger() <= 0 || computerRobot.getThirst() <= 0); //System.out.println("Your computer can hack.");
    }

    private boolean checkRiderIsDead() { // Returns false if Hackerman is dead
        if (hackerman.getEnergy() <= 0 || hackerman.getHunger() <= 0 || hackerman.getThirst() <= 0) {
            System.out.println("Your Hackerman has died.");
            return true;
        } else {
            return false;
        }
    }

    private void getNewLocation() { // Changes location based on random if called
        int value = r.nextInt(15);
        if (value <= 1 && value >= 0) {
            location = "circuit city";
        } else if (value <= 3 && value >= 2) {
            location = "electric hills";
        } else if (value <= 5 && value >= 4) {
            location = "hacker's paradise";
        } else if (value <= 11 && value >= 6) {
            location = "apple hq";
        } else {
            location = "best buy";
        }
    }

    private void getNewWeather() { // Changes weather based on random if called
        int value = r.nextInt(9);
        if (value == 0) {
            weather = "clear";
        } else if (value == 1) {
            weather = "raining";
        } else if (value == 2) {
            weather = "cloudy";
        } else if (value <= 5 && value >= 3) {
            weather = "sunny";
        } else {
            weather = "very hot";
        }
    }

    private int i;

    private void getNewTimeOfDay() { // Cycles through times of day
        String times[] = {"6:00 AM", "9:00 AM", "1:00 PM", "8:00 PM", "12:00 AM"};

        switch (i) { // Time of day cycle
            case 0:
                timeOfDay = times[0];
                i++;
                break;
            case 1:
                timeOfDay = times[1];
                i++;
                break;
            case 2:
                timeOfDay = times[2];
                i++;
                break;
            case 3:
                timeOfDay = times[3];
                i++;
                break;
            default:
                timeOfDay = times[4];
                i = 0;
                currentDay++; // Updates one day once reached
                break;
        }
    }

    private String getPursuerDistanceDescription() { // Distance between player and pursuer vocalized
        distanceBetweenPursuersAndRider = distanceTraveled - (pursuerDistance * (difficultyModifier));

        // Custom ranges output descriptions based on distance between player and pursuers
        if (distanceBetweenPursuersAndRider >= 75) {
            return "The government is far far away.";
        } else if (distanceBetweenPursuersAndRider < 75 && distanceBetweenPursuersAndRider >= 30) {
            return "The government is getting closer.";
        } else if (distanceBetweenPursuersAndRider < 30 && distanceBetweenPursuersAndRider > 0) {
            return "The government is right on your tail.";
        } else {
            return "Caught";
        }
    }

    private void outputTurnInformation() { // Prints out game information
        System.out.println("Day: " + currentDay + "  " + "Distance Traveled: " + distanceTraveled + "  " + "Government Distance: " + this.getPursuerDistanceDescription());
        System.out.println("Time: " + timeOfDay + "  " + "Location: " + location + "  " + "Weather: " + weather);
        System.out.println("Hackerman Status: " + "Your Hacker is " + DescriptionHelper.getStatus(hackerman));
        System.out.println("Computer Status: " + "Your Computer is " + DescriptionHelper.getStatus(computerRobot));
    }

    private void processTurn() { // Processes player input each turn
        int turnChoice;
        String turnChoiceMessage = "What would you like to do:\n 1) Rest\n 2) Search for Food\n 3) Search for Water\n 4) Hack Carefully\n 5) Hack Regularly\n 6) Hack Recklessly\nPlease enter a selection 1-6:";
        turnChoice = MenuHelper.displayMenu(turnChoiceMessage, 1, 6);

        switch (turnChoice) { // Calls on selected method
            case 1:
                this.processRest();
                break;
            case 2:
                this.processFoodSearch();
                break;
            case 3:
                this.processWaterSearch();
                break;
            case 4:
                this.processHackCarefully();
                break;
            case 5:
                this.processHackRegularly();
                break;
            case 6:
                this.processHackRecklessly();
                break;
            default:
                break;
        }
    }

    private void processRest() { // Replenishes rest of players
        // Updates game elements
        this.processPursuers();
        this.getNewTimeOfDay();

        // Special Conditions
        int restAmount = 0;
        if ("6:00 AM".equals(timeOfDay) || "apple hq".equals(location)) {
            System.out.println("Due to the time, you got little sleep.");
            restAmount = 5;
        }
        if ("8:00 PM".equals(timeOfDay)) {
            restAmount = 15;
            System.out.println("Due to the time, you got max sleep.");
        } else if ("hacker's paradise".equals(location)) {
            System.out.println("Due to the time, you got extra sleep.");
            restAmount = 9;
        } else {
            restAmount = 7;
            System.out.println("You slept well.");
        }

        // Changes energy values
        computerRobot.adjustEnergy(computerRobot.getEnergy() + restAmount - difficultyModifier);
        if (computerRobot.getEnergy() > 15) {
            computerRobot.adjustEnergy(15);
        }
        hackerman.adjustEnergy(hackerman.getEnergy() + restAmount - difficultyModifier);
        if (hackerman.getEnergy() > 15) {
            hackerman.adjustEnergy(15);
        }
        
    }

    private void processFoodSearch() { // Replenishes hunger of players
        // Updates game elements
        this.processPursuers();
        this.getNewTimeOfDay();

        // Special Conditions
        int foodChances = r.nextInt(8);
        int odds;
        if ("raining".equals(weather) || "very hot".equals(weather) || "electric hills".equals(location)) {
            odds = 5;
            System.out.println("Finding food is hard due to current conditions.");
        }
        if ("circuit city".equals(location)) {
            odds = 2;
            System.out.println("Finding food is very hard due to current conditions.");
        } else {
            odds = 3;
        }
        // Changes food values
        if (foodChances > odds) {
            computerRobot.adjustHunger(computerRobot.getHunger() + 7 - difficultyModifier);
            hackerman.adjustHunger(hackerman.getHunger() + 7 - difficultyModifier);
            System.out.println("You found food.");
        } else {
            System.out.println("You are unable to find any food.");
        }
        // Secret Special Condition
        if ("hacker's paradise".equals(location)) {
            computerRobot.adjustHunger(15);
            hackerman.adjustHunger(15);
            System.out.println("You recieved max food at paradise.");
        }
        if (computerRobot.getHunger() > 15) {
            computerRobot.adjustHunger(15);
        }
        if (hackerman.getHunger() > 15) {
            hackerman.adjustHunger(15);
        }
    }

    private void processWaterSearch() { // Replenishes thirst of players
        // Updates game elements
        this.processPursuers();
        this.getNewTimeOfDay();

        // Special Conditions
        int waterChances = r.nextInt(8);
        int odds;
        if ("raining".equals(weather) || "apple hq".equals(location)) {
            System.out.println("Finding water is very hard in your current conditions.");
            odds = 1;
        }
        if ("very hot".equals(weather) || "best buy".equals(location)) {
            System.out.println("Finding water is hard in your current conditions.");
            odds = 5;
        } else {
            odds = 3;
        }

        // Changes food values
        if (waterChances > odds) {
            computerRobot.adjustThirst(computerRobot.getThirst() + 7 - difficultyModifier);
            hackerman.adjustThirst(hackerman.getThirst() + 7 - difficultyModifier);
            System.out.println("You found water.");
        } else {
            System.out.println("You are unable to find any water.");
        }
        // Secret Special Condition
        if ("hacker's paradise".equals(location)) {
            computerRobot.adjustThirst(15);
            hackerman.adjustThirst(15);
            System.out.println("You recieved max water at paradise.");
        }
        if (computerRobot.getThirst() > 15) {
            computerRobot.adjustThirst(15);
        }
        if (hackerman.getThirst() > 15) {
            hackerman.adjustThirst(15);
        }
    }

    private void processHackCarefully() {
        // Time travel conditions
        int travelTimeRestriction;
        if (null == timeOfDay) {
            travelTimeRestriction = 0;
        } else switch (timeOfDay) {
            case "12:00 AM":
                travelTimeRestriction = 2;
                System.out.println("Travel is hard at this time.");
                break;
            case "9:00 AM":
                System.out.println("Travel is easy at time time.");
                travelTimeRestriction = -2;
                break;
            default:
                travelTimeRestriction = 0;
                break;
        }

        // Weather travel conditions
        int weatherTravelBonus;
        if (null == weather) {
            weatherTravelBonus = 0;
        } else switch (weather) {
            case "cloudy":
            case "clear":
                System.out.println("Travel is easy in your current condition.");
                weatherTravelBonus = 2;
                break;
            case "raining":
            case "very hot":
                System.out.println("Travel is hard in your current condition.");
                weatherTravelBonus = -1;
                break;
            default:
                weatherTravelBonus = 0;
                break;
        }

        // Adjusts traveled distance
        distanceTraveled = (distanceTraveled + r.nextInt(7) + 3) - difficultyModifier - travelTimeRestriction + weatherTravelBonus;

        // Updates player stats based on travel
        hackerman.adjustEnergy(hackerman.getEnergy() - (1));
        hackerman.adjustHunger(hackerman.getHunger() - (1));
        hackerman.adjustThirst(hackerman.getThirst() - (1));

        // Updates player stats based on travel
        computerRobot.adjustEnergy(computerRobot.getEnergy() - (1));
        computerRobot.adjustHunger(computerRobot.getHunger() - (1));
        computerRobot.adjustThirst(computerRobot.getThirst() - (1));

        // Updates game elements
        this.getNewTimeOfDay();
        this.getNewLocation();
        this.getNewWeather();
        this.processPursuers();
    }

    private void processHackRegularly() {
        // Time travel conditions
        int travelTimeRestriction;
        if (null == timeOfDay) {
            travelTimeRestriction = 0;
        } else switch (timeOfDay) {
            case "12:00 AM":
                travelTimeRestriction = 2;
                System.out.println("Travel is hard at this time.");
                break;
            case "9:00 AM":
                System.out.println("Travel is easy at time time.");
                travelTimeRestriction = -2;
                break;
            default:
                travelTimeRestriction = 0;
                break;
        }

        // Weather travel conditions
        int weatherTravelBonus;
        if (null == weather) {
            weatherTravelBonus = 0;
        } else switch (weather) {
            case "cloudy":
            case "clear":
                System.out.println("Travel is easy in your current condition.");
                weatherTravelBonus = 2;
                break;
            case "raining":
            case "very hot":
                System.out.println("Travel is hard in your current condition.");
                weatherTravelBonus = -1;
                break;
            default:
                weatherTravelBonus = 0;
                break;
        }

        // Adjusts traveled distance
        distanceTraveled = (distanceTraveled + (r.nextInt(10) + 4)) - difficultyModifier - travelTimeRestriction + weatherTravelBonus;

        // Updates player stats based on travel
        hackerman.adjustEnergy(hackerman.getEnergy() - (r.nextInt(2) + 1));
        hackerman.adjustHunger(hackerman.getHunger() - (r.nextInt(2) + 1));
        hackerman.adjustThirst(hackerman.getThirst() - (r.nextInt(2) + 1));

        // Updates player stats based on travel
        computerRobot.adjustEnergy(computerRobot.getEnergy() - (r.nextInt(2) + 1));
        computerRobot.adjustHunger(computerRobot.getHunger() - (r.nextInt(2) + 1));
        computerRobot.adjustThirst(computerRobot.getThirst() - (r.nextInt(2) + 1));

        // Updates game elements
        this.getNewTimeOfDay();
        this.getNewLocation();
        this.getNewWeather();
        this.processPursuers();
    }

    private void processHackRecklessly() {
        // Time travel conditions
        int travelTimeRestriction;
        if (null == timeOfDay) {
            travelTimeRestriction = 0;
        } else switch (timeOfDay) {
            case "12:00 AM":
                travelTimeRestriction = 2;
                System.out.println("Travel is hard at this time.");
                break;
            case "9:00 AM":
                System.out.println("Travel is easy at time time.");
                travelTimeRestriction = -2;
                break;
            default:
                travelTimeRestriction = 0;
                break;
        }

        // Weather travel conditions
        int weatherTravelBonus;
        if (null == weather) {
            weatherTravelBonus = 0;
        } else switch (weather) {
            case "cloudy":
            case "clear":
                System.out.println("Travel is easy in your current condition.");
                weatherTravelBonus = 2;
                break;
            case "raining":
            case "very hot":
                System.out.println("Travel is hard in your current condition.");
                weatherTravelBonus = -1;
                break;
            default:
                weatherTravelBonus = 0;
                break;
        }

        // Adjusts traveled distance
        distanceTraveled = (distanceTraveled + (r.nextInt(15) + 5)) - difficultyModifier - travelTimeRestriction + weatherTravelBonus;

        // Updates player stats based on travel
        hackerman.adjustEnergy(hackerman.getEnergy() - (r.nextInt(3) + 1));
        hackerman.adjustHunger(hackerman.getHunger() - (r.nextInt(3) + 1));
        hackerman.adjustThirst(hackerman.getThirst() - (r.nextInt(3) + 1));

        // Updates player stats based on travel
        computerRobot.adjustEnergy(computerRobot.getEnergy() - (r.nextInt(3) + 1));
        computerRobot.adjustHunger(computerRobot.getHunger() - (r.nextInt(3) + 1));
        computerRobot.adjustThirst(computerRobot.getThirst() - (r.nextInt(3) + 1));

        // Updates game elements
        this.getNewTimeOfDay();
        this.getNewLocation();
        this.getNewWeather();
        this.processPursuers();
    }

    private void processPursuers() { // Updates the distance the pursuers are away from the players
        int travelDistance = r.nextInt(10) + 3 + difficultyModifier;
        pursuerDistance = pursuerDistance + travelDistance;
    }
}
