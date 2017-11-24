package com.example.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: Aikachin
 * @Description: 测试用例的父类
 * @Date: Created in 14:46 2017/11/21 0021.
 * @Modified by :
 */
@Listeners({com.example.base.AssertionListener.class})
public class InitAppium {
//    调试设置名
    public static String deviceName = "121.0.0.1:52001";
//    调试设备系统版本
    public static String platformVersion = "4.4.2";
//    app路径
    public static String appPath = System.getProperty("user.dir") + "/apps/FirstShop-release.apk";  // firstshop.apk
//    包名
    public static String appPackage = "com.firstshop";
//    是否需要重新安装
    public static String noReset = "True";
//    是否不重新签名
    public static String noSign = "True";
//    是否使用unicode输入法，true是支持中文
    public static String unicodeKeyboard = "True";
//    是否恢复默认输入法
    public static String resetKeyboard = "True";
//    要启动的activity
    public static String appActivity = appPackage + ".SplashActivity";

    public static AndroidDriver<AndroidElement> driver = null;

//    构造方法
    public InitAppium() {
        this(new Builder());
    }

    public InitAppium(Builder builder) {
        appActivity = builder.appActivity;
        appPackage = builder.appPackage;
        appPath = builder.appPath;
        deviceName = builder.deviceName;
        noReset = builder.noReset;
        noSign = builder.noSign;
        unicodeKeyboard = builder.unicodeKeyboard;
        resetKeyboard = builder.resetKeyboard;
    }

    /**
     * @Description:
     * @param
     * @return: void
     * @exception: MalformedURLException
     * @Author: Aikachin
     * @Date: 15:48 2017/11/21 0021
     */
    @BeforeSuite
    public void beforeSuite() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        // web 浏览器名称（'Safari' ,'Chrome'等）。如果对应用进行自动化测试，这个关键字的值应为空。
        dc.setCapability("browserName", "");
        dc.setCapability("deviceName", deviceName);
        dc.setCapability("platformVersion", platformVersion);
        dc.setCapability("app", new File(appPath).getAbsolutePath());
        dc.setCapability("appPackage", appPackage);
    //    支持中文
        dc.setCapability("unicodeKeyboard", unicodeKeyboard);
    //    运行完毕之后，恢复系统默认输入法
        dc.setCapability("resetKeyboard", resetKeyboard);
    //    不重复安装
        dc.setCapability("noReset", noReset);
    //    打开的activity
        if (!TextUtils.isEmpty(appActivity)) {
            dc.setCapability("appActivity", appActivity);
        }

    //    启动driver
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    @AfterClass
    public void afterClass() {
    //    每一个用例完毕结束这次测试
    //     driver.quit();
    }

    /**
     * @Description: 打印字符
     * @param str 要打印的字符
     * @return: void
     * @exception:
     * @Author: Aikachin
     * @Date: 15:41 2017/11/21 0021
     */
    public <T> void print(T str) {
        if (!TextUtils.isEmpty(String.valueOf(str))) {
            System.out.println(str);
        } else {
            System.out.println("输出了空字符");
        }
    }

}
