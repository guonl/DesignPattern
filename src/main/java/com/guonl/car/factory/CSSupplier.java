package com.guonl.car.factory;

/**
 * 
 * @ClassName: JSSupplier 
 * @Description: 捷顺供应商
 * @author guonl
 * @date 2017年8月3日 上午10:46:32 
 * @version V1.0   
 *
 */
public class CSSupplier implements ParkingSupplier {

	@Override
	public Boolean checkIsExist() {
		return true;
	}

	@Override
	public String getParkingInfo(String carNo) {
		
		return "get parking car info  **测试** " + carNo;
	}

	@Override
	public String notifySupplier(String carNo) {
		return "notify to supplier **测试** " + carNo;
	}

}
