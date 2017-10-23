package com.guonl.jieshun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

public class TestFunction {
	// 接口地址
	public static final String url = "http://www.jslife.com.cn/jsaims/as";
	// 客户号
	public static final String cid = "880002101003628";
	// 接口版本号
	public static final String v = "2";
	// 签名signKey
	public static final String signKey = "38ea90b3a0760b42111b83cfd1cf8135";

	// 查询停车位置（暂时先不要使用，不一定是有数据的）
	public static void main(String[] args) throws Exception {

		// 参数
		RequestPara para = new RequestPara();
		String carNo = "沪-GNL233";
		para.setServiceId("3c.park.querycarparkingspot");
		para.setRequestType("DATA");
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("parkCode", "0000003729");
		attributes.put("carNo", carNo);
		para.setAttributes(attributes);
		String p = JSON.toJSONString(para);
		// 生成MD5签名
		MessageDigest md5Tool = MessageDigest.getInstance("MD5");
		byte[] md5Data = md5Tool.digest((p + signKey).getBytes("UTF-8"));
		String sn = toHexString(md5Data);

		// 构造参数
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", cid));
		list.add(new BasicNameValuePair("v", v));
		list.add(new BasicNameValuePair("p", p));
		list.add(new BasicNameValuePair("sn", sn));
		list.add(new BasicNameValuePair("tn", "a9ed27464fa14aeda4e430a069f881921505180674163"));
		HttpEntity en = new UrlEncodedFormEntity(list, HTTP.UTF_8);
		post.setEntity(en);

		// 发送消息和处理结果
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.println("执行成功！");
			String s = EntityUtils.toString(response.getEntity());
			System.out.println(s);
		} else {
			System.out.println("执行失败！");
		}
	}

	// 生成签名
	private static String toHexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(String.format("%02X", bytes[i]));
		}
		return buffer.toString();
	}

	// 生成订单
	@Test
	public void testGetParkingOrder() throws Exception {
		// 参数
		RequestPara para = new RequestPara();
		String carNo = "沪-AQ1234";
		para.setServiceId("3c.pay.createorderbycarno");
		para.setRequestType("DATA");
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("businesserCode", "880002101003628");
		attributes.put("parkCode", "0000003729");
		attributes.put("orderType", "VNP");
		attributes.put("carNo", carNo);
		para.setAttributes(attributes);
		String p = JSON.toJSONString(para);

		// 生成MD5签名
		MessageDigest md5Tool = MessageDigest.getInstance("MD5");
		byte[] md5Data = md5Tool.digest((p + signKey).getBytes("UTF-8"));
		String sn = toHexString(md5Data);

		// 构造参数
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", cid));
		list.add(new BasicNameValuePair("v", v));
		list.add(new BasicNameValuePair("p", p));
		list.add(new BasicNameValuePair("sn", sn));
		list.add(new BasicNameValuePair("tn", "434e449fb5474703aa4f2ec546cbf92b1507772839940"));
		HttpEntity en = new UrlEncodedFormEntity(list, HTTP.UTF_8);
		post.setEntity(en);

		// 发送消息和处理结果
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.println("执行成功！");
			String s = EntityUtils.toString(response.getEntity());
			System.out.println(s);
		} else {
			System.out.println("执行失败！");
		}

	}

	// 支付成功以后通知
	@Test
	public void testNotifyOrderResult() throws Exception {
		// 参数
		RequestPara para = new RequestPara();
		String orderNo = "BK74b170003ca34977a34f7c1399f192";// 捷顺的订单号
		para.setServiceId("3c.pay.notifyorderresult");
		para.setRequestType("DATA");
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("orderNo", orderNo);
		attributes.put("tradeStatus", 0);
		attributes.put("isCallBack", 0);
		attributes.put("notifyUrl", "http://www.test.com.cn/jsaims/callBackUrl.Servlet");
		para.setAttributes(attributes);
		String p = JSON.toJSONString(para);

		// 生成MD5签名
		MessageDigest md5Tool = MessageDigest.getInstance("MD5");
		byte[] md5Data = md5Tool.digest((p + signKey).getBytes("UTF-8"));
		String sn = toHexString(md5Data);
		System.out.println("签名信息：" + sn);

		// 构造参数
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", cid));
		list.add(new BasicNameValuePair("v", v));
		list.add(new BasicNameValuePair("p", p));
		list.add(new BasicNameValuePair("sn", sn));
		list.add(new BasicNameValuePair("tn", "82976e4cb0444ff1a632d4f2e41518871505698481879"));
		HttpEntity en = new UrlEncodedFormEntity(list, HTTP.UTF_8);
		post.setEntity(en);

		// 发送消息和处理结果
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.println("执行成功！");
			String s = EntityUtils.toString(response.getEntity());
			System.out.println(s);
		} else {
			System.out.println("执行失败！");
		}

	}
	
	// 签名解密
	@Test
	public void testDecodeSn(){
		String sn = "0893EF24A2DAEAF53B3758B1BF16352C";
		
	}
	

	// 获取json里面的参数值
	@Test
	public void testJsonToObject() {
		String json = "{resultCode: 0,token: \"163a7817e14d40f688e8bac49bfcda901505115767393\"}";
		JSONObject obj = JSONObject.fromObject(json);
		String token = obj.getString("token");
		int resultCode = obj.getInt("resultCode");
		System.out.println(token);
		System.out.println(resultCode);
	}

	@Test
	public void testJsonToBean() {
		// {"attributes":{"orderType":"VNP","carNo":"沪-GNL233","parkCode":"0000003729","businesserCode":"880002101003628"},"requestType":"DATA","serviceId":"3c.pay.createorderbycarno"}
		String json = "{\"attributes\":{\"orderType\":\"VNP\",\"carNo\":\"沪-GNL233\",\"parkCode\":\"0000003729\",\"businesserCode\":\"880002101003628\"},\"requestType\":\"DATA\",\"serviceId\":\"3c.pay.createorderbycarno\"}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		RequestPara para = (RequestPara) JSONObject.toBean(jsonObject, RequestPara.class);
		System.out.println(para.getServiceId());
		System.out.println(para.getRequestType());
		System.out.println(para.getAttributes());
	}
	
	@Test
	public void testFreeHour(){
		
		int hour1 = 30 / 60;
		int hour2 = 60 / 60;
		int hour3 = 90 / 60;
		System.out.println(hour1);
		System.out.println(hour2);
		System.out.println(hour3);
		
	}
	

}
