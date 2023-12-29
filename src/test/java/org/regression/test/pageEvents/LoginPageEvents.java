package org.regression.test.pageEvents;

import org.regression.test.pageObjects.LoginPage;
import org.regression.test.utils.ElementFetch;
import org.regression.test.utils.HttpConnTest;
import org.testng.Assert;

public class LoginPageEvents {
    ElementFetch elementFetch = new ElementFetch();

    public void passUserName(String userName){
        elementFetch.getWebElement("XPATH", LoginPage.usernameTxtBox).sendKeys(userName);
    }

    public void passPassword(String password){
        elementFetch.getWebElement("XPATH", LoginPage.passwordTxtBox).sendKeys(password);
    }

    public void loginBtnClick(){
        elementFetch.getWebElement("XPATH", LoginPage.loginBtn).click();
    }

    public void checkLogo(){
        String link = elementFetch.getWebElement("XPATH", LoginPage.facebookLogo).getAttribute("src");
        int responseCode = HttpConnTest.getResponseCode(link);
        Assert.assertEquals(responseCode, 200);
    }

    public void checkHeadingText(){
        String headingText = elementFetch.getWebElement("XPATH", LoginPage.loginPageHeading).getText();
        Assert.assertEquals(headingText, LoginPage.headingText);
    }
}
