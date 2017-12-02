package com.APITest.demo;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 10:46 2017/11/30 0030.
 * @Modified by :
 */
public class Demo_OutputUrlWithParam {
    public static void main(String[] args) {
        System.out.println(createUrl("teacher", "en", "zh-CHS"));
    }
    static String appKeyID = "7743eee7f7e11d75";
    static String appKey = "bwPochuFLSutY4nGvyJoeUNn9GBQduzl";

    /**
     *  生成有道URL
     * @param query 要搜索的词
     * @param from 源语言
     * @param to 目标语言
     * @return
     */
    public static String createUrl(String query, String from, String to) {
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = md5(appKeyID +query +salt + appKey);
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKeyID);
        String urlStr = "https://openapi.youdao.com/api";
        return getUrlWithQueryString(urlStr, params);
    }

    public static String createParam(String query,String from,String to)
    {
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = md5(appKeyID + query + salt + appKey);
        Map<String, String> params = new HashMap<String,String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKeyID);

        String urlStr = "";
        return getUrlWithQueryString(urlStr, params);
    }

    /**
     *  生成32位md5摘要
     * @param str
     * @return
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = str.getBytes();
        try {
            /** 创建MD5摘要算法的 MessageDigest对象 **/
            MessageDigest mdInput = MessageDigest.getInstance("MD5");
            // 使用指定字节更新摘要
            mdInput.update(btInput);
            // 获得密文
            byte[] md = mdInput.digest();
            // 把密文转化成16进制的字符串形式
            int j = md.length;
            char chars[] = new char[j*2];
            int k = 0;
            for (byte byte0 : md) {
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把url和params合并
     * @param url
     * @param params
     * @return: java.lang.String
     */
    public static String getUrlWithQueryString(String url, Map<String, String>params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) {    // 过滤空key
                continue;
            }

            if (i != 0) {
                builder.append("&");
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }
        return builder.toString();
    }

    /**
     *  进行URL编码
     * @param input
     * @return
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }
}
