package QuanlytaikhoanTestNG;

import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(QuanlytaikhoanTestNG.TestListener.class)
public class ThongtintaikhoanTestNG extends BaseTest {

    @Test
    public void Capnhathotenthanhcong() throws InterruptedException {
        test = extent.createTest("TC44 - Cập nhật họ tên thành công");
        loginWithValidAccount(); 
     // Xác minh đăng nhập thành công
        try {
            WebDriverWait waitLogin = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement avatar = waitLogin.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@src,'user_')]")
                )
            );
            Assert.assertTrue(avatar.isDisplayed());
            test.pass("Đăng nhập thành công.");
        } catch (Exception e) {
            test.fail("Đăng nhập thất bại.");
            Assert.fail("Đăng nhập thất bại.");
        }
        // Click vào ảnh đại diện tài khoản
        driver.findElement(By.xpath("//img[contains(@src,'user_')]")).click();
        Thread.sleep(1000);

        // Click vào mục "Thông tin tài khoản"
        driver.findElement(By.xpath("//div[contains(text(),'Thông tin tài khoản')]")).click();
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Thông tin"));
        test.pass("Đã tìm thấy văn bản 'Thông tin' trên trang.");
        test.fail("không tìm thấy văn bản 'Thông tin' trên trang.");
        // Click nút cập nhật
        driver.findElement(By.xpath("//span[text()='Cập nhật']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Xóa và nhập lại họ tên
        WebElement fullNameInput = driver.findElement(By.xpath("//input[@placeholder='Nhập họ và tên']"));
        fullNameInput.sendKeys(Keys.CONTROL + "a");
        fullNameInput.sendKeys(Keys.DELETE);
        Thread.sleep(1000);
        fullNameInput.sendKeys("Tran Thu Hien");

        // Xóa và chọn lại quận huyện
        WebElement quanHuyenInput = driver.findElement(By.xpath("//input[@placeholder='Chọn quận huyện']"));
        quanHuyenInput.click();
        quanHuyenInput.sendKeys(Keys.CONTROL + "a");
        quanHuyenInput.sendKeys(Keys.DELETE);
        Thread.sleep(500);
        quanHuyenInput.sendKeys("Kim Động");

        WebElement quanHuyenOption = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Huyện Kim Động')]"))
        );
        quanHuyenOption.click();

        // Xóa và chọn lại khối
        WebElement khoiInput = driver.findElement(By.xpath("//input[@placeholder='Chọn khối']"));
        khoiInput.click();
        khoiInput.sendKeys(Keys.CONTROL + "a");
        khoiInput.sendKeys(Keys.DELETE);
        Thread.sleep(500);
        khoiInput.sendKeys("Khối 1");

        WebElement khoiOption = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Khối 1')]"))
        );
        khoiOption.click();

        // Xóa và chọn lại trường học
        WebElement truongInput = driver.findElement(By.xpath("//input[@placeholder='Chọn trường học']"));
        truongInput.click();
        truongInput.sendKeys(Keys.CONTROL + "a");
        truongInput.sendKeys(Keys.DELETE);
        Thread.sleep(500);
        truongInput.sendKeys("Trường TH&THCS Nhân La");

        WebElement truongOption = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Trường TH') and contains(text(),'Nhân La')]"))
        );
        truongOption.click();

        // Click checkbox đồng ý
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='mantine-1vf457v mantine-Checkbox-inner']"));
        checkbox.click();

        // Click nút Lưu
        WebElement updateButton = driver.findElement(By.xpath("//button[@type='submit']//div[@class='mantine-1wpc1xj mantine-Button-inner']"));
        updateButton.click();
        Thread.sleep(2000);

        // Xác minh thông báo thành công
        try {
            WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(" //div[@class='mantine-Text-root break-words mantine-Notification-description mantine-dvh1xz']")
                )
            );
            Assert.assertTrue(successMessage.isDisplayed());
            test.pass("Cập nhật thông tin thành công!");
        } catch (Exception e) {
            test.fail("Không tìm thấy thông báo cập nhật thành công.");
            Assert.fail("Không tìm thấy thông báo cập nhật thành công.");
        }
    }
}
