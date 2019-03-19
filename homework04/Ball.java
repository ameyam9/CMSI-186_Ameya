import java.text.DecimalFormat;
public class Ball {
    private static double ballRadius = 4.45;//in inches
    private static double ballWeight = 1; //in pounds
    private double xCord;
    private double yCord;
    private double xSpeed;
    private double ySpeed;
    private double fieldWidth = 100.0;
    private double fieldHeight = 100.0;
    private double poleX = 50.0;
    private double poleY = 50.0;
    double[] loc = new double[2];



    public static void main(String[] args) {
      System.out.println( "\nBALL CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new ball: " );
      //Ball ball = new Ball(10,10,10,10);
      System.out.println( "    Testing move ....");
      try {
        Ball ball1 = new Ball(20,20,5,5);
        System.out.println( "    New ball created: " + ball1.toString() );
        ball1.move(60);
        System.out.println( "    Updated ball: " + ball1.toString() );
        System.out.println( "    Time is 60/1min, expecting a position of (320.0,320.0) and Velocity (2.74,2.74)");
        ball1.move(60);
        System.out.println( "    Updated ball: " + ball1.toString() );
        System.out.println( "    Time is 120/2min, expecting a position of (440.0,440.0) and Velocity(1.50,1.50)");


      }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: " + e.toString() );
      }
      try {
        Ball ball2 = new Ball(20,20,5,5);
        System.out.println( "    New ball created: " + ball2.toString() );
        ball2.move(30);
        System.out.println( "    Updated ball: " + ball2.toString() );
        System.out.println( "    Time is 30, expecting a position of (170.0,170.0) and Velocity(3.70,3.70)");
        ball2.move(30);
        System.out.println( "    Updated ball: " + ball2.toString() );
        System.out.println( "    Time is 60, expecting a position of (280.0,280.0)and Velocity(2.74,2.74)");


      }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: " + e.toString() );
      }

      try {
        Ball ball3 = new Ball(-300,-300,15,15);
        System.out.println( "    New ball created: " + ball3.toString() );
        ball3.move(20);
        System.out.println( "    Updated ball: " + ball3.toString() );
        System.out.println( "    Time is 20, expecting a position of (0.00,0.00) and Velocity(12.27,12.27)");
        ball3.move(20);
        System.out.println( "    Updated ball: " + ball3.toString() );
        System.out.println( "    Time is 40, expecting a position of (245.37,245.37)a and Velocity(10.03,10.03)");
        ball3.move(20);
        System.out.println( "    Updated ball: " + ball3.toString() );
        System.out.println( "    Time is 60, expecting a position of (446.06,446.06) and Velocity(8.21,8.21)");

      }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: " + e.toString() );
      }
      try {
        Ball ball4 = new Ball(-300,-300,15,15);
        System.out.println( "    New ball created: " + ball4.toString() );
        ball4.move(10);
        System.out.println( "    Updated ball: " + ball4.toString() );
        System.out.println( "    Time is 10, expecting a position of (-170.00,337.00) and Velocity(13.57,13.57)");
        ball4.move(10);
        System.out.println( "    Updated ball: " + ball4.toString() );
        System.out.println( "    Time is 20, expecting a position of (-215.22,300.31) and Velocity(12.27,12.27)");
        ball4.move(10);
        System.out.println( "    Updated ball: " + ball4.toString() );
        System.out.println( "    Time is 30, expecting a position of (-256.11,357.56) and Velocity(11.10,11.10)");

      }catch(NumberFormatException e){
         System.out.println ( " - Exception thrown: " + e.toString() );
      }

    }

    public Ball(double xCord, double yCord, double xSpeed, double ySpeed) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;




    }
    public double getXCord() {
        return xCord;
    }
    public double getYCord() {
        return yCord;
    }
    public double getXSpeed() {
        return xSpeed;
    }
    public double getYSpeed() {
        return ySpeed;
    }
    public void setXCord(double xCord) {
        this.xCord = xCord;
    }
    public void setYCord(double yCord) {
        this.yCord = yCord;
    }
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }




//should have location, size, position,
// ball should move

    public double[] getLocation() {
        loc[0] = xCord;
        loc[1] = yCord;
        return loc;

    }

    public boolean isInBounds() {
        return true;
//if its out of bound, set it speed to zero, but it needs to be off the field because its stil; can collide
    }
//COnsider speed is Zero if it moving less than 1 inch per second
    public boolean isStillMoving() {
        boolean result = true;
        if(xSpeed <= 0.084 && ySpeed <= 0.084){
            result = false;
        }
        return result;

    }

    public void move(double timeSlice) { // did not account for friction at every second.
        //double[] returnArray= new double[4];
        double timePeriod = timeSlice;

        //System.out.println (" Before move in Ball Xcor " + xCord + "ycord " + yCord);
        double xDist = xSpeed * timePeriod;
        double yDist = ySpeed * timePeriod;
        xCord = xCord + xDist;
        yCord = yCord + yDist;
        xSpeed = xSpeed * Math.pow(0.99,timePeriod);
        ySpeed = ySpeed * Math.pow(0.99,timePeriod);
        //returnArray[0] = xCord;
        //returnArray[1] = yCord;
        //returnArray[2] = xSpeed;
        //returnArray[3] = ySpeed;
        //System.out.println (" After move in Ball Xcor " + xCord + "ycord " + yCord);
        //return returnArray;



    }

  DecimalFormat d1 = new DecimalFormat("#0.00");

    public String toString() {


        return "Position:" + d1.format(this.xCord) +","+d1.format( this.yCord )+" "+ " Velocity:" + d1.format( this.xSpeed )+ ", " + d1.format(this.ySpeed);
    }
}
