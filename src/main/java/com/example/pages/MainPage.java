package com.example.pages;

import com.example.base.PageAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 11:21 2017/11/22 0022.
 * @Modified by :
 */
public class MainPage extends PageAppium {

    // 底部导航菜单id
    public final String BOTTOM_MENU_ELEMENT_ID = "tab_itme_txt";
    // 退出弹框确认id
    public final String QUIT_CONFIRM_ID = "positive";
    // 退出弹框取消id
    public final String QUIT_CANCEL_ID = "negative";
    // 首页主activity
    public final String MAIN_ACTIVITY = "mian.MainFragementActivity";
    // // 启动activity
    // public final String SPLASH_ACTIVITY = "SplashActivity";



    public MainPage (AndroidDriver driver) {
        super(driver);
    }

    // 取得底部菜单栏控件
    public AndroidElement switchToMenu(int index) {
        return getListOneElementById(BOTTOM_MENU_ELEMENT_ID, index);
    }

    // 取得退出APP时提示框的确认按钮
    public AndroidElement getQuitConfirm() {
        return waitAutoById(QUIT_CONFIRM_ID);
    }

    // 取得退出APP时提示框的取消按钮
    public AndroidElement getQuitCancel() {
        return waitAutoById(QUIT_CANCEL_ID);
    }

    // public String getSplashActivity() {
    //     return SPLASH_ACTIVITY;
    // }

    public String getMainActivity() {
        return MAIN_ACTIVITY;
    }

    public boolean isMainActivity() {
        return getCurrActivity().equals(getMainActivity());
    }

}
