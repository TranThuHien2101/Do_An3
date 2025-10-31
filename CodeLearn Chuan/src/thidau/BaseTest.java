package thidau;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReportManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver = null;
    WebDriverWait wait = null;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void beforeTest(ITestContext context) {
        extent = ExtentReportManager.getInstance();
        System.setProperty("WebDriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
    }

    public void loginWithValidAccount() throws InterruptedException {
        driver.get("https://codelearn.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Đăng nhập')]")));
            loginBtn.click();
        } catch (Exception e) {
            throw new RuntimeException("Không tìm thấy nút Đăng nhập", e);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-user-name"))).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();

        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'mantine-Modal-close')]")));
            closeButton.click();
        } catch (Exception ignored) {}

        try {
            WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Bỏ qua']/ancestor::button")));
            skipButton.click();
        } catch (Exception ignored) {}
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
