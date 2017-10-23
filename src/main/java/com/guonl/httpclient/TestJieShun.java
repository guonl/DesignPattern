package com.guonl.httpclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestJieShun {

	@Test
	public void testLogin() throws Exception {

		// 接口地址
		String url = "http://www.jslife.com.cn/jsaims/login";
		// 客户号
		String cid = "880002101003628";
		// 帐号
		String usr = "880002101003628";
		// 密码
		String psw = "888888";

		// 构造参数
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", cid));
		list.add(new BasicNameValuePair("usr", usr));
		list.add(new BasicNameValuePair("psw", psw));
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

}
