package com.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.Login;
import com.pages.Logout;
import com.utils.ExcelUtil;
import com.utils.ScreenshotUtil;

public class LoginTests {
	WebDriver driver;
	Login login;
	Logout logout;
	String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	ExcelUtil excelUtil;
	ScreenshotUtil snap;

	@BeforeClass
	public void steUp() throws IOException {
		// GoogleDriver & Excel
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		login = new Login(driver);
		logout = new Logout(driver);
		excelUtil = new ExcelUtil("src/test/resources/testdata/LoginData.xlsx", "Sheet1");
		snap = new ScreenshotUtil(driver);

	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
		return excelUtil.getTestData();
	}

	@Test(dataProvider = "loginData")
	public void testLogin(String username, String password, String expectedResult) throws InterruptedException {
		driver.get(baseURL);

		// Login
		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLogin();
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("dashboard")) {
			System.out.println("Login successful.");
			Thread.sleep(3000);

			String timestamp = String.valueOf(System.currentTimeMillis()); 
            snap.takeScreenshot("LoginSuccess_" + username + "_" + timestamp);
			logout.signout1();
			logout.signout2();
		} else {
			System.out.println("Login failed for username: " + username);
			Thread.sleep(3000);
			String timestamp = String.valueOf(System.currentTimeMillis()); 
            snap.takeScreenshot("LoginFailure_" + username + "_" + timestamp);
		}
	}

	@AfterClass
	public void close() throws IOException {
		excelUtil.closeWorkbook();
		driver.quit();
	}
}
