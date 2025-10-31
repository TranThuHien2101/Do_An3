package HocTapTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.ExtentReportManager;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

@Listeners(HocTapTestNG.TestListener.class)
public class Xemchitietkhoahoc {

    public WebDriver driver = null;
    WebDriverWait wait = null;

    ExtentReports extent;
    ExtentTest test;
    @BeforeClass
    public void beforeClass(ITestContext context)// log thong tin ket qua test 
    {
    	extent = ExtentReportManager.getInstance();  // Khởi tạo ExtentReports

        System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
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
    public void xemkhoahoc() throws InterruptedException {
        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".mantine-Overlay-root.mantine-Modal-overlay")));
            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Học tập']"))).click();
            test.log(Status.PASS, "Click menu 'Học tập' thành công");

            WebElement khoaHoc = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h1[contains(text(), 'Scratch Nâng Cao')]")));
            khoaHoc.click();
            test.log(Status.PASS, "Đã click vào khóa học 'Scratch Nâng Cao'");

            //  VERIFY: Kiểm tra đã chuyển sang trang chi tiết khóa học
            boolean openedCorrectCourse = wait.until(ExpectedConditions.urlContains("scratch-nang-cao"));
            if (openedCorrectCourse) {
                test.log(Status.PASS, "Đã chuyển tới trang chi tiết khóa học thành công");
            } else {
                test.log(Status.FAIL, "Không chuyển đến trang chi tiết khóa học 'Scratch Nâng Cao'");
                Assert.fail("Không mở được trang chi tiết của khóa học 'Scratch Nâng Cao'");
            }
         // Click vào nút Giáo trình bằng XPath
            WebElement giaotrinhButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div/div/div[3]/div[2]/div/div/div[2]"));
            giaotrinhButton.click();

            // Chờ trang tải và xuất hiện dòng "Nội dung khóa học"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement courseContentText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Nội dung khóa học')]")
            ));

            // Xác minh text đúng
            String actualText = courseContentText.getText();
            Assert.assertTrue(actualText.contains("Nội dung khóa học"), "Không tìm thấy dòng 'Nội dung khóa học'");

            System.out.println("Đã vào đúng trang và hiển thị nội dung khóa học.");
         // Click vào tab "Đánh giá"
            WebElement danhGiaTab = driver.findElement(By.xpath("//div[contains(text(),'Đánh giá') and contains(@class,'cursor-pointer')]"));
            danhGiaTab.click();
         // Đợi dòng chữ "Đánh giá" xuất hiện sau khi chuyển tab
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement danhGiaText = wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Đánh giá')]")
            ));
         // Kiểm tra nội dung text
            String actualText1 = danhGiaText.getText();
            Assert.assertTrue(actualText1.contains("Đánh giá"), " Không tìm thấy dòng 'Đánh giá' sau khi click");

            System.out.println(" Đã click vào Đánh giá và hiển thị đúng nội dung.");
   
         // Click vào tab "Chứng chỉ"
            WebElement chungChiTab = driver.findElement(By.xpath("//div[contains(text(),'Chứng chỉ') and contains(@class,'cursor-pointer')]"));
            chungChiTab.click();

            // Chờ nội dung văn bản xuất hiện
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement chungChiText = wait2.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'giấy chứng nhận hoàn thành khóa học')]")
            ));

            // Xác minh nội dung đúng
            String expected = "Bạn sẽ nhận được giấy chứng nhận hoàn thành khóa học sau khi hoàn thành tối thiểu 80% nội dung khóa học.";
            String actual = chungChiText.getText();
            Assert.assertTrue(actual.contains(expected), " Không tìm thấy nội dung chứng chỉ mong muốn!");

            System.out.println(" Đã click vào tab Chứng chỉ và xác minh nội dung thành công.");
         // Click vào tab "Mã ưu đãi của tôi"
            WebElement maUuDaiTab = driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div/div/div[3]/div[2]/div/div/div[5]"));
            maUuDaiTab.click();

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
        ExtentReportManager.flushReport(); 
    }
}

