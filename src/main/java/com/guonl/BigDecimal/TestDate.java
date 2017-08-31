package com.guonl.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	
	public static void main(String[] args) throws ParseException {
		
		String str = "2017-08-10 00:00:00";
		Date test = test(str);
		System.out.println(test);
		
	}
	
	public static Date test(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		sdf.parse(str);
		return sdf.parse(str);
		
	}

}
