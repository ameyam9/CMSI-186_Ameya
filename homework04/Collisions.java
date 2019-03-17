public class Collisions {
    public double firstBallXcord;
    public double firstBallYcord;
    public double secondBallXcord;
    public double secondBallYcord;
    public int firstBall;
    public int secondBall;
    public String time;

    public void Collisions(Ball ball1, Ball ball2, int i, int j) {

                this.firstBallXcord  =  ball1.getXCord();
                this.firstBallYcord  =  ball1.getYCord();
                this.secondBallXcord  = ball2.getXCord();
                this.secondBallYcord  = ball2.getYCord();
                this.firstBall = i;
                this.secondBall = j;
                this.time = time;


    }



}
