package com.guonl.car.factory;

public interface ParkingSupplier {
	
	public Boolean checkIsExist();//检查车辆是否存在
	
	public String getParkingInfo(String carNo);//获取停车信息
	
	public String notifySupplier(String carNo);//付款完成以后通知供应商

}
