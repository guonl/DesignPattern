package com.guonl.tingjiandan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class SendHttpPostUtil {
	protected static Logger logger = Logger.getLogger(SendHttpPostUtil.class);
	private static HttpClient hc = new DefaultHttpClient(); // 初始化一个HTTP的客户端对象
	// 功能: Get形式发送请求
	// @param url 请求地址
	// @param params 请求中传递的参数
	// @return

	@SuppressWarnings("deprecation")
	public static String get(String url, List params) {
		String body = null;
		try {
			// Get请求
			HttpGet httpget = new HttpGet(url);
			// 设置参数
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
			httpget.setURI(new java.net.URI(httpget.getURI().toString() + "?" + str));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httpget);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			if (entity != null) {
				entity.consumeContent();
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return body;
	}

	// 功能: Post形式发送请求
	// @param url 请求地址
	// @param params 请求中传递的参数
	// @return
	public static int post(String url, List params) {
		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			return httpresponse.getStatusLine().getStatusCode();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return -1;
	}

	// 功能: postBody形式发送数据
	// @param urlPath 对方地址
	// @param json 要传送的数据
	// @return
	// @throws Exception
	public static int postBody(String urlPath, String json) throws Exception {
		try {
			// Configure and open a connection to the site you will send the request
			URL url = new URL(urlPath);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			// 设置doOutput属性为true表示将使用此urlConnection写入数据
			urlConnection.setDoOutput(true);
			// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
			urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//			urlConnection.setRequestProperty("content-type", "application/json");
			// 得到请求的输出流对象
			OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			// 把数据写入请求的Body
			out.write(json);
			out.flush();
			out.close();

			// 从服务器读取响应
			InputStream inputStream = urlConnection.getInputStream();
			String encoding = urlConnection.getContentEncoding();
			String body = IOUtils.toString(inputStream, encoding);
			System.out.println(body);
			if (urlConnection.getResponseCode() == 200) {
				return 200;
			} else {
				throw new Exception(body);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	@Test
	public void testPost() {
		try {
			SendHttpPostUtil.postBody("http://localhost:8080/mmall/user/login.do",
					"{'username':'admin', 'password':admin}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
