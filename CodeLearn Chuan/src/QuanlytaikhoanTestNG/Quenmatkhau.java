package QuanlytaikhoanTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
@Listeners(QuanlytaikhoanTestNG.TestListener.class)
public class Quenmatkhau extends BaseTest {
    
    @Test
    public void loginWithValidAccount() throws InterruptedException {
    	test = extent.createTest("TC11 -  Quên mật khẩu không nhập email");
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
      	test = extent.createTest("TC12 -  Quên mật khẩu email không tồn tại ");
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
}

