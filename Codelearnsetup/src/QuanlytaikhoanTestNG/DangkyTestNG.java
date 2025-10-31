package QuanlytaikhoanTestNG;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class DangkyTestNG {

    public WebDriver driver = null;

    @BeforeTest
    public void beforeTest() {
    	System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @Test
    // TC01: Đăng ký bằng Microsoft
    public void DangKyBangMicrosoft() throws Exception {
        driver.get("https://codelearn.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Đăng ký']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Microsoft']"))).click();

        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL Microsoft: " + currentUrl);

      
    }

    @Test
 // TC02: Đăng ký bằng Google
    public void DangKyBangGoogle() throws Exception {
        driver.get("https://codelearn.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Đăng ký']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[2]//span[2][text()='Google']"))).click();

        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        System.out.println("URL Google: " + url);

    }

    @Test
    // TC03: Đăng ký bằng Github  
    public void DangKyBangGithub() throws Exception {
        driver.get("https://codelearn.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Đăng ký']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Github']"))).click();

        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL GitHub: " + currentUrl);
    }
    @Test
    //TC24:  Để trống trường tài khoản
    public void testDangKy_DeTrongTenTaiKhoan() throws InterruptedException {
        driver.get("https://codelearn.io/");

        // Chờ trang tải
        Thread.sleep(3000);

        // Click vào nút "Đăng ký"
        driver.findElement(By.xpath("//span[text()='Đăng ký']")).click();

        // Chờ form đăng ký hiển thị
        Thread.sleep(2000);

        // ĐỂ TRỐNG trường "Tên tài khoản"
        // Nhập email và mật khẩu
        driver.findElement(By.name("email")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input"))
        .sendKeys("Hien21012004");

        // Tick checkbox "Đồng ý điều khoản"
        WebElement checkbox = driver.findElement(By.name("agree"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        // Nhấn nút "Đăng ký"
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/button")).click();
        Thread.sleep(3000);
    }
    // TC29: Để trống trường Email
    @Test
    public void testDangKy_DeTrongEmail() throws InterruptedException {
        driver.get("https://codelearn.io/");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        Thread.sleep(3000);

        // Click vào nút "Đăng ký"
        driver.findElement(By.xpath("//span[text()='Đăng ký']")).click();

        // Chờ form đăng ký hiển thị
        Thread.sleep(2000);
        //  trường "Tên tài khoản"
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[1]/div/input")));
        usernameInput.sendKeys("tranthuhien");
        
        // Nhập email và mật khẩu
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input"))
        .sendKeys("Hien21012004");

        // Tick checkbox "Đồng ý điều khoản"
        WebElement checkbox = driver.findElement(By.name("agree"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        // Nhấn nút "Đăng ký"
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/button")).click();
        Thread.sleep(3000);
    }
 // TC29: Để trống trường Email
    @Test
    public void testDangKy_DeTrongmatkhau() throws InterruptedException {
        driver.get("https://codelearn.io/");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        Thread.sleep(3000);

        // Click vào nút "Đăng ký"
        driver.findElement(By.xpath("//span[text()='Đăng ký']")).click();

        // Chờ form đăng ký hiển thị
        Thread.sleep(2000);
        //  trường "Tên tài khoản"
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[1]/div/input")));
        usernameInput.sendKeys("tranthuhien");
        
        // Nhập email và mật khẩu
        driver.findElement(By.name("email")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input"))
        .sendKeys("");

        // Tick checkbox "Đồng ý điều khoản"
        WebElement checkbox = driver.findElement(By.name("agree"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        // Nhấn nút "Đăng ký"
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/button")).click();
        Thread.sleep(3000);
    }

    @Test
    // Đăng ký tài khoản không nhấn checkbox
    public void DangKyTaiKhoan() throws Exception {
        driver.get("https://codelearn.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Đăng ký']"))).click();

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[1]/div/input")));
        usernameInput.sendKeys("tranthuhien");

        driver.findElement(By.name("email")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input"))
              .sendKeys("Hien21012004");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/button")).click();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL sau đăng ký: " + currentUrl);

    }
    // Email sai định dạng 
    // TC29: Để trống trường Email
    @Test
    public void testDangKy_saiEmail() throws InterruptedException {
        driver.get("https://codelearn.io/");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        Thread.sleep(3000);

        // Click vào nút "Đăng ký"
        driver.findElement(By.xpath("//span[text()='Đăng ký']")).click();

        // Chờ form đăng ký hiển thị
        Thread.sleep(2000);
        //  trường "Tên tài khoản"
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[1]/div/input")));
        usernameInput.sendKeys("tranthuhien");
        
        // Nhập email và mật khẩu
        driver.findElement(By.name("email")).sendKeys("abc@.com");
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input"))
        .sendKeys("Hien21012004");

        // Tick checkbox "Đồng ý điều khoản"
        WebElement checkbox = driver.findElement(By.name("agree"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        // Nhấn nút "Đăng ký"
        driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/button")).click();
        Thread.sleep(3000);
    }
    
    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
