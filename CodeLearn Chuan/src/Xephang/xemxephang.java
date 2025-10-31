package Xephang;
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
@Listeners(Xephang.TestListener.class)
public class xemxephang extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("xem thông tin xếp hàng");

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
            // Click Xếp hạng
            WebElement rankingButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/leaderboard' and contains(text(),'Xếp hạng')]")));
            rankingButton.click();
            test.log(Status.PASS, "Click vào nút 'Xếp hạng' thành công");

            // Verify đã vào đúng trang Xếp hạng
            wait.until(ExpectedConditions.urlContains("/leaderboard"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/leaderboard"), "Chưa vào được trang Xếp hạng");
            test.log(Status.PASS, "Đã vào đúng trang 'Xếp hạng'");
            // Click vào toàn bộ 
         // Click vào tab "Toàn bộ"
            WebElement tabToanBo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), 'Toàn bộ')]")));
            tabToanBo.click();
            test.log(Status.PASS, "Click tab 'Toàn bộ' thành công");

            // Đợi nội dung tab hiển thị và xác minh
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Toàn bộ') or contains(text(),'toàn bộ')]")));

            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("toàn bộ"), "Không thấy nội dung của tab 'Toàn bộ'");
            test.log(Status.PASS, "Nội dung tab 'Toàn bộ' hiển thị đúng");
         // Click vào tab "Ngày"
            WebElement tabNgay = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Ngày')]/ancestor::button")));
            tabNgay.click();
            test.log(Status.PASS, "Click tab 'Ngày' thành công");
            // click  Tuần 
            WebElement tabTuan = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Tuần')]/ancestor::button")));
            tabTuan.click();
            test.log(Status.PASS, "Click tab 'Tuần' thành công");
         // Click vào tab "Tháng"
            WebElement tabThang = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Tháng')]/ancestor::button")));
            tabThang.click();
            test.log(Status.PASS, "Click tab 'Tháng' thành công");
         // Click vào tab "Năm"
            WebElement tabNam = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Năm')]/ancestor::button")));
            tabNam.click();
            test.log(Status.PASS, "Click tab 'Năm' thành công");

            
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
        	ExtentReportManager.flushReport();
        }
    }
}