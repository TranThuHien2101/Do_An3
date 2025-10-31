package Chattinnhan;
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
import utils.ScreenshotHelper;

@Listeners(Chattinnhan.TestListener.class)
public class nhantin extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("tin nhắn");

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

            // Click vào icon chat tin nhắn
            WebElement messengerIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//img[contains(@src, '/images/chat.png')]")));
            messengerIcon.click();
            test.log(Status.PASS, "Click vào icon tin nhắn thành công");

         // Tìm phần tử ảnh nhắn tin và click vào
            WebElement chatBackground = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[contains(@src, '/images/chat/bg-support-vi.png')]")
            ));

            chatBackground.click();
            test.log(Status.PASS, "Click vào hình nền nhắn tin CodeLearn thành công");
            
            try {
                // Tìm và click vào textarea
                WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//textarea[@id='txt-smg-965510_25002102']")));
                textarea.click();
                test.log(Status.PASS, "Click vào ô nhập tin nhắn thành công");
                // Gõ nội dung vào textarea
                textarea.sendKeys("Tôi muốn học code");
                test.log(Status.PASS, "Nhập nội dung 'Tôi muốn học code' thành công");
            } catch (Exception e) {
                test.log(Status.FAIL, "Không thể nhập tin nhắn: " + e.getMessage());
                throw e;
            }
            try {
                // Chờ đến khi nút Gửi sẵn sàng để click
                WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@title='Gửi']//span[@class='mantine-1ryt1ht mantine-Button-label']//*[name()='svg']")));
                
                // Click vào nút Gửi
                sendButton.click();
                test.log(Status.PASS, "Click nút Gửi tin nhắn thành công");
            } catch (Exception e) {
                test.log(Status.FAIL, "Không click được nút Gửi: " + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            test.log(Status.FAIL, " Test thất bại: " + e.getMessage());
            ScreenshotHelper.takeScreenshot(driver, "nhantin"); 
            Assert.fail(e.getMessage());
        } finally {
            ExtentReportManager.flushReport();
        }
    }
}
