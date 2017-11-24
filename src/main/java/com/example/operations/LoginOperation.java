package com.example.operations;

import com.example.base.OperateAppium;
import com.example.pages.LoginPage;
import com.example.pages.MainPage;
import com.example.pages.MyPage;
import com.example.pages.SplashPage;
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
        this.driver = driver;
        loginPage = new LoginPage(driver);
        myPage = new MyPage(driver);
        mainPage = new MainPage(driver);
        splashPage = new SplashPage(driver);
    }

    /**
     * 传递帐号密码登录，并验证登录身份
     * @param phone 帐号
     * @param pwd 密码
     * @param nickName 昵称
     * @return 是否成功登录到
     */
    public boolean login(String phone, String pwd, String nickName) {
        // 判断是否在启动activity
        if (getCurrActivity().equals(splashPage.getSplashActivity())) {
            clickView(splashPage.getSkipBtn());
        }
        // 判断是否在登录页
        if (!getCurrActivity().equals(loginPage.getLoginActivity())) {
            // 判断是否在主activity
            while (!getCurrActivity().equals(loginPage.getMainAcrtivity())) {
                // 向后回退到mainActivity
                turnBack();
                sleep(2000);
            }
            // 进入我的页面
            clickView(loginPage.getBottomMenu().get(4));
            sleep(3000);
        }
        // if (getCurrActivity().equals(loginPage.MAIN_ACTIVITY)) {
        //     // 进入我的页面
        //     clickView(loginPage.getBottomMenu().get(4));
        //     sleep(3000);
        // }

        // 登录
        if (login(phone, pwd)) {
            // 判断我的页面昵称是否符合预期
            return accountVerify(nickName);
        } else {
            return false;
        }
    }

    // 验证登录者昵称
    public boolean accountVerify(String nickName) {
        MyPage myPage = new MyPage(driver);
        if (getCurrActivity().equals(loginPage.MAIN_ACTIVITY)) {
            boolean flag = isElementRight(myPage.getNickName(), nickName, "昵称");
            // 如果昵称符合预期
            if (flag) {
                print("[" + nickName + "]登录成功");
                return true;
            } else {
                // 如果昵称控件找到
                if (myPage.getNickName() != null) {
                    print("登录的用户[" + nickName + "]不合预期");
                }
                // 如果昵称控件没找到
                return false;
            }
        }
        print("登录后跳转错误或超时");
        return false;
    }

    // 仅登录
    public boolean login(String phone, String pwd) {
        // 未登录的话进入【我的】页面会自动跳转登录页
        if (loginPage.isLoginActivity()) {
            // 输入用户名，密码
            inputManyText(phone, pwd);
            // 点击登录按钮
            clickView(loginPage.getLoginButton());
            if (loginPage.isLoginActivity()) {
                print("登录失败：账号密码错误，或者登录连接超时");
                return false;
            } else {
                print(phone + "登录成功");
                return true;
            }
        }
        print("当前不在登录页面或者连接超时");
        return false;
    }
}
