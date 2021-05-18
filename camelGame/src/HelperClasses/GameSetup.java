package HelperClasses;

import Game.Game;
import GameEntities.ComputerRobot;
import GameEntities.Hackerman;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Brandon Neri
 */
public class GameSetup {

    private GameSetup() {
    }

    public static Game setupGame() {

        // Difficulty selection
        int difficulty;
        String difficultyMessage = "Please select a difficulty:\n 1) Easy\n 2) Normal\n 3) Hardened\n 4) Crazy Hard\nPlease enter a selection 1-4:";
        difficulty = MenuHelper.displayMenu(difficultyMessage, 1, 4);
        
        // Computer Robot selection
        int computerRobotSelection;
        String computerRobotSelectionMessage = "What type of computer robot would you like to use:\n 1) Standard\n 2) Random\n 3) Custom\nPlease enter a selection 1-3:";
        computerRobotSelection = MenuHelper.displayMenu(computerRobotSelectionMessage, 1, 3);
        ComputerRobot computer = setupComputer(computerRobotSelection); // Create instance of ComputerRobot class

        // Hackerman selection
        int hackermanSelection;
        String hackermanSelectionMessage = "What type of Hackerman would you like to use:\n 1) Standard\n 2) Random\n 3) Custom\nPlease enter a selection 1-3:";
        hackermanSelection = MenuHelper.displayMenu(hackermanSelectionMessage, 1, 3);
        Hackerman hacker = setupHackerman(hackermanSelection); // Create instance of Hackerman class

        // Instance of Game Class
        Game theGame = new Game(difficulty, computer, hacker);
        return theGame;
    }

    public static ComputerRobot setupComputer(int option) { // creates ComputerRobot object

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        switch (option) {
            case (1): // Standard Case
                System.out.println("Enter your Computer Robot's name:");
                String name1 = s.nextLine();
                return new ComputerRobot(name1, 8, 8, 8);
            case (2): // Random Case
                System.out.println("Enter your Computer Robot's name:");
                String name2 = s.nextLine();
                return new ComputerRobot(name2, r.nextInt(14) + 1, r.nextInt(14) + 1, r.nextInt(14) + 1);
            case (3): // Custom Case
                System.out.println("Enter your Computer Robot's name:");
                String name3 = s.nextLine();

                int thirst; // Custom thirst value
                String computerRobotThirstMessage = "Enter your Computer Robot's Thirst (1-15):";
                thirst = MenuHelper.displayMenu(computerRobotThirstMessage, 1, 15);

                int health; // Custom health value
                String computerRobotHealthMessage = "Enter your Computer Robot's Health (1-15):";
                health = MenuHelper.displayMenu(computerRobotHealthMessage, 1, 15);

                int energy; // Custom energy value
                String computerRobotEnergyMessage = "Enter your Computer Robot's Energy (1-15):";
                energy = MenuHelper.displayMenu(computerRobotEnergyMessage, 1, 15);

                return new ComputerRobot(name3, thirst, health, energy);

            default:
                return null;
        }
    }

    public static Hackerman setupHackerman(int option) { // creates Hackerman object

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        switch (option) {
            case (1): // Standard Case
                System.out.println("Enter your Hackerman's name:");
                String name1 = s.nextLine();
                return new Hackerman(name1, 8, 8, 8);
            case (2): // Random Case
                System.out.println("Enter your Hackerman's name:");
                String name2 = s.nextLine();
                return new Hackerman(name2, r.nextInt(14) + 1, r.nextInt(14) + 1, r.nextInt(14) + 1);
            case (3): // Custom Case
                System.out.println("Enter your Hackerman's name:");
                String name3 = s.nextLine();
                
                int thirst; // Custom thirst value
                String hackermanThirstMessage = "Enter your Hackerman's Thirst (1-15):";
                thirst = MenuHelper.displayMenu(hackermanThirstMessage, 1, 15);

                int health; // Custom health value
                String hackermanHealthMessage = "Enter your Hackerman's Health (1-15):";
                health = MenuHelper.displayMenu(hackermanHealthMessage, 1, 15);

                int energy; // Custom energy value
                String hackermanEnergyMessage = "Enter your Hackerman's Energy (1-15):";
                energy = MenuHelper.displayMenu(hackermanEnergyMessage, 1, 15);

                return new Hackerman(name3, thirst, health, energy);

            default:
                return null;
        }
    }
}
