package com.example.operations;

import com.example.base.OperateAppium;
import com.example.pages.*;
import io.appium.java_client.android.AndroidDriver;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 14:11 2017/11/22 0022.
 * @Modified by :
 */
public class LoginOperation extends OperateAppium {

    private LoginPage loginPage;
    private MyPage myPage;
    private MainPage mainPage;
    private SplashPage splashPage;


    AndroidDriver driver;

    public LoginOperation(AndroidDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        myPage = new MyPage(driver);
        mainPage = new MainPage(driver);
        splashPage = new SplashPage(driver);
        this.driver = driver;
    }

    /**
     * 传递帐号密码登录，并验证登录身份
     *
     * @param phone    帐号
     * @param pwd      密码
     * @param nickName 昵称
     * @return 是否成功登录到
     */
    public boolean login(String phone, String pwd, String nickName) {
        // 判断是否在启动activity
        if (getCurrActivity().equals(splashPage.getSplashActivity())) {
            // 判断跳过按钮控件是否存在，有时候启动时不显示splashPage页面
            if (splashPage.isIdElementExist(splashPage.SKIP_ID, 5)) {
                clickView(splashPage.getSkipBtn());
            }
            while (getCurrActivity().equals(splashPage.getSplashActivity())) {
                swip(SWIP_LEFT, 200);
                // 点击屏幕中间进入首页
                press(getScreen()[0] / 2, getScreen()[1] / 2);
            }
        }
        // 判断是否在登录页
        if (!getCurrActivity().equals(loginPage.getLoginActivity())) {
            // 判断是否在主activity
            while (!getCurrActivity().equals(mainPage.getMainActivity())) {
                // 向后回退到mainActivity
                turnBack();
                sleep(2000);
            }
            // 进入我的页面
            clickView(loginPage.getBottomMenu().get(4));
            sleep(3000);
        }
        int loginMark = 0;
        // 未登录的话进入【我的】页面会自动跳转登录页
        if (loginPage.isLoginActivity()) {
            // 登录
            loginMark = login(phone, pwd);
            // 如果登录失败
            if (LOGIN_FAILED == loginMark) {
                return false;
                // 如果登录成功
            } else if (LOGIN_SUCCESS == loginMark) {
                // 判断我的页面昵称是否符合预期
                return accountVerify(nickName);
            } else {
                return false;
            }
        }
        // 判断我的页面昵称是否符合预期
        if (!accountVerify(nickName)) {
            logout();
            loginMark = login(phone, pwd);
            if (LOGIN_SUCCESS == loginMark) {
                return accountVerify(nickName);
            }
            return false;
        }
        return true;
    }

    // 仅登录
    public int login(String phone, String pwd) {
        if (loginPage.isLoginActivity()) {
            // 输入用户名，密码
            inputManyText(phone, pwd);
            // 点击登录按钮
            clickView(loginPage.getLoginButton());
            if (loginPage.isLoginActivity()) {
                print("登录失败：账号密码错误！(或者连接超时)");
                return LOGIN_FAILED;
            } else {
                print(phone + "登录成功");
                return LOGIN_SUCCESS;
            }
        }
        print("未跳转登录页面，认为已经登录");
        return LOGIN_ALREADY;
    }

    // 注销用户
    public boolean logout() {
        if (getCurrActivity().equals(mainPage.MAIN_ACTIVITY)) {
            if (myPage.getNickName() != null) {
                try {
                    // 点击设置
                    clickView(myPage.getSetting());
                    SettingPage settingPage = new SettingPage(driver);
                    if (getCurrActivity().equals(settingPage.getSettingActivity())) {
                        clickView(settingPage.getLogoutButton());
                    }
                    sleep(3000);
                    if (getCurrActivity().equals(loginPage.getLoginActivity())) {
                        return true;
                    }
                    print("注销后没有跳转登录页面");
                    return false;
                } catch (Exception e) {
                    print("找不到带有 [" + myPage.SETTING_TEXT + "] 字样的控件，异常信息：" + e.getMessage());
                    return false;
                }
            } else {
                print("尚未登录");
                return false;
            }
        } else {
            print("不在主activity");
            return false;
        }
    }

    // 验证登录者的昵称
    public boolean accountVerify(String nickName) {
        // MyPage myPage = new MyPage(driver);
        if (getCurrActivity().equals(mainPage.MAIN_ACTIVITY)) {

            try {
                 myPage.getNickName();
                // 如果昵称控件没找到
            } catch (Exception e) {
                print("登录后跳转未进入我的页面");
                // 进入我的页面
                clickView(loginPage.getBottomMenu().get(4));
            }
            boolean flag = isElementRight(myPage.getNickName(), nickName, "昵称");

            // 如果昵称符合预期
            if (flag) {
                print("[" + nickName + "]登录成功");
                return true;
            } else if (myPage.getNickName() != null) {
                // 如果昵称控件找到
                print("登录的用户[" + myPage.getNickName().getText() + "]不合预期");
            }
            return false;
        }
        print("登录后跳转错误或超时");
        return false;
    }

}
