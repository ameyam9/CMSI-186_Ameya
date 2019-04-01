import java.lang.Math;
import java.lang.String;
import java.lang.Object;


public class Riemann {

    public static void main (String [] args){
        int numArgs = args.length;
        double correctionPercent = 0.0;
        double defaultPercent = 1.0;
        double lowerBound = 0.0;
        double upperBound = 0.0;
        boolean existsPercent = false;

        double[] coefficients = new double[50];
        //System.out.println(args.length);
        if(args[0].equals("runtests")) {
          runMyTests();

        }
        if(args.length < 2 ) {
          System.out.println("Invalid Number of Arguments\n" + "Usage: java Riemann <functionType>, <coeffs 1...k >, <lowerBound>, <UpperBound>, <Optional: Percentage>");

        }

        if (args[0].equals("poly")) {
            if (args[args.length-1].endsWith("%")){
                numArgs = args.length -1;
                correctionPercent = Double.parseDouble(args[numArgs].substring(0,args[numArgs].length()-1));
                System.out.println(correctionPercent);
                correctionPercent= correctionPercent/100.0;
                existsPercent = true;

            }else {
                existsPercent = false;
                numArgs = args.length;
                correctionPercent = defaultPercent/100.0;
            }
            try {
              if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
                System.out.println("Must enter a Valid Percentage between (0,100)");
              }

            lowerBound  = Double.parseDouble(args[numArgs -2]);
            upperBound = Double.parseDouble(args[numArgs -1]);
            coefficients =  new double[numArgs - 3];

            for (int i = 1; i < numArgs - 2; i++) {
                coefficients[i-1] = Double.parseDouble(args[i]);
            }

          } catch(NumberFormatException e){
            System.out.println("Please input Valid Numbers for coeffs,ub or lb");
          }


          //integralPoly(coefficients,lowerBound,upperBound,correctionPercent);


          System.out.println(integralPoly(coefficients,lowerBound,upperBound,correctionPercent));
        }else if (args[0].equals("sin")) {

          if (args[args.length-1].endsWith("%")){
              numArgs = args.length -1;
              correctionPercent = Double.parseDouble(args[numArgs].substring(0,args[numArgs].length() - 1));
              correctionPercent= correctionPercent/100.0;
              existsPercent = true;

          }else {
              existsPercent = false;
              numArgs = args.length;
              correctionPercent = defaultPercent/100.0;
          }
          try {
            lowerBound = Double.parseDouble(args[1]);
            upperBound = Double.parseDouble(args[2]);
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
          }

  System.out.println((integralSine(lowerBound,upperBound,correctionPercent)));
        } else if (args[0].equals("cos")) {
          if (args[args.length-1].endsWith("%")){
              numArgs = args.length -1;
              correctionPercent = Double.parseDouble(args[numArgs].substring(0,args[numArgs].length()-1));
              correctionPercent= correctionPercent/100.0;
              existsPercent = true;

          }else {
              existsPercent = false;
              numArgs = args.length;
              correctionPercent = defaultPercent/100.0;
          }
          try {
            lowerBound = Double.parseDouble(args[1]);
            upperBound = Double.parseDouble(args[2]);
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
          }

        } else if (args[0].equals("tan")) {
          if (args[args.length-1].endsWith("%")){
              numArgs = args.length -1;
              correctionPercent = Double.parseDouble(args[numArgs].substring(0,args[numArgs].length() -1));
              correctionPercent= correctionPercent/100.0;
              existsPercent = true;

          }else {
              existsPercent = false;
              numArgs = args.length;
              correctionPercent = defaultPercent/100.0;
          }
          try {
            lowerBound = Double.parseDouble(args[1]);
            upperBound = Double.parseDouble(args[2]);
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
          }

        } else if (args[0].equals("exp")) {
            System.out.println("cos function unspported yet");
            System.exit(0);
        } else{
            System.out.println(" Invalid functions type - The function type has to be poly/sin/cos/tan/exp");
            System.exit(0);
        }
            //System.out.println("Integral Value" + integralPoly());
    }



    private static double integralPoly(double[] descriptors, double lowerBound, double upperBound,double correctionPercent) {

        double area = 0.0; // Area of the rectangle
        double sumOfArea = 0.0; // Sum of the area of the rectangles
        double oldSumOfArea = 0.0;
        double width = Math.abs(upperBound - lowerBound);
        boolean firstPass = true;
        int check = 0;

        while ((Math.abs((oldSumOfArea - sumOfArea) / sumOfArea) > correctionPercent) || firstPass ) {
            oldSumOfArea = sumOfArea;
            sumOfArea = 0.0;
                for (int i = 1; i <= ((upperBound - lowerBound) / width); i++){
                    for (int j = 0; j < descriptors.length; j++){
                        area = (width * descriptors[j]) * Math.pow ((double)( (i * width + lowerBound + (i -1) * width + lowerBound) / 2 ), j);
                        /*Above code computes area of each rectangle */
                        sumOfArea += area;

                    }

                }

            width = width / 2;
            firstPass = false;
            check++;




        }


        return sumOfArea;
    }

    private static double integralSine(double lowerBound, double upperBound, double correctionPercent ) {
              double area = 0.0; // Area of the rectangle
              double sumOfArea = 0.0; // Sum of the area of the rectangles
              double oldSumOfArea = 0.0;
              double width = Math.abs(upperBound - lowerBound);
              boolean firstPass = true;
              int check = 0;

              while ((Math.abs((oldSumOfArea - sumOfArea) / sumOfArea) > correctionPercent) || firstPass ) {
                  oldSumOfArea = sumOfArea;
                  sumOfArea = 0.0;
                      for (int i = 1; i <= ((upperBound - lowerBound) / width); i++) {
                              area = width * Math.sin((double)(i * width + lowerBound + (i-1) * width + lowerBound) / 2) ;
                              /*Above code computes area of each rectangle */
                              sumOfArea += area;

                      }

                  width = width / 2;
                  firstPass = false;
                  check++;
              }


              return sumOfArea;
  }
  // private static double integralCosine(){
  //
  //   double area = 0.0; // Area of the rectangle
  //   double sumOfArea = 0.0; // Sum of the area of the rectangles
  //   double oldSumOfArea = 0.0;
  //   double width = Math.abs(upperBound - lowerBound);
  //   boolean firstPass = true;
  //   int check = 0;
  //
  //   while ((Math.abs((oldSumOfArea - sumOfArea) / sumOfArea) > correctionPercent) || firstPass ) {
  //       oldSumOfArea = sumOfArea;
  //       sumOfArea = 0.0;
  //           for (int i = 1; i <= ((upperBound - lowerBound) / width); i++) {
  //                   area = (width) * Math.cos((double)(i * width + lowerBound + (i -1) * width + lowerBound) / 2 );
  //                   /*Above code computes area of each rectangle */
  //                   sumOfArea += area;
  //
  //           }
  //
  //       width = width / 2;
  //       firstPass = false;
  //       check++;
  //   }
  //
  //   return sumOfArea;
  // }
  private static double integralLog(){
    return 0.0;
  }
  private static double integralExp() {
      return 0.0;
  }

  private static double integralSqrt() {
    return 0.0;

  }
  private static void runMyTests() {

    System.out.println("----Testing Polynomial Integration");
    try {
       double descriptors[] = {1.0,2.0,3.0,5.0};
          System.out.println();
          System.out.println("Expected Sum with default percent:");
          System.out.println("Actual Sum:"+integralPoly(descriptors,3.0,10.0,5.0));
          //System.out.println("Expected Sum with 5%:");
          //System.out.println("Actual Sum:" + integralPoly({1.0,2.0,3.0},3.0,10.0,5,0));



    }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: ");
      }
  }

}
