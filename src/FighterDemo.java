/**
 * Created by: Jovana Hurra
 * Date: 2020-11-10
 * Project: Inlämningsuppgift 3
 */

import java.util.*;

public class FighterDemo {
    public static void main(String[] args) {
        //Skapa två krigare
        Fighter Fighter1 = new Fighter();
        Fighter Fighter2 = new Fighter();

        Scanner input = new Scanner(System.in);

        System.out.println("I detta spel kommer du att få skapa två krigare som ska slåss mot varandra.");
        boolean cont = true;
        while (cont) {
            // Spara indata, egenskaper för krigare
            Fighter.updateFighter(Fighter1);
            Fighter.updateFighter(Fighter2);

            //Fight
            Fighter.fight(Fighter1, Fighter2);

            //Fråga om användaren vill sluta spela, avsluta om användaren skriver "y", annars börja om spelet
            System.out.println("Vill du avsluta, skriv Y");
            String in = input.nextLine();
            if (in.equalsIgnoreCase("y"))
                cont = false;
        }
        System.out.println("Game over. Tack för att du spelade!");
    }
}
