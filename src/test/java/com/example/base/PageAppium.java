package com.example.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.base.InitAppium.appPackage;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 9:54 2017/11/22 0022.
 * @Modified by :
 */
public class PageAppium {
    AndroidDriver driver;

    private static int WAIT_TIME = 3;   // 默认的等待控件时间

    public PageAppium(AndroidDriver androidDriver) {
        this.driver = androidDriver;
        waitAuto(WAIT_TIME);
    }

    public boolean isIdElementExist(String id) {
        return isIdElementExist(id, 0);
    }

    public boolean isIdElementExist(String id, int timeOut) {
        return isIdElementExist(id, timeOut, false);
    }

    /**
     * @Description:
     * @param id 要查找的id
     * @param timeOut 超时时间
     * @param isDisplayed 判断控件是否显示
     * @return: boolean
     * @throws:
     * @Author: Aikachin
     * @Date: 9:58 2017/11/22 0022
     */
    public boolean isIdElementExist(String id, int timeOut, boolean isDisplayed) {
        return isElementExist(By.id(appPackage + ":id/" + id), timeOut, isDisplayed);
    }

    /**
     * 选择当前界面的有这个文字的控件
     *
     * @param name
     * @return
     */
    public boolean isNameElementExist(String name) {
        return isNameElementExist(name, 0);
    }

    public boolean isNameElementExist(String name, int timeOut) {
        return isNameElementExist(name, timeOut,false);
    }

    public boolean isNameElementExist(String name, int timeOut, boolean isDisplayed) {
        return isElementExist(By.name(name),timeOut, isDisplayed);
    }

    /**
     * 判断当前界面有没有这个字符串存在
     *
     * @param text 要判断的字符串
     * @return 存在返回真
     */
    public boolean isTextExist(String text) {
        String str = driver.getPageSource();
        print(str);
        return str.contains(text);
    }

    /**
     * 判断当前界面有没有这个Xpath控件存在
     *
     * @param text 要判断的字符串
     * @return 存在返回真
     */
    public boolean isXpathExist(String text) {
        return isXpathExist(text, 0);
    }

    public boolean isXpathExist(String text, int timeOut) {
        return isXpathExist(text, timeOut, false);
    }


    public boolean isXpathExist(String text, int timeOut, boolean isDisplayed) {
        // //android.widget.TextView[@text='"+text+"']
        return isElementExist(By.xpath(text), timeOut, isDisplayed);
    }

    /**
     * 判断控件是否存在
     *
     * @param by      By
     * @param timeOut 等待的事件
     * @return
     */
    public boolean isElementExist(By by, int timeOut, boolean isDisplayed) {
        try {
            AndroidElement element = waitAuto(by, timeOut);
            if (element == null) {
                return false;
            } else {
                if (isDisplayed) {
                    return element.isDisplayed();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当前的activity,返回文件名
     *
     * @return
     */
    public String getCurrActivity() {
        String str = driver.currentActivity();
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /**
     * 根据id获取当前界面的一个控件
     *
     * @param id 要查找的id
     * @return 返回对应的控件
     */
    public AndroidElement findById(String id,String desc) {
        return findElementBy(By.id(appPackage + ":id/" + id),desc);
    }

    public AndroidElement findById(String id) {
        return findById(id,"");
    }

    public AndroidElement findByFullId(String id) {
        try {
            if (driver != null) {
                return (AndroidElement) driver.findElement(By.id(id));
            } else {
                print("driver为空");
            }
        } catch (NoSuchElementException e) {
            print("找不到控件:" +" 异常信息:"+ e.getMessage());
        }
        return null;
    }

    /**
     * 选择当前界面的有这个文字的控件
     *
     * @param name 内容
     * @return 找到的控件
     */
    public AndroidElement findByName(String name,String desc) {
        return findElementBy(By.name(name),desc);
    }

    public AndroidElement findByName(String name) {
        return findByName(name,"");
    }

    /**
     * 根据id获取当前界面的一个控件
     *
     * @param name 要查找的控件的类名
     * @return 返回对应的控件
     */
    public AndroidElement findByClassName(String name,String desc) {
        return findElementBy(By.className(name),desc);
    }

    public AndroidElement findByClassName(String name) {
        return findByClassName(name,"");
    }

    /**
     * 根据XPath获取当前界面的一个控件
     *
     * @param str 要查找的控件的XPath
     * @return 返回对应的控件
     */
    public AndroidElement findByXpath(String str,String desc) {
        return findElementBy(By.xpath(str),desc);
    }

    public AndroidElement findByXpath(String str) {
        return findByXpath(str,"");
    }


    /**
     * 根据by获取当前界面的一个控件
     *
     * @param by 要查找的控件的by
     * @return 返回对应的控件
     */
    public AndroidElement findElementBy(By by){
        return findElementBy(by, "");
    }

    public AndroidElement findElementBy(By by, String str) {
        try {
            if (driver != null) {
                return (AndroidElement) driver.findElement(by);
            } else {
                print("driver为空");
            }
        } catch (NoSuchElementException e) {
            print("找不到控件：" + str + "，异常信息：" + e.getMessage());
        }
        return null;
    }

    /**
     * 打印字符
     *
     * @param str 要打印的字符
     */
    public <T> void print(T str) {
        if (!TextUtils.isEmpty(String.valueOf(str))) {
            System.out.println(str);
        } else {
            System.out.println("输出了空字符");
        }
    }

    /**
     * 线程休眠秒数，单位秒
     *
     * @param s 要休眠的秒数
     */
    public void sleep(long s) throws InterruptedException {
        Thread.sleep(s);
    }

    /**
     * 显示等待，等待Id对应的控件出现time秒，一出现马上返回，time秒不出现也返回
     */
    public AndroidElement waitAuto(By by, int time) {
        try {
            return new AndroidDriverWait(driver, time)
                    .until(new ExpectedCondition<AndroidElement>() {
                        @Override
                        public AndroidElement apply(AndroidDriver androidDriver) {
                            return (AndroidElement) androidDriver.findElement(by);
                        }
                    });
        } catch (TimeoutException e) {
            return null;
        }
    }

    /**
     * @Description: 根据id等待查找元素
     * @param id 元素id
     * @return: io.appium.java_client.android.AndroidElement
     * @Author: Aikachin
     * @Date: 10:27 2017/11/22 0022
     */
    public AndroidElement waitAutoById(String id) {
        return waitAutoById(id, WAIT_TIME);
    }

    public AndroidElement waitAutoById(String id, int time) {
        return waitAuto(By.id(id), time);
    }

    /**
     * @Description: 根据name等待查找元素
     * @param name 元素类名？
     * @return: io.appium.java_client.android.AndroidElement
     * @Author: Aikachin
     * @Date: 10:27 2017/11/22 0022
     */
    public AndroidElement waitAutoByName(String name) {
        return waitAutoByName(name, WAIT_TIME);
    }

    public AndroidElement waitAutoByName(String name, int time) {
        return waitAuto(By.name(name), time);
    }

    /**
     * @Description: xPath
     * @param xPath 元素xpath
     * @return: io.appium.java_client.android.AndroidElement
     * @Author: Aikachin
     * @Date: 10:27 2017/11/22 0022
     */
    public AndroidElement waitAutoByXp(String xPath) {
        return waitAutoByXp(xPath, WAIT_TIME);
    }

    public AndroidElement waitAutoByXp(String xPath, int time) {
        return waitAuto(By.xpath(xPath), time);
    }

    public void waitAuto() {
        waitAuto(WAIT_TIME);
    }

    /**
     * ，隐式等待，如果在指定时间内还是找不到下个元素则会报错停止脚本
     * 全局设定的，find控件找不到就会按照这个事件来等待
     *
     * @param time 要等待的时间
     */
    public void waitAuto(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * 获取屏幕的宽高
     *
     * @return 返回宽高的数组
     */
    public int[] getScreen() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        return new int[]{width, height};
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        return driver.manage().window().getSize().getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        return driver.manage().window().getSize().getHeight();
    }

    /**
     * 根据ClassName获取多个控件
     *
     * @param className 控件的类名字，例如 android.widget.EditText
     * @param num       返回的数量
     * @return
     */
    public List<AndroidElement> getManyElementByClassName(String className, int num) {
        List<AndroidElement> textFieldsList = driver.findElementsByClassName(className);
        List<AndroidElement> list = new ArrayList<>();
        try{
            for(int i=0; i<num; i++){
                list.add(textFieldsList.get(i));
            }
            return list;
        }catch (Exception e){
            print("获取多个控件异常"+e.getMessage());
        }
        return null;
    }

    /**
     * 根据Id获取多个控件
     *
     * @param id 控件的类名字，例如 android.widget.EditText
     * @param num       返回的数量
     * @return
     */
    public List<AndroidElement> getManyElementById(String id, int num) {
        if(driver != null){
            List<AndroidElement> textFieldsList = driver.findElementsById(id);
            List<AndroidElement> list = new ArrayList<>();
            try{
                for(int i=0; i<num; i++){
                    list.add(textFieldsList.get(i));
                }
                return list;
            }catch (Exception e){
                print("获取多个控件异常"+e.getMessage());
            }
        }else{
            print("获取多个控件"+id+"时候driver为空");
        }
        return null;
    }

    /**
     * 获取同id的list的控件
     * @param id id
     * @param num 取list中的第几个
     * @return
     */
    public AndroidElement getListOneElementById(String id,int num) {
        if (driver != null) {
            try {
                return (AndroidElement) driver.findElementsById(appPackage+":id/"+id).get(num);
            } catch (Exception e) {
                print("getListOneElementById找不到第"+num+"个控件"+id);
                return null;
            }
        } else {
            print("getListOneElementById:"+id+" 时候driver为空");
            return null;
        }
    }

    // 额外添加的方法，参考：http://blog.csdn.net/wanglha/article/details/51435894
    /**
     * 检查网络
     * @return 是否正常
     */
    public boolean checkNetStatus() {
        if (driver != null) {
            String netStatus = driver.getNetworkConnection().toString();
            return netStatus.contains("Data: true");
        } else {
            print("checkNetStatus()的时候driver为空");
            return false;
        }
    }

    /***
     * 根据UIautomator底层方法得到对应desc的view
     * @param name desc名
     * @return View
     */
    public AndroidElement getViewByUidesc(String name) {
        if (driver != null) {
            waitAuto();
            return (AndroidElement) driver.findElementByAndroidUIAutomator(
                    "new UiSelector().descriptionContains(\"" + name + "\")");
        } else {
            print("getViewByUidesc()的时候driver为空");
            return null;
        }
    }

    /***
     * 根据UIautomator底层方法得到对应text的view
     * @param name text名
     * @return View
     */
    public AndroidElement getViewbyUitext(String name) {
        if (driver != null) {
            waitAuto();
            return (AndroidElement) driver.findElementByAndroidUIAutomator(
                    "new UiSelector().textContains(\"" + name + "\")");
        } else {
            print("getViewbyUitext()的时候driver为空");
            return null;
        }
    }

    /***
     * 截图 文件名: 内容-年月日时分秒
     */
    public String getScreen(String name){
        if (driver != null) {
            waitAuto();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String picname = name+df.format(new Date()).toString()+".png";
            File screen = driver.getScreenshotAs(OutputType.FILE);
            System.out.println(picname);
            File screenFile = new File(picname);
            try {
                FileUtils.copyFile(screen, screenFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return picname;
        } else {
            print("getScreen()截图的时候driver为空");
            return null;
        }
    }

}
