package QuanlytaikhoanTestNG;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import org.testng.annotations.Test;

public class ThongtintaikhoanTestNG extends BaseTest {  

	@Test
	// TC044: Cập nhật họ tên thành công 
	public void Capnhathotenthanhcong() throws InterruptedException {
		loginWithValidAccount(); // Gọi hàm login() từ BaseTest

	    // Click vào ảnh đại diện tài khoản (icon avatar)
	    driver.findElement(By.xpath("//img[contains(@src,'user_')]")).click();

	    // Đợi menu xổ xuống
	    Thread.sleep(1000);
	    // Click vào mục "Thông tin tài khoản"
	    driver.findElement(By.xpath("//div[contains(text(),'Thông tin tài khoản')]")).click();
	    // Click nút cập nhật
	    driver.findElement(By.xpath("//span[text()='Cập nhật']")).click();
	    // SetKey hoten
	    WebElement fullNameInput = driver.findElement(By.xpath("//input[@placeholder='Nhập họ và tên']"));
	    fullNameInput.clear();
	    Thread.sleep(2000);
	    fullNameInput.sendKeys("Tran Thu Hien");
	   
	}
}

   
   
   




   
   

   
                                                                                                                                             