package tests;

import Base.BaseTest;
import dataproviders.RegisterDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import resultreport.RegisterDataResult;

public class DangKyTest extends BaseTest {

    @Test(dataProvider = "RegisterData", dataProviderClass = RegisterDataProvider.class)
    public void testDangKy(String username, String email, String password, String agree, String expectedResult) throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);

        // Mở form đăng ký
        registerPage.openRegisterForm();

        // Nhập dữ liệu
        Thread.sleep(1000);
        registerPage.enterUsername(username);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.setAgreeCheckbox(agree);

        // Click đăng ký
        if (agree.equalsIgnoreCase("yes") || agree.equalsIgnoreCase("true")) {
            registerPage.setAgreeCheckbox(agree);
        }

        registerPage.clickRegister();

        // Lấy kết quả
        String actualResult;
        String status;

        String successMsg = registerPage.getSuccessMessage();
        if (successMsg != null && !successMsg.isEmpty()) {
            actualResult = successMsg.trim();
            status = expectedResult.toLowerCase().contains("thành công") ? "PASS" : "FAIL";
        } else {
            String errorMsg = registerPage.getErrorMessage();
            if (errorMsg != null && !errorMsg.isEmpty()) {
                actualResult = errorMsg.trim();
            } else {
                actualResult = "Không xác định được lỗi";
            }
            status = expectedResult.toLowerCase().contains("thành công") ? "FAIL" : "PASS";
        }

        // Ghi file Excel
        RegisterDataResult.writeResult(username, email, password, agree, expectedResult, actualResult, status);
        System.out.println("Test case: " + username + " | " + email + " | " + password + " | " + agree);
        System.out.println("Expected: " + expectedResult + " | Actual: " + actualResult + " | Status: " + status);

        Assert.assertEquals(status, "PASS", "Kết quả không khớp với mong đợi");
    }
}
