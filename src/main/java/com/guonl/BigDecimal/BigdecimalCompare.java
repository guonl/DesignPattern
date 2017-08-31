package com.guonl.BigDecimal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BigdecimalCompare {
	
	public static void main(String[] args) {
		
		BigDecimal a = BigDecimal.valueOf(2.0);
		BigDecimal b = BigDecimal.valueOf(8);
		if(a.compareTo(b) == 0){
			System.out.println("a = b");
		}else if(a.compareTo(b) == -1){
			System.out.println("a < b");
		}else if(a.compareTo(b) == 1){
			System.out.println("a > b");
		}
	}
	
	@Test
	public void testBigdecimal(){
		
		BigDecimal actualCash = BigDecimal.valueOf(0.00);
		int score = 9000;
		int Max = score / 3000;
		
		BigDecimal div = BigDecimalUtil.div(actualCash.doubleValue(), 4);
		System.out.println("MAX:" + Max);
		System.out.println("div:" + div);
		if(div.compareTo(BigDecimal.valueOf(Max)) == 1 || div.compareTo(BigDecimal.valueOf(Max)) == 0){
			actualCash = BigDecimalUtil.sub(actualCash.doubleValue(), Max * 4);
			System.out.println("actualCash:" + actualCash);
		}
		else if (div.compareTo(BigDecimal.valueOf(Max)) == -1){
			int ceil = (int) Math.ceil(div.doubleValue());
			System.out.println("reduceScore:" + ceil * 3000);
			System.out.println("ceil:" + ceil);
			System.out.println("actualCash:" + actualCash);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("01", "123");
		map.put("02", "456");
		String ss = (String) map.get("03");
		System.out.println(ss);
		
	}
	
     

     

}
