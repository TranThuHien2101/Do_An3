package HoctapRecapcha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class NewTest {
    public WebDriver driver = null;
    public WebDriverWait wait = null;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void timkiem() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']"))).click();

        WebElement timkiem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-course")));
        timkiem.clear();
        timkiem.sendKeys("Python");

        // Click icon tìm kiếm bằng JavaScript
        WebElement searchIconDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.mantine-TextInput-rightSection")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);

        Thread.sleep(3000); // Chờ kết quả

        logout();
    }

    @Test
    public void timkiemkhongnhaptukhoa() throws InterruptedException {
        driver.navigate().to("https://codelearn.io/home");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']"))).click();

        WebElement timkiem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-course")));
        timkiem.clear();
        timkiem.sendKeys("");

        WebElement searchIconDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.mantine-TextInput-rightSection")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);

        Thread.sleep(3000);

        List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));
        if (!thongBao.isEmpty()) {
            System.out.println("Thông báo 'Không tìm thấy kết quả' xuất hiện.");
        } else {
            System.out.println("Không tìm thấy thông báo 'Không tìm thấy kết quả'.");
        }

        // Quay về trang chủ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/home')]"))).click();
    }

    @Test
    public void timkiemtukhoakhongtontai() throws InterruptedException {
        driver.navigate().to("https://codelearn.io/home");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']"))).click();

        WebElement timkiem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-course")));
        timkiem.clear();
        timkiem.sendKeys("abcxyz");
        timkiem.sendKeys(Keys.ENTER);

        Thread.sleep(3000);

        List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));
        if (!thongBao.isEmpty()) {
            System.out.println("Thông báo 'Không tìm thấy kết quả' xuất hiện.");
        } else {
            System.out.println("Không tìm thấy thông báo 'Không tìm thấy kết quả'.");
        }
    }

    public void logout() throws InterruptedException {
        try {
            WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'user_')]")));
            avatar.click();

            Thread.sleep(2000);

            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng xuất']")));
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Không thể logout: " + e.getMessage());
        }
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
