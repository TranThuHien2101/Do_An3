package QuanlytaikhoanTestNG;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HosocuatoiTestNG extends BaseTest {

  @Test
  // TC: Bỏ trống nội dung
  public void Botrongnoidung() throws InterruptedException {
	  loginWithValidAccount();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Chờ overlay biến mất để thực hiện tiếp theo 
    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(
          By.cssSelector(".mantine-Overlay-root")));
    } catch (Exception e) {
      System.out.println("Không tìm thấy overlay hoặc overlay đã biến mất.");
    }

    // Chờ đến khi ảnh avatar có thể click được, rồi mới click
    WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//img[contains(@src,'user_')]")));
    avatar.click();

    // Chờ menu xổ xuống và click vào "Hồ sơ của tôi"
    WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[text()='Hồ sơ của tôi']")));
    profileButton.click();

    // Click vào icon bút sửa (tùy thuộc vào cấu trúc cụ thể của HTML)
    WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//svg[@class='text-blue-600 cursor-pointer' and @width='20' and @height='24']")));

        icon.click();

  }
}

  
  

