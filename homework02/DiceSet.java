import java.util.Random;
public class DiceSet {
    public static void main(String[] args) {


    }

    private int count;
    private int sides;
    private Die[] ds = null;
    private int[] intValue  = new int[1000];


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

    public int sum() {
        int totalSum = 0;
        for(int i = 0; i <count; i++ ) {
            totalSum = totalSum  + intValue[i];
        }
        return totalSum;
    }


    public void roll() {
        for(int i =0; i<count; i++){

            rollIndividual(i);
        }
    }


    public int rollIndividual(int dieIndex) {

        intValue[dieIndex] = (int) (1 +(Math.random() * (sides -1)) +1);

        return  intValue[dieIndex];
    }


    public int getIndividual(int dieIndex) {
        int dieValue = intValue[dieIndex];
        return dieValue;
    }

    public String toString() {
        String result = "";
        for(int i = 0; i< count; i++){
            result =    result +"["+intValue[i] + "]";
        }

        return  result  ;
    }


    public static String toString(DiceSet ds) {
        return ds.toString();
    }

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
