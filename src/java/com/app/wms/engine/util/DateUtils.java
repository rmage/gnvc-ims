package com.app.wms.engine.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Name: Date related utility class
 * Note: This class was copied from DateHelper.java in possWeb project(Authored By Reza)
 *       intended to be used in possEngine Project.
 *
 * @author Jeco
 * @since July 10, 2010
 */
public class DateUtils
{

    private static Log log = LogFactory.getLog(DateUtils.class);
    /**
     * default date pattern dd/MM/yyyy
     */
    public static final String DEFAULT_DATE_PATTERN = "MM/dd/yyyy";//dd/MM/yyyy";

    public static final String MONTH_PATTERN = "MM/yyyy";

    public static final String MONTH_PATTERN_DASH = "MM-yyyy";

    /**
     * date pattern dd-MM-yyyy
     */
    public static final String DATE_PATTERN_DASH = "dd-MM-yyyy";
    /**
     * default time pattern hh:mm:ss
     */
    public static final String DEFAULT_TIME_PATTERN = "hh:mm:ss";
    /**
     * default date time pattern dd/MM/yyyy hh:mm:ss
     */
    public static final String DEFAULT_DATETIME_PATTERN = "dd/MM/yyyy hh:mm:ss";

    public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";

    public static final String DATE_PATTERN_yyyy_MM_dd  = "yyyy-MM-dd";

    public static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH-mm-ss";

    public static final String DATE_PATTERN_DDMMYYYY = "ddMMyyyy";


    private DateUtils()
    {
    }

    /**
     * Get now time as long
     * @return long
     */
    public static long nowAsLong()
    {
        return System.currentTimeMillis();
    }

    /**
     * Get now time as date
     * @return time object
     */
    public static Date nowAsDate()
    {
        return new Date(nowAsLong());
    }

    /**
     * Get now time as timestamp
     * @return timestamp object
     */
    public static Timestamp nowAsTimestamp()
    {
        return new Timestamp(nowAsLong());
    }

    /**
     * Get now time as calendar
     * @return Calendar
     */
    public static Calendar nowAsCalendar()
    {
        return Calendar.getInstance();
    }

    /**
     * Convert date to timestamp
     * @param date
     * @return Timestamp
     */
    public static Timestamp dateToTimestamp(Date date)
    {
        return date == null ? null : new Timestamp(date.getTime());
    }


    /**
     * Convert string to date
     * @param dateString string to convert
     * @return date
     */
    public static Date stringToDate(String dateString)
    {
        return stringToDate(DEFAULT_DATE_PATTERN, dateString);
    }

    //  ADDED BY JECO, JULY 13, 2010
    public static Date sqlDateToUtil(java.sql.Date date)
    {
        try
        {
            System.out.println("java.sql.Date:"+date);
            if(date!=null)
                return new SimpleDateFormat(DATE_PATTERN_yyyy_MM_dd).parse(date.toString());
        }
        catch (ParseException ex)
        {
            String message = "DateUtils.sqlDateToUtil -> java.sqlDate: %s, exception message: %s";
            message = String.format(message, date.toString(), ex.getMessage());
            log.error(message);
        }
        return null;
    }

    /**
     * Convert string to date
     * @param pattern date pattern
     * @param dateString string to convert
     * @return date
     */
    public static Date stringToDate(String pattern, String dateString)
    {
        if (!StringUtils.isEmpty(pattern) && !StringUtils.isEmpty(dateString))
        {
            try
            {
                return new SimpleDateFormat(pattern).parse(dateString);
            }
            catch (ParseException ex)
            {
                String message = "DateHelper.stringToDate -> Pattern: %s, dateString: %s, exception message: %s";
                message = String.format(message, pattern, dateString, ex.getMessage());
                log.error(message);
            }
        }

        return null;
    }

    public static Date stringYYYYDDMMToDateYYYYMMDDHHMMSS(String dateString)
    {
        if ( !StringUtils.isEmpty(dateString))
        {
           
            dateString = dateString + " 00:00:00" ;
            try
            {
                return new SimpleDateFormat(DATE_PATTERN_YYYYMMDDHHMMSS).parse(dateString);
            }
            catch (ParseException ex)
            {
                String message = "DateHelper.stringToDate -> dateString: %s, exception message: %s";
                message = String.format(message, dateString, ex.getMessage());
                log.error(message);
            }
        }

        return null;
    }

    /**
     * Convert date to string
     * @param date date to convert
     * @return string
     */
    public static String dateToString(Date date)
    {
        return dateToString(DEFAULT_DATE_PATTERN, date);
    }

    /**
     * Convert date to string
     * @param pattern date pattern
     * @param date date to convert
     * @return string
     */
    public static String dateToString(String pattern, Date date)
    {
        if (!StringUtils.isEmpty(pattern) && date != null)
        {
            return new SimpleDateFormat(pattern).format(date);
        }
        return null;
    }

    /**
     * convert timestamp to sql string date format
     * @param dateTime
     * @param withTime
     * @return String
     */
    public static String convertTimestampToString(Timestamp dateTime, boolean withTime) {
        String s = "";
        java.text.SimpleDateFormat ft = null;
        if (withTime) {
          ft = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
          ft = new java.text.SimpleDateFormat("yyyy-MM-dd");
        }
        s = ft.format(dateTime);
        return s;
    }

    /**
     * convert timestamp to format yyyyMMdd
     * @param dateTime
     * @return String
     */
    public static String convertTimestampToString(Timestamp dateTime, String dateFormat) {
        String s = "";
        java.text.SimpleDateFormat ft = null;
        ft = new java.text.SimpleDateFormat(dateFormat);
        s = ft.format(dateTime);
        return s;
    }


    /**
     * convert timestamp to format dd MMMM yyyy
     * @param dateTime
     * @return String
     */
    public static String convertTimestampToDDMMMMYYYY(Timestamp dateTime) {
        String s = "";
        java.text.SimpleDateFormat ft = null;
        ft = new java.text.SimpleDateFormat("dd MMMM yyyy");
        s = ft.format(dateTime);
        if (s.indexOf("Jan") > -1) {
            s = s.replace("January", "Januari");
        } else if (s.indexOf("Feb") > -1) {
            s = s.replace("February", "Februari");
        } else if (s.indexOf("Mar") > -1) {
            s = s.replace("March", "Maret");
        } else if (s.indexOf("May") > -1) {
            s = s.replace("May", "Mei");
        } else if (s.indexOf("Jun") > -1) {
            s = s.replace("June", "Juni");
        } else if (s.indexOf("Jul") > -1) {
            s = s.replace("Jul", "Juli");
        } else if (s.indexOf("Aug") > -1) {
            s = s.replace("August", "Agustus");
        } else if (s.indexOf("Oct") > -1) {
            s = s.replace("October", "Oktober");
        } else if (s.indexOf("Feb") > -1) {
            s = s.replace("November", "Nopember");
        } else if (s.indexOf("Dec") > -1) {
            s = s.replace("December", "Desember");
        }
        return s;
    }


    public static Timestamp convertDateStringToTimestamp(String dateString, String format) {
        String day = "";
        String month = "";
        String year = "";
        String hour = "";
        String minute = "";
        String second = "";
        StringTokenizer st;
        Timestamp dateTime = null;
        try {
          if (format.equalsIgnoreCase("dd/mm/yy")) {
            day = dateString.substring(0, 2);
            month = dateString.substring(3, 5);
            year = "20" + dateString.substring(6, 8);
            hour = "00";
            minute = "00";
            second = "00";
          }
          else if (format.equalsIgnoreCase("dd/mm/yyyy")) {
            st = new StringTokenizer(dateString, "/");
            day = st.nextToken(); //dateString.substring(0, 2);
            month = st.nextToken(); //dateString.substring(3, 5);
            year = st.nextToken(); //dateString.substring(6, 10);
            if (day.length()==1) day = "0" + day;
            if (month.length()==1) month = "0" + month;
            hour = "00";
            minute = "00";
            second = "00";
          }
          else if (format.equalsIgnoreCase("yyyy-mm-dd")) {
            day = dateString.substring(8, 10);
            month = dateString.substring(5, 7);
            year = dateString.substring(0, 4);
            hour = "00";
            minute = "00";
            second = "00";
          }
          else if (format.equalsIgnoreCase("yyyy-mm-dd hh:mm:ss")) {
            day = dateString.substring(8, 10);
            month = dateString.substring(5, 7);
            year = dateString.substring(0, 4);
            hour = dateString.substring(11, 13);
            minute = dateString.substring(14, 16);
            second = dateString.substring(17, 19);
          }
          String currTime = year + "-" + month + "-" + day + " " + hour + ":" +
              minute + ":" + second + ".000000000";
          dateTime = Timestamp.valueOf(currTime);
        } catch(Exception e) {
          e.printStackTrace();
          dateTime = null;
        }
        return dateTime;
      }


    public static int getDayDiff(String d1, String m1, String y1,
            String d2, String m2, String y2) {
        int q = 0;
        Date dt = null;
        //converts dates to m/d/y format
        String date1 = m1+"/"+d1+"/"+y1;
        String date2 = m2+"/"+d2+"/"+y2;
        long j = Date.parse(date1); //converts date into milliseconds
        long k = Date.parse(date2);
        q = (int)Math.abs((j-k)/24/60/60/1000);
        q++;
        return q;
    }


    public static String milisToSec(long millis) {
        int menit = (int) millis / 60000;
        int detik = (int) (millis % 60000) / 1000;
        int koma = (int) (millis % 1000) / 10;

        return new String(menit + " menit, " + detik + "." + koma + " detik");
    }

}