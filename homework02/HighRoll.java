//Some code was taken from B.Johnson's MainProgLoopsDemo.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

    public static void main(String args[]) {
        int numbDice=0;
        int numbSides =0;
        int highScore = 0;
        int sum;

        if (args.length != 2) {
            System.out.println("Invalid Arguments");
            System.out.println("Java HighRoll <number of dice> <number of sides>");
            System.exit(0);
        }else{
          try {
            numbDice = Integer.parseInt(args[0]);
            numbSides = Integer.parseInt(args[1]);
          } catch(NumberFormatException e) {
            System.out.println("Please enter Valid Integers");
            System.exit(0);
          }
        }
        if (numbSides < 4 || numbDice < 1) {
            System.out.println("Invalid Arguments");
            System.exit(0);
        }
        DiceSet ds = new DiceSet(numbDice, numbSides);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n   WElCOME TO HIGHROLL!!\n");
        while (true) {
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("ENTER 1 TO ROLL ALL THE DICE");
            System.out.println("ENTER 2 ROLL A SINGLE DIE");
            System.out.println("ENTER 3 CALCULATE THE SCORE FOR THIS SET");
            System.out.println("ENTER 4 SAVE THIS SCORE AS HIGH SCORE");
            System.out.println("ENTER 5 DISPLAY THE HIGH SCORE");
            System.out.println("ENTER Q TO QUIT THE PROGRAM ");

            System.out.print(">>");
            String inputLine = null;
            try {
                inputLine = input.readLine();
                if (0 == inputLine.length()) {
                    System.out.println("enter some text!:");
                }
                System.out.println("You typed: " + inputLine);
                if ('1' == inputLine.charAt(0)) {
                    ds.roll();
                }
                if ('2' == inputLine.charAt(0)) {
                    ds.rollIndividual(1);
                }
                if ('3' == inputLine.charAt(0)) {
                    sum = ds.sum();
                    System.out.println("CURRENT SCORE:" + sum);
                }
                if ('4' == inputLine.charAt(0)) {
                    sum = ds.sum();
                    highScore = sum;
                }
                if ('5' == inputLine.charAt(0)) {
                    System.out.println("HIGH SCORE:" + highScore);
                }
                if ('q' == inputLine.charAt(0)) {
                    break;
                }

            } catch (IOException ioe){
                System.out.println("Caught IOException");
            }
        }
    }
}
