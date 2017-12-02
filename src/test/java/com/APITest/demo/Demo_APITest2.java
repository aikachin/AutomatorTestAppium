package com.APITest.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 16:38 2017/11/30 0030.
 * @Modified by :
 */
public class Demo_APITest2 {
    public static void main(String[] args) {
        String url = null;
        Demo_OutputUrlWithParam getUrl = new Demo_OutputUrlWithParam();
        url = getUrl.createUrl("invoke", "en", "zn-CHS");
        System.out.println(GetRequests(url, ""));
    }

    // Get请求
    public static String GetRequests(String url, String params) {
        // 定义返回结果
        String result = null;
        HttpClient client = HttpClients.createDefault();

        try {
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            // 获取请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void PostRequests(String url, Map<String, String> params) {
        HttpClient client = HttpClients.createDefault();
        String result = null;

        try {
            HttpPost post = new HttpPost(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
