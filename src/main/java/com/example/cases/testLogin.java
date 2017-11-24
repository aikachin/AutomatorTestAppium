package com.example.cases;

import com.example.base.Assertion;
import com.example.base.InitAppium;
import com.example.operations.LoginOperation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 15:48 2017/11/23 0023.
 * @Modified by :
 */
public class testLogin extends InitAppium{

    private LoginOperation loginOperation;

    @BeforeClass
    public void initDriver() {
        Assert.assertNotNull(driver);
        loginOperation = new LoginOperation(driver);
    }

    /**
     * 测试帐号对 密码不对情况
     */
    @Test(priority = 0)
    public void loginErrorUser() {
        boolean flag = loginOperation.login("18096600175", "123456", "Onigokko");
        Assertion.verifyEquals(flag, true, "账号对密码错误是否登录成功");
        print("账号密码错误情况登录：" + flag);
    }

    /**
     * 测试帐号密码规格不对情况
     */
    @Test(priority = 1)
    public void loginErrorType() {
        boolean flag = loginOperation.login("18096600175", "123456", "Onigokko");
        Assertion.verifyEquals(flag, true, "账号对密码错误是否登录成功");
        print("账号对密码错误情况登录：" + flag);
    }

    /**
     * 测试帐号密码规格不对情况
     */
    @Test(priority = 2)
    public void loginConfirm() {
        boolean flag = loginOperation.login("18096600175", "a123456", "Onigokko");
        Assertion.verifyEquals(flag, true, "账号密码正确是否登录成功");
        print("账号密码正确情况登录：" + flag);
    }
}
