public class ClockSolver {

    /**
     * Class field definintions go here
     */
    private final double MAX_TIME_SLICE_IN_SECONDS = 1800.00;
    private static final double MAXIMUM_DEGREE_VALUE = 360.0;
    private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
    private final double EPSILON_VALUE = 0.1;      // small value for double-precision comparisons
    private double inputTimeSlice = DEFAULT_TIME_SLICE_SECONDS;
    private double inputAngle;
    private Clock clock;


    public ClockSolver() {
        super();

    }

    /**
     * Method to handle all the input arguments from the command line
     * this sets up the variables for the simulation
     */
    public void handleInitialArguments(String args[]) {


        // args[0] specifies the angle for which you are looking
        //  your simulation will find all the angles in the 12-hour day at which those angles occur
        // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
        // you may want to consider using args[2] for an "angle window"

        System.out.println("\n   Hello world, from the ClockSolver program!!\n\n");
        if (0 == args.length) {
            System.out.println("   Sorry you must enter at least one argument\n" +
                    "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                    "   Please try again...........");
            System.exit(1);
        }
        if (args.length >= 1) {
            try {
                inputAngle = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter Valid Angle");
                System.exit(0);
            }
         if (args.length >= 2) {
             try {
                    inputTimeSlice = Double.parseDouble(args[1]);

             } catch (NumberFormatException e) {
                    System.out.println("Please enter Valid TimeSlice");
                    System.exit(0);
             }
          }

          if (inputAngle > MAXIMUM_DEGREE_VALUE || inputAngle < 0.0) {
                System.out.println("Input a Positive Angle or an Angle Less Than or Equal to 360.0");
                System.exit(0);
          }
          if(inputTimeSlice > MAX_TIME_SLICE_IN_SECONDS || inputTimeSlice < 0.0){
                System.out.println("Input a Positive TimeSlice Value or Value Less Than or Equal to 1800.0");
                System.exit(0);
          }
          clock = new Clock(args);
        }
    }
        public void processAngle() {

			long noOfSteps = (long)(43200/inputTimeSlice);
            for (int i = 0; i < noOfSteps; i++) {

                if (Math.abs(inputAngle - clock.getHandAngle()) < EPSILON_VALUE) {
                    System.out.println(clock.toString());

                }
                clock.tick();
            }
        }

        public static void main (String[]args){
            ClockSolver cs = new ClockSolver();
            //double[] timeValues = new double[3];

            cs.handleInitialArguments(args);


            cs.processAngle();


        }
    }
