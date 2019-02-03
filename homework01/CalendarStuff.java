
public class CalendarStuff {

   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


   //public CalendarStuffEmpty() {
      //System.out.println( "Constructor called..." );  // replace this with the actual code
   //}

   long year;
   public static boolean isLeapYear( long year ) {
      if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
        return true;
      }else {
        return false;
      }
   }


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

   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return true;  // replace this with the actual code
   }

   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return 0;  // replace this with the actual code
   }

   long day;
   public static boolean isValidDate( long month, long day, long year ) {
     try {
          if(month <= 0 || day <= 0 || year <= 0) {
              return false;
          }if(day >= 32){
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
      catch (NumberFormatException e){
          System.out.println("Error, please input valid dates.");
      }
          return true;
    }

   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

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
