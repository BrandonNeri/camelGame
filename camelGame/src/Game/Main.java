package Game;

import HelperClasses.GameSetup;

/**
 *
 * @author Brandon Neri
 */
public class Main {

    public static void main(String[] args) {
        // Game welcome
        System.out.println("Welcome to Hackerman's Great Escape!\n"
                + "The government is currently after you becasue you leaked secret documents.\n"
                + "Escape them or spend the rest of your life in jail!\n"); 
        
        // Game setup
        Game game = GameSetup.setupGame(); 
        
        // Game start
        game.start(); 
    }
}
