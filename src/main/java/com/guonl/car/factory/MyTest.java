package com.guonl.car.factory;

import java.io.IOException;
import java.util.Scanner;

public class MyTest {
	
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入供应商编号：");
		String supplierCode = scanner.nextLine();
		System.out.println("请输入车牌：");
		String carNo = scanner.nextLine();
		scanner.close();
		
		SupplierFactory factory = new SupplierFactory();
		ParkingSupplier supplier = factory.getSupplier(supplierCode);
		
		String parkingInfo = supplier.getParkingInfo(carNo);
		System.out.println(parkingInfo);
	}

}
