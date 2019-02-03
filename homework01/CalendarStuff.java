/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <Ameya Mellacheruvu>
 *  Date          :  <date of writing here>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
  // you can finish the rest on your own

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
  // you can finish these on your own, too

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   //public CalendarStuffEmpty() {
      //System.out.println( "Constructor called..." );  // replace this with the actual code
   //}

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   long year;
   public static boolean isLeapYear( long year ) {
      if(year % 4 == 0 && year % 100 != 0) {
            return true;
      }
      if(year % 400 == 0){
        return true;
      }
      return false;

    }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
  long month;
   public static long daysInMonth( long month, long year ) {
     if(isLeapYear(year) == true && month == 2){
       return 29;
     }if(month == 2){
       return 28;
     }if(month== 4|| month == 6 || month ==9|| month ==11) {
       return 30;
     }else{
       return 31;
     }


   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return true;  // replace this with the actual code
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return 0;  // replace this with the actual code
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   long day;

   public static boolean isValidDate( long month, long day, long year ) {
     try {
            if(month <= 0 || day <= 0 || year <= 0) {
                return false;
            }
            if(day >= 32){
                return false;
            }if(month >= 13) {
              return false;
            }

            if(month == 2){
                if(!isLeapYear(year)){
                    if(day >= 29){
                        return false;
                    }
                }

            }
        }
        catch (Exception e){
            System.out.println("Error, please input valid dates.");
        }
        return true;  // replace this with the actual code

    }





  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days

   */
   long month1;
   long day1;
   long year1;
   long month2;
   long day2;
   long year2;

   public static long daysBetween(long month1, long day1, long year1, long month2, long day2, long year2) {
        long dayCount = 0;
        long[] monthArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        long days = day2 -day1;
        long monthsInDays1 = 0;
        long monthsInDays2 = 0;




        for (int i = 0; i <= month1 - 2; i++) {

                    monthsInDays1 += monthArray[i];

        }
        for (int i = 0; i <= month2 - 2; i++) {

            monthsInDays2 += monthArray[i];

        }
        long adjustedYear1 = year1 - 1;

        if(isLeapYear(adjustedYear1)) {
            adjustedYear1 = (long) (365.25* adjustedYear1);
        }else {
            adjustedYear1 = (long) 365.25* (adjustedYear1 - adjustedYear1%4) + (adjustedYear1%4)*365;
        }

        long adjustedYear2 = year2 - 1;

        if(isLeapYear(adjustedYear2)) {
            adjustedYear2 = (long) (365.25* adjustedYear2);
        }else {
            adjustedYear2 = (long) 365.25* (adjustedYear2 - adjustedYear2%4) + (adjustedYear2%4)*365;
        }

        dayCount = Math.abs(monthsInDays1 - monthsInDays2) + Math.abs(adjustedYear1 - adjustedYear2) +Math.abs(days);

        return dayCount;
    }

}
