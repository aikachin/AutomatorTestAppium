package com.example.pages;

import com.example.base.PageAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 17:22 2017/11/22 0022.
 * @Modified by :
 */
public class MyPage extends PageAppium {

    // 昵称
    public final String NICK_NAME_ID = "usename";
    // 设置选项文字“设置”
    public final String SETTING_TEXT = "设置";   // android.widget.TextView
    // 底部导航菜单id
    public final String BOTTOM_MENU_ELEMENT_ID = "tab_itme_txt";

    public MyPage(AndroidDriver driver) {
        super(driver);
    }

    public AndroidElement getNickName() {
        return waitAutoById(NICK_NAME_ID);
    }

    public AndroidElement getSetting() {
        return getViewbyUitext(SETTING_TEXT);
    }

    // 取得底部菜单栏控件
    public AndroidElement switchToMenu(int index) {
        return getListOneElementById(BOTTOM_MENU_ELEMENT_ID, index);
    }

}
