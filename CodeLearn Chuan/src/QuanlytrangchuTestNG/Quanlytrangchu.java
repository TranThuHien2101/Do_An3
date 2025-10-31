package QuanlytrangchuTestNG;

import QuanlytaikhoanTestNG.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import com.aventstack.extentreports.Status;
import java.time.Duration;

@Listeners(QuanlytrangchuTestNG.TestListener.class)
public class Quanlytrangchu extends BaseTest {

    ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;

    @Test
    public void testthongtin() throws InterruptedException {
        test = extent.createTest("Test Quản Lý Trang Chủ");

        try {
            loginWithValidAccount();
            test.log(Status.PASS, "Đăng nhập thành công");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Click logo trang chủ
            WebElement homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
            homeLogo.click();
            test.log(Status.PASS, "Click logo trang chủ thành công");

            wait.until(ExpectedConditions.urlContains("codelearn.io"));
            Assert.assertTrue(driver.getCurrentUrl().contains("codelearn.io"), "Chưa vào trang Home");
            test.log(Status.PASS, "Đã vào trang Home với URL đúng");

            // Click khóa học
            WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, 'thu-vien-chuan-cpp')]")));
            js.executeScript("arguments[0].click();", courseLink);
            test.log(Status.PASS, "Click vào khóa học thành công");

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("thu-vien-chuan-cpp"),
                    ExpectedConditions.urlContains("learning")
            ));
            String currentCourseUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentCourseUrl.contains("thu-vien-chuan-cpp") || currentCourseUrl.contains("learning"), "Chưa vào trang khóa học");
            test.log(Status.PASS, "Điều hướng đến trang khóa học thành công");

            // Tab Giới thiệu
            WebElement tabGioiThieu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Giới thiệu']")));
            tabGioiThieu.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'GIỚI THIỆU','giới thiệu'),'giới thiệu')]")));
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("giới thiệu"), "Không thấy nội dung Giới thiệu");
            test.log(Status.PASS, "Tab Giới thiệu hiển thị đúng");

            // Tab Giáo trình
            WebElement tabGiaoTrinh = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Giáo trình']")));
            tabGiaoTrinh.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'GIÁO TRÌNH','giáo trình'),'giáo trình')]")));
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("giáo trình"), "Không thấy nội dung Giáo trình");
            test.log(Status.PASS, "Tab Giáo trình hiển thị đúng");

            // Tab Đánh giá
            WebElement tabDanhGia = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Đánh giá']")));
            tabDanhGia.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'ĐÁNH GIÁ','đánh giá'),'đánh giá')]")));
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("đánh giá"), "Không thấy nội dung Đánh giá");
            test.log(Status.PASS, "Tab Đánh giá hiển thị đúng");

            // Tab Chứng chỉ
            WebElement tabChungChi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Chứng chỉ']")));
            tabChungChi.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(),'Chứng chỉ')]")));
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("chứng chỉ"), "Không thấy nội dung Chứng chỉ");
            test.log(Status.PASS, "Tab Chứng chỉ hiển thị đúng");

            // Tab Bình luận
            WebElement commentButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(), 'Bình luận')]")));
            commentButton.click();
            test.log(Status.PASS, "Click vào tab Bình luận thành công");

            // Về trang home
            js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");

            WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//img[@alt='logo' and contains(@src, 'codelearn-logo.png')]")));
            js.executeScript("arguments[0].click();", logo);
            wait.until(ExpectedConditions.urlContains("/home"));
            test.log(Status.PASS, "Về trang Home thành công");

            // Cuộc thi
            WebElement cuocThi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='t7']//a[1]//img[1]")));
            cuocThi.click();
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("challenge"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Cuộc thi')]"))));
            Assert.assertTrue(driver.getCurrentUrl().contains("challenge") || driver.getPageSource().contains("Cuộc thi"), "Không vào được trang cuộc thi");
            test.log(Status.PASS, "Truy cập trang Cuộc thi thành công");

            // Về lại Home
            homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
            homeLogo.click();
            wait.until(ExpectedConditions.urlContains("home"));
            test.log(Status.PASS, "Về lại trang Home thành công");

            // Bài viết
            WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), 'Rắn Săn Mồi')]")));

            WebElement clickableParent = titleElement.findElement(By.xpath("./ancestor::a | ./ancestor::div[@role='link']"));
            js.executeScript("arguments[0].click();", clickableParent);

            Thread.sleep(2000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("ran-san-moi") || currentUrl.contains("snake") || currentUrl.contains("cpp"),
                    "Không điều hướng đúng trang bài viết!");
            test.log(Status.PASS, "Điều hướng tới trang bài viết thành công");

            // Về lại trang chủ
            WebElement homeLogo1 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/home')]//img")));
            homeLogo1.click();
            wait.until(ExpectedConditions.urlContains("home"));
            test.log(Status.PASS, "Về lại trang Home thành công");

            // Click vào 'Luyện tập hàng ngày'
            WebElement exerciseTitle = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='maxIncreaseSubArray']")));

            WebElement trainingLink = exerciseTitle.findElement(By.xpath("./ancestor::a"));

            js.executeScript("arguments[0].click();", trainingLink);
            test.log(Status.PASS, "Click vào 'Luyện tập hàng ngày' thành công");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test thất bại với lỗi: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportManager.flushReport();
        }
    }
}