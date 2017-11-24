package com.example.pages;

import com.example.base.PageAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 16:20 2017/11/23 0023.
 * @Modified by :
 */
public class SplashPage extends PageAppium{
    // 启动activity
    public final String SPLASH_ACTIVITY = "SplashActivity";
    // 跳过启动activity
    public final String SKIP_ID = "skip";
    // 启动activity广告
    public final String SPLASH_ADVERTISEMENT_ID = "advertisement";


    public SplashPage(AndroidDriver driver) {
        super(driver);
    }

    public String getSplashActivity() {
        return SPLASH_ACTIVITY;
    }

    public AndroidElement getSkipBtn() {
        return waitAutoById(SKIP_ID);
    }

    public AndroidElement getSplashAd() {
        return waitAutoById(SPLASH_ADVERTISEMENT_ID);
    }

}
