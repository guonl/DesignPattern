package com.guonl.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DateCommonUtils {

	public static String getTheDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateStr = sd.format(new Date());
		print("*******得到今天的日期*******" + dateStr);
		return dateStr;
	}

	public static Map getWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", df.format(cal.getTime()));
		System.out.println("星期一：" + cal.getTime());
		print("********得到本周一的日期*******" + df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		map.put("sun", df2.format(cal.getTime()));
		System.out.println("星期天：" + cal.getTime());
		print("********得到本周天的日期*******" + df2.format(cal.getTime()));
		return map;
	}

	public static Map getMonthDate() {
		Map<String, String> map = new HashMap<String, String>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("monthF", format.format(calendar.getTime()));
		print("*********得到本月的最小日期**********" + format.format(calendar.getTime()));
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		// 打印
		map.put("monthL", format.format(calendar.getTime()));
		print("*********得到本月的最大日期**********" + format.format(calendar.getTime()));
		return map;
	}

	private static void print(Object o) {
		System.out.println(o.toString());
	}
	
	@Test
	public void testDate(){
		String theDate = DateCommonUtils.getTheDate();
		System.out.println(theDate);
		
		DateCommonUtils.getWeekDay();
		
//		DateCommonUtils.getMonthDate();
	}

}
