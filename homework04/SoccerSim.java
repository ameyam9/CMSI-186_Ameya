import java.text.DecimalFormat;
public class SoccerSim{

    private static final double ballRadius = 4.45 /12.0;//in feet
    private static final double ballWeight = 1; //in pounds
    private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
    private double[] xCord = new double[50] ;
    private double[] yCord = new double[50] ;
    private double[] xSpeed = new double[50] ;
    private double[] ySpeed = new double[50] ;

    private Collisions collArray[] = new Collisions[50];


    private double poleX = 0.0;
    private double poleY = 0.0;
    private double inputTimeSlice;
    private int totalNumOfBalls;
    int numCollisions = 0;
    private Ball balls[];
    private int totalNumberOfBallsHitPole = 0;

    private double fieldXLength = 1000.0;
    private double fieldYLength = 1000.0;
    private boolean[] hitPoleBalls = new boolean [50];
    Timer time;

//x size, y size, pole

//double[] bc = new double[2];


    public static void main(String[] args) {
        SoccerSim soccer = new SoccerSim();

        soccer.handleInitialArguments(args);

        soccer.createBalls();
        soccer.runSim();

        //Ball.checkForCollison();
    }

    public SoccerSim() {
    this.inputTimeSlice = inputTimeSlice;

    }
    public void runSim() {
		System.out.println("INPUT TIME SLICE " + inputTimeSlice + "  Seconds" );
        report("FIRST");
        while(true){
            time.tick();
            calculatePosition();

            report("TIMETICK");
            checkForCollison();
            for (int i=0; i < totalNumOfBalls; i ++) {
				if (!hitPoleBalls[i]) {
					if (hitPole(balls[i])) {
						hitPoleBalls[i] = true;
						totalNumberOfBallsHitPole++;
					}
				}
			}


            if(allOutOfBounds() || allBallsStop() || (totalNumberOfBallsHitPole == totalNumOfBalls)) {

                report("FINAL");

                System.exit(0);
            }




        }

    }
    public void createBalls() {
        balls = new Ball[totalNumOfBalls];
        for(int i = 0; i< totalNumOfBalls; i++){
            Ball newBall = new Ball(xCord[i],yCord[i],xSpeed[i],ySpeed[i]);
            balls[i] = newBall;
            hitPoleBalls[i] = false;
        }
    }
    public boolean allOutOfBounds() {
        int numbOutofBounds = 0;
        for(int i = 0; i< totalNumOfBalls; i++){
            if(Math.abs(balls[i].getXCord())  > fieldXLength/2 || Math.abs(balls[i].getYCord()) > fieldYLength/2){
                numbOutofBounds++;
            }
        }
        if(numbOutofBounds == totalNumOfBalls){
            return true;
        }else {
            return false;
        }
    }
    public void calculatePosition() {
        for(int i = 0; i < totalNumOfBalls; i++){
            //System.out.println(" Before move in Soccersim SoccerSim Xcord " + balls[i].getXCord() + " Ycord " +  balls[i].getYCord());
            //double [] returnArray = balls[i].move(inputTimeSlice);
            balls[i].move(inputTimeSlice);
            //balls[i].setXCord(returnArray[0]);
            //balls[i].setYCord(returnArray[1]);
            //balls[i].setXSpeed(returnArray[2]);
            //balls[i].setYSpeed(returnArray[3]);

           //System.out.println(" After move in Soccersim SoccerSim Xcord " + balls[i].getXCord() + " Ycord " +  balls[i].getYCord());
        }
    }

     public boolean hitPole(Ball ball) {
	        if(Math.abs(ball.getXCord() - poleX) < ballRadius && Math.abs(ball.getYCord() - poleY) < ballRadius) {
	            return true;
	        }
	        return false;
    }

    public void checkForCollison() {

        double distBetweenBalls = 0.0;

        for (int i=0; i<totalNumOfBalls-1; i++) {
            for (int j = i+1; j < totalNumOfBalls; j++) {
                //System.out.println(" fx,sx,fy,sy " + balls[i].getXCord() + " "+ balls[j].getXCord()+ " "+balls[i].getYCord()+ " "+balls[j].getYCord());
                //System.out.println("first val " + (balls[i].getXCord() - balls[j].getXCord()));
                //System.out.println("second val " + (balls[i].getYCord() - balls[j].getYCord()));
                distBetweenBalls = Math.sqrt(Math.pow(balls[i].getXCord() - balls[j].getXCord(), 2.0) +
                                             Math.pow(balls[i].getYCord() - balls[j].getYCord(), 2.0));
                //    System.out.println("distance "+ distBetweenBalls);
                if (distBetweenBalls <= ballRadius / 2) {
                    //System.out.println("COLLISION DETECTED");
                    double firstXCord = balls[i].getXCord();
                    double firstYCord = balls[i].getYCord();
                    double secondXCord = balls[j].getXCord();
                    double secondYCord = balls[j].getYCord();
                    //Ball firstBall = new Ball (balls[i].getXCord(), balls[i].getYCord(), balls[i].getXSpeed(), balls[i].getYSpeed());
                    //Ball secondBall = new Ball (balls[j].getXCord(), balls[j].getYCord(), balls[j].getXSpeed(), balls[j].getYSpeed());
                    Collisions newCollision = new Collisions(firstXCord,firstYCord,secondXCord,secondYCord,time.toString(),i,j);
                    collArray[numCollisions] = newCollision;
                    numCollisions++;


                }
            }
        }


    }


    public void report(String whenReporting) {

        DecimalFormat d1 = new DecimalFormat("#0.00");
        if (whenReporting.equals("FIRST")) {
            System.out.println("Initial Report " + time.toString());
            for(int i = 0; i < totalNumOfBalls; i++) {
                System.out.print(i + ":   position " + d1.format(xCord[i]) + "," + d1.format(yCord[i]));
                System.out.println(" velocity " + d1.format(xSpeed[i]) + "," + d1.format(ySpeed[i]));
            }
            return;

        }else if (whenReporting.equals("TIMETICK")) {
            System.out.println("Report At" + time.toString());
            for (int i = 0; i < totalNumOfBalls; i++) {
				if (!hitPoleBalls[i]) {
                	System.out.print(i + ":   position " +d1.format(balls[i].getXCord()) + "," + d1.format(balls[i].getYCord()));
                }	System.out.println(" velocity " + d1.format(balls[i].getXSpeed()) + "," + d1.format(balls[i].getYSpeed()));

            }
            return;
        }
        if (whenReporting.equals("FINAL")) {
            System.out.println("Final Report At" + time.toString());
            if (totalNumberOfBallsHitPole == 0) {
				System.out.println("No Balls have hit Pole ");
			} else {
				System.out.print("The following balls hit the pole " );
				for (int i =0; i < totalNumOfBalls; i ++) {
					if (hitPoleBalls[i]) { System.out.print( i + " ");}
				}
				System.out.println("");
			}

            if (numCollisions == 0){
                System.out.println("NO COLLISION IS POSSIBLE");
            }
            else {
                for(int i=0; i<numCollisions; i++) {
                   // System.out.println(balls[i]);
                    System.out.println("The following balls collided " + collArray[i].firstBall + " " + collArray[i].secondBall);
                    System.out.println("Collision occured at " + collArray[i].time);
                    //System.out.println(" Position of first ball");
                    System.out.println(" First Ball:   position " + d1.format(collArray[i].firstBallXcord) + "," + d1.format(collArray[i].firstBallYcord));
                    //System.out.println(" Position of second ball");
                    System.out.println(" Second Ball:   position " + d1.format(collArray[i].secondBallXcord) + "," + d1.format(collArray[i].secondBallYcord));

                }

            }

        }


        return;


    }
    public boolean allBallsStop() {
        boolean result = false;

        for(int i = 0; i< totalNumOfBalls; i++) {
            if (xSpeed[i] <= 0.084 && ySpeed[i] <= 0.084) {
                result = true;
            }else {
                return false;
            }
        }
            return result;

    }
    public void handleInitialArguments(String args[]) {
        System.out.println("\n   Hello world, from the SoccerSim Program!!\n");
        System.out.println("FIELD LENGTH " + fieldXLength + " " + fieldYLength);
        System.out.println("POLE POSITION @ " + poleX + " " + poleY);
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
                    if(xCord[i] > fieldXLength/2 || yCord[i]> fieldXLength/2 || xCord[i] < -1 *(fieldXLength/2) || yCord[i]< -1 *(fieldXLength/2)) {
                        System.out.print("Ball X and Y Cordinates are invalid " + xCord[i] + yCord[i]);
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
            for (int i = 0; i < totalNumOfBalls; i++) {
                try {
                    xCord[i] = Double.parseDouble(args[4 * i]);
                    yCord[i] = Double.parseDouble(args[(4 * i) + 1]);
                    if (xCord[i] > fieldXLength / 2 || yCord[i] > fieldXLength / 2 || xCord[i] < -1 * (fieldXLength / 2) || yCord[i] < -1 * (fieldXLength / 2)) {
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
