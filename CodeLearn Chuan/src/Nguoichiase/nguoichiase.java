package Nguoichiase;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

@Listeners(Nguoichiase.TestListener.class)
public class nguoichiase extends BaseTest{
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("xem thông tin chia sẻ");

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
           
         // Click vào nút "Chia sẻ"
            WebElement chiaSeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/sharing') and contains(text(),'Chia sẻ')]")));
            chiaSeButton.click();
            test.log(Status.PASS, "Click vào nút 'Chia sẻ' thành công");
           

            // Chờ trang chia sẻ load và xác minh URL chứa 'sharing'
            wait.until(ExpectedConditions.urlContains("/sharing"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/sharing"), "Không điều hướng đến trang 'Chia sẻ'");
            test.log(Status.PASS, "Điều hướng đến trang 'Chia sẻ' thành công");
          
            //Click nút tìm kiếm
            try {
                // Tìm ô tìm kiếm và click vào
                WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, 'Tìm kiếm tiêu đề')]")));
                searchInput.click();
                searchInput.sendKeys("Thuật toán");
                searchInput.sendKeys(Keys.ENTER);
                test.log(Status.PASS, "Đã nhập từ khóa 'Thuật toán' và nhấn Enter");

                // Chờ kết quả hiển thị
                Thread.sleep(2000); // Hoặc dùng wait nếu có element kết quả

                // Kiểm tra kết quả có chứa từ "Thuật" hoặc "Thuật toán"D
                String pageSource = driver.getPageSource().toLowerCase();
                if (pageSource.contains("thuật")) {
                    test.log(Status.PASS, "Kết quả tìm kiếm hiển thị nội dung liên quan đến 'Thuật toán'");
                } else {
                    test.log(Status.FAIL, "Không tìm thấy nội dung liên quan đến 'Thuật toán' sau khi tìm kiếm");
                    Assert.fail("Không có kết quả phù hợp với từ khóa 'Thuật toán'");
                }

            } catch (Exception e) {
                test.log(Status.FAIL, "Lỗi khi thao tác tìm kiếm: " + e.getMessage());
                Assert.fail("Exception xảy ra trong tìm kiếm: " + e.getMessage());
            }

            try {
                // Click vào tiêu đề bài viết Thuật toán
                WebElement baiViet = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='5 Thuật Toán Tìm Kiếm Mọi LTV Nên Biết']")));
                baiViet.click();
                test.log(Status.PASS, "Đã click vào bài viết '5 Thuật Toán Tìm Kiếm Mọi LTV Nên Biết'");

                // Đợi trang load và kiểm tra tiêu đề chính trong trang bài viết
                WebElement tieuDeChiTiet = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[text()='5 Thuật Toán Tìm Kiếm Mọi LTV Nên Biết']")));
                
                Assert.assertTrue(tieuDeChiTiet.isDisplayed(), "Tiêu đề bài viết không hiển thị đúng");
                test.log(Status.PASS, "Đã vào đúng trang bài viết với tiêu đề hiển thị chuẩn xác");

            } catch (Exception e) {
                test.log(Status.FAIL, "Không thể vào đúng trang bài viết hoặc tiêu đề không đúng: " + e.getMessage());
                Assert.fail("FAIL: Không tìm thấy tiêu đề bài viết đúng hoặc không load được trang");
            }

            
        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
        	ExtentReportManager.flushReport();
        }
    }
}