package tests;

import Base.BaseTest;
import dataproviders.LoginDataProvider;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.testng.Assert;
import resultreport.LoginTestResultWriter;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void testLogin(String email, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getEmailInputLocator()));
        } catch (Exception e) {
            Assert.fail("Không hiển thị được form đăng nhập.");
        }

        loginPage.clearFields();

        String actualResult = "";
        String status = "";

        if (email.isEmpty() || password.isEmpty()) {
            actualResult = "Tài khoản không được bỏ trống";
            System.out.println("Thiếu thông tin với: " + email);
        } else {
            loginPage.login(email, password);

            try {
                Thread.sleep(3000); // Đợi xử lý đăng nhập
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // Kiểm tra thông báo lỗi (nếu có)
                WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement alertElement = alertWait.until(
                    ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginAlertLocator())
                );
                String alertText = alertElement.getText();

                if (alertText.contains("Tên người dùng/email/mật khẩu đã cung cấp không chính xác.") ||
                    alertText.contains("Tên người dùng/email/mật khẩu đã cung cấp không chính xác.")) {
                    actualResult = "Tên người dùng/email/mật khẩu đã cung cấp không chính xác.";
                    System.out.println("Sai thông tin với: " + email);
                } else {
                    actualResult = "Không xác định";
                }
            } catch (Exception e) {
                // Nếu không có alert, kiểm tra trang có dấu hiệu đăng nhập thành công
                try {
                    boolean isLoggedIn = driver.getPageSource().contains("Bỏ qua") ||
                                         driver.getCurrentUrl().contains("codelearn.io");
                    if (isLoggedIn) {
                        actualResult = "Tài khoản";
                        System.out.println("Đăng nhập thành công với: " + email);
                    } else {
                        actualResult = "Không xác định";
                    }
                } catch (Exception ex) {
                    actualResult = "Không xác định";
                }
            }
        }

        status = actualResult.trim().equalsIgnoreCase(expectedResult.trim()) ? "PASSED" : "FAILED";
        LoginTestResultWriter.writeResult(email, password, expectedResult, actualResult, status);
        Assert.assertEquals(actualResult.trim(), expectedResult.trim(), "Kết quả thực tế không khớp với mong đợi.");
    }
}
