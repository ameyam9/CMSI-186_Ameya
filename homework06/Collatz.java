//used code from previously written program in CMSi 185,
// see https://codepen.io/amy_mella09/pen/XxwJyq for more details

import java.util.ArrayList;



public class Collatz{

  private static int count = 0;
  static ArrayList<BrobInt> collatzBrobs = new ArrayList<BrobInt>();


  public static void main( String[] args ) {

     System.out.println( "\n\n   Welcome to the Collatz sequence!\n" );
     if(0 == args.length){
       System.out.println("Please enter valid Number of arguments");
       System.exit(0);
     }

     BrobInt inputValue = new BrobInt(args[0]);
     if (inputValue.intNegative) {
            System.out.println("Please input Positive Values only");
            System.exit(-1);
     }

     collatzBrobs.add(inputValue);
     System.out.println("The Collatz sequence is");
     while (collatzBrobs.get(count).compareTo(BrobInt.ONE)!= 0){

         if (collatzBrobs.get(count).remainder(BrobInt.TWO).equals(BrobInt.ZERO)) {
             collatzBrobs.add( collatzBrobs.get(count).divide(BrobInt.TWO));
         } else {
             collatzBrobs.add( collatzBrobs.get(count).multiply(BrobInt.THREE).add(BrobInt.ONE));

         }
        count++;

       System.out.print(collatzBrobs.get(count).toString() + ", ");
   }
    System.out.println("\n");
    System.out.println("Number of steps " + count);

  }

 }
