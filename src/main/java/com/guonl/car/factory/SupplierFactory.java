package com.guonl.car.factory;

public class SupplierFactory {
	
	//根据code获取供应商
	public ParkingSupplier getSupplier(String supplierCode) {
		if (supplierCode == null) {
			return null;
		}
		if (supplierCode.equalsIgnoreCase("JIESHUN")) {
			return new JSSupplier();
		} else if (supplierCode.equalsIgnoreCase("CESHI")) {
			return new CSSupplier();
		}
		return null;

	}

}
