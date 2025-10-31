package Xephang;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.ScreenshotHelper;

public class TestListener implements ITestListener {
    public static Object test;

	@Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        String methodName = result.getMethod().getMethodName();

        if (driver != null) {
            String screenshotPath = ScreenshotHelper.takeScreenshot(driver, methodName);

            // Lấy ExtentTest từ test context nếu có
            Object testAttr = result.getAttribute("extentTest");
            if (testAttr instanceof ExtentTest) {
                ExtentTest test = (ExtentTest) testAttr;
                if (screenshotPath != null) {
                    test.fail("Đính kèm ảnh lỗi:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
            }
        }
    }
}