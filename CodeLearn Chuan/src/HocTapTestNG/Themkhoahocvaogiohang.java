package HocTapTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.ExtentReportManager;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
@Listeners(HocTapTestNG.TestListener.class)
public class Themkhoahocvaogiohang {

    public WebDriver driver = null;
    WebDriverWait wait = null;
    
    Actions action;
    ExtentReports extent;
    ExtentTest test;
	 @BeforeTest
	    public void beforeTest(ITestContext context) {
	        extent = ExtentReportManager.getInstance(); // khởi tạo báo cáo
	        driver = new ChromeDriver();
	        action = new Actions(driver);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	        driver.manage().window().maximize();
	        // Truyền driver vào context để Listener hoặc các class khác dùng
	        context.setAttribute("driver", driver);
	    }

	    public void loginWithValidAccount() throws InterruptedException {
	        driver.get("https://codelearn.io/");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[text()='Đăng nhập']/ancestor::button")));
	        loginButton.click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-user-name"))).sendKeys("tranthuhien21012004@gmail.com");
	        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");
	        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();

	        Thread.sleep(3000);
	        try {
	            driver.findElement(By.xpath("//button[contains(@class, 'mantine-Modal-close')]")).click();
	        } catch (Exception e) {}

	        try {
	            driver.findElement(By.xpath("//span[text()='Bỏ qua']/ancestor::button")).click();
	        } catch (Exception e) {}
	    }
	    @Test
	    public void hoverthemgiohang() throws InterruptedException {
	        test = extent.createTest("Test thêm khóa học vào giỏ hàng trên CodeLearn");

	        try {
	            loginWithValidAccount();
	            test.log(Status.PASS, "Đăng nhập thành công");
	        } catch (Exception e) {
	            test.log(Status.FAIL, " Đăng nhập thất bại: " + e.getMessage());
	            Assert.fail("Đăng nhập thất bại");
	            return;
	        }

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        Actions action = new Actions(driver);

	        try {
	            // 1. Click menu "Học tập"
	            WebElement hocTap = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[text()='Học tập']")));
	            wait.until(ExpectedConditions.elementToBeClickable(hocTap)).click();
	            test.log(Status.PASS, " Click menu 'Học tập' thành công.");
	        } catch (Exception e) {
	            test.log(Status.FAIL, " Không click được menu 'Học tập': " + e.getMessage());
	            Assert.fail("Không click được menu Học tập");
	        }

	        try {
	            // 2. Di chuột đến tiêu đề khóa học
	            WebElement tieuDe = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h1[contains(text(),'Hoàn thiện ứng dụng web thực tế với C#')]")));
	            action.moveToElement(tieuDe).perform();
	            test.log(Status.PASS, " Di chuột vào tiêu đề khóa học thành công.");
	            Thread.sleep(1000);
	        } catch (Exception e) {
	            test.log(Status.FAIL, " Không thể hover vào tiêu đề khóa học: " + e.getMessage());
	            Assert.fail("Hover thất bại");
	        }

	        try {
	            // 3. Click vào nút "Thêm vào giỏ hàng"
	            WebElement nutThem = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[contains(text(),'Thêm vào giỏ hàng')]")));
	            nutThem.click();
	            test.log(Status.PASS, " Click nút 'Thêm vào giỏ hàng' thành công.");
	        } catch (Exception e) {
	            test.log(Status.FAIL, " Không click được nút 'Thêm vào giỏ hàng': " + e.getMessage());
	            Assert.fail("Click nút thêm giỏ hàng thất bại");
	        }

	        try {
	            // 4. Xác minh có thông báo "Đã thêm vào giỏ hàng"
	            WebElement thongBao = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(text(),'Đã thêm vào giỏ hàng')]")));
	            Assert.assertTrue(thongBao.isDisplayed(), "Thông báo không hiển thị!");
	            test.log(Status.PASS, " Hiển thị thông báo 'Đã thêm vào giỏ hàng'.");
	            System.out.println("Đã thêm vào giỏ hàng thành công.");
	        } catch (Exception e) {
	            test.log(Status.FAIL, "Không hiển thị thông báo 'Đã thêm vào giỏ hàng': " + e.getMessage());
	            Assert.fail("Không thấy thông báo 'Đã thêm vào giỏ hàng'");
	        }
	    }


  @AfterTest
  public void afterTest() throws Exception {
      Thread.sleep(2000);
      if (driver != null) {
          driver.quit();
      }
      ExtentReportManager.flushReport(); 
  }
}
