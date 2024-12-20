package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout {
	WebDriver driver;
	@FindBy(xpath="//*[@id='app']/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")
	WebElement logoutlink;

	@FindBy(linkText="Logout")
	WebElement logoutButton;
	//constructor to initialize page elements
		public Logout(WebDriver driver) {
			this.driver=driver;
	        PageFactory.initElements(driver, this);
		}
		public void signout1() {
			logoutlink.click();
		}
		public void signout2() {
			logoutButton.click();
}
}
