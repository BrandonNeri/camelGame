package GameEntities;

import java.util.Random;

/**
 *
 * @author Brandon Neri
 */
public class ComputerRobot {

    Random r = new Random();

    private String name;
    private int thirst;
    private int energy;
    private int hunger;

    public ComputerRobot(String name) {
        // constructor with random values
        this.name = name;
        thirst = r.nextInt(16);
        energy = r.nextInt(16);
        hunger = r.nextInt(16);
    }

    public ComputerRobot(String name, int thirst, int hunger, int energy) {
        // Constructor with declared values
        this.name = name;

        if (thirst >= 15) { // Thirst limits
            this.thirst = 15;
        } else if (thirst <= 0) {
            this.thirst = 0;
        } else {
            this.thirst = thirst;
        }

        if (energy >= 15) { // Energy limits
            this.energy = 15;
        } else if (energy <= 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }

        if (hunger >= 15) { // Hunger limits
            this.hunger = 15;
        } else if (hunger <= 0) {
            this.hunger = 0;
        } else {
            this.hunger = hunger;
        }
    }

    public int getThirst() { // Gets computer thirst
        return thirst;
    }

    public int getEnergy() { // Gets computer energy
        return energy;
    }

    public int getHunger() { // Gets computer hunger
        return hunger;
    }

    public void adjustThirst(int thirst) { // Sets computer thirst & limits
        if (thirst >= 15) {
            this.thirst = 15;
        } else if (thirst <= 0) {
            this.thirst = 0;
        } else {
            this.thirst = thirst;
        }
    }

    public void adjustEnergy(int energy) { // Sets computer energy & limits
        if (energy >= 15) {
            this.energy = 15;
        } else if (energy <= 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    public void adjustHunger(int hunger) { // Sets computer hunger & limits
        if (hunger >= 15) {
            this.hunger = 15;
        } else if (hunger <= 0) {
            this.hunger = 0;
        } else {
            this.hunger = hunger;
        }
    }
}
