package com.guonl.regex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestDate {
	
	
	@Test
	public void test(){
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		
		String date1 = "2017-08-30 23:59:59";
		String date2 = "2017-08-30";
		
		try {
			Date parse1 = sdf1.parse(date1);
			System.out.println(parse1);
			System.out.println(sdf1.format(parse1));
			String format = sdf3.format(new Date());
			System.out.println(sdf3.format(new Date()));
			System.out.println(sdf1.parse(format));
			System.out.println(date1.substring(0, 10));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
