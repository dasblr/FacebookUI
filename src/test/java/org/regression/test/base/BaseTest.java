package org.regression.test.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.regression.test.utils.Constants;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest logger;

    @BeforeTest
    public void beforeTest(){
         sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +
                 File.separator+"reports"+File.separator+"FacebookUiExtentReports.html");
         extentReports = new ExtentReports();
         extentReports.attachReporter(sparkReporter);
         sparkReporter.config().setTheme(Theme.DARK);
         extentReports.setSystemInfo("HostName","RHEL8");
         extentReports.setSystemInfo("UserName", "root");
         sparkReporter.config().setDocumentTitle("UIAutomationReport");
         sparkReporter.config().setReportName("Automation Test Results by Abhishek Das");
    }
    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String browser, Method testMethod){
         logger = extentReports.createTest(testMethod.getName());
         setupDriver(browser);
         driver.get(Constants.url);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    private void setupDriver(String browser) {
        if(browser.equalsIgnoreCase("CHROME")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("EDGE")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+ " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ " - Test Case Passed", ExtentColor.GREEN));
        }
        driver.quit();
    }
    @AfterTest
    public void afterTest(){
        extentReports.flush();
    }
}
