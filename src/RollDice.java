/**
 * Created by: Jovana Hurra
 * Date: 2020-11-10
 * Project: Inlämningsuppgift 3
 */

public class RollDice {

    /**
     * En metod för tärningkast
     *
     * @param numberOfFaces typ av tärning
     * @param numberOfRolls antal tärningar
     * @return dice
     */
    public static int rollDice(int numberOfFaces, int numberOfRolls) {
        double dice = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            dice += (Math.random() * numberOfFaces) + 1;
        }
        return (int) dice;
    }
}
