/**
 * Created by: Jovana Hurra
 * Date: 2020-11-10
 * Project: Inlämningsuppgift 3
 */

import java.util.*;

/**
 * En klass som innehåller nödvändiga metoder för att skapa krigare, alla dess egenskaper och fight
 */
public class Fighter {

    // Instansvariabler

    String name;
    int healthPoints;
    int armorClass;
    int numberOfRolls;
    int numberOfFaces;
    int[] arrayOfFaces = {4, 6, 8, 10, 12, 20}; // array för godkända tärningar
    String[] arrayName = {"Bob", "Yovienna", "Arav", "Nour"}; // array för att slumpa namn, om användaren inte väljer ett namn

    // Metoder

    /**
     * En instansmetod som returnerar namnet på krigaren
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * En metod för utskrift av krigarens namn, inkl. slumpat namn om användaren inte skriver ett namn
     *
     * @param newName en ströng för krigarens nya namn
     */
    public void setName(String newName) {

        if (newName.equals("")) {
            System.out.println("Krigaren måste ha ett namn. Krigaren får ett slumpat namn.");
            this.name = arrayName[(int) (Math.random() * arrayName.length)];
        } else
            this.name = newName;
        System.out.println("Krigaren heter nu: " + this.name + ".");
    }

    /**
     * En instansmetod som returnerar krigarens HP
     *
     * @return this.healthPoints
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * En metod som sätter krigarens HP med logik för begränsningar
     *
     * @param newHP int för krigarens nya HP
     */
    public void setHealthPoints(int newHP) {
        if (newHP < 1)
            System.out.println("HP måste vara större än 0.");
        else if (newHP > 100)
            System.out.println("HP får högst vara 100.");
        else {
            this.healthPoints = newHP;
            System.out.println(this.name + " har nu " + this.healthPoints + " HP.");
        }
    }

    /**
     * En instansmetod som returnerar krigarens AC
     *
     * @return this.armorClass
     */
    public int getArmorClass() {
        return this.armorClass;
    }

    /**
     * En metod för att sätta krigarens AC med logik för begränsningar
     *
     * @param newAC int för krigarens nya AC
     */
    public void setArmorClass(int newAC) {
        if (newAC < 1)
            System.out.println("AC måste vara större än 0.");
        else if (newAC > 20)
            System.out.println("AC får högst vara 20.");
        else {
            this.armorClass = newAC;
            System.out.println(this.name + " har nu " + this.armorClass + " AC.");
        }

    }

    /**
     * En instansmetod som returnerar antal kast
     *
     * @return this.numberOfRolls
     */
    public int getNumberOfRolls() {
        return this.numberOfRolls;
    }

    /**
     * En metod för att sätta antal kast och logik för begränsningar
     *
     * @param newNumberOfRolls int för krigarens antal attack-kast
     */
    public void setNumberOfRolls(int newNumberOfRolls) {
        if (newNumberOfRolls < 1)
            System.out.println("Antal tärningar måste vara större än 0.");
        else if (newNumberOfRolls > 6)
            System.out.println("Du får högst kasta 6 tärningar.");
        else {
            this.numberOfRolls = newNumberOfRolls;
            System.out.println(this.name + " kastar nu " + this.numberOfRolls + " tärningar för Attack.");
        }

    }

    /**
     * En instansmetod som returnerar typ av tärning
     *
     * @return this.numberOfFaces
     */
    public int getNumberOfFaces() {
        return this.numberOfFaces;
    }

    /**
     * En metod för att sätta typ av tärning enligt förvalda typer (se arrayOfFaces)
     *
     * @param newNumberOfFaces int för krigarens typ av tärning
     */
    public void setNumberOfFaces(int newNumberOfFaces) {
        boolean numberOfFacesChanged = false;
        for (int arrayOfFace : arrayOfFaces) {
            if (newNumberOfFaces == arrayOfFace) {
                this.numberOfFaces = newNumberOfFaces;
                numberOfFacesChanged = true;
                break;
            }
        }
        if (numberOfFacesChanged)
            System.out.println(this.name + " använder nu en " + this.numberOfFaces + "-sidig tärning för Attack.");
        else
            System.out.println("Följande tärningar finns: " + Arrays.toString(arrayOfFaces));

    }

    /**
     * En metod för att två krigare ska slåss tills en har vunnit
     *
     * @param f1 första krigaren
     * @param f2 andra krigaren
     */
    public static void fight(Fighter f1, Fighter f2) {

        int numberOfTurns = 1;
        while (f1.getHealthPoints() > 0 && f2.getHealthPoints() > 0) {
            System.out.println("---------------------------");
            System.out.println("Runda: " + numberOfTurns);

            fightTurn(f1, f2);
            if (f2.getHealthPoints() <= 0) {
                System.out.println(f2.getName() + " dog.");
                break;
            }
            fightTurn(f2, f1);
            if (f1.getHealthPoints() <= 0) {
                System.out.println(f1.getName() + " dog.");
                break;
            }
            numberOfTurns++;
        }
    }

    /**
     * En metod för en runda av en fight
     *
     * @param f1 första krigaren
     * @param f2 andra krigaren
     */
    public static void fightTurn(Fighter f1, Fighter f2) {
        if (RollDice.rollDice(20, 1) > f2.getArmorClass()) {
            int damage = RollDice.rollDice(f1.getNumberOfFaces(), f1.getNumberOfRolls());
            System.out.println(f1.getName() + " träffade " + f2.getName() + " och gjorde " + damage + " skada.");
            f2.healthPoints -= damage;
            System.out.println(f2.getName() + " har nu " + f2.getHealthPoints() + " HP.");

        } else
            System.out.println(f1.getName() + " missade " + f2.getName() + ".");
    }

    /**
     * En metod för att uppdatera krigarens egenskaper (namn, HP, AC, Attack)
     * inkl. felhantering av indata
     *
     * @param Fighter1 Fighter som ska få uppdaterade egenskaper
     */
    public static void updateFighter(Fighter Fighter1) {

        //Fråga om namn
        System.out.println("Ange krigarens namn:");
        Scanner input = new Scanner(System.in);
        Fighter1.setName(input.nextLine());

        //Fråga om HP
        while (true) {
            System.out.println("Ange Health Points (HP) för " + Fighter1.getName());
            try {
                Fighter1.setHealthPoints(Integer.parseInt(input.nextLine()));
                if (Fighter1.getHealthPoints() > 1 && Fighter1.getHealthPoints() < 101)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("HP måste vara heltal.");
            }
        }

        //Fråga om AC
        while (true) {
            System.out.println("Ange Armor Class (AC) för " + Fighter1.getName());
            try {
                Fighter1.setArmorClass(Integer.parseInt(input.nextLine()));
                if (Fighter1.getArmorClass() > 0 && Fighter1.getArmorClass() < 21)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("AC måste vara heltal.");

            }
        }

        //Fråga om typ av tärning
        while (true) {
            System.out.println("Ange antal sidor för Attack-tärningen för " + Fighter1.getName());
            try {
                Fighter1.setNumberOfFaces(Integer.parseInt(input.nextLine()));
                if (Fighter1.getNumberOfFaces() != 0)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Antal sidor måste vara heltal.");
            }
        }

        //Fråga om antal kast
        while (true) {
            System.out.println("Ange antal tärningar för Attack för " + Fighter1.getName());
            try {
                Fighter1.setNumberOfRolls(Integer.parseInt(input.nextLine()));
                if (Fighter1.getNumberOfRolls() > 0 && Fighter1.getNumberOfRolls() < 7)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Antal tärningar måste vara heltal.");
            }
        }

    }

}
