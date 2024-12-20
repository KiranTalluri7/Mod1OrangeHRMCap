package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	WebDriver driver;
	
	@FindBy(name="username")
	WebElement usernameField;
	
	@FindBy(name="password")
	WebElement passwordField;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")
	WebElement loginButton;
	
	
	
	public Login(WebDriver driver) {
		this.driver=driver;
        PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	public void enterPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	public void clickLogin() {
		loginButton.click();
	}
	
	}

	
	
	
	
	
	





   


    
   
   
