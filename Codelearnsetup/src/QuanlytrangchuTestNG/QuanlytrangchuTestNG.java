package QuanlytrangchuTestNG;

import QuanlytaikhoanTestNG.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class QuanlytrangchuTestNG extends BaseTest {

  

	@Test
    public void testthongtin() throws InterruptedException {
        // Đăng nhập
        loginWithValidAccount();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Trang Home - click logo
        WebElement homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
        homeLogo.click();

        // Chờ URL chứa "codelearn.io" để xác nhận về trang Home
        wait.until(ExpectedConditions.urlContains("codelearn.io"));
        Assert.assertTrue(driver.getCurrentUrl().contains("codelearn.io"), "Chưa vào trang Home");

        // Click vào khóa học (click vào thẻ <a> chứa khóa học)
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'thu-vien-chuan-cpp')]")));

        // Dùng JS click để tránh lỗi không click được
        js.executeScript("arguments[0].click();", courseLink);

        // Chờ URL thay đổi sang trang khóa học
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("thu-vien-chuan-cpp"),
                ExpectedConditions.urlContains("learning")
        ));

        String currentCourseUrl = driver.getCurrentUrl();
        System.out.println("Course URL: " + currentCourseUrl);
        Assert.assertTrue(currentCourseUrl.contains("thu-vien-chuan-cpp") || currentCourseUrl.contains("learning"), "Chưa vào trang khóa học");

        // Giới thiệu
        WebElement tabGioiThieu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Giới thiệu']")));
        tabGioiThieu.click();

        // Chờ nội dung "giới thiệu" hiển thị
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'GIỚI THIỆU','giới thiệu'),'giới thiệu')]")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("giới thiệu"), "Không thấy nội dung Giới thiệu");

        // Giáo trình
        WebElement tabGiaoTrinh = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Giáo trình']")));
        tabGiaoTrinh.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'GIÁO TRÌNH','giáo trình'),'giáo trình')]")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("giáo trình"), "Không thấy nội dung Giáo trình");

        // Đánh giá
        WebElement tabDanhGia = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Đánh giá']")));
        tabDanhGia.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(text(),'ĐÁNH GIÁ','đánh giá'),'đánh giá')]")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("đánh giá"), "Không thấy nội dung Đánh giá");

        // Chứng chỉ
        WebElement tabChungChi = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Chứng chỉ']")));
        tabChungChi.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Chứng chỉ')]")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("chứng chỉ"), "Không thấy nội dung Chứng chỉ");

        // Bình luận
        WebElement commentButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), 'Bình luận')]")));
        commentButton.click();
     
        // Về trang home
        js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");

        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//img[@alt='logo' and contains(@src, 'codelearn-logo.png')]")));
        	JavascriptExecutor js1 = (JavascriptExecutor) driver;
        	js1.executeScript("arguments[0].click();", logo);
        	wait.until(ExpectedConditions.urlContains("/home"));

        // Cuộc thi
        WebElement cuocThi = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='t7']//a[1]//img[1]")));
        cuocThi.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("challenge"),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Cuộc thi')]"))));
        Assert.assertTrue(driver.getCurrentUrl().contains("challenge") || driver.getPageSource().contains("Cuộc thi"), "Không vào được trang cuộc thi");

        // Về lại Home
        homeLogo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img")));
        homeLogo.click();
        wait.until(ExpectedConditions.urlContains("home"));

        // Bài viết
        WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(), 'Rắn Săn Mồi')]")));

        WebElement clickableParent = titleElement.findElement(By.xpath("./ancestor::a | ./ancestor::div[@role='link']"));
        js1.executeScript("arguments[0].click();", clickableParent);

        // Chờ URL hoặc page load
        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ran-san-moi") || currentUrl.contains("snake") || currentUrl.contains("cpp"),
                "Không điều hướng đúng trang bài viết!");

        // Về lại trang chủ
        WebElement homeLogo1 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/home')]//img")));
        homeLogo1.click();
        wait.until(ExpectedConditions.urlContains("home"));

        // Click vào 'Luyện tập hàng ngày'
        WebElement exerciseTitle = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='maxIncreaseSubArray']")));

        WebElement trainingLink = exerciseTitle.findElement(By.xpath("./ancestor::a"));

        js1.executeScript("arguments[0].click();", trainingLink);

    }
}