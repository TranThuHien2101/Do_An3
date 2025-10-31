package utils; // hoặc để package trống nếu không có

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotHelper {
    public static String takeScreenshot(WebDriver driver, String methodName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = "Screenshots/" + methodName + "_" + timestamp + ".png";
            File destFile = new File(path);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
            return path; //  trả về đường dẫn
        } catch (IOException e) {
            System.out.println("Không thể chụp màn hình: " + e.getMessage());
            return null;
        }
    }
}
