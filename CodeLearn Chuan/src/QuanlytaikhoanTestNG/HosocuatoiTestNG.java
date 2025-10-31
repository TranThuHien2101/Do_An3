package QuanlytaikhoanTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(QuanlytaikhoanTestNG.TestListener.class)
public class HosocuatoiTestNG extends BaseTest {

    @Test
    public void Botrongnoidung() throws InterruptedException {
        test = extent.createTest("TC01 - Bỏ trống nội dung phần giới thiệu");

        loginWithValidAccount();
        test.pass(" Đăng nhập thành công");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Chờ đến khi ảnh avatar có thể click được, rồi mới click
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@src,'user_')]")));
        avatar.click();

        // Chờ menu xổ xuống và click vào "Hồ sơ của tôi"
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[text()='Hồ sơ của tôi']")));
        profileButton.click();
     // Click vào biểu tượng chỉnh sửa (icon hình bút)
        try {
        	WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("/html/body/div[1]/div[1]/main/div/div/div[2]/div[1]/div/div[8]/div/svg/path[2]")
        	    ));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", editIcon);
            test.pass(" Click vào icon chỉnh sửa thành công");
        } catch (Exception e) {
            test.fail(" Không thể click vào icon chỉnh sửa: " + e.getMessage());
            Assert.fail("Không thể click vào icon chỉnh sửa");
        }

        // Click check box 
        try {
            // Tìm phần tử checkbox
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("mantine-ajmyn0dlc")));

            // Kiểm tra nếu checkbox đã được chọn (checked)
            if (checkbox.isSelected()) {
                test.pass(" Checkbox đã được chọn đúng như mong đợi.");
            } else {
                test.fail(" Checkbox chưa được chọn.");
                Assert.fail("Checkbox chưa được chọn.");
            }
        } catch (Exception e) {
            test.fail(" Không tìm thấy hoặc không kiểm tra được trạng thái checkbox: " + e.getMessage());
            Assert.fail("Không tìm thấy checkbox");
        }

        // Click nút Lưu
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[span[text()='Lưu']]")));
            saveBtn.click();
            test.pass(" Click nút Lưu");
        } catch (Exception e) {
            test.fail(" Không thể click nút Lưu");
            Assert.fail();
        }

        // Xác minh có hiện thông báo lỗi
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[@class='mantine-UnstyledButton-root mantine-Button-root mantine-1kju35q']")));
            Assert.assertTrue(errorMsg.isDisplayed());
            test.pass(" Cập nhật thành công!");
        } catch (Exception e) {
            test.fail(" Không cập nhật được thông tin ");
            Assert.fail("Không có cảnh báo khi bỏ trống phần giới thiệu");
        }
    }
}
