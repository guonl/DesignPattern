package com.guonl.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestLogin {

	@Test
	public void testLogin() throws ClientProtocolException, IOException {

		// 接口地址
//		String url = "http://localhost:8080/mmall/user/login.do";
		String url = "http://www.jslife.com.cn/jsaims/login";
		// 客户号
//		String cid = "00001";
		// 帐号
		String username = "admin";
		// 密码
		String password = "123";

		// 构造参数
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		ArrayList<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("password", password));
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
