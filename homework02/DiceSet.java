import java.util.Random;
public class DiceSet {
    public static void main(String[] args) {
      rollIndividual(1);
      //  DiceSet myDie = new DiceSet(3, 5);

    }


    private int count;
    private int sides;
    private Die[] ds = null;
    int[] intValue  = new int[count];
    // public constructor:

    /**
     * constructor
     *
     * @param count int value containing total dice count
     * @param sides int value containing the number of pips on each die
     * @throws IllegalArgumentException if one or both arguments don't make sense
     * @note parameters are checked for validity; invalid values throw "IllegalArgumentException"
     */

    public DiceSet(int count, int sides) {

        if(count < 1 ) {
            throw new IllegalArgumentException("Wrong Number of Dice Used");
        }
        if(sides <4 ){
            throw new IllegalArgumentException("Wrong Number of Sides");
        }

        this.count = count;
        this.sides = sides;
        //ds = new Die[count];
        roll();
    }

    /**
     * @return the sum of all the dice values in the set
     */
    public int sum() {
        int totalSum = 0;
        for(int i = 0; i < count; count++ ) {
            totalSum = totalSum  + intValue[i];
        }
        return totalSum;
    }

    /**
     * Randomly rolls all of the dice in this set
     * NOTE: you will need to use one of the "toString()" methods to obtain
     * the values of the dice in the set
     */
    public void roll() {
        for(int i =0; i<= count; i++){
            rollIndividual(i);
        }
    }

    /**
     * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
     *
     * @param dieIndex int of which die to roll
     * @return the integer value of the newly rolled die
     * @trhows IllegalArgumentException if the index is out of range
     */

    public int rollIndividual(int dieIndex) {
      System.out.println(intValue[dieIndex]);
        intValue[dieIndex] = (int) (1 +(Math.random() * (sides -1)) +1);

        return  intValue[dieIndex];
    }

    /**
     * Gets the value of the die in this set indexed by 'dieIndex'
     *
     * @param dieIndex int of which die to roll
     * @trhows IllegalArgumentException if the index is out of range
     */
    public int getIndividual(int dieIndex) {
        int dieValue = intValue[dieIndex];
        return dieValue;
    }
    /**
     * @return Public Instance method that returns a String representation of the DiceSet instance
     */
    public String toString() {
        String result = "";
        for(int i = 0; i< count; i++){
            result =  "[" + result + intValue[i] + "]";
        }

        return  result ;
    }

    /**
     * @return Class-wide version of the preceding instance method
     */
    public static String toString(DiceSet ds) {
        return ds.toString();
    }

    /**
     * @return tru iff this set is identical to the set passed as an argument
     */
       public boolean isIdentical( DiceSet ds ) {
           if(ds.count != this.count || ds.sides != this.sides){
               return false;
           }
           for(int i = 0; i<count; i++){
               if(ds.intValue[i] != this.intValue[i]){
                   return false;
               }
           }
           return true;
       }

}
