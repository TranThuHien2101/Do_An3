package HocTapTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menukhoahoc {

    public WebDriver driver = null;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public void loginWithValidAccount() throws InterruptedException {
        driver.get("https://codelearn.io/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Nhấn nút "Đăng nhập"
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Đăng nhập']/ancestor::button")
        ));
        loginButton.click();

        // Đợi hiện form và nhập email + mật khẩu
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-user-name"))).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");

        // Nhấn nút đăng nhập
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();

        // Các xử lý popup giữ nguyên
        Thread.sleep(3000);
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(@class, 'mantine-Modal-close')]"));
            closeButton.click();
        } catch (Exception e) {}

        try {
            WebElement skipButton = driver.findElement(By.xpath("//span[text()='Bỏ qua']/ancestor::button"));
            skipButton.click();
        } catch (Exception e) {}
    }

    @Test
    public void dieuhuong() throws InterruptedException {
        loginWithValidAccount();

        // Click vào "Học tập"
        WebElement hocTap = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
        hocTap.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

            // Chờ nút visible và clickable
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Scroll tới nút để chắc chắn nút nằm trong viewport
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            // Chờ một chút nữa trước khi click
            Thread.sleep(1500);

            try {
                button.click();
            } catch (Exception e) {
                // Nếu click thường lỗi thì click bằng JS
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            // Chờ sau click để menu load
            Thread.sleep(2000);
        }
    }

    @AfterTest
    public void afterTest() throws Exception {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}
