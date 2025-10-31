package thidau;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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
public class Thidautimkiem extends BaseTest {
    ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        loginWithValidAccount();
    }

    public void vaoTrangThiDau() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement thiDauBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/fights' and text()='Thi đấu']")));
        thiDauBtn.click();

        try {
            wait.until(ExpectedConditions.urlContains("/fights"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/fights"), "Không điều hướng đến trang Thi đấu");
            test.log(Status.PASS, "Vào trang Thi đấu thành công");
        } catch (Exception e) {
            test.log(Status.FAIL, "Không vào được trang Thi đấu: " + e.getMessage());
            throw e;
        }
    }

    public void timKiem(String tuKhoa) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputTimKiem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Tên cuộc thi']")));
        inputTimKiem.clear();
        inputTimKiem.sendKeys(tuKhoa);
        inputTimKiem.sendKeys(Keys.ENTER);
    }

    @Test(priority = 1)
    public void timkiemtrenthanhcongcu() throws InterruptedException {
        test = extent.createTest("TC01 - Tìm kiếm đúng từ khóa");
        test.pass("Đăng nhập thành công");

        vaoTrangThiDau();

        timKiem("Đường đua lập trình");

        try {
            WebElement ketQua = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[contains(text(),'ĐƯỜNG ĐUA LẬP TRÌNH 2024-2025 KHỐI THCS - VÒNG CHẠ')]")));
            Assert.assertTrue(ketQua.isDisplayed(), "Không tìm thấy cuộc thi mong đợi");
            test.log(Status.PASS, "Tìm thấy cuộc thi thành công");
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "Không tìm thấy cuộc thi với tên 'Đường đua lập trình'");
            Assert.fail("Không tìm thấy cuộc thi với tên 'Đường đua lập trình'");
        }
    }

    @Test(priority = 2)
    public void timkiemtukhoasai() throws InterruptedException {
        test = extent.createTest("TC02 - Tìm kiếm với từ khóa không hợp lệ");
        test.pass("Đăng nhập thành công");

        // KHÔNG cần vào lại trang thi đấu
        timKiem("abcdefxyz123");

        try {
            WebElement ketQua = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='mantine-Text-root mantine-1p0vt5f']")));
            Assert.assertTrue(ketQua.isDisplayed(), "Không có cuộc thi nào");
            test.log(Status.PASS, "Không có cuộc thi nào");
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "Vẫn có kết quả dù từ khóa không hợp lệ");
            Assert.fail("Vẫn có kết quả dù từ khóa không hợp lệ");
        }
    }
    @Test(priority = 3)
    public void timkiemtukhoaHoathuonglanlon() throws InterruptedException {
        test = extent.createTest("TC03 - Tìm kiếm từ khóa viết hoa thường lẫn lộn ");
        test.pass("Đăng nhập thành công");
        timKiem("Đường đua lập trình");

        try {
            WebElement ketQua = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[contains(text(),'ĐƯỜNG ĐUA LẬP TRÌNH 2024-2025 KHỐI THCS - VÒNG CHẠ')]")));
            Assert.assertTrue(ketQua.isDisplayed(), "Không tìm thấy cuộc thi mong đợi");
            test.log(Status.PASS, "Tìm thấy cuộc thi thành công");
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "Không tìm thấy cuộc thi với tên 'Đường đua lập trình'");
            Assert.fail("Không tìm thấy cuộc thi với tên 'Đường đua lập trình'");
        }
    } 

}
