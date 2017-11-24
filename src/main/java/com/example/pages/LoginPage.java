package com.example.pages;

import com.example.base.PageAppium;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 10:56 2017/11/22 0022.
 * @Modified by :
 */
public class LoginPage extends PageAppium{
    // public final String PHONE_ID = "phone";
    // public final String PASSWORD_ID = "pass";

    // 登录按钮
    public final String LOGIN_BUTTON_ID = "login";
    // 账号密码控件
    public final String PHONE_PASS_ELEMENT_CLASS = "android.widget.EditText";
    // 登录activity
    public final String LOGIN_ACTIVITY = "LoginActivity";
    // 取消按钮
    public final String CANCEL_BUTTON_ID = "cancel";
    // 注册按钮
    public final String REGISTER_BUTTON_ID = "toRegist";
    // 取消隐藏密码按钮
    public final String HIDE_PASSWORD_ID = "passVisible";
    // 使用手机短信码登录
    public final String TO_MESSAGE_LOGIN_ID = "toMessageLogin";
    // 忘记密码
    public final String TO_FIND_PASS_ID = "toFindPass";
    // QQ登录
    public final String QQ_LOGIN_ID = "qq_login";
    // 微信登录
    public final String WEIXIN_LOGIN_ID = "weixin_login";
    // 登录页面标识
    public final String LOGIN_PAGE_TITLE_PATH = "//android.widget.EditText[contains(@text, '登录')]";
    // 首页主activity
    public final String MAIN_ACTIVITY = "MainFragementActivity";
    // 底部导航菜单id
    public final String BOTTOM_MENU_ELEMENT_ID = "tab_itme_txt";


    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     *
     * @return 返回是否能获取到登录按钮id
     */
    public boolean isLoginBtnDisplayed() {
        return isIdElementExist(LOGIN_BUTTON_ID, 3, true);
    }

    /**
     * 获取登录页面的activity的名字
     * @return
     */
    public String getLoginActivity() {
        return LOGIN_ACTIVITY;
    }

    // 判断当前是否在登录activity
    public boolean isLoginActivity() {
        return getCurrActivity().equals(getLoginActivity());
    }

    /**
     * 获取主页面的activity的名字
     * @return
     */
    public String getMainAcrtivity() {
        return MAIN_ACTIVITY;
    }

    // 获取页面控件——开始
    public AndroidElement getCancelButton() {
        return waitAutoById(CANCEL_BUTTON_ID);
    }

    public AndroidElement getRegisterButton() {
        return waitAutoById(REGISTER_BUTTON_ID);
    }

    public AndroidElement getMessageLogin() {
        return waitAutoById(TO_MESSAGE_LOGIN_ID);
    }

    public AndroidElement getForgetPassword() {
        return waitAutoById(TO_FIND_PASS_ID);
    }

    public AndroidElement getLoginButton() {
        return waitAutoById(LOGIN_BUTTON_ID);
    }

    public AndroidElement getQQLogin() {
        return waitAutoById(QQ_LOGIN_ID);
    }

    public AndroidElement getWeixinLogin() {
        return waitAutoById(WEIXIN_LOGIN_ID);
    }

    public AndroidElement getHidePwd() {
        return waitAutoById(HIDE_PASSWORD_ID);
    }

    public AndroidElement getLoginPageTitle() { return waitAutoByXp(LOGIN_PAGE_TITLE_PATH); }

    /**
     * 获取底部导航菜单控件
     * @return
     */
    public List<AndroidElement> getBottomMenu() {
        // 1：首页，2：商家，3：社区，4：购物车，5：我的
        return getManyElementById(BOTTOM_MENU_ELEMENT_ID, 5);
    }

    /**
     * 获取账号密码框的控件
     * @return
     */
    public List<AndroidElement> getNamePassElement() {
        return getManyElementByClassName(PHONE_PASS_ELEMENT_CLASS, 2);
    }
    // 获取页面控件——结束


    // public boolean getLoginFlag() {
    //     return ;
    // }



}
