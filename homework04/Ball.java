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
        //Ball myBall = new Ball();

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



    public void checkSpeed() {


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

    public void move(double timeSlice) {
        //double[] returnArray= new double[4];
        double timePeriod = timeSlice;
        //System.out.println (" Before move in Ball Xcor " + xCord + "ycord " + yCord);
        double xDist = xSpeed * timePeriod;
        double yDist = ySpeed * timePeriod;
        xCord = xCord + xDist;
        yCord = yCord + yDist;
        //returnArray[0] = xCord;
        //returnArray[1] = yCord;
        xSpeed = xSpeed * Math.pow(0.99,timePeriod);
        ySpeed = ySpeed * Math.pow(0.99,timePeriod);
        //returnArray[2] = xSpeed;
        //returnArray[3] = ySpeed;
        //System.out.println (" After move in Ball Xcor " + xCord + "ycord " + yCord);
        //return returnArray;
        return;


    }



    public String toString() {

        return "String rep.";
    }
}
