package com.JangNam.printlog;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecurePrintLogin {
	public static void main(String[] args) {
		// Chrome Access
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver(options);
		
		try {
			//Login Address
			driver.get("*");
			
			//Login Info input
			WebElement idField = driver.findElement(By.id("inputUserId"));
			WebElement pwField = driver.findElement(By.id("inputUserPw"));
			idField.sendKeys("*");
			pwField.sendKeys("*");
			
			//Submit 이벤트가 아닌 클릭 이벤트로 동작
			driver.findElement(By.className("submitLogin")).click();
			
			//기본은 데이터 10개로 되어있어서 1000개로 값 전달(Ajax 및 Post로 동작)
			driver.manage().addCookie(new Cookie("rowNum", "1000"));
			//pwField.submit();
			
			//요소가 있는지 확인하는거는 정상적으로 동작
			new WebDriverWait(driver, Duration.ofSeconds(15))
		    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'출력일자')]")));
			
			Thread.sleep(3000);
			//주소 변경 감지는 정상적으로 동작하지 않았음.
			
			//소스를 문자열에 모두 담기
			String pageSource = driver.getPageSource();
			//System.out.println(pageSource);
			
			//다른 클래스
			PrintLogParser.parse(pageSource);
								
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//driver.quit();
		}
	}

}
