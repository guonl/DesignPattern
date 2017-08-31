package com.guonl.BigDecimal;

import java.math.BigDecimal;
import org.junit.Test;

public class TestBigDecimal {
	
	public class TestVO {
		
		private String name;
		
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
	
	
	@Test
	public void test(){
		
		
		int v1 = 1;
		double v2 = 5;
		
		System.out.println(BigDecimalUtil.add(v1, v2));
		System.out.println(BigDecimalUtil.mul(v1, v2));
		System.out.println(BigDecimalUtil.sub(v1, v2));
		
	}
	
	
	@Test
	public void testInt(){
		
		TestVO testVO = new TestVO();
		testVO.setName("guonl");
		
		
		int age = testVO.getAge();
		System.out.println(age);
		
	}
	
	
	
	
	

}
