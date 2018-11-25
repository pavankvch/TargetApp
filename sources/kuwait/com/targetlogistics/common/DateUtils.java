package kuwait.com.targetlogistics.common;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final String DAY_DD_MMM_YYYY = "dd MMM yyyy\nEEEE";
    public static final String DD_MMMM = "dd MMM";
    public static final String DD_MMMM_HH_MM = "dd MMM hh:mm";
    public static final String DD_MMM_YYYY = "dd MMM yyyy";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DD_MM_YYYY_HH_MM = "dd/MM/yy hh:mm a";
    public static final String DD_MM_YYYY_HH_MM_SS = "dd.MM.yyyy hh:mm";
    public static final String HH_MM = "hh:mm";
    public static final String HH_MM_24 = "HH:mm";
    public static final String HH_MM_PM = "hh:mm a";
    public static final String IST_TO_EDT = "UTC−05:00";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_24 = "yyyy-MM-dd HH:mm:ss";
    private static String inputFormat = DD_MMM_YYYY;
    private static String outputFormat = "dd.MM.yyyy";

    public static String formateDateFromstring(String inputDate) {
        String outputDate = "invalid";
        SimpleDateFormat df_input = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_24, Locale.ENGLISH);
        try {
            outputDate = new SimpleDateFormat(DD_MM_YYYY_HH_MM, Locale.ENGLISH).format(df_input.parse(inputDate));
        } catch (ParseException e) {
            Log.d("date", e.getMessage());
        }
        return outputDate;
    }

    public static Date getDate(String format, String date) {
        Date parsed = null;
        try {
            parsed = new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
        } catch (Exception e) {
        }
        return parsed;
    }

    public static String getDate(String format, Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format, Locale.ENGLISH).format(date);
    }

    public static String getDate(String timeZone, String format, Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat outPutFormate = new SimpleDateFormat(format, Locale.ENGLISH);
        outPutFormate.setTimeZone(TimeZone.getTimeZone(timeZone));
        return outPutFormate.format(date);
    }

    public static boolean isToday(long timestamp) {
        Calendar now = Calendar.getInstance();
        Calendar timeToCheck = Calendar.getInstance();
        timeToCheck.setTimeInMillis(timestamp);
        if (now.get(1) == timeToCheck.get(1) && now.get(6) == timeToCheck.get(6)) {
            return true;
        }
        return false;
    }

    public static boolean isYesterday(long date) {
        Calendar now = Calendar.getInstance();
        Calendar cdate = Calendar.getInstance();
        cdate.setTimeInMillis(date);
        now.add(5, -1);
        if (now.get(1) == cdate.get(1) && now.get(2) == cdate.get(2) && now.get(5) == cdate.get(5)) {
            return true;
        }
        return false;
    }

    public static String convertDateTime(Date date) {
        if (date == null) {
            return "";
        }
        if (isToday(date.getTime())) {
            return "Today " + getDate(HH_MM, date);
        }
        if (isYesterday(date.getTime())) {
            return "Yesterday " + getDate(HH_MM, date);
        }
        return getDate(DD_MMMM_HH_MM, date);
    }
}
