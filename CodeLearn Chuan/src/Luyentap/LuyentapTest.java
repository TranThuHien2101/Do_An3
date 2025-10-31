package Luyentap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

@Listeners(Luyentap.TestListener.class)
public class LuyentapTest {
    public WebDriver driver = null;
    WebDriverWait wait = null;
    ExtentReports extent;
    ExtentTest test;
    @BeforeTest
    public void beforeTest(ITestContext context) {
        extent = ExtentReportManager.getInstance();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
    }
    @BeforeClass
    public void loginOnce() throws InterruptedException {
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
        } catch (Exception e) {
        }

        try {
            driver.findElement(By.xpath("//span[text()='Bỏ qua']/ancestor::button")).click();
        } catch (Exception e) {
        }
    }
    @Test
    public void testLuyentapSearch() throws InterruptedException {
        test = extent.createTest("TC01 - Tìm kiếm Python trong Luyện tập có click bộ lọc trạng thái");

        // Nhấn vào "Luyện tập"
        try {
            WebElement luyenTapLink = driver.findElement(By.xpath("//a[text()='Luyện tập']"));
            luyenTapLink.click();
            Thread.sleep(2000);
            test.pass(" Truy cập 'Luyện tập' thành công");
        } catch (Exception e) {
            test.fail(" Không truy cập được mục 'Luyện tập'");
        }    
     // Tìm kiếm "Python"
        try {
            WebElement searchInput = driver.findElement(By.id("filter-mentor"));
            searchInput.clear();  // Xóa dữ liệu cũ
            searchInput.sendKeys("Python");
            Thread.sleep(2000);
            test.pass(" Nhập từ khóa 'Python' thành công sau khi xóa dữ liệu cũ");
        } catch (Exception e) {
            test.fail(" Không nhập được từ khóa tìm kiếm");
        }
        // Mở dropdown trạng thái và chọn "Hoàn thành"
        try {
            WebElement statusDropdown = driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div/div/div[3]/div[2]/div[1]/div/div/input"));
            statusDropdown.click();
            Thread.sleep(1000);

            WebElement hoanThanhOption = driver.findElement(By.xpath("//div[contains(text(),'Hoàn thành')]"));
            hoanThanhOption.click();
            Thread.sleep(1000);
            test.pass(" Chọn trạng thái 'Hoàn thành' thành công");
        } catch (Exception e) {
            test.fail(" Không chọn được trạng thái 'Hoàn thành'");
        }

        // Verify kết quả tìm kiếm 
        System.out.println(
            driver.findElements(By.xpath("//div[contains(@class, 'item-container')]//*[contains(text(),'Python')]")).size() > 0
                ? " Có kết quả chứa từ khóa 'Python'"
                : " Không tìm thấy dữ liệu tương ứng với từ khóa tìm kiếm"
        );
    }
    @Test
    public void LuyentapSearch() throws InterruptedException {
        test = extent.createTest("TC02 - Tìm kiếm Python trong Luyện tập không chọn bộ lọc");
        // Nhấn vào "Luyện tập"
        try {
            WebElement luyenTapLink = driver.findElement(By.xpath("//a[text()='Luyện tập']"));
            luyenTapLink.click();
            Thread.sleep(2000);
            test.pass(" Truy cập 'Luyện tập' thành công");
        } catch (Exception e) {
            test.fail(" Không truy cập được mục 'Luyện tập'");
        }
        // Tìm kiếm "Python"
        try {
            WebElement searchInput = driver.findElement(By.id("filter-mentor"));
            searchInput.sendKeys("Python");
            Thread.sleep(2000);
            test.pass(" Nhập từ khóa 'Python' thành công");
        } catch (Exception e) {
            test.fail(" Không nhập được từ khóa tìm kiếm");
        }

        // Verify kết quả tìm kiếm 
        System.out.println(
            driver.findElements(By.xpath("//div[contains(@class, 'item-container')]//*[contains(text(),'Python')]")).size() > 0
                ? " Có kết quả chứa từ khóa 'Python'"
                : " Không tìm thấy dữ liệu tương ứng với từ khóa tìm kiếm"
        );
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
