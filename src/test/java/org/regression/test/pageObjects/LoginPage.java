package org.regression.test.pageObjects;

public interface LoginPage {
    String usernameTxtBox = "//input[@id='email']";
    String passwordTxtBox = "//input[@id='pass']";
    String loginBtn = "//button[@name='login']";
    String facebookLogo = "//img[@class='fb_logo _8ilh img']";
    String loginPageHeading = "//h2[@class='_8eso']";

    String headingText = "Facebook helps you connect and share with the people in your life.";
}
