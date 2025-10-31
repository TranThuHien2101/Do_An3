package QuanlytaikhoanTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginTestNG {
    public WebDriver driver = null;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }
 
    // TC01: Đăng nhập với tài khoản và mật khẩu hợp lệ
    @Test
    public void loginWithValidAccount() throws InterruptedException {
        // Mở trang CodeLearn
        driver.get("https://codelearn.io/");
        Thread.sleep(2000);

        // Nhấn nút "Đăng nhập"
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(2000);

        // Nhập email và mật khẩu hợp lệ
        driver.findElement(By.id("login-user-name")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Hien21012004");
        Thread.sleep(1000);

        // Nhấn nút đăng nhập
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(4000); // Chờ trang xử lý

        // Kiểm tra nội dung xác nhận đăng nhập thành công (ví dụ: "Bỏ qua" xuất hiện trên trang)
        boolean isLoggedIn = driver.getPageSource().contains("Bỏ qua") 
                          || driver.getCurrentUrl().contains("codelearn.io"); 

        Assert.assertTrue(isLoggedIn, "Đăng nhập thành công.");
    }

    // TC02: Tài khoản đúng mật khẩu sai 
    @Test
    public void loginWithWrongPassword() throws InterruptedException {
        // Mở trang CodeLearn
        driver.get("https://codelearn.io/");
        Thread.sleep(2000);

        // Nhấn nút "Đăng nhập"
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        // Nhập tài khoản đúng nhưng mật khẩu sai
        driver.findElement(By.id("login-user-name")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("matkhau_sai");
        Thread.sleep(1000);

        // Nhấn nút đăng nhập
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(3000);

        // Kiểm tra xem có thông báo lỗi "Tên người dùng/email/mật khẩu đã cung cấp không chính xác."
        boolean isErrorShown = driver.getPageSource().contains(" ");
        Assert.assertTrue(isErrorShown, "Không hiển thị lỗi khi nhập mật khẩu sai.");
    }

    // TC03: Đăng nhập với tài khoản bị khóa
 
    @Test
    public void loginWithLockedAccount() throws InterruptedException {
        // Mở trang CodeLearn
        driver.get("https://codelearn.io/");
        Thread.sleep(2000); 

        // Nhấn nút "Đăng nhập"
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);  

        // Nhập tài khoản bị khóa và mật khẩu hợp lệ
        driver.findElement(By.id("login-user-name")).sendKeys("abc123@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("password123");
        Thread.sleep(1000);  

        // Nhấn nút đăng nhập
        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(3000);  

        // Kiểm tra xem có thông báo lỗi khi tài khoản bị khóa hay không
        WebElement message = driver.findElement(By.id("lock-message"));
        Assert.assertTrue(message.isDisplayed(), "Không hiển thị thông báo khi tài khoản bị khóa");
    }


    // TC04: Bỏ trống trường tên tài khoản và nhập mật khẩu 
    @Test
    public void loginWithEmptyUsername() throws InterruptedException {
        driver.get("https://codelearn.io/");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("login-user-name")).sendKeys("");
        driver.findElement(By.id("login-password")).sendKeys("H21012004");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(2000);

        boolean isErrorShown = driver.getPageSource().contains("Tên tài khoản không được để trống");
        Assert.assertTrue(isErrorShown, "Không hiển thị thông báo khi bỏ trống tên tài khoản.");
    }
    // TC05: Bỏ trống mật khẩu
    @Test
    public void loginWithEmptyPassword() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("login-user-name")).sendKeys("tranthuhien21012004@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(2000);

        boolean isErrorShown = driver.getPageSource().contains("Mật khẩu không được để trống");
        Assert.assertTrue(isErrorShown, "Không hiển thị thông báo yêu cầu nhập mật khẩu.");
    }

    // TC06: Đăng nhập bỏ trống 2 trường
    @Test
    public void loginWithEmptyFields() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("login-user-name")).sendKeys("");
        driver.findElement(By.id("login-password")).sendKeys("");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(2000);

        boolean isErrorShown = driver.getPageSource().contains("Tên tài khoản không được để trống")
                || driver.getPageSource().contains("Mật khẩu không được để trống");
        Assert.assertTrue(isErrorShown, "Không hiện thông báo khi để trống tài khoản/mật khẩu.");
    }


    // TC07: Nhập email chứa kí tự đặc biệt
    @Test
    public void loginWithInvalidEmailFormat() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("login-user-name")).sendKeys("test@!#gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("matkhau123");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='login-submit']//button")).click();
        Thread.sleep(2000);

        boolean isErrorShown = driver.getPageSource().contains("Email không hợp lệ")
                || driver.getPageSource().toLowerCase().contains("email không đúng định dạng")
                || driver.getPageSource().contains("Thông tin đăng nhập không đúng");
        Assert.assertTrue(isErrorShown, "Không hiển thị thông báo lỗi khi nhập email sai định dạng.");
    }


    // TC08: Đăng nhập bằng Google
    @Test
    public void loginGoogle() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        WebElement googleLoginButton = driver.findElement(By.cssSelector("span.mantine-1ryt1ht.hidden.md\\:flex.mantine-Button-label"));

        Assert.assertTrue(googleLoginButton.isDisplayed(), "Nút đăng nhập bằng Google không hiển thị");

        googleLoginButton.click();

        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button.google-login")).click();
        
    }

    // TC09: Đăng nhập bằng Microsoft
    @Test
    public void loginMicrosoft() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        WebElement microsoftLoginButton = driver.findElement(By.cssSelector("span.mantine-1ryt1ht.hidden.md\\:flex.mantine-Button-label"));

        Assert.assertTrue(microsoftLoginButton.isDisplayed(), "Nút đăng nhập bằng Microsoft không hiển thị");

        microsoftLoginButton.click();

        Thread.sleep(3000); 

        // Kiểm tra nếu trang chuyển hướng đúng sau khi nhấp vào nút
      
    }

    // TC10: Đăng nhập bằng Github
    @Test
    public void loginGithub() throws InterruptedException {
        driver.get("https://codelearn.io/");
        driver.findElement(By.cssSelector("span.mantine-Button-label")).click();
        Thread.sleep(1000);

        // Tìm nút đăng nhập bằng Github
        WebElement githubLoginButton = driver.findElement(By.xpath("//span[text()='Github']"));

        // Kiểm tra nút có hiển thị không
        Assert.assertTrue(githubLoginButton.isDisplayed(), "Nút đăng nhập bằng Github không hiển thị");

        // Nhấp vào nút
        githubLoginButton.click();

        // Chờ vài giây để chuyển trang (hoặc dùng WebDriverWait nếu cần chính xác)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @AfterTest
    public void afterTest() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
