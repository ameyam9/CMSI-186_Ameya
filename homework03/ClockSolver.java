
public class ClockSolver extends Clock {

    /**
     *  Class field definintions go here
     */
    private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
    private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
    private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons
    double inputTimeSlice = DEFAULT_TIME_SLICE_SECONDS;
    double inputAngle;
    /**
     *  Constructor
     *  This just calls the superclass constructor, which is "Object"
     */
    public ClockSolver() {
        super();
        Clock clock = new Clock();
    }

    /**
     *  Method to handle all the input arguments from the command line
     *   this sets up the variables for the simulation
     */
    public void handleInitialArguments( String args[] ) {


        // args[0] specifies the angle for which you are looking
        //  your simulation will find all the angles in the 12-hour day at which those angles occur
        // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
        // you may want to consider using args[2] for an "angle window"

        System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" );
        if( 0 == args.length ) {
            System.out.println( "   Sorry you must enter at least one argument\n" +
                    "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                    "   Please try again..........." );
            System.exit( 1 );
        }else if(args.length >= 1) {
          try {
            inputAngle = Double.parseDouble(args[0]);
          }catch(NumberFormatException e){
              System.out.println("Please enter Valid Angle");
              System.exit(0);
          }
          if(args.legnth >=2) {
            try {
                inputTimeSlice = Double.parseDouble(args[1]);
            }catch(NumberFormatException e){
                System.out.println("Please enter Valid Number");
                System.exit(0);
            }
          if(inputAngle > MAXIMUM_DEGREE_VALUE || inputAngle < 0.0) {
              throw new IllegalArgumentException("Input a Positive Angle or an Angle Less Than or Equal to 360.0");
          }
          if (inpuTimeSlice < 0.0 || inputTimeSlice > MAX_TIME_SLICE_IN_SECONDS){
              throw new IllegalArgumentException("Input a Positive Value  or a Value less than 1800.0 ");
          }


      }

    public void processAngle() {
      for(int i =0; i<43200; i++){
        if(inputAngle < (MAXIMUM_DEGREE_VALUE * 0.5) {
          if(Math.abs(inputAngle - clock.getHandAngle()) < EPSILON_VALUE) {
                System.out.println(clock.toString());
           }
        }
      }
  }
    /**
     *  The main program starts here
     *  remember the constraints from the project description
     *  @see /johnson.lmu.build/cmsi186web/homework04.html
     *  @param  args  String array of the arguments from the command line
     *                args[0] is the angle for which we are looking
     *                args[1] is the time slice; this is optional and defaults to 60 seconds
     */
    public static void main( String args[] ) {
        ClockSolver cs = new ClockSolver();
        Clock clock    = new Clock();
        double[] timeValues = new double[3];
        cs.handleInitialArguments( args );
        cs.processAngle();
        System.exit( 0 );

    }
}
