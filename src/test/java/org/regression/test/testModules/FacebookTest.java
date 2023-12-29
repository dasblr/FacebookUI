package org.regression.test.testModules;

import org.regression.test.base.BaseTest;
import org.regression.test.pageEvents.LoginPageEvents;
import org.regression.test.utils.ElementFetch;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FacebookTest extends BaseTest {
    ElementFetch elementFetch = new ElementFetch();
    LoginPageEvents loginPageEvents = new LoginPageEvents();

    @Parameters({"username", "password"})
    @Test
    public void runTest(String username, String password){
        loginPageEvents.checkLogo();
        loginPageEvents.checkHeadingText();
        loginPageEvents.passUserName(username);
        loginPageEvents.passPassword(password);
        loginPageEvents.loginBtnClick();
    }

}
