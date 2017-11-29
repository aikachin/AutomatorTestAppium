package com.example.cases;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 14:05 2017/11/22 0022.
 * @Modified by :
 */
public class test1 {

    public static void main(String[] args) {
        String a = "1234.5.678";
        String str = a.substring(a.lastIndexOf(".") + 1);
        System.out.println(str);    // 输出678
    }
}
