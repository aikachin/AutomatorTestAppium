package com.APITest.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 14:51 2017/11/30 0030.
 * @Modified by :
 */
public class Demo_APITest {
    public static void main(String[] args) {
        new Get().start();
        System.out.println(33333333);
    }


}

class Get extends Thread {
    HttpClient client = HttpClients.createDefault();


        static Demo_OutputUrlWithParam getUrl = new Demo_OutputUrlWithParam();
        static String url = getUrl.createUrl("method", "en", "zh-CHS");


    @Override
    public void run() {
        String urlStr = url;
        HttpGet get = new HttpGet(urlStr);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(123123);
    }


}
