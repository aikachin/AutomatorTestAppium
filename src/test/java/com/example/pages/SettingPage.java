package com.example.pages;

import com.example.base.PageAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 11:22 2017/11/28 0028.
 * @Modified by :
 */
public class SettingPage extends PageAppium {
    public final String SETTING_ACTIVITY = "SettingActivity";

    public final String LOGOUT_BUTTON_ID = "exit";

    public SettingPage(AndroidDriver driver) {
        super(driver);
    }

    public String getSettingActivity() {
        return SETTING_ACTIVITY;
    }


    public AndroidElement getLogoutButton() {
        return waitAutoById(LOGOUT_BUTTON_ID);
    }
}
