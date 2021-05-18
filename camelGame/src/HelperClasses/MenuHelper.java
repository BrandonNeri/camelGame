package HelperClasses;

import java.util.Scanner;

/**
 *
 * @author Brandon Neri
 */
public class MenuHelper {

    private MenuHelper() {
    }

    public static int displayMenu(String message, int min, int max) {

        Scanner s = new Scanner(System.in);
        int userInput;
        System.out.println(message);
        do {
            while (!s.hasNextInt()) {
                System.out.println("Thats not an acceptable value. Please try again:");
                s.next();
            }
            userInput = s.nextInt();
            if (userInput < min || userInput > max) {
                System.out.println("Thats not an acceptable value. Please try again:");
            }
        } while (!(userInput >= min && userInput <= max));

        return userInput;
    }

    public static String getInput(String prompt) {
        return ""; // Did not need to complete project 2.
    }
}
