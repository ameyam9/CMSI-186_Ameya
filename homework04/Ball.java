//Ball handles one ball
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

    public boolean isStillMoving() {
        boolean result = true;
        if(xSpeed <= 0.0 && ySpeed <= 0.0){
            result = false;
        }
        return result;

    }

    public void move(double timeSlice) {
        double timePeriod = timeSlice/60;

        double xDist = xSpeed * timePeriod;
        double yDist = ySpeed * timePeriod;
        xCord = xCord + xDist;
        yCord = yCord + yDist;
        xSpeed = timePeriod * (xSpeed *0.99);
        ySpeed = timePeriod * (ySpeed *0.99);



    }



    public String toString() {

        return "String rep.";
    }

}
