package com.guonl.tingjiandan;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 类MD5Sign.java的实现描述：MD5签名和验签
 * 
 * @author leon 2016年10月10日 下午2:52:04
 */
public class MD5Sign {

    /**
     * 方法描述:将字符串MD5加码 生成32位md5码
     * 
     * @author leon 2016年10月10日 下午3:02:30
     * @param inStr
     * @return
     */
    public static String md5(String inStr) {
        try {
            return DigestUtils.md5Hex(inStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误");
        }
    }

    /**
     * 方法描述:签名字符串
     * 
     * @author leon 2016年10月10日 下午2:54:47
     * @param params 需要签名的参数
     * @param appSecret 签名密钥
     * @return
     */
    public static String sign(HashMap<String, Object> params, String appSecret) {
        StringBuilder valueSb = new StringBuilder();
        params.put("appSecret", appSecret);
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
        Set<Entry<String, Object>> entrys = sortParams.entrySet();
        // 遍历排序的字典,并拼接value1+value2......格式
        for (Entry<String, Object> entry : entrys) {
        	String key = entry.getKey();
        	String value = entry.getValue().toString();
            valueSb.append(key).append("=").append(value).append("&");
        }
        String valueSbString = valueSb.toString();
        System.out.println("格式化字符串：" + valueSbString.substring(0, valueSbString.length() - 1));
        params.remove("appSecret");
        return md5(valueSb.toString());
    }

    /**
     * 方法描述:验证签名
     * 
     * @author leon 2016年10月10日 下午2:31:23
     * @param appSecret 加密秘钥
     * @param request
     * @return
     * @throws Exception
     */
    public static boolean verify(String appSecret, HttpServletRequest request) throws Exception {

        String sign = request.getParameter("sign");
        if (sign == null) {
            throw new Exception(URLEncoder.encode("请求中没有带签名", "UTF-8"));
        }
        if (request.getParameter("timestamp") == null) {
            throw new Exception(URLEncoder.encode("请求中没有带时间戳", "UTF-8"));
        }

        HashMap<String, String> params = new HashMap<String, String>();

        // 获取url参数
        @SuppressWarnings("unchecked")
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paramName = enu.nextElement().trim();
            if (!paramName.equals("sign")) {
                // 拼接参数值字符串并进行utf-8解码，防止中文乱码产生
                params.put(paramName, URLDecoder.decode(request.getParameter(paramName), "UTF-8"));
            }
        }

        params.put("appSecret", appSecret);

        // 将参数以参数名的字典升序排序
        Map<String, String> sortParams = new TreeMap<String, String>(params);
        Set<Entry<String, String>> entrys = sortParams.entrySet();

        // 遍历排序的字典,并拼接value1+value2......格式
        StringBuilder valueSb = new StringBuilder();
        for (Entry<String, String> entry : entrys) {
        	
            valueSb.append(entry.getValue());
        }
        System.out.println("格式化字符串：" + valueSb.toString());

        String mysign = md5(valueSb.toString());
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }

    }
    
    
    public static void main(String[] args) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("service", "parkhub.car.register");
		map.put("carNum", "京RED001");
		map.put("carNumColor", "blue");
		map.put("version", "1.0");
		map.put("sign", "3347b109a1e44f3fd5baa78b74a84948");
		map.put("partner", "5836b8b52ada463ebc6199579f029566");
		map.put("timestamp", sdf.format(new Date()));
		map.put("charset", "utf-8");
		map.put("signType", "md5");
    	String sign = sign(map, "123");
    	System.out.println("签名：" + sign);
	}

}

