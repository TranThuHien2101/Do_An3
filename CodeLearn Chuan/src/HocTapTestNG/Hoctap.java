package HocTapTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.ExtentReportManager;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

@Listeners(HocTapTestNG.TestListener.class)
public class Hoctap {

    public WebDriver driver = null;
    WebDriverWait wait = null;

    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void beforeTest(ITestContext context) {
        extent = ExtentReportManager.getInstance();  // Khởi tạo ExtentReports

        System.setProperty("WebDriver.chrome.driver","C:\\chromedriver-win64");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        context.setAttribute("driver", driver);
    }

    @BeforeMethod
    public void setupExtentTest(Method method) // tao 1 test báo cáo trong Extenport
    {
        test = extent.createTest(method.getName(), "Mô tả kiểm thử: " + method.getName());
        Reporter.getCurrentTestResult().setAttribute("extentTest", test);
    }

    public void loginWithValidAccount() throws InterruptedException {
        driver.get("https://codelearn.io/");
        WebDriverWait waitLogin = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginButton = waitLogin.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Đăng nhập']/ancestor::button")));
        loginButton.click();

        waitLogin.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-user-name")))
                .sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");

        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();

        Thread.sleep(3000);
        // Đóng popup nếu có
        try {
            driver.findElement(By.xpath("//button[contains(@class, 'mantine-Modal-close')]")).click();
        } catch (Exception ignored) {
        }
        try {
            driver.findElement(By.xpath("//span[text()='Bỏ qua']/ancestor::button")).click();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void timkiemhople() throws InterruptedException {
        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".mantine-Overlay-root.mantine-Modal-overlay")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']"))).click();
            test.log(Status.PASS, "Click menu 'Học tập' thành công");

            WebElement timkiem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-course")));
            timkiem.sendKeys("Python");
            Thread.sleep(2000);

            WebElement searchIconDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.mantine-TextInput-rightSection")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl().toLowerCase();
            boolean urlContainsPython = currentUrl.contains("python");
            boolean pageContainsPython = driver.getPageSource().toLowerCase().contains("python");
            Assert.assertTrue(urlContainsPython || pageContainsPython, "Không thấy kết quả tìm kiếm liên quan đến 'Python'");

            test.log(Status.PASS, "Tìm kiếm thành công với từ khóa 'Python'");

            logout();

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void timkiemkhongnhaptukhoa() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("");
            Thread.sleep(1000);

            WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không tìm thấy thông báo 'Không tìm thấy kết quả'.");

            test.log(Status.PASS, "Thông báo 'Không tìm thấy kết quả' xuất hiện đúng như kỳ vọng.");

            WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
            Thread.sleep(2000);

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void timkiemtukhoakhongtontai() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("abcxyz");
            Thread.sleep(1000);

            // Có thể dùng Enter thay vì click
            timkiem.sendKeys(Keys.ENTER);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không tìm thấy thông báo 'Không tìm thấy kết quả'.");

            test.log(Status.PASS, "Thông báo 'Không tìm thấy kết quả' xuất hiện đúng như kỳ vọng.");

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }

    public void logout() {
        try {
            WebDriverWait waitLogout = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement avatar = waitLogout.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'user_')]")));
            avatar.click();
            Thread.sleep(2000);

            WebElement logoutButton = waitLogout.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng xuất']")));
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Không thể logout: " + e.getMessage());
        }
    }
    @Test
    public void timkiemtukhoadacbiet() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("@#$%^&*()");
            Thread.sleep(1000);

            WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không tìm thấy thông báo khi nhập ký tự đặc biệt.");

            test.log(Status.PASS, "Tìm kiếm với ký tự đặc biệt hiển thị thông báo đúng.");

            WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
            Thread.sleep(2000);

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }
    @Test
    public void timkiemvoikhoangtrang() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("         ");
            Thread.sleep(1000);

            WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không có thông báo khi tìm với dấu cách.");

            test.log(Status.PASS, "Không tìm thấy kết quả' hiển thị đúng khi tìm với khoảng trắng.");

            WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
            Thread.sleep(2000);

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }
    @Test
    public void timkiemvoituviethoatoanbo() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("JAVA");
            Thread.sleep(1000);

            WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không tìm thấy kết quả khi tìm với từ khóa viết hoa toàn bộ.");

            test.log(Status.PASS, "Tìm kiếm với từ khóa viết hoa cho kết quả đúng.");

            WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
            Thread.sleep(2000);

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }
    @Test
    public void timkiemtukhoakhoangtrangdaucuoi() throws InterruptedException {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            WebElement hocTap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
            hocTap.click();
            Thread.sleep(2000);

            WebElement timkiem = driver.findElement(By.id("search-course"));
            timkiem.clear();
            timkiem.sendKeys("  Java  ");
            Thread.sleep(1000);

            WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
            Thread.sleep(3000);

            List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));

            Assert.assertFalse(thongBao.isEmpty(), "Không tìm thấy kết quả khi từ khóa có khoảng trắng đầu/cuối.");

            test.log(Status.PASS, "Tìm kiếm vẫn trả kết quả đúng khi từ khóa có khoảng trắng thừa.");

            WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
            Thread.sleep(2000);

        } catch (AssertionError ae) {
            test.log(Status.FAIL, "Verify thất bại: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại: " + e.getMessage());
            throw e;
        }
    }
    @AfterTest
    public void afterTest() throws Exception {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flushReport();  // Ghi báo cáo ra file
    }
}
