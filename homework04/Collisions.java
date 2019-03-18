import java.text.DecimalFormat;
public class Collisions {
    public double firstBallXcord;
    public double firstBallYcord;
    public double secondBallXcord;
    public double secondBallYcord;
    public int firstBall;
    public int secondBall;
    public String time;

    public Collisions(double firstBallXcord, double firstBallYcord, double secondBallXcord, double secondBallYcord ,String time,int i, int j) {

                /*this.firstBallXcord  =  ball1.getXCord();
                this.firstBallYcord  =  ball1.getYCord();
                this.secondBallXcord  = ball2.getXCord();
                this.secondBallYcord  = ball2.getYCord();
                */
        this.firstBallXcord = firstBallXcord;
        this.firstBallYcord = firstBallYcord;
        this.secondBallXcord = secondBallXcord;
        this.secondBallYcord = secondBallYcord;

        this.firstBall = i;
        this.secondBall = j;
        this.time = time;


    }



}
