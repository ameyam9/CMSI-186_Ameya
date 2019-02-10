import java.awt.datatransfer.MimeTypeParseException;

import java.util.Random;
public class Die {
    public static void main( String[] args ) {
      //  Die myDie = new Die(5);



    }

    /**
     * private instance data
     */
    private int sides;
    private int pips;
    private int value;
    private final int MINIMUM_SIDES = 4;

    // public constructor:
    /**
     * constructor
     * @param nSides int value containing the number of sides to build on THIS Die
     * @throws       IllegalArgumentException
     * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
     */
    int nSides;
    public Die( int nSides ) { // this is our constructor
        if (nSides < MINIMUM_SIDES) {
            throw new IllegalArgumentException("Wrong Number of Sides Used");
        }
        this.nSides = nSides;

    }
    /**
     * Roll THIS die and return the result
     * @return  integer value of the result of the roll, randomly selected
     */
// Die d = newDie(), d.roll()

    public int roll() { //instance method not a class method!
        int value = (int) (1 +(Math.random() * (nSides -1)) +1);
        this.value = value;
        return value;

    }

    /**
     * Get the value of THIS die to return to the caller; note that the way
     *  the count is determined is left as a design decision to the programmer
     *  For example, what about a four-sided die - which face is considered its
     *  "value"?
     * @return the pip count of THIS die instance
     */
    public int getValue() {
        return value;
    }


    public int setSides( int sides ) {
            nSides = sides ;
            return nSides;
    }

    public int getSides(){
        return nSides;
    }

    /**
     * Public Instance method that returns a String representation of THIS die instance
     * @return String representation of this Die
     */
    public String toString() { //String rep. of this class
        return "[" + this.nSides + "]";
    }

    /**
     * Class-wide method that returns a String representation of THIS die instance
     * @return String representation of this Die
     */
    public static String toString(Die d) {

        return d.toString();
    }




}
