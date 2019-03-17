
import java.text.DecimalFormat;
public class Timer{

    /**
     *  Class field definintions go here
     */

     // taken from previous program clock.java

    private static final double INVALID_ARGUMENT_VALUE = -1.0;
    private static final double MAXIMUM_DEGREE_VALUE = 360.0;
    private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
    private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
    private double seconds = 0.0;
    private double inputTimeSlice;
    private double minuteHandAngle = 0.0;
    private double hourHandAngle = 0.0;


    /**
     *  Constructor goes here
     */

    public Timer() {
			  inputTimeSlice = 60.0;

    }

    public Timer(String[] args) {
		if (args.length == 2) {
          inputTimeSlice = Double.parseDouble(args[1]);
		}
		else {
		  inputTimeSlice = 60.0;
		}

    }
    /**
     *  Methods go here
     *
     *  Method to calculate the next tick from the time increment
     *  @return double-precision value of the current clock tick
     */


    public double tick() { //increment angles here in tick
      seconds += inputTimeSlice;
      minuteHandAngle = minuteHandAngle + (MINUTE_HAND_DEGREES_PER_SECOND * inputTimeSlice);
      hourHandAngle = hourHandAngle + (HOUR_HAND_DEGREES_PER_SECOND * inputTimeSlice);
      hourHandAngle %= MAXIMUM_DEGREE_VALUE;
      minuteHandAngle %= MAXIMUM_DEGREE_VALUE;
      return seconds;
    }

    /**
     *  Method to validate the angle argument
     *  @param   argValue  String from the main programs args[0] input
     *  @return  double-precision value of the argument
     *  @throws  NumberFormatException
     */

    public double validateAngleArg( String argValue ) throws NumberFormatException {
      	double validAngle = Double.parseDouble(argValue);

        return validAngle;
    }

    /**
     *  Method to validate the optional time slice argument
     *  @param  argValue  String from the main programs args[1] input
     *  @return double-precision value of the argument or -1.0 if invalid
     *  note: if the main program determines there IS no optional argument supplied,
     *         I have elected to have it substitute the string "60.0" and call this
     *         method anyhow.  That makes the main program code more uniform, but
     *         this is a DESIGN DECISION, not a requirement!
     *  note: remember that the time slice, if it is small will cause the simulation
     *         to take a VERY LONG TIME to complete!
     */
    public double validateTimeSliceArg( String argValue ) {
      double inputTimeSliceArg = 0.0;

      	inputTimeSliceArg = Double.parseDouble(argValue);
      	inputTimeSlice = inputTimeSliceArg;
        return inputTimeSlice;
    }

    /**
     *  Method to calculate and return the current position of the hour hand
     *  @return double-precision value of the hour hand location
     */


    public double getHourHandAngle() {
        return  hourHandAngle;
    }


    /**
     *  Method to calculate and return the current position of the minute hand
     *  @return double-precision value of the minute hand location
     */

    public double getMinuteHandAngle() {
        return minuteHandAngle;
    }

    /**
     *  Method to calculate and return the angle between the hands
     *  @return double-precision value of the angle between the two hands
     */

    public double getHandAngle() {
        double getHandAngle = Math.abs(getHourHandAngle() - getMinuteHandAngle());
        return getHandAngle;

    }

    /**
     *  Method to fetch the total number of seconds
     *   we can use this to tell when 12 hours have elapsed
     *  @return double-precision value the total seconds private variable
     */

    public double getTotalSeconds() {
        return seconds;

    }

    /**
     *  Method to return a String representation of this clock
     *  @return String value of the current clock
     */

     public String getTime() {
      String resultTimeString = " ";
        if(getHour() <10){
          resultTimeString = resultTimeString + "0";
	    }
        resultTimeString = resultTimeString + getHour() + " Hours ";


        if(getMinutes() < 10){
         resultTimeString = resultTimeString + "0";
        }
        resultTimeString = resultTimeString + getMinutes() + " Minutes ";
        if(getSeconds() < 10){
		         resultTimeString = resultTimeString + "0";
        }
        DecimalFormat f = new DecimalFormat("##.00");
        resultTimeString = resultTimeString + f.format(getSeconds()) + " Seconds ";

        return resultTimeString;
     }

     public int getHour() {
       if((int)((seconds)/3600) == 0)
	             return 0;

      return (int)((seconds)/3600);
     }

     public int getMinutes() {
       return (int)((seconds - (getHour()*3600))/60);
     }

     public double getSeconds() {
		 return (seconds - (getHour() *3600) - (getMinutes()*60)) ;
 	 }
//DecimalFormat dec = new DecimalFormat("#,#0.0");
    public String toString() {

      return getTime();

    }
