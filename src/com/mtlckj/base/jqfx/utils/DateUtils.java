package com.mtlckj.base.jqfx.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	 
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("当天24点时间：" + getTimesnight().toLocaleString());
		System.out.println("当天0点时间：" + getTimesmorning().toLocaleString());
		System.out.println("昨天0点时间：" + getYesterdaymorning().toLocaleString());
		System.out.println("近7天时间：" + getWeekFromNow().toLocaleString());
		
		System.out.println("本周开始时间：" + getThisWeekStart().toLocaleString());
		System.out.println("本周结束时间：" + getThisWeekEnd().toLocaleString());
		System.out.println("本月开始时间：" + getThisMonthStart().toLocaleString());
		System.out.println("本月结束时间：" + getThisMonthEnd().toLocaleString());
		
		System.out.println("本季度开始点时间：" + getThisQuarterStart().toLocaleString());
		System.out.println("本季度结束点时间：" + getThisQuarterEnd().toLocaleString());
		
		System.out.println("本年开始点时间：" + getThisYearStart().toLocaleString());
		System.out.println("本年结束点时间：" + getThisYearEnd().toLocaleString());
		
		System.out.println("上周开始时间：" + getLastWeekStart().toLocaleString());
		System.out.println("上周结束时间：" + getLastWeekEnd().toLocaleString());
		
		System.out.println("上月开始时间：" + getLastMonthStart().toLocaleString());
		System.out.println("上月结束时间：" + getLastMonthEnd().toLocaleString());
		
		System.out.println("上季度开始时间：" + getLastQuarterStart().toLocaleString());
		System.out.println("上季度结束时间：" + getLastQuarterEnd().toLocaleString());

		System.out.println("上年开始时间：" + getLastYearStartTime().toLocaleString());
		System.out.println("上年结束时间：" + getLastYearEndTime().toLocaleString());
		
		System.out.println("上年本月开始时间：" + getLastYearMonthStart().toLocaleString());
		System.out.println("上年本月结束时间：" + getLastYearMonthEnd().toLocaleString());
		
		System.out.println("上年本季度开始时间：" + getLastYearQuarterStart().toLocaleString());
		System.out.println("上年本季度结束时间：" + getLastYearQuarterEnd().toLocaleString());
		
		System.out.println("上年本周开始时间：" + getLastYearWeekStartTime().toLocaleString());
		System.out.println("上年本周开始时间：" + getLastYearWeekEndTime().toLocaleString());

		
		System.out.println("上上周开始时间：" + getBeforeLastWeekStart().toLocaleString());
		System.out.println("上上周结束时间：" + getBeforeLastWeekEnd().toLocaleString());

		System.out.println("当天是本年几周：" + getWeekNumber());
		
		System.out.println("获得去年根据周数的开始时间：" + getLastYearWeekStartByNumber().toLocaleString());
		System.out.println("获得去年根据周数的结束时间：" + getLastYearWeekEndByNumber().toLocaleString());
		
		System.out.println("根据传入时间获得上周开始时间：" + getLastWeekIntervalStart("2018-10-01").toLocaleString());
		System.out.println("根据传入时间获得上周结束时间：" + getLastWeekIntervalEnd(getLastWeekIntervalStart("2018-10-01")).toLocaleString());
		
		String s = "2018-10-11 - 2018-10-12";
		String[] str = s.split("-");
		String startDate = str[0]+"-"+str[1]+"-"+str[2]+"00:00:00";
		String endDate = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		System.out.println(startDate.trim());
		System.out.println(endDate.trim());
		
		String ss = "AsdfDD";
		/*for (int i = 0; i < str.length; i++) {
			if(!ss.substring(i,i+1).equals(ss.substring(i,i+1).toLowerCase())){
				ss.substring(i,i+1).toLowerCase();
			}
		}*/
	//	ss = ss.toLowerCase();
		System.out.println(ss);

	}
	
	
	
	
	
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		return sdf.format(date);     
	}
	
	public static String DateToString(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");    
		return sdf.format(date);     
	}
	
	
 
	/**
	 * 获得当天0点时间
	 * @return
	 */
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
 
 
	}
	/**
	 * 获得昨天0点时间
	 * @return
	 */
	public static Date getYesterdaymorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimesmorning().getTime()-3600*24*1000);
		return cal.getTime();
	}
	
	/**
	 * 获得当天近7天时间
	 * @return
	 */
	public static Date getWeekFromNow() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( getTimesmorning().getTime()-3600*24*1000*7);
		return cal.getTime();
	}
 
	/**
	 *  获得当天24点时间
	 * @return
	 */
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
 
	/**
	 *  获得本周开始时间
	 * @return
	 */
	public static Date getThisWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
 
	
	/**
	 *  获得本周结束时间
	 * @return
	 */
	public static Date getThisWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekStart());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
 
	/**
	 *  获得本月开始时间
	 * @return
	 */
	public static Date getThisMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
 
	/**
	 *  获得本月结束时间
	 * @return
	 */
	public static Date getThisMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
 
	/**
	 *  获得本季度开始时间
	 * @return
	 */
	public static Date getThisQuarterStart() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
 
	/**
	 *  获得本季度结束时间
	 * @return
	 */
	public static Date getThisQuarterEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisQuarterStart());
		cal.add(Calendar.MONTH, 3);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
 
	/**
	 * 获得本年度开始时间
	 * @return
	 */
	public static Date getThisYearStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		return getDayStartTime(cal.getTime());
	}
	
	/**
	 * 获得本年度结束时间
	 * @return
	 */
	public static Date getThisYearEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisYearStart());
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	/**
	 *  获得上周开始时间
	 * @return
	 */
	public static Date getLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekStart());
		cal.add(Calendar.DAY_OF_WEEK, -7);
		return cal.getTime();
	}
	
	/**
	 *  获得上周结束时间
	 * @return
	 */
	public static Date getLastWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastWeekStart());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	/**
	 * 获得上月开始时间
	 * @return
	 */
	public static Date getLastMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisMonthStart());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}
	
	/**
	 * 获得上月结束时间
	 * @return
	 */
	public static Date getLastMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastMonthStart());
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	/**
	 * 获得上季度开始时间
	 * @return
	 */
	public static Date getLastQuarterStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisQuarterStart());
		cal.add(Calendar.MONTH, -3);
		return cal.getTime();
	}
	
	/**
	 * 获得上季度结束时间
	 * @return
	 */
	public static Date getLastQuarterEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastQuarterStart());
		cal.add(Calendar.MONTH, 3);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
 
	/**
	 * 获得上一年开始时间
	 * @return
	 */
	public static Date getLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisYearStart());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
	
	/**
	 * 获得上一年结束时间
	 * @return
	 */
	public static Date getLastYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastYearStartTime());
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	
	/**
	 *  获得去年本月开始时间
	 * @return
	 */
	public static Date getLastYearMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
 
	/**
	 *  获得去年本月结束时间
	 * @return
	 */
	public static Date getLastYearMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, -1);
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
	
	
	/**
	 *  获得去年本季度开始时间
	 * @return
	 */
	public static Date getLastYearQuarterStart() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			c.add(Calendar.YEAR, -1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
 
	/**
	 *  获得去年本季度结束时间
	 * @return
	 */
	public static Date getLastYearQuarterEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastYearQuarterStart());
		cal.add(Calendar.MONTH, 3);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	
	
	
	/**
	 *  获得去年本周开始时间
	 * @return
	 */
	public static Date getLastYearWeekStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	
	/**
	 *  获得去年本周结束时间
	 * @return
	 */
	public static Date getLastYearWeekEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekEnd());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
	
	/**
	 *  获得上上周开始时间
	 * @return
	 */
	public static Date getBeforeLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastWeekStart());
		cal.add(Calendar.DAY_OF_WEEK, -7);
		return cal.getTime();
	}
	
	/**
	 *  获得上上周结束时间
	 * @return
	 */
	public static Date getBeforeLastWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeforeLastWeekStart());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	/**
	 * 获取某个日期的开始时间
	 * @return
	 */
	public static Timestamp getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if(null != d){
			calendar.setTime(d);
		}
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 获取今年是哪一年
	 * @return
	 */
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}
	
	/**
	 * 获得当前时间是本年的第几周
	 * @return
	 */
	public static Integer getWeekNumber() {
		Calendar cal = Calendar.getInstance();
		int i = cal.get(Calendar.WEEK_OF_YEAR);
		return i;
	}
	
	/**
	 * 获得去年根据周数的开始时间
	 * @return
	 */
	public static Date getLastYearWeekStartByNumber() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR,-1);
		cal.set(Calendar.WEEK_OF_YEAR, getWeekNumber());
		cal.set(Calendar.DAY_OF_WEEK, 2);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		return cal.getTime();
	}
	
	
	/**
	 * 获得去年根据周数的结束
	 * @return
	 */
	public static Date getLastYearWeekEndByNumber() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastYearWeekStartByNumber());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}
	
	/**
	 * 根据所传时间获取传入时间对应的上周开始时间
	 * @param date
	 * @return
	 */
	public static Date getLastWeekIntervalStart(String date) {  
        Calendar cal = Calendar.getInstance();  
        Date result = null;
        try {
			cal.setTime(sdf.parse(date));
	        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	        if (1 == dayWeek) {  
	           cal.add(Calendar.DAY_OF_MONTH, -1);  
	        }  
	        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	        cal.setFirstDayOfWeek(Calendar.MONDAY);  
	        // 获得当前日期是一个星期的第几天  
	        int day = cal.get(Calendar.DAY_OF_WEEK);  
	        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
	        cal.add(Calendar.DAY_OF_WEEK,-7);
	        String imptimeBegin = sdf.format(cal.getTime());
	        result = sdf.parse(imptimeBegin);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return result;
   }
	
	
	/**
	 * 根据所传时间获取传入时间对应的上周结束事件
	 * @param date
	 * @return
	 */
	public static Date getLastWeekIntervalEnd(Date date) {  
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK,7);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();  
   }
	
	/**
	 * 根据传入时间获得该时间是第几周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 获取当前时间所在年份的最大周数
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}
	
	/**
	 * 获取某年的第几周的开始日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getStartDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getFirstDayOfWeek(cal.getTime());
	}
	
	/**
	 * 获取某年的第几周的结束日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getEndDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY,23);
		c.set(Calendar.MINUTE,59);
		c.set(Calendar.SECOND,59);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getLastDayOfWeek(cal.getTime());
	}
	
	/**
	 * 获取当前时间所在周的开始日期
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}
	
	/**
	 * 获取当前时间所在周的结束日期
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	
	/**
	 * 根据传入时间获得时间差
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDifferenceDay(String startTime,String endTime) {
		int day = 0;
		try {
			long from = sdf.parse(startTime).getTime();
			long to = sdf.parse(endTime).getTime();
			day = (int) ((to - from)/(1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day;

	}
	
	
	/**
	 * 根据传入时间和时间差返回之前时间（环比）
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForRing(String date,int day) {
        Calendar cal = Calendar.getInstance();
        try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cal.add(Calendar.DAY_OF_YEAR,-day);//日期加10天
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 根据传入时间和时间差返回之前时间（同比）
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForWith(String date,int day) {
        Calendar cal = Calendar.getInstance();
        try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cal.add(Calendar.YEAR, -1); 
        cal.add(Calendar.DAY_OF_YEAR,-day);
		return sdf.format(cal.getTime());
	}
	
	public static Map<String,Object> getLastFirstYearMonth(String startDateString){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map=new HashMap<>();
		try {
			cal.setTime(formt.parse(startDateString));
			cal.set(Calendar.DAY_OF_MONTH, 21);
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date endTime=cal.getTime();
			map.put("endTime", formt.format(endTime));
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			Date startTime=cal.getTime();
			map.put("startTime", formt.format(startTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String,Object> getLastSecendYearMonth(String startDateString){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map=new HashMap<>();
		try {
			cal.setTime(formt.parse(startDateString));
			cal.set(Calendar.DAY_OF_MONTH, 21);
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-2);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date endTime=cal.getTime();
			map.put("endTime", formt.format(endTime));
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			Date startTime=cal.getTime();
			map.put("startTime", formt.format(startTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	public static Map<String,Object> getLastThirdYearMonth(String startDateString){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map=new HashMap<>();
		try {
			cal.setTime(formt.parse(startDateString));
			cal.set(Calendar.DAY_OF_MONTH, 21);
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-3);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date endTime=cal.getTime();
			map.put("endTime", formt.format(endTime));
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			Date startTime=cal.getTime();
			map.put("startTime", formt.format(startTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static int getDateSubtract(String startTime,String endTime){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BigDecimal result=BigDecimal.ZERO;
		
		try {
			Date start = format.parse(startTime);
			Date end = format.parse(endTime);
			BigDecimal startDecimal=new BigDecimal(start.getTime());
			BigDecimal endDecimal = new BigDecimal(end.getTime());
			BigDecimal divider = new BigDecimal(1000*3600*24);
			result=(endDecimal.subtract(startDecimal)).divide(divider,0,BigDecimal.ROUND_HALF_DOWN);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.intValue();
	}
	
	
	
}
