package QuanlytaikhoanTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.time.Duration;

public class Quenmatkhau {
    public WebDriver driver = null;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    // TC11: Quên mật khẩu không nhập email
    @Test
    public void loginWithValidAccount() throws InterruptedException {
        // Mở trang CodeLearn
        driver.get("https://codelearn.io/");
        Thread.sleep(2000); // Chờ 2 giây để trang tải xong

        // Nhấn nút "Đăng nhập"
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(4000); // Chờ 2 giây để trang chuyển tiếp

        // Quên mật khẩu 
        WebElement forgotPasswordButton = driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[4]/div[1]/div"));
        forgotPasswordButton.click();
    
        //Đặt mật khẩu
        WebElement resetButton = driver.findElement(By.xpath("//span[text()='Đặt lại mật khẩu']"));
        resetButton.click();

        // Kiểm tra thông báo lỗi xuất hiện
        WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Trường này là bắt buộc, không được để trống']"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Dòng thông báo lỗi xuất hiện.");
        } else {
            System.out.println("Dòng thông báo lỗi không xuất hiện.");
        }

    }
    // Quên mật khẩu email không tồn tại 
    @Test
    public void Emailkhongtontai() throws InterruptedException {
        // Mở trang CodeLearn
        driver.get("https://codelearn.io/");
        Thread.sleep(2000); // Chờ 2 giây để trang tải xong

        // Nhấn nút "Đăng nhập"
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(4000); // Chờ 2 giây để trang chuyển tiếp

        // Quên mật khẩu 
        WebElement forgotPasswordButton = driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[4]/div[1]/div"));
        forgotPasswordButton.click();
        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Nhập tên đăng nhập hoặc email']"));
        emailInput.sendKeys("tranhien@gmail.com");

    
        //Đặt mật khẩu
        WebElement resetButton = driver.findElement(By.xpath("//span[text()='Đặt lại mật khẩu']"));
        resetButton.click();
        // Kiểm tra thông báo xuất hiện
        WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Tài khoản không tồn tại!']"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Dòng thông báo lỗi 'Tài khoản không tồn tại!' xuất hiện.");
        } else {
            System.out.println("Dòng thông báo lỗi 'Tài khoản không tồn tại!' không xuất hiện.");
        }

       
    }
    // 
    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
