import java.text.DecimalFormat;
public class Clock{

    /**
     *  Class field definintions go here
     */

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

    public Clock() {

			  inputTimeSlice = 60.0;


    }

    public Clock(String[] args) {
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

    public static void main( String args[] ) {

        System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                "--------------------------\n" );
        System.out.println( "  Creating a new clock: " );

        Clock clock = new Clock(args);
        clock.getMinuteHandAngle();
        System.out.println( "    New clock created: " + clock.toString() );

        System.out.println( "    Testing validateAngleArg()....");
        System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
        try {
            System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" );
        }
        catch( Exception e ) {
            System.out.println ( " - Exception thrown: " + e.toString() );
		}
		System.out.print( "      sending '  30 degrees', expecting double value   30.0" );
		        try {
		            System.out.println( (30.0 == clock.validateAngleArg( "30.0" )) ? " - got 30.0" : " - no joy" );
		        }
		        catch( Exception e ) {
		            System.out.println ( " - Exception thrown: " + e.toString() );
		}
		System.out.print( "      sending '  120 degrees', expecting double value   120.0" );
				        try {
				            System.out.println( (120.0 == clock.validateAngleArg( "120.0" )) ? " - got 120.0" : " - no joy" );
				        }
				        catch( Exception e ) {
				            System.out.println ( " - Exception thrown: " + e.toString() );
		}
		System.out.print( "      sending '  359.999999 degrees', expecting double value   359.999999" );
						        try {
						             System.out.println( (359.999999 == clock.validateAngleArg("359.999999")) ? " - got 359.999999" : " - no joy" );
						        }
						        catch( Exception e ) {
						            System.out.println ( " - Exception thrown: " + e.toString() );
								}
		System.out.println( "\n    Testing validateTimeSliceArg()....");
		      System.out.print( "      sending '  12.0 degrees', expecting double value   12.0" );
		      try { System.out.println( (10.0 == clock.validateTimeSliceArg( "10.0" )) ? " - got 10.0" : " - no joy" ); }
		      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
		      System.out.println("      Current Time Slice is " + clock.inputTimeSlice);
		      System.out.print( "      sending '  67.4 degrees', expecting double value   67.4" );
		       try { System.out.println( (67.4 == clock.validateTimeSliceArg( "67.4" )) ? " - got 67.4" : " - no joy" ); }
		      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
		      System.out.println("      Current Time Slice is " + clock.inputTimeSlice);
		      System.out.print( "      sending '  60.0 degrees', expecting double value   60.0" );
		      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "60.0" )) ? " - got 60.0" : " - no joy" ); }
		      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      		  System.out.println("      Current Time Slice is " + clock.inputTimeSlice);
      	System.out.println( "\n   Testing  tick()....");
		      Clock clock2 = new Clock();

		      System.out.println("**Time Slice: 60.0**");
		      System.out.println("\t1 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
		      System.out.println("\t2 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
		      System.out.println("\t3 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());


		      System.out.println("\t59 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
		      System.out.println("\t60 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
		      System.out.println("\t61 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());

	  }

    }
