package QuanlytaikhoanTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;

public class BaseTest {
	public WebDriver driver = null;

	@BeforeClass
  public void beforeTest() {
      System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
      driver = new ChromeDriver(); 
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
      driver.manage().window().maximize();
  }
  public void loginWithValidAccount() throws InterruptedException {
      // Mở trang CodeLearn
      driver.get("https://codelearn.io/");
      Thread.sleep(2000);

      // Nhấn nút "Đăng nhập"
      driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
      Thread.sleep(2000);

      // Nhập email và mật khẩu hợp lệ
      driver.findElement(By.id("login-user-name")).sendKeys("tranthuhien21012004@gmail.com");
      driver.findElement(By.id("login-password")).sendKeys("Hien21012004");
      Thread.sleep(1000);

      // Nhấn nút đăng nhập
      driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
      Thread.sleep(4000); // Chờ trang xử lý
      // Click nút hủy
  	  WebElement closeButton = driver.findElement(
  		    By.xpath("//button[contains(@class, 'mantine-Modal-close')]")
  		);
  		closeButton.click();

      Thread.sleep(2000);
      // Click nút bỏ qua
      WebElement skipButton = driver.findElement(By.xpath("//span[text()='Bỏ qua']"));
      skipButton.findElement(By.xpath("./ancestor::button")).click();
      Thread.sleep(2000);
      
  }
  @AfterTest
  public void afterTest() {
      if (driver != null) {
          driver.quit();
      }
  }
}
