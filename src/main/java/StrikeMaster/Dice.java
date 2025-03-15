package StrikeMaster;

import java.util.Random;

/**
 * Simulates the rolling of dice.
 */
public class Dice {
    /**
     * @return the sum of 2 six sided die
     */
    public static int roll2d6(){
        Random rand = new Random();
        // roll 2 dice that each range from 1 to 6
        return rand.nextInt(6)+1 + rand.nextInt(6)+1;
    }

    /**
     * @param targetNumber the number needed to reach to be a success
     * @return if the roll was a success
     */
    public static boolean roll2d6(int targetNumber){
        return Dice.roll2d6() >= targetNumber;
    }
}
