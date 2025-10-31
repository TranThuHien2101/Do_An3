package Nguoidonggop;
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

@Listeners(Nguoidonggop.TestListener.class)
public class nguoidonggop extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("xem thông tin người đóng góp");

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
            
            // Click button người đóng góp 
         // Chờ và click nút "Người đóng góp"
            WebElement contributorButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/contributor') and contains(text(), 'Người đóng góp')]")));
            contributorButton.click();
            test.log(Status.PASS, "Click nút 'Người đóng góp' thành công");

            // Chờ điều hướng đến đúng URL
            wait.until(ExpectedConditions.urlContains("/contributor"));

            // Xác minh URL
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/contributor")) {
                test.log(Status.PASS, "Đã điều hướng đến trang 'Người đóng góp' thành công với URL: " + currentUrl);
            } else {
                test.log(Status.FAIL, "Không điều hướng được đến trang 'Người đóng góp', URL hiện tại: " + currentUrl);
            }
            
         // Chờ phần tử chứa tiêu đề "Danh sách người đóng góp" xuất hiện
            WebElement contributorTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'mantine-Text-root') and contains(text(),'Danh sách người đóng góp')]")));

            // Kiểm tra nội dung
            String titleText = contributorTitle.getText();
            if (titleText.contains("Danh sách người đóng góp")) {
                test.log(Status.PASS, "Tiêu đề 'Danh sách người đóng góp' hiển thị đúng.");
            } else {
                test.log(Status.FAIL, "Không tìm thấy tiêu đề 'Danh sách người đóng góp'. Nội dung: " + titleText);
            }
         // Click vào phần tử giao diện người dùng - avatar hoặc thẻ chứa profile
            WebElement profileCard = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/profile/3488')]")));
            profileCard.click();
            test.log(Status.PASS, "Click vào giao diện người dùng thành công");

            // Chờ điều hướng tới trang profile
            wait.until(ExpectedConditions.urlContains("/profile/3488"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/profile/3488"), "Không điều hướng tới trang hồ sơ đúng.");
            test.log(Status.PASS, "Điều hướng đến trang hồ sơ thành công");
            // Click xem tất cả khóa học của người dùng
            WebElement allCoursesTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Tất cả khóa học']/ancestor::button")));
            allCoursesTab.click();
            test.log(Status.PASS, "Click vào tab 'Tất cả khóa học' thành công");
         // Click tab "Đã đăng ký"
            WebElement registeredTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Đã đăng ký']/ancestor::button")));
            registeredTab.click();
            test.log(Status.PASS, "Click vào tab 'Đã đăng ký' thành công");
         // Click tab "Hoàn thành"
            WebElement completedTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Hoàn thành']/ancestor::button")));
            completedTab.click();
            test.log(Status.PASS, "Click vào tab 'Hoàn thành' thành công");

            
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
        	ExtentReportManager.flushReport();
        }
    }
}