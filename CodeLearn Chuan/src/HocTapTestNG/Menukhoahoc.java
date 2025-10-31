package HocTapTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.ExtentReportManager;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;
import java.time.Duration;
import java.util.List;
import org.testng.ITestContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

@Listeners(HocTapTestNG.TestListener.class)
public class Menukhoahoc {

    public WebDriver driver = null;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void beforeTest(ITestContext context) {
        extent = ExtentReportManager.getInstance(); // khởi tạo báo cáo
        driver = new ChromeDriver();
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
    public void dieuhuong() throws InterruptedException {
        test = extent.createTest("Kiểm thử Menu Học tập", "Test các mục trong menu Học tập trên CodeLearn");

        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");
        } catch (Exception e) {
            test.log(Status.FAIL, "Đăng nhập thất bại: " + e.getMessage());
            return;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement hocTap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Học tập']")));
            wait.until(ExpectedConditions.elementToBeClickable(hocTap)).click();
            test.log(Status.PASS, "Click menu 'Học tập' thành công.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Không click được menu 'Học tập': " + e.getMessage());
        }

        String[] menuItems = {
            "Thuật toán",
            "Kiến thức cơ sở",
            "Lập trình cơ sở",
            "Lập trình nâng cao",
            "Giải quyết vấn đề",
            "Kĩ năng nâng cao",
            "Kiến thức khác"
        };

        for (String item : menuItems) {
            String xpath = "//button[.//span[text()='" + item + "']]";
            try {
                List<WebElement> overlays = driver.findElements(By.cssSelector(".mantine-Overlay-root"));
                if (!overlays.isEmpty()) {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".mantine-Overlay-root")));
                }

                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
                Thread.sleep(1000);
                try {
                    button.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                }

                Thread.sleep(1500);
                List<WebElement> headings = driver.findElements(By.xpath("//h2"));
                boolean matched = headings.stream().anyMatch(h -> h.getText().contains(item));

                if (matched) {
                    test.log(Status.PASS, " Mục '" + item + "' hiển thị đúng.");
                } else {
                    test.log(Status.WARNING, " Không tìm thấy tiêu đề đúng cho mục: " + item);
                }

            } catch (Exception e) {
                test.log(Status.FAIL, " Lỗi khi xử lý mục: " + item + " → " + e.getMessage());
            }
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
