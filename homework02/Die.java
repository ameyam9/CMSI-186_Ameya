import java.awt.datatransfer.MimeTypeParseException;

import java.util.Random;
public class Die {
    public static void main( String[] args ) {
      //  Die myDie = new Die(5);
    }

    private int sides;
    private int pips;
    private int value;
    private final int MINIMUM_SIDES = 4;

    int nSides;
    public Die( int nSides ) {
        if (nSides < MINIMUM_SIDES) {
            throw new IllegalArgumentException("Wrong Number of Sides Used");
        }
        this.nSides = nSides;
    }

    public int roll() {
        int value = (int) (1 +(Math.random() * (nSides -1)) +1);
        this.value = value;
        return value;
    }

    public int getValue() {
        return value;
    }

    public int setSides( int sides ) {
        nSides = sides ;
        return nSides;
    }

    public int getSides(){// not required to make this method, but the tester harness requires for it
        return nSides;
    }

    public String toString() {
        return "[" + this.nSides + "]";
    }

    public static String toString(Die d) {
        return d.toString();
    }
}
