package com.example.base;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 12:48 2017/11/21 0021.
 * @Modified by :
 */
public class Assertion {
    public static boolean flag = true;
    public static List<Error> errors = new ArrayList<Error>();

    /**
     * @Description: 验证值是否相等
     * @param actual 第一个值
     * @param expected 要对比的值
     * @return: void
     * @Author: Aikachin
     * @Date: 13:06 2017/11/21 0021
     */
    public static void verifyEquals(Object actual, Object expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Error e) {
            errors.add(e);
            flag = false;
        }
    }

    /**
     * @Description: 验证值是否相等
     * @param actual 第一个值
     * @param expected 要对比的值
     * @param message 出错时候的提示消息
     * @return: void
     * @Author: Aikachin
     * @Date: 13:08 2017/11/21 0021
     */
    public static void verifyEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (Error e) {
            errors.add(e);
            flag = false;
        }
    }
}
