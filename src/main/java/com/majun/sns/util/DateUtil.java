package com.majun.sns.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jun_ma on 2016/4/14.
 */
public class DateUtil {
    public static Date getDate() {
        Calendar canlendar = Calendar.getInstance();
        return canlendar.getTime();
    }

    /**
     * 用年，月，日，时，分，秒构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @param iMinute
     * @param iSecond
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute, int iSecond) {
        iMonth--;
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.set(iYear, iMonth, iDate, iHour, iMinute, iSecond);
        return canlendar.getTime();
    }

    /**
     * 用年，月，日，时，分构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @param iMinute
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute) {
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute, 0);
    }

    /**
     * 用年，月，日，时构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour) {
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, 0, 0);
    }

    /**
     * 用年，月，日构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate) {
        return DateUtil.getDate(iYear, iMonth, iDate, 0, 0, 0);
    }

    /**
     * 用年，月构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @return
     */
    public static Date getDate(int iYear, int iMonth) {
        return DateUtil.getDate(iYear, iMonth, 1, 0, 0, 0);
    }

    /**
     * 用年构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @return
     */
    public static Date getDate(int iYear) {
        return DateUtil.getDate(iYear, 1, 1, 0, 0, 0);
    }

    public static Date getDate(String sYear) {
        int iYear = DateUtil.getRightNumber(sYear);
        return DateUtil.getDate(iYear);
    }

    public static Date getDate(String sYear, String sMonth) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        return DateUtil.getDate(iYear, iMonth);
    }

    public static Date getDate(String sYear, String sMonth, String sDate) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        return DateUtil.getDate(iYear, iMonth, iDate);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour, String sMinute) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        int iMinute = DateUtil.getRightNumber(sMinute);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour, String sMinute, String sSecond) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        int iMinute = DateUtil.getRightNumber(sMinute);
        int iSecond = DateUtil.getRightNumber(sSecond);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute, iSecond);
    }

    private static int getRightNumber(String sNumber) {
        try {
            return Integer.parseInt(sNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isMax(Date date0, Date date1) {
        if (date0 == null || date1 == null) {
            return false;
        }
        if (date0.getTime() > date1.getTime()) {
            return true;
        }
        return false;

    }

    public static Date Max(Date date0, Date date1) {
        if (date0 != null && date1 != null) {
            if (date0.getTime() > date1.getTime()) {
                return date0;
            }
            return date1;
        } else if (date0 != null && date1 == null) {
            return date0;
        } else if (date0 == null && date1 != null) {
            return date1;
        } else {
            return null;
        }
    }

    public static Date Min(Date date0, Date date1) {
        if (date0 != null && date1 != null) {
            if (date0.getTime() < date1.getTime()) {
                return date0;
            }
            return date1;
        } else {
            return null;
        }
    }

    /**
     * 得到两个日期毫秒级差
     *
     * @param date0
     * @param date1
     * @return
     */
    public static long getMillisecondDif(Date date0, Date date1) {
        if (date0 == null || date1 == null) {
            return 0;
        }
        return date0.getTime() - date1.getTime();
    }

    /**
     * 得到两个日期秒级差
     *
     * @param date0
     * @param date1
     * @return
     */
    public static long getSecondDif(Date date0, Date date1) {
        return DateUtil.getMillisecondDif(date0, date1) / 1000;
    }

    /**
     * 得到两个日期分钟差
     *
     * @param date0
     * @param date1
     * @return
     */
    public static long getMinuteDif(Date date0, Date date1) {
        return DateUtil.getSecondDif(date0, date1) / 60;
    }

    /**
     * 得到两个日期小时差
     *
     * @param date0
     * @param date1
     * @return
     */
    public static int getHourDif(Date date0, Date date1) {
        return (int) DateUtil.getMinuteDif(date0, date1) / 60;
    }

    /**
     * 得到两个日期天数差
     *
     * @param date0
     * @param date1
     * @return
     */
    public static int getDayDif(Date date0, Date date1) {
        return (int) DateUtil.getHourDif(date0, date1) / 24;
    }

    private static Date addDate(Date date, int iArg0, int iDate) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(iArg0, iDate);
        return canlendar.getTime();
    }

    /**
     * 日期增加秒
     *
     * @param date
     * @param iSecond
     * @return
     */
    public static Date addSecond(Date date, int iSecond) {
        return addDate(date, Calendar.SECOND, iSecond);
    }

    public static Date addMillSecond(Date date, int iMillSecond) {
        return addDate(date, Calendar.MILLISECOND, iMillSecond);
    }

    /**
     * 日期增加分钟
     *
     * @param date
     * @param iMinute
     * @return
     */
    public static Date addMinute(Date date, int iMinute) {
        return addDate(date, Calendar.MINUTE, iMinute);
    }

    /**
     * 日期增加小时
     *
     * @param date
     * @param iHour
     * @return
     */
    public static Date addHour(Date date, int iHour) {
        return addDate(date, Calendar.HOUR, iHour);
    }

    /**
     * 日期增加天数
     *
     * @param date
     * @param iDate
     * @return
     */
    public static Date addDay(Date date, int iDate) {
        return addDate(date, Calendar.DAY_OF_MONTH, iDate);
    }

    /**
     * 日期增加月
     *
     * @param date
     * @param iMonth
     * @return
     */
    public static Date addMonth(Date date, int iMonth) {
        return addDate(date, Calendar.MONTH, iMonth);
    }

    /**
     * 日期增加年
     *
     * @param date
     * @param iYear
     * @return
     */
    public static Date addYear(Date date, int iYear) {
        return addDate(date, Calendar.YEAR, iYear);
    }

    public static Date addSecond(Date date, String sSecond) {
        return addSecond(date, getRightNumber(sSecond));
    }

    public static Date addMi(Date date, String sMinute) {
        return addMinute(date, getRightNumber(sMinute));
    }

    public static Date addHo(Date date, String sHour) {
        return addHour(date, getRightNumber(sHour));
    }

    public static Date addDa(Date date, String sDate) {
        return addDay(date, getRightNumber(sDate));
    }

    public static Date addMo(Date date, String sMonth) {
        return addMonth(date, getRightNumber(sMonth));
    }

    public static Date addYe(Date date, String sYear) {
        return addYear(date, getRightNumber(sYear));
    }

    public static String getDateFormate(Date date, String formate) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        return simpleDateFormate.format(date);
    }

    public static String get4yMdHmsS(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String get4yMdHms(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String get4yMdHm(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm");
    }

    public static String get4yMd(Date date) {
        return getDateFormate(date, "yyyy-MM-dd");
    }
    public static String get4yHh(Date date) {
        return getDateFormate(date, "HH:mm:ss");
    }

    public static Date parseDateFullYear(String sDate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到时间的部分
     *
     * @param date
     * @param part
     * @return
     */
    public static int getPartOfTime(Date date, String part) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTime(date);
        if (part.equals("year")) {
            return canlendar.get(Calendar.YEAR);
        }
        if (part.equals("month")) {
            return canlendar.get(Calendar.MONTH);
        }
        if (part.equals("date")) {
            return canlendar.get(Calendar.DAY_OF_MONTH);
        }
        if (part.equals("hour")) {
            return canlendar.get(Calendar.HOUR_OF_DAY);
        }
        if (part.equals("minute")) {
            return canlendar.get(Calendar.MINUTE);
        }
        if (part.equals("second")) {
            return canlendar.get(Calendar.SECOND);
        }
        if (part.equals("milliSecond")) {
            return canlendar.get(Calendar.MILLISECOND);
        }
        return -1;
    }

    /**
     * 判断一个日期是不是今天
     *
     * @return
     */
    public static boolean isToday(Date date) {
        if (DateUtil.get4yMd(date).equals(
                DateUtil.get4yMd(Calendar.getInstance().getTime()))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        /** 判断是否为闰年，赋值给一标识符flag */
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
            isLeep = true;
        } else if (yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 计算某年某月的结束日期
     *
     * @param yearNum
     * @param monthNum
     * @return
     * @throws ParseException
     */
    public static String getYearMonthEndDay(int yearNum, int monthNum)
            throws ParseException {
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if (tempMonth.equals("1") || tempMonth.equals("3")
                || tempMonth.equals("5") || tempMonth.equals("7")
                || tempMonth.equals("8") || tempMonth.equals("10")
                || tempMonth.equals("12")) {
            tempDay = "31";
        }
        if (tempMonth.equals("4") || tempMonth.equals("6")
                || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }
        if (tempMonth.equals("2")) {
            if (isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }

        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(sdf.parse(tempDate));
        return sDate;
    }

    /**
     * 计算当前日期是星期几(星期日为0)
     * @param strDate
     * @return
     */
    public static int getWeekDay(Date strDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }


    /**当前时间是否在 两个时间之间
     * @param start
     * @param end
     * @return
     */
    public static boolean nowIsBetweenDates(Date start,Date end){
        Date temp  = null;
        if(start.getTime() > end.getTime()){//换位
            temp = start ;
            start = end;
            end = temp ;
        }

        long  nowTime = new Date().getTime();
        if(nowTime <  start.getTime()  || nowTime >  end.getTime()){//直接返回
            return false;
        }

        return true;
    }

    /**
     * 获取某一天的开始时间
     * @param date
     */
    public static Date getStartDateByDate(Date date){
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 获取某一天的结束时间
     * @param date
     */
    public static Date getEndDateByDate(Date date){
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 获取日期的年份
     * @param date
     * @return
     */
    public static int getYearByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月份
     * @param date
     * @return
     */
    public static int getMonthByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = 0;
        month = cal.get(Calendar.MONTH)+1;
        if(month == 13){
            month = 1;
        }
        return month;
    }

    /**
     * 获取日期
     * @param date
     * @return
     */
    public static int getDayByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某一月的开始时间
     * @param date
     */
    public static Date getStartDateByMonth(Date date){
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     *
     * @function : 得到日期部分
     * @param date
     * @return
     */
    public static Date getDatePart(Date date) throws ParseException{
        String strdate = get4yMd(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strdate);
    }

    /**
     *
     * @function : 相差日期字符串（格式自己定义）
     * @param date
     * @return
     */
    public static String getPastTime(Date now,Date before) {
        long c =now.getTime() - before.getTime();

        Long d = c/(3600000*24);

        StringBuffer s = new StringBuffer();

        boolean show = false;

        if(1 != d + 1) {
            s.append(d).append("天,");
            show = true;

        }

        Long h = (c%(3600000*24))/3600000;

        if(1 != h + 1 || show) {
            s.append(h).append("小时,");
            show = true;
        }

        Long m = (c%(3600000))/60000;

        if(1 != m + 1 || show) {
            s.append(m).append("分钟前");
        }

        return s.toString();
    }

    public static Date getDay(int n) throws ParseException{

        Calendar c = Calendar.getInstance();

        c.setTime(new Date());

        c.set(Calendar.HOUR_OF_DAY, 0);

        c.set(Calendar.MINUTE, 0);

        c.set(Calendar.SECOND, 0);

        c.add(Calendar.DATE, -n);

        String dateString = String.valueOf(new java.sql.Date(c.getTimeInMillis()));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        return sf.parse(dateString);

    }

}


