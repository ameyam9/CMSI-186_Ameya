/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DynamicChangeMaker.java
 *  Purpose       :  Make Change
 *  @author       :  Ameya Mellacheruvu
 *  Date written  :  2019-09-05
 *  Description   :  Class to make change
 *  Notes         :  I have written comments in accordance to javadoc formating,
 *  Warnings      :  None
 *  Exceptions    :
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0                            Initial writing and release, accidnetally called it final upload, not the final upload
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.*;
import java.util.HashSet;


public class DynamicChangeMaker {

  /**
      *acts as a Constructor, sets the values of count and tuple and declares them
      * @see makeChangeWithDynamicProgramming()
      */

    private static class Entry {
        private Tuple tuple;
        private int count;
        Entry(Tuple tuple, int count) {
            this.tuple = tuple;
            this.count = count;
        }
    }

    /**
        *  main method to handle command line arguments.
        * Takes denominations in the '#,#,#,#,...' format as arg[0]
        * Takes target as arg[1]
        * @see makeChangeWithDynamicProgramming()
        * @throws NumberFormatException
        */
    public static void main( String args[] ) {

            String[] denominations;
            int[] intArr = new int[0];
            int target = 0;
            try {
                if( args.length != 2 ) {
                    throw new NumberFormatException();
                }
                denominations = args[0].split(",");
                intArr = new int[denominations.length];
                for(int i = 0; i < denominations.length; i++){
                    intArr[i] = Integer.parseInt( denominations[i] );
                }
                target = Integer.parseInt( args[1] );
                System.out.print("Created demominations ");
                for( int z : intArr ) {
                    System.out.print(" " + z + " ");
                }
                System.out.println(" with target " + target);
            }
            catch( NumberFormatException nfe ) {
                System.out.println("BAD DATA: Invalid Digits");
                System.out.println( nfe.toString() );
                System.exit(0);
            }

            System.out.print( makeChangeWithDynamicProgramming(intArr , target ) );
        }

        /**
            *   Method that checks for bad data.
            *   @param  intArr  int array of coin denominations
            *   @param  target int target value
            *   @return Tuple, will return error(tupple- impossible) if bad data is used
            */

        public static Tuple makeChangeWithDynamicProgramming(int[] intArr, int target) {
            // check the corner cases for bad inputs
            HashSet<Integer> temp = new HashSet<>();
            for (int i = 0; i < intArr.length; i++) {
                if (intArr[i] < 0) {
                    System.out.println("BAD DATA containing a negative");
                    return Tuple.IMPOSSIBLE;
                }
                if (intArr[i] == 0) {
                    System.out.println("BAD DATA containing a zero");
                    return Tuple.IMPOSSIBLE;
                }
                if (temp.contains(intArr[i])) {
                    System.out.println("BAD DATA containing repeats");
                    return Tuple.IMPOSSIBLE;
                }
                temp.add(intArr[i]);
            }

            if (target < 1) {
                return Tuple.IMPOSSIBLE;
            }


            Entry[] dp = new Entry[target + 1];
            for (int i = 0; i <= target; i++) {
                if(i == 0) {
                    dp[i] = new Entry(new Tuple(intArr.length), 0);
                } else {
                    int minValue = Integer.MAX_VALUE;
                    int resultCoin = -1;
                    for(int j = 0; j < intArr.length; j++) {
                        if ((i >= intArr[j]) && (dp[i - intArr[j]].count <= minValue - 1)) {
                            minValue = dp[i - intArr[j]].count + 1;
                            resultCoin = j;
                        }
                    }
                    Tuple tuple = new Tuple(intArr.length);
                    if(resultCoin != -1) {
                        tuple = tuple.add(dp[i - intArr[resultCoin]].tuple);
                        tuple.setElement(resultCoin, tuple.getElement(resultCoin) + 1);
                    }
                    dp[i] = new Entry(tuple, minValue);
                }
            }

            if(dp[target].count == Integer.MAX_VALUE)
                return Tuple.IMPOSSIBLE;
            return dp[target].tuple;
        }

 }
