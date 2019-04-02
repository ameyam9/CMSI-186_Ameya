import java.lang.Math;
import java.lang.String;
import java.lang.Object;
import java.io.IOException;


public class Riemann {

    public static void main (String [] args){
        int numArgs = args.length;
        double correctionPercent = 0.0;
        double defaultPercent = 1.0;
        double lowerBound = 0.0;
        double upperBound = 0.0;
        boolean existsPercent = false;

        double[] coefficients = new double[50];


        if(args[0].equals("runtests")) {
          runMyTests();
          System.exit(0);
        }

        if(args.length < 3){
          System.out.println("Invalid Number of Arguments\n" + "Usage: java Riemann <functionType>, <coeffs 1...k >, <lowerBound>, <UpperBound>, <Optional: Percentage>");
          System.exit(0);
        }


        if (args[0].equals("poly")) {
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
              if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
                System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
              }

            lowerBound  = Double.parseDouble(args[numArgs -2]);
            upperBound = Double.parseDouble(args[numArgs -1]);
            coefficients =  new double[numArgs - 3];

            for (int i = 1; i < numArgs - 2; i++) {
                coefficients[i-1] = Double.parseDouble(args[i]);
            }

          } catch(NumberFormatException e){
            System.out.println("Please input Valid Numbers for coeffs,ub or lb");
              System.exit(0);
          }


          System.out.println("Sum:" + integralPoly(coefficients,lowerBound,upperBound,correctionPercent));
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
            if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
              System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
            }

          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
            System.exit(0);
          }


  System.out.println("Sum:"+(integralTrig("sin",lowerBound,upperBound,correctionPercent)));
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
            if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
              System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
            }
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
              System.exit(0);
          }
          System.out.println(("Sum"+integralTrig("cos",lowerBound,upperBound,correctionPercent)));

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
            if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
              System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
            }
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
              System.exit(0);
          }

          System.out.println("Sum:"+(integralTrig("tan",lowerBound,upperBound,correctionPercent)));
        } else if (args[0].equals("log")) {
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
            if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
              System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
            }
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");
              System.exit(0);
          }
          System.out.println("Sum"+integralLogAndExponent("log",lowerBound,upperBound,correctionPercent));


        } else if (args[0].equals("exp")){
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
            if(correctionPercent <= 0.0 || correctionPercent >= 100.0 ){
              System.out.println("Must enter a Valid Percentage between (0,100)");
                System.exit(0);
            }
          }catch (NumberFormatException e){
            System.out.println("Enter Valid Numbers for UpperBound and LowerBound");

          }
          System.out.println("Sum"+integralLogAndExponent("exp",lowerBound,upperBound,correctionPercent));

        }
        else{
            System.out.println(" Invalid functions type - The function type has to be poly/sin/cos/tan/log/exp");
            System.exit(0);
        }

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

                        sumOfArea += area;

                    }

                }

            width = width / 2;
            firstPass = false;
            check++;

        }
        return sumOfArea;
    }


  private static double integralLogAndExponent(String funcType, double lowerBound,double upperBound, double correctionPercent){
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
                if (funcType.equals("log")) {
                    area = (width) * Math.log( (double) ((i * width + lowerBound + (i -1) * width + lowerBound) / 2 ));
                }else if(funcType.equals("exp")){
                  area = (width) * Math.exp((double)((i * width + lowerBound + (i -1) * width + lowerBound) / 2 ));
                }
                    sumOfArea += area;
            }

        width = width / 2;
        firstPass = false;
        check++;
    }
    return sumOfArea;
}

  private static double integralTrig(String funcType, double lowerBound, double upperBound, double correctionPercent ) {
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
                      	      if (funcType.equals("sin")) {
                      	        area = width * Math.sin((double)(i * width + lowerBound + (i-1) * width + lowerBound) / 2) ;
                      	      }
                      	      else if (funcType.equals("cos")) {
                      	      	area = width * Math.cos((double)(i * width + lowerBound + (i-1) * width + lowerBound) / 2) ;
                      	      }
                      	      else if(funcType.equals("tan")){
                      	      	area = width * Math.tan((double)(i * width + lowerBound + (i-1) * width + lowerBound) / 2) ;
                      	      } else {

                              }

                              sumOfArea += area;

                      }

                  width = width / 2;
                  firstPass = false;
                  check++;
              }


              return sumOfArea;
  }

  private static void runMyTests() { //tried to run tests using a batch file, but was unsucessful

  //   // String path="cmd /c start testIntegral.bat";
  //   // Runtime rn=Runtime.getRuntime();
  //   // Process pr=rn.exec(path);
  //
  //   try {
  //     //Process p = Runtime.getRuntime().exec(
  //   //new String[]{"cmd", "/C", "testIntegralbat"},null);
  //   String[] command = {"cmd.exe", "/C", "Start", "testIntegral.bat "};
  //      Process p =  Runtime.getRuntime().exec(command);
  //
  // }catch(IOException e) {
  //     System.out.println(e);
  // }


    System.out.println("----Testing Polynomial Integration");
    try {
       double descriptors[] = {1.0,2.0,3.0,5.0};
          System.out.println();
          System.out.println("Expected Sum:13290.21093");
          System.out.println("Actual Sum:"+ integralPoly(descriptors,3.0,10.0,0.0000001));
          System.out.println();
          System.out.println("----Testing Sine Integration");
          System.out.println("Expected Sum:-0.252");
          System.out.println("Actual Sum:"+ integralTrig("sin", -4.9,7.4,0.0001));
          System.out.println();
          System.out.println("----Testing Cosine Integration");
          System.out.println("Expected Sum:-0.451");
          System.out.println("Actual Sum:"+ integralTrig("cos", 12,30,0.0000000003));
          System.out.println();
          System.out.println("----Testing Tangent Integration");
          System.out.println("Expected Sum:37.017");
          System.out.println("Actual Sum:"+ integralTrig("tan", -4.9,7.4,2.0));
          System.out.println();
          System.out.println("----Testing  Integration Exp");
          System.out.println("Expected Sum:42.931");
          System.out.println("Actual Sum:"+ integralLogAndExponent("exp", -4.9,7.4,5.0));
          System.out.println();
          System.out.println("----Testing  Integration Log");
          System.out.println("Expected Sum:4.433");
          System.out.println("Actual Sum:"+ integralLogAndExponent("exp", -15.0,60.0,9.0));

        }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: ");
      }
      try{
          System.out.println();
            System.out.println();
        System.out.println("Testing New Set");
        double descriptors[] = {-10.7,34.0,-12.5,7.7,9.0};
           System.out.println();
           System.out.println("Expected Sum:551594.5929");
           System.out.println("Actual Sum:"+ integralPoly(descriptors,6.0,12.4,0.0001));
           System.out.println();
           System.out.println("----Testing Sine Integration");
           System.out.println("Expected Sum:-0.0277");
           System.out.println("Actual Sum:"+ integralTrig("sin", 3,9.7,0.012));
           System.out.println();
           System.out.println("----Testing Cosine Integration");
           System.out.println("Expected Sum:0.424");
           System.out.println("Actual Sum:"+ integralTrig("cos", 19.0,21.5,7.0));
           System.out.println();
           System.out.println("----Testing Tangent Integration");
           System.out.println("Expected Sum:1.936");
           System.out.println("Actual Sum:"+ integralTrig("sin", -0.25,3.4,0.00000001));
           System.out.println();
           System.out.println("----Testing  Integration Exp");
           System.out.println("Expected Sum:4.493");
           System.out.println("Actual Sum:"+ integralLogAndExponent("exp", 134.0,167.3,0.05));
           System.out.println();
           System.out.println("----Testing  Integration Log");
           System.out.println("Expected Sum:1131.431");
           System.out.println("Actual Sum:"+ integralLogAndExponent("log", 12.0,245.0,12.0));

      }catch(NumberFormatException e){


      }

  }
}
