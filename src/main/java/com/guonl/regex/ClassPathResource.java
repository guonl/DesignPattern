package com.guonl.regex;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @ClassName: ClassPathResource
 * @Description: 校验手机号
 * @author guonl
 * @date 2017年8月15日 下午5:08:17
 * @version V1.0
 *
 */
public class ClassPathResource {

	public Boolean isMobileNO(String mobiles) {

		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();
	}

	public Boolean isMobileNO2(String mobiles) {

		String regExp = "^1(3|4|5|7|8)/d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		System.out.println(m.find() + "---");
		return m.find();// boolean
	}
	
	public void calendar(){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		System.out.println(calendar.getTime());
		calendar.add(Calendar.MONTH, -1);
		System.out.println(calendar.getTime());
	}
	
	public Boolean checkCarNo(String carNo){
//		^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{1}-[A-Z]{1}[A-Z_0-9]{5}$");
		Matcher m = p.matcher(carNo.toUpperCase());
		System.out.println(m.matches());
		System.out.println(carNo.toUpperCase());
		return m.matches();
	}
	
	
	@Test
	public void test(){
//		this.isMobileNO("18221825926");
//		this.isMobileNO2("18221825926");
//		this.calendar();
		this.checkCarNo("沪-a12g45");
	}

}
