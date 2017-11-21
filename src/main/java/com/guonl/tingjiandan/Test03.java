package com.guonl.tingjiandan;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;

import net.sf.json.util.JSONUtils;

public class Test03 {

	public String httpPostWithJson(String url, String json) {
		String returnValue = "这是默认返回值，接口调用失败";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			// 第一步：创建HttpClient对象
			httpClient = HttpClients.createDefault();

			// 第二步：创建httpPost对象
			HttpPost httpPost = new HttpPost(url);

			// 第三步：给httpPost设置JSON格式的参数
			StringEntity requestEntity = new StringEntity(json, "utf-8");
			requestEntity.setContentEncoding("UTF-8");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(requestEntity);

			// 第四步：发送HttpPost请求，获取返回值
			returnValue = httpClient.execute(httpPost, responseHandler); // 调接口获取返回值时，必须用此方法
			// CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
			// int statusCode = httpResonse.getStatusLine().getStatusCode();
			// if(statusCode!=200)
			// {
			// System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
			// returnValue = httpResonse.getStatusLine().toString();
			// }
			//

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 第五步：处理返回值
		return returnValue;
	}

	@Test
	public void test() {
		String url = "http://localhost:8080/mmall/user/login.do";
		Map<String, Object> map = new HashMap<>();
		JsonObject j = new JsonObject();
		j.addProperty("username", "admin");
		j.addProperty("password", "admin");
		System.out.println("参数信息" + j.toString());
		String httpPostWithJson = this.httpPostWithJson(url, j.toString());
		System.out.println(httpPostWithJson);
	}

	// 停简单参数问题
	// 测试车牌
	// 京RED001
	// 京RED002
	// 京RED003
	// 京RED004
	// 京RED005
	// 京RED006
	// 京RED006
	// 京RED007
	// 京RED008
	// 京RED009

	// 测试注册车牌
	// {
	// "service": "parkhub.car.register",
	// "carNum": "京 A45781",
	// "carNumColor": "blue",
	// "phone": "15210501514",
	// "outCarId": "45454545454",
	// "version":"1.0",
	// "sign":"3347b109a1e44f3fd5baa78b74a84948",
	// "partner":"5836b8b52ada463ebc6199579f029566",
	// "timestamp":"2016-05-26 11:30:10",
	// "charset":"utf-8",
	// "signType":"md5"
	// }
	@Test
	public void testRegister() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String url = "http://test.tingjiandan.com/openapi/gateway";
		String partner = "030c7ae618044c5bb9d2d94e9ac32862";
		Map<String, Object> map = new HashMap<>();
		map.put("service", "parkhub.car.register");
		map.put("carNum", "京RED001");
		map.put("carNumColor", "blue");
		map.put("version", "1.0");
		map.put("partner", "030c7ae618044c5bb9d2d94e9ac32862");
		map.put("timestamp", sdf.format(new Date()));
		map.put("charset", "utf-8");
		map.put("signType", "md5");
		String sign = this.sign(map);
		map.put("sign", sign);
		String p = JSON.toJSONString(map);
		System.out.println("参数：" + p);
		String response = this.httpPostWithJson(url, p);
		System.out.println(response);
	}

	public String sign(Map<String, Object> params) {
		StringBuilder valueSb = new StringBuilder();
		String signKey = "9fa0036fc46b42489dc8562901c02f7d";
		// 将参数以参数名的字典升序排序
		Map<String, Object> sortParams = new TreeMap<String, Object>(params);
		Set<Entry<String, Object>> entrys = sortParams.entrySet();
		// 遍历排序的字典,并拼接value1+value2......格式
		for (Entry<String, Object> entry : entrys) {
			String key = entry.getKey();
			if (!"signType".equals(key)) {
				String value = entry.getValue().toString();
				valueSb.append(key).append("=").append(value).append("&");
			}
		}
		String valueSbString = valueSb.toString();
		valueSbString = valueSbString.substring(0, valueSbString.length() - 1);
		valueSbString += signKey;
		System.out.println("签名：" + valueSbString);
		return md5(valueSbString);
	}

	public String md5(String inStr) {
		try {
			return DigestUtils.md5Hex(inStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误");
		}
	}

	// 查询费用接口
	@Test
	public void testQueryParkingInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String url = "http://test.tingjiandan.com/openapi/gateway";
		String partner = "030c7ae618044c5bb9d2d94e9ac32862";
		Map<String, Object> map = new HashMap<>();
		map.put("service", "parkhub.order.infoForFreeMins");
		map.put("carNum", "京RED001");
		map.put("version", "1.0");
		map.put("partner", "030c7ae618044c5bb9d2d94e9ac32862");
		map.put("timestamp", sdf.format(new Date()));
		map.put("freeMins", "60");
		map.put("charset", "utf-8");
		map.put("signType", "md5");
		String sign = this.sign(map);
		map.put("sign", sign);
		String p = JSON.toJSONString(map);
		System.out.println("参数：" + p);
		String response = this.httpPostWithJson(url, p);
		System.out.println(response);
	}

	// 费用通知接口
	// P150423210025090
	@Test
	public void testNotifySupplier() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String url = "http://test.tingjiandan.com/openapi/gateway";
		String partner = "030c7ae618044c5bb9d2d94e9ac32862";
		Map<String, Object> map = new HashMap<>();
		map.put("service", "parkhub.order.deductionNotSettle");
//		map.put("carNum", "京RED001");
		map.put("version", "1.0");
		map.put("partner", "030c7ae618044c5bb9d2d94e9ac32862");
		map.put("timestamp", sdf.format(new Date()));
		
		map.put("tradeId", "45c4368f39d149a6966f12311a9d4664");
		map.put("deductionAmount", "154.50");
		map.put("outTradeNo", "P150423210025090");
		map.put("accountId", "0d7e7652775c40eaac2e3bf93befe55e");
		
		map.put("charset", "utf-8");
		map.put("signType", "md5");
		String sign = this.sign(map);
		map.put("sign", sign);
		String p = JSON.toJSONString(map);
		System.out.println("参数：" + p);
		String response = this.httpPostWithJson(url, p);
		System.out.println(response);
	}

}
