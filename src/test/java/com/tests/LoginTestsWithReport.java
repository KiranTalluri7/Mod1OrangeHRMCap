package com.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pages.Login;
import com.pages.Logout;
import com.utils.ExcelUtil;
import com.utils.ExtentReportManager;

public class LoginTestsWithReport {
    WebDriver driver;
    Login login;
    Logout logout;
    String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    ExcelUtil excelUtil;

    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void steUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        login = new Login(driver);
        logout = new Logout(driver);
        excelUtil = new ExcelUtil("src/test/resources/testdata/LoginData.xlsx", "Sheet1");

        extent = ExtentReportManager.getInstance(); 
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return excelUtil.getTestData();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedResult) throws InterruptedException {
        test = extent.createTest("Login Test - " + username)
                     .assignCategory("Login Tests")
                     .assignAuthor("Your Name");

        driver.get(baseURL);

        try {
            test.log(Status.INFO, "Navigated to " + baseURL);

            login.enterUsername(username);
            test.log(Status.INFO, "Entered Username: " + username);

            login.enterPassword(password);
            test.log(Status.INFO, "Entered Password.");

            login.clickLogin();
            test.log(Status.INFO, "Clicked Login button.");

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("dashboard")) {
                test.log(Status.PASS, "Login successful for username: " + username);
                logout.signout1();
                logout.signout2();
            } else {
                test.log(Status.FAIL, "Login failed for username: " + username);
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }

    @AfterClass
    public void close() throws IOException {
        excelUtil.closeWorkbook();
        driver.quit();
        extent.flush(); 
    }
}
