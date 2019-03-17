// represents field and tracks all ball objects, this is similar to diceSet in that die handles one thing and diceSet handeled all dice objects and several die
// soccer sim detects collision
import java.text.DecimalFormat;
public class SoccerSim{

    private static final double ballRadius = 4.45;//in inches
    private static final double ballWeight = 1; //in pounds
    private final double DEFAULT_TIME_SLICE_SECONDS = 1.0;
    private double[] xCord = new double[50] ;
    private double[] yCord = new double[50] ;
    private double[] xSpeed = new double[50] ;
    private double[] ySpeed = new double[50] ;
    private Collisions collArray[];

    private double poleX = 50.0;
    private double poleY = 50.0;
    private double inputTimeSlice;
    private double totalNumOfBalls;
    int numCollisions = 0;
    private Ball balls[];

    private double fieldXLength = 100.0;
    private double fieldYLength = 100.0;
    Timer time;

//x size, y size, pole

//double[] bc = new double[2];


    public static void main(String[] args) {
        SoccerSim soccer = new SoccerSim();

        soccer.handleInitialArguments(args);
        soccer.createBalls();
        soccer.runSim();

        // Ball.checkForCollison()
    }

    public SoccerSim() {
    this.inputTimeSlice = inputTimeSlice;

    }
    public void runSim() {
        report("FIRST");
        while(true){
            time.tick();
            calculatePosition();
            report("TIMETICK");

            if(allOutOfBounds() || allBallsStop()) {
                checkForCollison();
                report("FINAL");
                System.exit(0);
            }




        }

    }
    public void createBalls() {

        for(int i = 0; i< totalNumOfBalls; i++){
            balls[i]= new Ball(xCord[i],yCord[i],xSpeed[i],ySpeed[i]);
        }
    }
    public boolean allOutOfBounds() {
        for(int i = 0; i< totalNumOfBalls; i++){
            if(xCord[i] > fieldXLength || yCord[i] > fieldYLength) {

            }
        }
        return true;
    }
    public void calculatePosition() {
        for(int i = 0; i < totalNumOfBalls; i++){
            balls[i].move(inputTimeSlice);
        }
    }
    public void checkForCollison() {

        double distBetweenBalls = 0.0;
        for (int i=0; i<totalNumOfBalls; i++) {
            for (int j = i; j < totalNumOfBalls; j++) {
                distBetweenBalls = Math.sqrt(Math.pow(xCord[i] - xCord[j], 2.0) + Math.pow(yCord[i] - yCord[j], 2));

                if (distBetweenBalls < ballRadius / 2) {
                    System.out.println("COLLISION DETECTED");
                    numCollisions++;
                    Ball firstBall = new Ball (balls[i].getXCord(), balls[i].getYCord(), balls[i].getXSpeed(), balls[i].getYSpeed());
                    Ball secondBall = new Ball (balls[j].getXCord(), balls[j].getYCord(), balls[j].getXSpeed(), balls[j].getYSpeed());
                    Collisions newCollision = new Collisions();
                    collArray[numCollisions -1] = newCollision;


                }
            }
        }


    }


    public void report(String whenReporting) {

        DecimalFormat d1 = new DecimalFormat("#0.0000");
        if (whenReporting.equals("FIRST")) {
            System.out.println("Initial Report " + time.toString());
            for(int i = 0; i < totalNumOfBalls; i++) {
                System.out.print(i + ":   position -" + d1.format(xCord) + "," + d1.format(yCord));
                System.out.println("-velocity-" + d1.format(xSpeed) + "," + d1.format(ySpeed));
            }
            return;

        }else if (whenReporting.equals("TIMETICK")) {
            System.out.println("Report At" + time.toString());
            for (int i = 0; i < totalNumOfBalls; i++) {
                System.out.print(i + ":   position -" + d1.format(xCord) + "," + d1.format(yCord));
                System.out.println("-velocity-" + d1.format(xSpeed) + "," + d1.format(ySpeed));
            }
            return;
        }
        if (whenReporting.equals("FINAL")) {
            System.out.println("Final Report At" + time.toString());
            if (numCollisions == 0){
                System.out.println("NO COLLISION IS POSSIBLE");
            }
            else {
                for(int i=0; i<numCollisions; i++) {
                   // System.out.println(balls[i]);
                    System.out.println("The following balls collided" + collArray[i].firstBall + " " + collArray[i].secondBall);
                    System.out.println("Collision occured at " + collArray[i].time);
                    System.out.println("Position of first ball");
                    System.out.print(i + ":   position <" + d1.format(collArray[i].firstBallXcord) + "," + d1.format(collArray[i].firstBallYcord));
                    System.out.println("Position of second ball");
                    System.out.print(i + ":   position <" + d1.format(collArray[i].secondBallXcord) + "," + d1.format(collArray[i].secondBallYcord));

                }

            }

        }


        return;


    }
    public boolean allBallsStop() {
        boolean result = false;

        for(int i = 0; i< totalNumOfBalls; i++) {
            if (xSpeed[i] <= 0.0 && ySpeed[i] <= 0.0) {
                result = true;
            }else {
                return false;
            }
        }
            return result;

    }
    public void handleInitialArguments(String args[]) {
        System.out.println("\n   Hello world, from the SoccerSim Program!!\n");
        if (0 == args.length) {
            System.out.println("Please enter at least eight arguments \n");
            System.exit(0);

        }


        if(args.length < 4){
            System.out.println("Please input the details of at least one ball");
            System.exit(0);
        }

        if(args.length % 4 == 0) {
            inputTimeSlice = DEFAULT_TIME_SLICE_SECONDS;

            for(int i =0; i<args.length/4; i++){
                try {
                    xCord[i] = Double.parseDouble(args[4*i]);

                    yCord[i] = Double.parseDouble(args[(4*i) + 1]);
                    if(xCord[i] > fieldXLength/2 || yCord[i]> fieldXLength/2 || xCord[i] < -1 *(fieldXLength/2) || yCord[i]> -1 *(fieldXLength/2)) {
                        System.out.print("Ball X and Y Cordinates are invalid");
                        System.exit(0);
                    }
                    xSpeed[i] = Double.parseDouble(args[(4*i) + 2]);
                    ySpeed[i] = Double.parseDouble(args[(4*i) + 3]);

                }catch(NumberFormatException e) {
                    System.out.println("Please use valid numbers");
                }
                totalNumOfBalls ++;
            }



        }else if((args.length% 4) -1 == 0) {
            totalNumOfBalls = args.length / 4;
            for (int i = 0; i <= totalNumOfBalls; i++) {
                try {
                    xCord[i] = Double.parseDouble(args[4 * i]);

                    yCord[i] = Double.parseDouble(args[(4 * i) + 1]);
                    if (xCord[i] > fieldXLength / 2 || yCord[i] > fieldXLength / 2 || xCord[i] < -1 * (fieldXLength / 2) || yCord[i] > -1 * (fieldXLength / 2)) {
                        System.out.print("Ball X and Y Cordinates are invalid");
                        System.exit(0);
                    }
                    xSpeed[i] = Double.parseDouble(args[(4 * i) + 2]);
                    ySpeed[i] = Double.parseDouble(args[(4 * i) + 3]);

                } catch (NumberFormatException e) {
                    System.out.println("Please use valid numbers");
                }
            }
            try {
                inputTimeSlice = Double.parseDouble(args[args.length - 1]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid TimeSlice");
            }


        }else{
            System.out.println("Invalid Number of Arguments");
            System.exit(0);
        }
        time = new Timer(inputTimeSlice);
    }
}
