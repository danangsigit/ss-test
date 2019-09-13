package com.java.test.ss.commons.utils;

import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String DD_MM_YY = "dd/MM/yy";
    public static final String DDMMYYYY = "ddMMyyyy";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String MM_YYYY = "MM/yyyy";
    public static final String DD_MM_YY_HH_MM_SS = "dd/MM/yy HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_AM_PM = "HH:mm a";
    public static final String DATE_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String INDONESIA_LOCALE = "in_ID";
    public static final String INDONESIA_TIMEZONE = "Asia/Jakarta";
    public static final String dd_MMMM_YYYY = "dd MMMM YYYY";
    public static final String MMMM_dd_YYYY = "MMMM_dd_YYYY";

    static Map<String, String> mapOffset = new HashMap<>();
    static Map<String, String> timeZone = new HashMap<>();

    static {
        mapOffset.put("UM12", "-12");
        mapOffset.put("UM11", "-11");
        mapOffset.put("UM10", "-10");
        mapOffset.put("UM95", "-9.5");
        mapOffset.put("UM9", "-9");
        mapOffset.put("UM8", "-8");
        mapOffset.put("UM7", "-7");
        mapOffset.put("UM6", "-6");
        mapOffset.put("UM5", "-5");
        mapOffset.put("UM45", "-4.5");
        mapOffset.put("UM4", "-4");
        mapOffset.put("UM35", "-3.5");
        mapOffset.put("UM3", "-3");
        mapOffset.put("UM2", "-2");
        mapOffset.put("UM1", "-1");
        mapOffset.put("UTC", "+0");
        mapOffset.put("UP1", "+1");
        mapOffset.put("UP2", "+2");
        mapOffset.put("UP3", "+3");
        mapOffset.put("UP35", "+3.5");
        mapOffset.put("UP4", "+4");
        mapOffset.put("UP45", "+4.5");
        mapOffset.put("UP5", "+5");
        mapOffset.put("UP55", "+5.5");
        mapOffset.put("UP575", "+5.75");
        mapOffset.put("UP6", "+6");
        mapOffset.put("UP65", "+6.5");
        mapOffset.put("UP7", "+7");
        mapOffset.put("UP8", "+8");
        mapOffset.put("UP875", "+8.75");
        mapOffset.put("UP9", "+9");
        mapOffset.put("UP95", "+9.5");
        mapOffset.put("UP10", "+10");
        mapOffset.put("UP105", "+10.5");
        mapOffset.put("UP11", "+11");
        mapOffset.put("UP115", "+11.5");
        mapOffset.put("UP12", "+12");
        mapOffset.put("UP1275", "+12.75");
        mapOffset.put("UP13", "+13");
        mapOffset.put("UP14", "+14");

        timeZone.put("UM12", "GMT-12:00");
        timeZone.put("UM11", "GMT-11:00");
        timeZone.put("UM10", "GMT-10:00");
        timeZone.put("UM95", "GMT-9:30");
        timeZone.put("UM9", "GMT-9:00");
        timeZone.put("UM8", "GMT-8:00");
        timeZone.put("UM7", "GMT-7:00");
        timeZone.put("UM6", "GMT-6:00");
        timeZone.put("UM5", "GMT-5:00");
        timeZone.put("UM45", "GMT-4:30");
        timeZone.put("UM4", "GMT-4:00");
        timeZone.put("UM3", "GMT-3:00");
        timeZone.put("UM2", "GMT-2:00");
        timeZone.put("UM1", "GMT-1:00");
        timeZone.put("UTC", "GMT");
        timeZone.put("UP1", "GMT+1:00");
        timeZone.put("UP2", "GMT+2:00");
        timeZone.put("UP3", "GMT+3:00");
        timeZone.put("UP4", "GMT+4:00");
        timeZone.put("UP5", "GMT+5:00");
        timeZone.put("UP6", "GMT+6:00");
        timeZone.put("UP7", "GMT+7:00");
        timeZone.put("UP8", "GMT+8:00");
        timeZone.put("UP9", "GMT+9:00");
        timeZone.put("UP10", "GMT+10:00");
        timeZone.put("UP11", "GMT+11:00");
        timeZone.put("UP12", "GMT+12:00");
        timeZone.put("UP13", "GMT+13:00");
        timeZone.put("UP14", "GMT+14:00");
        timeZone.put("UP35", "GMT+3:30");
        timeZone.put("UP45", "GMT+4:30");
        timeZone.put("UP55", "GMT+5:30");
        timeZone.put("UP575", "GMT+5:45");
        timeZone.put("UP65", "GMT+6:30");
        timeZone.put("UP875", "GMT+8:45");
        timeZone.put("UP95", "GMT+9:30");
        timeZone.put("UP105", "GMT+10:30");
        timeZone.put("UP115", "GMT+11:30");
        timeZone.put("UP1275", "GMT+12:45");
    }

    private static Date currentSystemDate;

    public static int WEEKEND_START_DAY = Calendar.FRIDAY;
    public static int WEEKEND_START_HOUR = 17;
    public static int WEEKEND_END_DAY = Calendar.MONDAY;
    public static int WEEKEND_END_HOUR = 8;

    public static String getGmtOffset(String timezone) {

        if (mapOffset.get(timezone) != null) {
            return "GMT".intern() + mapOffset.get(timezone);
        }
        return null;
    }

    public static String getUPOffset(String timezone) {
        Map<String, String> map = new HashMap<>();
        map.put("GMT-12:00","UM12");
        map.put("GMT-11:00", "UM11");
        map.put("GMT-10:00", "UM10");
        map.put("GMT-9:30", "UM95");
        map.put("GMT-9:00", "UM9");
        map.put("GMT-8:00", "UM8");
        map.put("GMT-7:00", "UM7");
        map.put("GMT-6:00", "UM6");
        map.put("GMT-5:00", "UM5");
        map.put("GMT-4:30", "UM45");
        map.put("GMT-4:00", "UM4");
        map.put("GMT-3:00", "UM3");
        map.put("GMT-2:00", "UM2");
        map.put("GMT-1:00", "UM1");
        map.put("GMT", "UTC");
        map.put("GMT+1:00", "UP1");
        map.put("GMT+2:00", "UP2");
        map.put("GMT+3:00", "UP3");
        map.put("GMT+4:00", "UP4");
        map.put("GMT+5:00", "UP5");
        map.put("GMT+6:00", "UP6");
        map.put("GMT+7:00", "UP7");
        map.put("GMT+8:00", "UP8");
        map.put("GMT+9:00", "UP9");
        map.put("GMT+10:00", "UP10");
        map.put("GMT+11:00", "UP11");
        map.put("GMT+12:00", "UP12");
        map.put("GMT+13:00", "UP13");
        map.put("GMT+14:00", "UP14");
        map.put("GMT+3:30", "UP35");
        map.put("GMT+4:30", "UP45");
        map.put("GMT+5:30", "UP55");
        map.put("GMT+5:45", "UP575");
        map.put("GMT+6:30", "UP65");
        map.put("GMT+8:45", "UP875");
        map.put("GMT+9:30", "UP95");
        map.put("GMT+10:30", "UP105");
        map.put("GMT+11:30", "UP115");
        map.put("GMT+12:45", "UP1275");
        if (map.get(timezone) != null) {
            return map.get(timezone);
        }
        return null;
    }

    public static void setWeekendConf(int startDay, int startHour, int endDay, int endHour) {
        WEEKEND_START_DAY = startDay;
        WEEKEND_START_HOUR = startHour;
        WEEKEND_END_DAY = endDay;
        WEEKEND_END_HOUR = endHour;
    }

    public static Date getCurrentSystemDate() {
        if (currentSystemDate != null) {
            return currentSystemDate;
        } else {
            return new Date(System.currentTimeMillis());
        }
    }

    public static void resetCurrentSystemDate() {
        currentSystemDate = null;
    }

    public static Timestamp nowTimestamp() {
        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public static Date nowDate() {
        return new Date();
    }

    public static String getISO8601StringForTimestamp(Timestamp timestamp) {
        return formatDate(timestamp, null, DATE_ISO_8601);

    }

    public static String getISO8601StringForDate(Date date) {
        return formatDate(date, null, DATE_ISO_8601);
    }

    public static Date getDateFromISO8601String(String ISO8601String) {
        return DatatypeConverter.parseDateTime(ISO8601String).getTime();
    }

    public static String formatDate(Date inputDate, TimeZone timezone, String datePattern) {
        if (inputDate != null) {
            SimpleDateFormat df = new SimpleDateFormat(datePattern);
            if (timezone != null) {
                df.setTimeZone(timezone);
            } // else by server timezone
            return df.format(inputDate.getTime());
        } else {
            return null;
        }
    }

    public static String formatDateTime(Date inputDate, String pattern) {
        if (inputDate != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(inputDate.getTime());
        } else {
            return null;
        }
    }

    public static String formatDateTime(Long timestamp, String pattern) {
        if (timestamp != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(timestamp);
        } else {
            return null;
        }
    }

    public static Date parseDate(String dateValue, String format, TimeZone timezone) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        if (timezone != null) {
            df.setTimeZone(timezone);
        }
        return df.parse(dateValue);
    }

    public static Date parseDate(String dateValue, String datePattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        return df.parse(dateValue);
    }

    public static Date parseDate(String dateValue) throws ParseException {
        return parseDate(dateValue, YYYY_MM, MM_YYYY, YYYY_MM_DD, DD_MM_YYYY, YYYY_MM_DD_HH_MM_SS, DD_MM_YY_HH_MM_SS, DATE_ISO_8601, DDMMYYYY, YYYYMMDD);
    }

    public static Date getDayBeginTime(Long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDayEndTime(Long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date shiftDays(Long timeInMillis, int d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        cal.add(Calendar.DAY_OF_YEAR, d);
        return cal.getTime();
    }

    public static Date createDateInMilliseconds(int m) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, m);
        return cal.getTime();
    }

    public static boolean isExpired(Date date) {
        Calendar cal = Calendar.getInstance();
        return cal.after(date);
    }

    public static Date getWeekStartCalendar(Date weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekday);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getWeekEndCalendar(Date weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekday);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getWeekStart(Date weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekday);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getWeekEnd(Date weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekday);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getLastTimeDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getCurrentDateWithoutTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getDuration(String start, String end) throws ParseException {
        Date startDate = DateUtils.parseDate(start);
        Date endDate = DateUtils.parseDate(end);
        long diff = endDate.getTime() - startDate.getTime();
        Date d = new Date(diff);
        return d.getYear() - 70 + " year(s) " + d.getMonth() + " month(s)";
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<>();
        dates.add(startdate);
        while (startdate.before(enddate)) {
            startdate = DateUtils.addDays(startdate, 1);
            dates.add(startdate);
        }
        return dates;
    }

    public static Date getEndDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static boolean isWeekend() {
        return isWeekend(WEEKEND_START_DAY, WEEKEND_START_HOUR, WEEKEND_END_DAY, WEEKEND_END_HOUR);
    }

    private static boolean isWeekend(int startDay, int startHour, int endDay, int endHour) {
        Calendar today = new GregorianCalendar();
        int day = today.get(Calendar.DAY_OF_WEEK);
        int hour = today.get(Calendar.HOUR_OF_DAY);
        return isWeekend(day, hour, startDay, startHour, endDay, endHour);
    }

    private static boolean isWeekend(int day, int hour, int startDay, int startHour, int endDay, int endHour) {
        endDay = (endDay < startDay ? endDay + Calendar.SATURDAY : endDay);
        day = (day != startDay && endDay > Calendar.SATURDAY && day < startDay ? day + Calendar.SATURDAY : day);
        return (startDay != endDay && day == startDay && hour >= startHour) ||
                (startDay != endDay && day == endDay && hour < endHour) ||
                (startDay == endDay && hour >= startHour && hour < endHour) ||
                (day > startDay && day < endDay);
    }

    public static void main(String[] args) {
        int startDay = Calendar.FRIDAY;
        int startHour = 17;
        int endDay = Calendar.MONDAY;
        int endHour = 8;

        int day = Calendar.WEDNESDAY;
        int hour = 10;
        System.out.println("" + isWeekend(day, hour, startDay, startHour, endDay, endHour));
        System.out.println(formatDate(new Date(), getTimeZoneFromUTC("UP8"), "MMM dd, EEE HH:mm z"));
    }

    public static TimeZone getTimeZoneFromUTC(String timeZoneStr) {
        if (timeZone.containsKey(timeZoneStr))
            return TimeZone.getTimeZone(timeZone.get(timeZoneStr));
        else return TimeZone.getTimeZone("GMT+7:00");
    }

    public static String getGMTTimezone(TimeZone tz) {
        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        minutes = Math.abs(minutes);

        String result;
        if (hours > 0) {
            result = String.format("GMT+%d:%02d", hours, minutes);
        } else {
            result = String.format("GMT%d:%02d", hours, minutes);
        }
        return result;
    }

}
