package Sukien;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import QuanlytaikhoanTestNG.BaseTest;
import utils.ExtentReportManager;
import utils.ScreenshotHelper;

@Listeners(Luyentap.TestListener.class)
public class xemsukien extends BaseTest {

    ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @BeforeMethod
    public void setupWait(ITestContext context) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testthongtin(ITestContext context) throws InterruptedException {
        test = extent.createTest("Xem thông tin sự kiện");
        context.setAttribute("extentTest", test); // Gửi test object cho TestListener
        context.setAttribute("driver", driver);    // Gửi driver cho TestListener

        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");

            // Click logo trang chủ
            WebElement homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
            homeLogo.click();
            test.log(Status.PASS, "Click logo trang chủ thành công");

            wait.until(ExpectedConditions.urlContains("codelearn.io"));
            Assert.assertTrue(driver.getCurrentUrl().contains("codelearn.io"), "Chưa vào trang Home");
            test.log(Status.PASS, "Đã vào trang Home với URL đúng");

            // Click "Sự kiện"
            WebElement suKienBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sự kiện')]")));
            suKienBtn.click();

            wait.until(ExpectedConditions.urlContains("/event"));

            // Kiểm tra banner sự kiện
            WebElement bannerImg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[@src='/images/event/event-page-banner.jpg' and contains(@class, 'w-full')]")));

            if (bannerImg.isDisplayed()) {
                test.log(Status.PASS, "Banner trang sự kiện hiển thị thành công.");
            } else {
                test.log(Status.FAIL, "Banner không hiển thị.");
                Assert.fail("Banner không hiển thị.");
            }

            // Click vào sự kiện cụ thể
            WebElement eventTitle = driver.findElement(By.xpath("//div[contains(text(),'Codewar Junior - Warm-Up Round For High School Students')]"));
            eventTitle.click();

            // Kiểm tra hình ảnh sự kiện
            WebElement image = driver.findElement(By.xpath("//img[contains(@src, '78fcce273ada44589ad026d473db1c4b.jpg')]"));
            if (image.isDisplayed()) {
                test.log(Status.PASS, "Hình ảnh sự kiện hiển thị đúng.");
            } else {
                test.log(Status.FAIL, "Hình ảnh sự kiện không hiển thị.");
                Assert.fail("Không tìm thấy hình ảnh.");
            }

            // Click vào tab "Bảng xếp hạng"
            WebElement bangXepHangTab = driver.findElement(By.xpath("//span[contains(text(),'Bảng xếp hạng')]"));
            bangXepHangTab.click();

            // Click vào tab "Bình luận"
            WebElement binhLuanTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Bình luận')]")));
            binhLuanTab.click();

            // Chuyển vào iframe TinyMCE
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                    By.cssSelector("iframe[id^='tiny-react']")));

            // Kiểm tra vùng nhập bình luận
            WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("body[aria-placeholder='Viết bình luận của bạn']")));

            if (textArea.isDisplayed()) {
                test.log(Status.PASS, "Hiển thị vùng nhập bình luận.");
            } else {
                test.log(Status.FAIL, "Không hiển thị vùng nhập bình luận.");
                Assert.fail("Không tìm thấy vùng nhập bình luận.");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Đã xảy ra lỗi: " + e.getMessage());
            String screenshotPath = ScreenshotHelper.takeScreenshot(driver, "testthongtin");
            test.addScreenCaptureFromPath(screenshotPath);
            Assert.fail("Test thất bại do lỗi: " + e.getMessage());
        }
    }
}
