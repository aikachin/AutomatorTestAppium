package com.example.base;

import com.google.common.base.Function;
import io.appium.java_client.android.AndroidDriver;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 17:00 2017/11/21 0021.
 * @Modified by :
 */
public interface ExpectedCondition<T> extends Function<AndroidDriver, T>{
}
