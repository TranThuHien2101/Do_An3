package thidau;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import QuanlytaikhoanTestNG.BaseTest;
import utils.ExtentReportManager;

@Listeners(thidau.TestListener.class)
public class xemthongtincuocthi extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("xem thông tin cuộc thi");

        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Click logo trang chủ
            WebElement homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
            homeLogo.click();
            test.log(Status.PASS, "Click logo trang chủ thành công");

            wait.until(ExpectedConditions.urlContains("codelearn.io"));
            Assert.assertTrue(driver.getCurrentUrl().contains("codelearn.io"), "Chưa vào trang Home");
            test.log(Status.PASS, "Đã vào trang Home với URL đúng");
            
            // Click vào tab "Thi đấu"
            WebElement thiDauButton = driver.findElement(By.xpath("//a[text()='Thi đấu']"));
            thiDauButton.click();
            // Đợi hoặc kiểm tra URL chuyển đến trang thi đấu
            String expectedUrl = "https://codelearn.io/fights";
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.equals(expectedUrl)) {
                System.out.println(" Pass: Chuyển đến trang 'Thi đấu' thành công.");
            } else {
                System.out.println(" Fail: Chuyển đến trang 'Thi đấu' thất bại. URL hiện tại: " + actualUrl);
            }
         // Click vào thẻ tiêu đề của cuộc thi
            WebElement cuocThi = driver.findElement(By.xpath("//h1[text()='ĐƯỜNG ĐUA LẬP TRÌNH 2024-2025 KHỐI THCS - VÒNG CHẠY ĐÀ']"));
            cuocThi.click();

            // Chờ trang tải (tuỳ tốc độ mạng, có thể thêm sleep hoặc wait tường minh nếu cần)
            Thread.sleep(2000);

            // Tìm span chứa nội dung cần verify
            WebElement spanElement = driver.findElement(By.xpath("//span[contains(text(),'ĐƯỜNG ĐUA LẬP TRÌNH 2024-2025 KHỐI THCS - VÒNG CHẠY ĐÀ')]"));

            // Kiểm tra nếu span hiển thị
            if (spanElement.isDisplayed()) {
                System.out.println(" Pass: Cuộc thi được mở thành công và tiêu đề hiển thị đúng.");
            } else {
                System.out.println(" Fail: Không tìm thấy tiêu đề trong trang chi tiết cuộc thi.");
            }
            
         // Click vào tab "Bảng xếp hạng"
            WebElement bangXepHangTab = driver.findElement(By.xpath("//span[text()='Bảng xếp hạng']"));
            bangXepHangTab.click();
         // Tìm và click vào tab "Bình luận"
            WebElement binhLuanTab = driver.findElement(By.xpath("//span[text()='Bình luận']"));
            binhLuanTab.click();
            // Click vào tab "Câu hỏi"
            WebElement cauHoiTab = driver.findElement(By.xpath("//span[text()='Câu hỏi']"));
            cauHoiTab.click();
            // Tìm dòng strong chứa nội dung cần xác minh
            WebElement questionText = driver.findElement(By.xpath("//strong[contains(text(),'1. Tôi có thể hiểu')]"));

            if (questionText.isDisplayed()) {
                System.out.println(" Pass: Câu hỏi hiển thị đúng.");
            } else {
                System.out.println(" Fail: Không tìm thấy câu hỏi sau khi click tab.");
            }
            
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
        	ExtentReportManager.flushReport();
        }
    }
}