package Quanlygiohang;
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
@Listeners(Quanlygiohang.TestListener.class)
public class giohang extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("xem thông tin giỏ hàng");

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
            
            // Click giỏ hàng
            try {
                // Click trực tiếp vào phần tử SVG hoặc phần tử cha chứa SVG
                WebElement gioHangIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/cart']")));
                gioHangIcon.click();

                test.log(Status.PASS, "Đã click vào icon giỏ hàng thành công");

            } catch (Exception e) {
                test.log(Status.FAIL, "Không thể click vào icon giỏ hàng: " + e.getMessage());
                Assert.fail("Không tìm thấy hoặc không thể tương tác với icon giỏ hàng");
            }
           
            
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
        	ExtentReportManager.flushReport();
        }
    }
}