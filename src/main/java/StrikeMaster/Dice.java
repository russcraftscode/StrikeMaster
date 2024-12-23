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
        return rand.nextInt(1,6) + rand.nextInt(1,7);
    }

    /**
     * @param targetNumber the number needed to reach to be a success
     * @return if the roll was a success
     */
    public static boolean roll2d6(int targetNumber){
        return Dice.roll2d6() >= targetNumber;
    }
}
