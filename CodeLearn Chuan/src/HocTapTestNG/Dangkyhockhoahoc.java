package HocTapTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.ExtentReportManager;

import java.time.Duration;

@Listeners(HocTapTestNG.TestListener.class)
public class Dangkyhockhoahoc {
    public WebDriver driver = null;
    WebDriverWait wait = null;
    Actions action;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void beforeTest(ITestContext context) {
        extent = ExtentReportManager.getInstance();
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
    }

    public void loginWithValidAccount() throws InterruptedException {
        driver.get("https://codelearn.io/");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='\u0110\u0103ng nh\u1eadp']/ancestor::button")));
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-user-name"))).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("mantine-Overlay-root")));
        } catch (TimeoutException ignored) {}

        try {
            WebElement closeBtn = driver.findElement(By.xpath("//button[contains(@class, 'mantine-Modal-close')]"));
            if (closeBtn.isDisplayed()) closeBtn.click();
        } catch (NoSuchElementException ignored) {}

        try {
            WebElement skipBtn = driver.findElement(By.xpath("//span[text()='Bỏ qua']/ancestor::button"));
            if (skipBtn.isDisplayed()) skipBtn.click();
        } catch (NoSuchElementException ignored) {}
    }

    @Test
    public void dangkykhoahoc() {
        test = extent.createTest("TC01: Kiểm thử Đăng ký khóa học", "Hover vào khóa học và đăng ký thành công");

        try {
            loginWithValidAccount();
            WebElement hocTapMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));

            // Đảm bảo overlay không chặn trước khi click
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("mantine-Overlay-root")));

            hocTapMenu.click();
            test.log(Status.PASS, "Click menu 'Học tập' thành công.");

            WebElement khoaHoc = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//h1[text()='Hoàn thiện ứng dụng web thực tế với C# và .NET Core']")));
            Assert.assertEquals(khoaHoc.getText(), "Hoàn thiện ứng dụng web thực tế với C# và .NET Core");
            khoaHoc.click();
            test.log(Status.PASS, "Click vào tiêu đề khóa học thành công.");

            WebElement btnHocThu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Học thử')]")));
            btnHocThu.click();
            test.log(Status.PASS, "Click nút 'Học thử' thành công.");

            WebElement quayVeHocTap = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='breadcrumbs']//a[contains(@href,'/learning')]")));
            quayVeHocTap.click();
            test.log(Status.PASS, "Quay về trang 'Học tập' thành công.");

        } catch (Exception e) {
            test.log(Status.FAIL, "Lỗi kiểm thử: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void hockhoahocmienphi() {
        test = extent.createTest("TC02: Kiểm thử Đăng ký khóa học miễn phí", "Hover vào khóa học và đăng ký thành công");

        try {
            WebElement hocTapMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("mantine-Overlay-root")));
            hocTapMenu.click();
            test.log(Status.PASS, "Click menu 'Học tập' thành công.");

            WebElement courseElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[text()='Java cơ bản']")));
            courseElement.click();
            test.log(Status.PASS, "Click vào khóa học Java cơ bản thành công.");

            WebElement courseTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(), 'Java cơ bản')]")));
            Assert.assertTrue(courseTitle.isDisplayed());
            test.log(Status.PASS, "Trang chi tiết khóa học hiển thị đúng.");

            WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Vào học ngay')]")));
            startButton.click();
            test.log(Status.PASS, "Click nút 'Vào học ngay' thành công.");

            WebElement filterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Tìm kiếm bài học']")));
            Assert.assertTrue(filterInput.isDisplayed());
            test.log(Status.PASS, "Trang học hiển thị thành công.");
            driver.navigate().refresh();

            WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Chạy thử')]")));
            runButton.click();
            test.log(Status.PASS, "Click nút 'Chạy thử' thành công.");

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Compile code failed')]")));
            Assert.assertTrue(errorMessage.isDisplayed());

            WebElement detailedError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'illegal start of expression')]")));
            Assert.assertTrue(detailedError.isDisplayed());
            test.log(Status.PASS, "Hiển thị lỗi biên dịch đúng như mong đợi.");

        } catch (Exception e) {
            test.log(Status.FAIL, "Lỗi kiểm thử đăng ký khóa học: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @AfterTest
    public void afterTest() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flushReport();
    }
}
