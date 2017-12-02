package com.APITest.demo;


import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 11:26 2017/11/30 0030.
 * @Modified by :
 */
public class Demo_GetPasswordMD5 {
    public static void main(String[] args) {
        System.out.println(getPwdByMD5("a123456"));
        System.out.println(md5("a123456"));
    }

    public static String getPwdByMD5(String pwd) {
        String salt = String.valueOf(System.currentTimeMillis());
        System.out.println(salt);
        String originStr = md5(pwd + salt);

        Map<String, String> params = new HashMap<String, String>();
        params.put("password", pwd);
        return originStr;
    }

    public static String md5(String originStr) {
        if (originStr == null) {
            return null;
        }
        char hexDigest[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // 获得要加密的字节数组
        byte[] btInput = originStr.getBytes();
        try {
            // 创建MD5摘要算法的MessageDigest对象
            MessageDigest mdInput = MessageDigest.getInstance("MD5");
            // 更新摘要对象为要加密的字节数组
            mdInput.update(btInput);
            // 获得密文
            byte[] mdBytes = mdInput.digest();
            // 把密文转化为16进制字符串
            int i = mdBytes.length;
            char[] str = new char[i * 2];
            int k = 0;
            for (byte byte0 : mdBytes) {
                str[k++] = hexDigest[byte0 >>> 4 & 0xf];
                str[k++] = hexDigest[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
