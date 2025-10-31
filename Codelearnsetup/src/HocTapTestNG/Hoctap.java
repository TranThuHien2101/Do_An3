package HocTapTestNG;

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
import org.testng.Assert;
import org.testng.annotations.*;

public class Hoctap {
    public WebDriver driver = null;
    WebDriverWait wait = null;


    @BeforeTest
    public void beforeTest() {
        System.setProperty("WebDriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
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

        //  xử lý popup giữ nguyên
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
    // Tìm kiếm từ khóa hợp lệ
    public void timkiem() throws InterruptedException {
        // Gọi hàm đăng nhập
        loginWithValidAccount();

        // Click vào "Học tập"
        WebElement hocTap = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Học tập']")));
        hocTap.click();

        // Gõ từ khóa tìm kiếm vào ô tìm kiếm
        WebElement timkiem = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("search-course")));
        timkiem.sendKeys("Python");
        Thread.sleep(2000);

        // Tìm div chứa icon tìm kiếm, dùng JavaScript để click vào icon tìm kiếm
        WebElement searchIconDiv = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.mantine-TextInput-rightSection")));

        // Dùng JavaScript click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
        Thread.sleep(3000);
        logout();
   }
    public void logout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[contains(@src,'user_')]")));
            avatar.click();
            Thread.sleep(2000);

            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Đăng xuất']")));
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Không thể logout: " + e.getMessage());
        }
    }
    @Test
    // Nhấn tìm kiếm khi không nhập từ khóa
    public void timkiemkhongnhaptukhoa() throws InterruptedException {
        driver.navigate().refresh(); 
        Thread.sleep(2000);

        // Click vào "Học tập"
        WebElement hocTap = driver.findElement(By.xpath("//a[text()='Học tập']"));
        hocTap.click();
        Thread.sleep(2000); 

        // Gõ từ khóa tìm kiếm vào ô tìm kiếm
        WebElement timkiem = driver.findElement(By.id("search-course"));
        timkiem.clear(); 
        timkiem.sendKeys("");
        Thread.sleep(1000); 
        // Click icon tìm kiếm bằng JavaScript
        WebElement searchIconDiv = driver.findElement(By.cssSelector("div.mantine-TextInput-rightSection"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIconDiv);
        Thread.sleep(3000); // Chờ kết quả hiển thị

        // Kiểm tra sự tồn tại của thông báo
        List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));
        if (!thongBao.isEmpty()) {
            System.out.println("Thông báo 'Không tìm thấy kết quả' xuất hiện.");
        } else {
            System.out.println("Không tìm thấy thông báo 'Không tìm thấy kết quả'.");
        }

        // Quay về trang chủ
        WebElement logoHome = driver.findElement(By.xpath("//a[contains(@href, '/home')]")); // hoặc logo chính
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHome);
        Thread.sleep(2000);
    }

    @Test
 // Tìm kiếm từ khóa không tồn tại
 public void timkiemtukhoakhongtontai() throws InterruptedException {
    	driver.navigate().refresh(); 
        Thread.sleep(2000);
     // Click vào "Học tập"
     WebElement hocTap = driver.findElement(By.xpath("//a[text()='Học tập']"));
     hocTap.click();
     Thread.sleep(2000); // Chờ trang tải

     // Gõ từ khóa tìm kiếm vào ô tìm kiếm
     WebElement timkiem = driver.findElement(By.id("search-course"));
     timkiem.clear();
     timkiem.sendKeys("abcxyz");
     Thread.sleep(1000); // Chờ nhập liệu

     WebElement timkiem1 = driver.findElement(By.id("search-course"));
     timkiem1.clear();
     timkiem1.sendKeys("");
     Thread.sleep(1000);
     
     timkiem1.sendKeys(Keys.ENTER);

     Thread.sleep(3000); 

     List<WebElement> thongBao = driver.findElements(By.xpath("//*[contains(text(),'Không tìm thấy kết quả')]"));
     if (!thongBao.isEmpty()) {
         System.out.println("Thông báo 'Không tìm thấy kết quả' xuất hiện.");
     } else {
         System.out.println("Không tìm thấy thông báo 'Không tìm thấy kết quả'.");
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
